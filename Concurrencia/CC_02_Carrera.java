package entregables;

public class CC_02_Carrera {

	final private static int N = 10; // numero de decrementos/incrementos
	final private static int M = 10; // numero de hilos de tipo decremento/incremento
	
	// variable global compartida que modificamos en cada hilo
	private static int n = 0; 

	private static class Incrementador extends Thread {

		public void run() {

			for(int i = 0; i < N; i++)
				n++;

		}
	}

	private static class Decrementador extends Thread {

		public void run() {

			for(int i = 0; i < N; i++)
				n--;

		}
	}

	public static void main(String [] argv) {

		// Creamos los diferentes hilos de ejecucion
		Thread t[] = new Thread[M*2];

		for(int i = 0; i < M*2; i++)
			if(i%2==0)	// Me creo la mitad de tipo incrementador y la otra mitad de tipo decrementador
				t[i] = new Incrementador();
			else
				t[i] = new Decrementador();

		// Arrancamos la ejecucion de todos los hilos
		for(int i = 0; i < M*2; i++)
			t[i].start();

		// Esperamos a que todos los hilos terminen antes de finalizar el programa
		for(int i = 0; i < M*2; i++)
			try {
				t[i].join();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}

		// Mostramos el resultado final de n
		System.out.println("n = " + n);
	}

}