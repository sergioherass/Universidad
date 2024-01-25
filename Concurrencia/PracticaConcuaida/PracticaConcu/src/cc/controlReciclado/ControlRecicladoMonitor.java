package cc.controlReciclado;
import java.util.ArrayList;
import java.util.List;

import es.upm.babel.cclib.Monitor;

public final class ControlRecicladoMonitor implements ControlReciclado {
	private enum Estado { LISTO, SUSTITUIBLE, SUSTITUYENDO }

	private final int MAX_P_CONTENEDOR;
	private final int MAX_P_GRUA;

	// Atributos de la clase
	private int peso;
	private Estado estado;
	private int accediendo;

	// Monitores y condiciones
	// Necesitamos un monitor para gestionar las condiciones
	Monitor mutex;
	// Listas para la gestion de esperas de notificarPeso, incrementarPeso o prepararSustitucion
	List<NotificarPeso> listNP;
	List<IncrementarPeso> listIP;
	List<PrepararSustitucion> listPS;
	/*
	// Necesitamos una condicion para estudiar si se cumple la CPRE del metodo notificarPeso()
	Monitor.Cond condNotificar;
	// Necesitamos una condicion para estudiar si se cumple la CPRE del metodo incrementarPeso()
	Monitor.Cond condIncrementar;
	// Necesitamos una condicion para estudiar si se cumple la CPRE del metodo prepararSustitucion()
	Monitor.Cond condPreparar;
	 */

	public ControlRecicladoMonitor (int max_p_contenedor, int max_p_grua) {
		MAX_P_CONTENEDOR = max_p_contenedor;
		MAX_P_GRUA = max_p_grua;

		// Inicializamos los atributos de la clase
		peso = 0;
		estado = Estado.LISTO;
		accediendo = 0;

		// Inicializamos el monitor y sus condiciones
		mutex = new Monitor();
		listNP = new ArrayList<>();
		listIP = new ArrayList<>();
		listPS = new ArrayList<>();
		//condNotificar = mutex.newCond();
		//condIncrementar = mutex.newCond();
		//condPreparar = mutex.newCond();
	}

	public void notificarPeso(int p) {
		mutex.enter();

		// COMPROBAMOS QUE SE CUMPLE LA PRE, en caso contrario, nos salimos del programa y 
		// lanzamos la excepcion IllegalArgumentException
		if(p <= 0 || p > MAX_P_GRUA) {
			mutex.leave();
			throw new IllegalArgumentException("El peso debe estar entre 0 y el peso máximo de la grúa");
		}

		// COMPROBAMOS QUE SE CUMPLE LA CPRE, en caso contrario, me quedo esperando hasta que
		// se cumpla para desbloquear la funcion
		if(estado == Estado.SUSTITUYENDO) {
			NotificarPeso np = new  NotificarPeso();
			listNP.add(np);
			np.condNP.await();
		}

		// POST: si el peso actual del contenedor mas el peso que introducimos
		// supera el peso maximo del contenedor, entonces el estado pasa a ser sustituible 
		// (lo que indica que el contenedor necesita ser sustituido);
		// e.o.c. el estado pasa a ser listo (lo que indica que el contenedor admite mas carga)
		if(peso + p > MAX_P_CONTENEDOR)
			estado = Estado.SUSTITUIBLE;
		else
			estado = Estado.LISTO;

		// Desbloqueamos las condiciones con la funcion auxiliar para estudiar la condicion 
		// que debemos desbloquear y liberamos la exclusion mutua
		desbloqueoSimple();
		mutex.leave();
	}

	public void incrementarPeso(int p) {
		mutex.enter();

		// Comprobamos la PRE, si no se cumple abandonamos la funcion y 
		// lanzamos la IllegalArgumentException
		if(p <= 0 || p > MAX_P_GRUA) {
			mutex.leave();
			throw new IllegalArgumentException("El peso debe estar entre 0 y el peso máximo de la grúa");
		}

		// COMPROBAMOS QUE SE CUMPLE LA CPRE, en caso contrario, me quedo esperando hasta que
		// se cumpla para desbloquear la funcion
		if(peso + p >  MAX_P_CONTENEDOR || estado == Estado.SUSTITUYENDO) {
			IncrementarPeso np = new  IncrementarPeso(p);
			listIP.add(np);
			np.condIP.await();
		}

		// POST
		peso += p;
		accediendo++;

		// Desbloqueamos las condiciones con la funcion auxiliar para estudiar la condicion 
		// que debemos desbloquear y liberamos la exclusion mutua
		desbloqueoSimple();
		mutex.leave();
	}

	public void notificarSoltar() {
		mutex.enter();

		// POST
		accediendo--;

		// Desbloqueamos las condiciones con la funcion auxiliar para estudiar la condicion 
		// que debemos desbloquear y liberamos la exclusion mutua
		desbloqueoSimple();
		mutex.leave();
	}

	public void prepararSustitucion() {
		mutex.enter();

		// COMPROBAMOS QUE SE CUMPLE LA CPRE, en caso contrario, me quedo esperando hasta que
		// se cumpla para desbloquear la funcion
		if(estado != Estado.SUSTITUIBLE || accediendo != 0) {
			PrepararSustitucion ps = new PrepararSustitucion();
			listPS.add(ps);
			ps.condPS.await();
		}

		// POST
		estado = Estado.SUSTITUYENDO;

		// Desbloqueamos las condiciones con la funcion auxiliar para estudiar la condicion 
		// que debemos desbloquear y liberamos la exclusion mutua
		desbloqueoSimple();
		mutex.leave();
	}

	public void notificarSustitucion() {
		mutex.enter();

		// POST
		peso = 0;
		estado = Estado.LISTO;
		accediendo = 0;

		// Desbloqueamos las condiciones con la funcion auxiliar para estudiar la condicion 
		// que debemos desbloquear y liberamos la exclusion mutua
		desbloqueoSimple();
		mutex.leave();
	}

	private void desbloqueoSimple() {
		/*if(estado != Estado.SUSTITUYENDO && condNotificar.waiting() > 0)
			condNotificar.signal();
		else if(estado == Estado.LISTO && (peso + pesoAux) <= MAX_P_CONTENEDOR && condIncrementar.waiting() > 0) 
			condIncrementar.signal();
		else if(estado == Estado.SUSTITUIBLE && accediendo == 0 && condPreparar.waiting() > 0)
			condPreparar.signal();*/
		boolean desbloqueado = false;
		
		if(listNP.size() > 0 && estado != Estado.SUSTITUYENDO) {
			listNP.get(0).condNP.signal();
			listNP.remove(0);
			desbloqueado = true;
		}
		
		if(!desbloqueado && listIP.size() > 0 && estado == Estado.LISTO) {
			for(int i=0; i<listIP.size() && !desbloqueado; i++) {
				if(peso + listIP.get(i).pesoAux <= MAX_P_CONTENEDOR) {
					listIP.get(i).condIP.signal();
					listIP.remove(i);
					desbloqueado = true;
				}
			}
		}
		
		if(!desbloqueado && estado == Estado.SUSTITUIBLE && accediendo == 0 && listPS.size() > 0) {
			listPS.get(0).condPS.signal();
			listPS.remove(0);
		}

	}

	/* 
	 	Clase encargada de la gestion de las esperas de notificar peso y su condition
	 */
	private class NotificarPeso {
		private Monitor.Cond condNP;

		public NotificarPeso() {
			condNP = mutex.newCond();
		}

	}

	/* 
 		Clase encargada de la gestion de las esperas de incrementar peso y su condition
 		Esta formado por el peso y su propia condicion Monitor.Cond 
	 */
	private class IncrementarPeso {
		private int pesoAux;
		private Monitor.Cond condIP;

		public IncrementarPeso(int peso) {
			pesoAux = peso;
			condIP = mutex.newCond();
		}
	}

	/* 
 		Clase encargada de la gestion de las esperas de preparar sustitucion y su condition
	 */
	private class PrepararSustitucion {
		private Monitor.Cond condPS;

		public PrepararSustitucion() {
			condPS = mutex.newCond();
		}
	}
}
