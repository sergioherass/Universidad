package clase1;

/*
 * Ingresar los nombres de tres corredores y simular una carrera de 100 metros 
 * (indicar el paso cada 20 metros) e indicar cuando llegan todos a la meta.
 * 
 */

public class ejercicio1 {
	
	private static class Corredor extends Thread {
		
		String nombre;
		
		public Corredor(String nombre) {
			this.nombre = nombre;
		}
		
		public void run() {
			int metros = 20;
			
			System.out.println("Empieza a correr " + nombre);
			
			for(int i=0; i<4; i++) {
				try {
					sleep((long) Math.random()*5000);
				} catch (InterruptedException e) {
					System.err.println(e.getMessage());
				}
				System.out.println(nombre + " ha corrido " + metros + "metros");
				metros+=20;
			}
			
			System.out.println("Termina de correr " + nombre);
		}
		
	}
	
	public static void main(String[] args) {
		
		// Creamos los corredores (hilos)
		Corredor[] corredores = new Corredor[3];
		corredores[0] = new Corredor("Pepe");
		corredores[1] = new Corredor("Juan");
		corredores[2] = new Corredor("Jose");
		
		// Arrancamos la ejecucion de cada uno de ellos
		for(int i=0; i<3; i++)
			corredores[i].start();
		
		// Espero a que lleguen todos a la meta
		for(int i=0; i<3; i++)
			try {
				corredores[i].join();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		
		System.out.println("Todos los corredores han llegado a la meta");
	}

}
