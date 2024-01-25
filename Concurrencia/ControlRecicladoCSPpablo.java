package cc.controlReciclado;

import org.jcsp.lang.*;
import java.util.ArrayList;
import java.util.List;



public class ControlRecicladoCSP implements ControlReciclado, CSProcess {
	
	private enum Estado { LISTO, SUSTITUIBLE, SUSTITUYENDO }

	private final int MAX_P_CONTENEDOR;
	private final int MAX_P_GRUA;

	// Declaramos los canales que utilizaremos para enviar varios procesos por el mismo canal pero solo uno puede recibir
	
	private Any2OneChannel chNotP;
	private Any2OneChannel chIncP;
	private Any2OneChannel chNotS;
	private Any2OneChannel chPrepS;
	private Any2OneChannel chNotSus;
	
	
	
	public ControlRecicladoCSP (int max_p_contenedor, int max_p_grua) {
		
		MAX_P_CONTENEDOR = max_p_contenedor;
		MAX_P_GRUA = max_p_grua;

		// Inicializamos todos los canales
		chNotP = Channel.any2one();
		chIncP = Channel.any2one();
		chNotS = Channel.any2one();
		chPrepS = Channel.any2one();
		chNotSus = Channel.any2one();
		
		// Arrancamos el servidor 
		new ProcessManager(this).start();
	}
	
	// Creamos la clase privada para hacer las peticiones aplazadas de incrementarPeso
	
		private static class PetIncrementarPeso {
			
			//declaramos los atributos de la clase PetIncremetarPeso
			public int p;
			public One2OneChannel chAck;
			
			//Inicializamos los atributos
			PetIncrementarPeso (int p) {
				this.p = p;
				this.chAck = Channel.one2one();
			}
		}

	public void notificarPeso(int p) {
		
		 // Evaluamos la PRE
		
		if(p <= 0 || p > MAX_P_GRUA) { 
			
			throw new IllegalArgumentException();
		}

		// Enviamos la peticion con el metodo write y esperara a su correspondeinte metodo read
		chNotP.out().write(p);
	}


	public void incrementarPeso(int p) {
		
		// Comprobamos la PRE
		if(p <= 0 || p > MAX_P_GRUA) {
			throw new IllegalArgumentException();
		}

		// Nos creamos la peticion para el servidor correspodiente
		
		PetIncrementarPeso petIncP = new PetIncrementarPeso (p);
		
		// Enviamos la peticion con el metodo write y esperara a su correspondeinte metodo read
		
		chIncP.out().write(petIncP);
		
		// Y esperamos hasta que se confirme con su write
		
		petIncP.chAck.in().read();
		
	}

	public void notificarSoltar() {
		
		 // Enviamos la peticion
		 chNotS.out().write(null);
	}


	public void prepararSustitucion() {
		
		// Enviamos la peticion
		chPrepS.out().write(null);
	}


	public void notificarSustitucion() {

		chNotSus.out().write(null);
	}

	public void run() {
		
		// Inicializamos los atributos con su estado inicial
		int peso = 0 ;
		Estado estado = Estado.LISTO ;
		int accediendo = 0;

		// Creamos el array de guardas con los canales
		Guard[] entradas = {chNotP.in(),chIncP.in(),chNotS.in(),chPrepS.in(),chNotSus.in()};
		
		Alternative servicios =  new Alternative (entradas);

		// Utilizamos la siguientes variables para los indices
		final int NOTIFICAR_PESO = 0;
		final int INCREMENTAR_PESO = 1;
		final int NOTIFICAR_SOLTAR = 2;
		final int PREPARAR_SUSTITUCION = 3;
		final int NOTIFICAR_SUSTITUCION = 4;


		// Creamos el array sincCond para aniadir las conditions
		
		final boolean[] sincCond = new boolean[5] ;

		sincCond[NOTIFICAR_SOLTAR] = true;
		sincCond[NOTIFICAR_SUSTITUCION] = true;
		
		// Nos implementamos la lista para las peticiones aplazadas
		List<PetIncrementarPeso> peticiones = new ArrayList<PetIncrementarPeso>();

		// bucle de servicio infinito
		
		while(true) {
			
			//declaramos variables que nos haran falta en el codigo 
			int pAux; 	
			int i=0;
			PetIncrementarPeso petIncP_2;

			// Aqui hacemos el estudio de cada una de las CPRES
			sincCond[NOTIFICAR_PESO] = estado != Estado.SUSTITUYENDO;
			sincCond[INCREMENTAR_PESO] = estado != Estado.SUSTITUYENDO;
			sincCond[PREPARAR_SUSTITUCION] = estado == Estado.SUSTITUIBLE && accediendo == 0;

			//Evaluamos cada caso de sincCond a traves de un swith y del uso de fairSelect
			switch(servicios.fairSelect(sincCond)) {

			case NOTIFICAR_PESO:
	
				// leemos la peticion
				pAux = (int)chNotP.in().read();
				// Hacemos las operaciones para la peticion
				if(peso+pAux > MAX_P_CONTENEDOR) {
					estado = Estado.SUSTITUIBLE;
				}
				//si no entra en el if ponemos el estado a listo
				else { 		
					estado = Estado.LISTO;
				}
				break;

			case INCREMENTAR_PESO:
				// Leemos la  peticion 
				
				petIncP_2 = (PetIncrementarPeso) chIncP.in().read();

				if(peso + petIncP_2.p <= MAX_P_CONTENEDOR) { //Si se cumple hacemos la correspondiente operacion del POST
					accediendo++;
					peso += petIncP_2.p;
					petIncP_2.chAck.out().write(null);
				}
				//Si no entra en el if añadimos a peticiones petIncP2
				else { 
					peticiones.add(petIncP_2);
				}
				break;

			case NOTIFICAR_SOLTAR:
				
				// Leemos la  peticion y hacemos la correspondiente operacion del POST
				chNotS.in().read();
				accediendo--;
				break;

			case PREPARAR_SUSTITUCION:
				// Leemos la  peticion y hacemos la correspondiente operacion del POST
				chPrepS.in().read();
				estado = Estado.SUSTITUYENDO;
				accediendo = 0;
				break;

			case NOTIFICAR_SUSTITUCION:
				// Leemos la  peticion y hacemos la correspondiente operacion del POST
				chNotSus.in().read();
				peso = 0;
				estado = Estado.LISTO;
				accediendo = 0;
				break;

			} 
			//Recorremos la lista de peticiones aplazadas
			while( i < peticiones.size() ) {
				//escogemmos la peticion
				petIncP_2 = peticiones.get(i);
				//La evaluamos y si se cumple hacemos las operaciones del Post
				if((petIncP_2.p + peso) <= MAX_P_CONTENEDOR && estado != Estado.SUSTITUYENDO) {
					
					accediendo++;
					peso += petIncP_2.p;
					petIncP_2.chAck.out().write(null);
					peticiones.remove(i); //importante! ,hay que sacarlo de la lista de peticiones
					i--;
				 }
				i++;
				}
			}		// No quedan peticiones aplazadas que podrian ser atendidas

			} 
		} 

	

