package clase1;

public class HolaMundos {
	
	private static class HolaMundo extends Thread {
		
		int id;
		
		public HolaMundo(int id) {
			this.id = id;
		}
		
		public void run() {
			System.out.println("Hola mundo " + id);
		}
	}
	
	public static void main(String[] args) { // PROCESO PRINCIPAL
		
		// Creamos dos hilos de ejecucion
		HolaMundo hola1 = new HolaMundo(1);
		HolaMundo hola2 = new HolaMundo(2);
		
		// Arrancamos los hilos
		hola1.start(); // proceso concurrente 1
		hola2.start(); // proceso concurrente 2
		
		try {
			hola1.join(); // Espera a que termine el hilo para seguir con el proceso principal
			hola2.join(); // puede seguir ejecutando el otro hilo hasta que termine 
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
		
		// Sabemos que los dos procesos secundarios han acabado, sigue mi proceso principal
		System.out.println("Hola, soy ’el main’");
	}


}
