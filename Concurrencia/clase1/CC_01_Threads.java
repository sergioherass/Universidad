package clase1;

public class CC_01_Threads {
	
	private static class Hilo extends Thread {
		
		int id;
		long T = 1000; // Duerme 1 segundo
		
		public Hilo(int id) {
			this.id = id;
		}
		
		public void run() {
			// Imprimimos una linea que identifique que el thread va a empezar
			System.out.println("Empieza el thread " + id);
			
			// Hacemos que el hilo duerma T ms
			try {
				sleep(T*id);
			} catch(InterruptedException e) {
				System.err.println(e.getMessage());
			}
			
			// Imprimimos una linea que identifique que el thread va a terminar
			System.out.println("Acaba el thread " + id);
			
		}
		
	}
	
	public static void main(String[] args) {
		int N = 10;
		
		// Creo los hilos
		Hilo[] hilos = new Hilo[N];
		
		for(int i = 0; i<N; i++)
			hilos[i] = new Hilo(i);
		
		// Arrancamos la ejecucion de los hilos
		for(int i=0; i<N; i++) 
			hilos[i].start();
		
		
		// Esperamos a que todos los hilos terminen
		for(int i=0; i<N; i++) 
			try {
				hilos[i].join();
			} catch(InterruptedException e) {
				System.err.println("Thread " + i + " interrumpido.");
			}
			
		
		// Informamos de que todos los procesos han acabado
		System.out.println("Han finalizado todos los threads.");
	}

}
