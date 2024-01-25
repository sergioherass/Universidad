package entregables;

import es.upm.babel.cclib.Producto;
import es.upm.babel.cclib.MultiAlmacen;

import java.util.LinkedList;
import java.util.Queue;

//importamos la librerıa JCSP
import org.jcsp.lang.*;

class MultiAlmacenJCSP implements MultiAlmacen , CSProcess {
	
	// Canales para enviar y recibir peticiones al/del servidor
	private final Any2OneChannel chAlmacenar = Channel.any2one();
	private final Any2OneChannel chExtraer = Channel.any2one();
	private int TAM;
	
	private AltingChannelInput petAlmacenar;
	private AltingChannelInput petExtraer;
	private Queue<Producto> cola;
	
	// Para evitar la construccion de almacenes sin inicializar la
	// capacidad
	private MultiAlmacenJCSP () { }
	
	public MultiAlmacenJCSP(int n) {
		this.TAM = n;
		// COMPLETAR: inicializacion de otros atributos
		cola = new LinkedList<Producto>();
	}
	
	public void almacenar(Producto [] productos) {
		// COMPLETAR: comunicacion con el servidor
		petAlmacenar = chAlmacenar.in();
	}
	
	public Producto [] extraer(int n) {
		Producto [] result = new Producto[n];
		// COMPLETAR: comunicacion con el servidor
		petExtraer = chExtraer.in();
		return result;
	}
	
	// codigo del servidor
	private static final int ALMACENAR = 0;
	private static final int EXTRAER = 1;
	
	public void run() {
		
		// COMPLETAR: declaracion de canales y estructuras auxiliares
		ChannelOutput resp;
		Producto item;
		final boolean[] sincCond = new boolean[2];
		sincCond[ALMACENAR] = cola.size() < TAM;
		sincCond[EXTRAER] = cola.size() > 0;
		
		Guard [] entradas = {chAlmacenar.in(), chExtraer.in()};
		Alternative servicios = new Alternative(entradas);
		int choice = 0;
		
		while (true) {
			try {
				choice = servicios.fairSelect();
			} catch (ProcessInterruptedException e){}
			
			switch(choice){
			case ALMACENAR:
				// COMPLETAR: tratamiento de la peticion
				item = (Producto) petAlmacenar.read();
				cola.add(item);
				break;
			case EXTRAER:
				// COMPLETAR: tratamiento de la peticion
				resp = (ChannelOutput) petExtraer.read();
				resp.write(cola.peek());
				cola.poll();
				break;
			}
			// COMPLETAR: atencion de peticiones pendientes
			
		}
	}
}