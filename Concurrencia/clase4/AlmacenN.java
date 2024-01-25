package clase4;

import es.upm.babel.cclib.Producto;
import es.upm.babel.cclib.Almacen;

// TODO: importar la clase de los semáforos
import es.upm.babel.cclib.Semaphore;

/**
 * Implementación de la clase Almacen que permite el almacenamiento
 * FIFO de hasta un determinado número de productos y el uso
 * simultáneo del almacén por varios threads.
 */
class AlmacenN implements Almacen {
	private int capacidad = 0;
	private Producto[] almacenado = null;
	private int nDatos = 0;
	private int aExtraer = 0;
	private int aInsertar = 0;

	// TODO: declaración de los semáforos necesarios
	Semaphore vacio;
	Semaphore lleno;
	Semaphore mutex; 	// aseguramos la exclusion mutua para no acceder 
						// simultaneamente a almacenar/producir dos veces
	
	public AlmacenN(int n) {
		capacidad = n;
		almacenado = new Producto[capacidad];
		nDatos = 0;
		aExtraer = 0;
		aInsertar = 0;

		// TODO: inicialización de los semáforos
		vacio = new Semaphore(n);
		lleno = new Semaphore(0);
		mutex = new Semaphore(1);
		
	}

	public void almacenar(Producto producto) {
		// TODO: protocolo de acceso a la sección crítica y código de
		// sincronización para poder almacenar.
		vacio.await();
		mutex.await();

		// Sección crítica
		almacenado[aInsertar] = producto;
		nDatos++;
		aInsertar++;
		aInsertar %= capacidad;

		// TODO: protocolo de salida de la sección crítica y código de
		// sincronización para poder extraer.
		lleno.signal();
		mutex.signal();
	}

	public Producto extraer() {
		Producto result;

		// TODO: protocolo de acceso a la sección crítica y código de
		// sincronización para poder extraer.
		lleno.await();
		mutex.await();

		// Sección crítica
		result = almacenado[aExtraer];
		almacenado[aExtraer] = null;
		nDatos--;
		aExtraer++;
		aExtraer %= capacidad;

		// TODO: protocolo de salida de la sección crítica y código de
		// sincronización para poder almacenar.
		vacio.signal();
		mutex.signal();
		
		return result;
	}
}