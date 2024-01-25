package clase2;

public class CC_02_Carrera {
	
	public static final int N = 1000; // numero veces que se ejecuta cada thread
	public static final int M = 10; // numero threads de cada tipo
	
	public static volatile int contador = 0;
	
	// Thread de incrementadores
	private static class Incrementador extends Thread {
		public void run() {
			for(int i=0; i<N; i++)
				contador++;
		}
	}
	
	// Thread de decrementadores
	private static class Decrementador extends Thread {
		public void run() {
			for(int i=0; i<N; i++)
				contador--;
		}
	}
	
	public static void main(String[] args) {
		
		// Creamos los hilos
		Thread hilos[] = new Thread[2*M];
		for(int i=0; i<2*M; i++) {
			if(i<M)
				hilos[i] = new Incrementador();
			else
				hilos[i] = new Decrementador();
		}
		
		// Lanzamos los hilos
		for(int i=0; i<2*M; i++)
			hilos[i].start();
		
		// Esperamos a que terminen todos los hilos
		for(int i=0; i<2*M; i++)
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		
		System.out.println("contador = " + contador);
	}
	

}
