package cc.controlReciclado;
import es.upm.babel.cclib.Monitor;

public final class ControlRecicladoMonitor implements ControlReciclado {
	private enum Estado { LISTO, SUSTITUIBLE, SUSTITUYENDO }

	private final int MAX_P_CONTENEDOR;
	private final int MAX_P_GRUA;

	// Atributos de la clase
	private int peso;
	private Estado estado;
	private int accediendo;
	
	// Atributo auxiliar
	private int p;

	// Monitores y condiciones
	// Necesitamos un monitor para gestionar las condiciones
	Monitor mutex;
	// Necesitamos una condicion para estudiar si se cumple la CPRE del metodo notificarPeso()
	Monitor.Cond condNotificar;
	// Necesitamos una condicion para estudiar si se cumple la CPRE del metodo incrementarPeso()
	Monitor.Cond condIncrementar;
	// Necesitamos una condicion para estudiar si se cumple la CPRE del metodo prepararSustitucion()
	Monitor.Cond condPreparar;
	

	public ControlRecicladoMonitor (int max_p_contenedor, int max_p_grua) {
		MAX_P_CONTENEDOR = max_p_contenedor;
		MAX_P_GRUA = max_p_grua;

		// Inicializamos los atributos de la clase
		peso = 0;
		estado = Estado.LISTO;
		accediendo = 0;
		
		// Inicializacion atributo auxiliar
		p = 0;

		// Inicializamos el monitor y sus condiciones
		mutex = new Monitor();
		condNotificar = mutex.newCond();
		condIncrementar = mutex.newCond();
		condPreparar = mutex.newCond();
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
		if(estado == Estado.SUSTITUYENDO)
			condNotificar.await();

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
		this.p = p;
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
		if(peso + p >  MAX_P_CONTENEDOR || estado == Estado.SUSTITUYENDO)
			condIncrementar.await();

		// POST
		peso += p;
		accediendo++;

		// Desbloqueamos las condiciones con la funcion auxiliar para estudiar la condicion 
		// que debemos desbloquear y liberamos la exclusion mutua
		this.p = p;
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
		if(estado != Estado.SUSTITUIBLE || accediendo != 0)
			condPreparar.await();

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
		if(estado != Estado.SUSTITUYENDO && condNotificar.waiting() > 0)
			condNotificar.signal();
		else if(estado == Estado.LISTO && (peso + p) <= MAX_P_CONTENEDOR && condIncrementar.waiting() > 0) 
			condIncrementar.signal();
		else if(estado == Estado.SUSTITUIBLE && accediendo == 0 && condPreparar.waiting() > 0)
			condPreparar.signal();
	}
}
