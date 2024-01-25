package cc.controlReciclado;
import java.util.ArrayList;
import java.util.List;

import es.upm.babel.cclib.Monitor;

public final class ControlRecicladoMonitor implements ControlReciclado {
	private enum Estado { LISTO, SUSTITUIBLE, SUSTITUYENDO }

	private final int MAX_P_CONTENEDOR;
	private final int MAX_P_GRUA;

	// Incializar atributos
	private int peso;
	private Estado estado;
	private int accediendo;
	
	//Aplicar monitor
	Monitor mutex;
	
	// Listas para la gestion de esperas de notificarPeso, incrementarPeso o prepararSustitucion
	List<NotificarPeso> listNotificarPeso;
	List<IncrementarPeso> listIncrementarPeso;
	List<PrepararSustitucion> listPrepararSustitucion;
	
	

	public ControlRecicladoMonitor (int max_p_contenedor, int max_p_grua) {
		MAX_P_CONTENEDOR = max_p_contenedor;
		MAX_P_GRUA = max_p_grua;

		// Inicializamos los atributos 
		peso = 0;
		estado = Estado.LISTO;
		accediendo = 0;

		/// Monitores y condiciones
		// Un monitor para gestionar las condiciones y saber si se cumple la CPRE de estos metodos
		mutex = new Monitor();
		listNotificarPeso = new ArrayList<>();
		listIncrementarPeso = new ArrayList<>();
		listPrepararSustitucion = new ArrayList<>();
		
	}

	public void notificarPeso(int p) {
		mutex.enter();

		// Se comprueba PRE
		if(p > MAX_P_GRUA || p <= 0 ) {
			mutex.leave();
			//De lo contrario,lanzamos la excepcion IllegalArgumentException
			throw new IllegalArgumentException("El peso debe estar en este intervalo [0, MAX_GRUA]");
		}

		// Comprobar CPRE y de lo contrario se tiene que desbloquear
		if(estado == Estado.SUSTITUYENDO) {
			NotificarPeso np = new  NotificarPeso();
			listNotificarPeso.add(np);
			np.condNP.await();
		}

		// Comprobar POST: Cuando supera el peso maximo pasa a ser sustituible
		if(MAX_P_CONTENEDOR < peso + p)
			estado = Estado.SUSTITUIBLE;  // sustituible, se cambia el contenedor
		else
			estado = Estado.LISTO;  // listo, admite mas carga

		// Desbloquear la condicion y liberar la Exclusion mutua
		desbloqueoFuncion();
		mutex.leave();
	}

	public void incrementarPeso(int p) {
		mutex.enter();

		// Se comprueba PRE
		if(p > MAX_P_GRUA || p <= 0 ) {
			mutex.leave();
			//De lo contrario,lanzamos la excepcion IllegalArgumentException
			throw new IllegalArgumentException("El peso debe estar en este intervalo [0, MAX_GRUA]");
		}

		// Comprobar CPRE y de lo contrario se tiene que desbloquear
		if(peso + p >  MAX_P_CONTENEDOR || estado == Estado.SUSTITUYENDO) {
			IncrementarPeso np = new  IncrementarPeso(p);
			listIncrementarPeso.add(np);
			np.condIP.await();
		}

		// POST
		peso += p;
		accediendo++;

		// Desbloquear la condicion y liberar la Exclusion mutua
		desbloqueoFuncion();
		mutex.leave();
	}

	public void notificarSoltar() {
		mutex.enter();

		// POST
		accediendo--;

		// Desbloquear la condicion y liberar la Exclusion mutua
		desbloqueoFuncion();
		mutex.leave();
	}

	public void prepararSustitucion() {
		mutex.enter();

		// Se comprueba CPRE y de lo contrario se tiene que desbloquear
		if(estado != Estado.SUSTITUIBLE || accediendo != 0) {
			PrepararSustitucion ps = new PrepararSustitucion();
			listPrepararSustitucion.add(ps);
			ps.condPS.await();
		}

		// POST
		estado = Estado.SUSTITUYENDO;

		// Desbloquear la condicion y liberar la Exclusion mutua
		desbloqueoFuncion();
		mutex.leave();
	}

	public void notificarSustitucion() {
		mutex.enter();

		// POST
		peso = 0;
		estado = Estado.LISTO;
		accediendo = 0;

		// Desbloquear la condicion y liberar la Exclusion mutua
		desbloqueoFuncion();
		mutex.leave();
	}

	private void desbloqueoFuncion() {
		boolean desbloqueado = false;
		
		if(listNotificarPeso.size() > 0 && estado != Estado.SUSTITUYENDO) {
			listNotificarPeso.get(0).condNP.signal();
			listNotificarPeso.remove(0);
			desbloqueado = true;
		}
		
		if(!desbloqueado && listIncrementarPeso.size() > 0 && estado == Estado.LISTO) {
			for(int i=0; i<listIncrementarPeso.size() && !desbloqueado; i++) {
				if(peso + listIncrementarPeso.get(i).aux <= MAX_P_CONTENEDOR) {
					listIncrementarPeso.get(i).condIP.signal();
					listIncrementarPeso.remove(i);
					desbloqueado = true;
				}
			}
		}
		
		if(!desbloqueado && estado == Estado.SUSTITUIBLE && accediendo == 0 && listPrepararSustitucion.size() > 0) {
			listPrepararSustitucion.get(0).condPS.signal();
			listPrepararSustitucion.remove(0);
		}

	}

	 	//Gestionar las esperas de NotificarPeso, IncrementarPeso y PrepararSustitucion y sus condiciones
	 
	private class NotificarPeso {
		private Monitor.Cond condNP;

		public NotificarPeso() {
			condNP = mutex.newCond();
		}

	}

	
	 
	private class IncrementarPeso {
		private int aux;
		private Monitor.Cond condIP;

		public IncrementarPeso(int peso) {
			aux = peso;
			condIP = mutex.newCond();
		}
	}

	private class PrepararSustitucion {
		private Monitor.Cond condPS;

		public PrepararSustitucion() {
			condPS = mutex.newCond();
		}
	}
}