package cc.controlReciclado;

import es.upm.babel.cclib.Monitor;

import java.util.ArrayList;
import java.util.List;


public final class ControlRecicladoMonitor implements ControlReciclado {
	private enum Estado { LISTO, SUSTITUIBLE, SUSTITUYENDO }
	//El peso maximo para el contenedor
	private final int MAX_P_CONTENEDOR;
	//El peso maximo para la grua
	private final int MAX_P_GRUA;

	// Declaramos los atributos de la clase	
	// Guarda el estado del contenedor
	private Estado estado;
	// Guarda el peso del contenedor
	private int peso;
	// Guarda el numero de gruas que acceden al contenedor
	private int accediendo;

	// Declaramos un monitor 
	Monitor mutexCR;
	//Declaramos las listas para gestionar las esperas
	List<NotificarPeso> list_np;
	List<IncrementarPeso> list_ip;
	List<PrepararSustitucion> list_ps;

	public ControlRecicladoMonitor (int max_p_contenedor, int max_p_grua) {
		MAX_P_CONTENEDOR = max_p_contenedor;
		MAX_P_GRUA = max_p_grua;
		// Inicializamos atributos de la clase
		estado = Estado.LISTO;
		peso = 0;
		accediendo = 0;
		// Inicializamos el monitor
		mutexCR = new Monitor();
		//Inicializamos las condiciones
		list_np = new ArrayList<>();
		list_ip = new ArrayList<>();
		list_ps = new ArrayList<>();
	}

	public void notificarPeso(int p) {
		// Seccion critica
		mutexCR.enter();
		// Excepcion si el peso no es el adecuado
		if(p <= 0 || p > MAX_P_GRUA) {
			mutexCR.leave();
			throw new IllegalArgumentException("El peso de la grua tiene que ser mayor que cero y menor que el maximo");
		}

		// Se comprueba la CPRE
		if(estado == Estado.SUSTITUYENDO) {
			NotificarPeso np = new  NotificarPeso();
			list_np.add(np);
			// Si no es, se bloquea
			np.condNP.await();
		}

		//Si el peso actual del contenedor mas el nuevo peso introducido es mayor que el peso maximo del contenedor, el estado pasa a ser sustituible 
		if(peso + p > MAX_P_CONTENEDOR)
			estado = Estado.SUSTITUIBLE;
		//Si no, el estado pasa a ser listo 
		else
			estado = Estado.LISTO;

		// Desbloqueamos y nos salimos
		desbloqueoSimple();
		mutexCR.leave();
	}

	public void incrementarPeso(int p) {
		// Seccion critica
		mutexCR.enter();
		// El peso tiene que ser el adecuado
		if(p <= 0 || p > MAX_P_GRUA) {
			mutexCR.leave();
			throw new IllegalArgumentException("El peso de la grua tiene que ser mayor que cero y menor que el maximo");
		}

		// Si el estado es sustituyendo o el nuevo peso es mayor que el maximo, se bloquea
		if(estado == Estado.SUSTITUYENDO || peso + p >  MAX_P_CONTENEDOR ) {
			IncrementarPeso np = new  IncrementarPeso(p);
			list_ip.add(np);
			// Bloqueo
			np.condIP.await();
		}

		// Incrementar peso (POST)
		peso = peso + p;
		accediendo++;
		// Desbloqueamos y nos salimos
		desbloqueoSimple();
		mutexCR.leave();
	}

	public void notificarSoltar() {
		// Seccion critica
		mutexCR.enter();
		// Uno menos accediendo a la grua (POST)
		accediendo--;
		// Desbloqueamos y nos salimos
		desbloqueoSimple();
		mutexCR.leave();
	}

	public void prepararSustitucion() {
		// Seccion critica
		mutexCR.enter();
		// Comprobamos que se cumple la CPRE
		if(accediendo != 0 || estado != Estado.SUSTITUIBLE) {
			PrepararSustitucion ps = new PrepararSustitucion();
			list_ps.add(ps);
			ps.condPS.await();
		}

		// Estado cambia a sustituyendo (POST)
		estado = Estado.SUSTITUYENDO;
		// Desbloqueamos y nos salimos
		mutexCR.leave();
		desbloqueoSimple();
	}

	public void notificarSustitucion() {
		// Seccion critica
		mutexCR.enter();
		// El estado pasa a estar listo, y se reinician valores de peso y accediendo
		estado = Estado.LISTO;
		peso = 0;
		accediendo = 0;
		// Desbloqueamos y nos salimos
		desbloqueoSimple();
		mutexCR.leave();
	}

	private void desbloqueoSimple() {
		boolean signaled = false;
		// Desbloquea una grua si se cumple la CPRE de notificarPeso y el estado no es sustituyendo
		if(list_np.size() > 0 && estado != Estado.SUSTITUYENDO) {
			list_np.get(0).condNP.signal();
			list_np.remove(0);
			signaled = true;
		}
		
		// Desbloquea una grua si no esta ya desbloqueada, se cumple la CPRE de incrementarPeso y el estado es sustituible
		if(!signaled && estado == Estado.SUSTITUIBLE && accediendo == 0 && list_ps.size() > 0) {
			list_ps.get(0).condPS.signal();
			list_ps.remove(0);
			signaled = true;
		}
		
		// Desbloquea una grua si no esta ya desbloqueada, se cumple la CPRE de incrementarPeso y el estado es listo
				if(!signaled && list_ip.size() > 0 && estado == Estado.LISTO) {
					for(int i=0; i<list_ip.size() && !signaled; i++) {
						if(peso + list_ip.get(i).pesoAux <= MAX_P_CONTENEDOR) {
							list_ip.get(i).condIP.signal();
							list_ip.remove(i);
							signaled = true;
						}
					}
				}

	}

	// Creamos una clase para la gestion de las esperas de notificar peso y su condition
	private class NotificarPeso {
		// Condicion
		private Monitor.Cond condNP;

		public NotificarPeso() {
			condNP = mutexCR.newCond();
		}

	}

	
	// Creamos una clase para la gestion de las esperas de incrementarPeso
	private class IncrementarPeso {
		// El peso y su condicion
		private int pesoAux;
		private Monitor.Cond condIP;

		public IncrementarPeso(int peso) {
			pesoAux = peso;
			condIP = mutexCR.newCond();
		}
	}

	// Creamos una clase para la gestion de las esperas de prepararSustitucion 
	private class PrepararSustitucion {
		// Condicion
		private Monitor.Cond condPS;

		public PrepararSustitucion() {
			condPS = mutexCR.newCond();
		}
	}
}
