package clase1;

/*
 * Queremos programar dos procesos, uno imprime los numeros pares del 1 al 10 y la suma de dichos numeros, 
 * y otro imprime los numeros impares del 1 al 10.
 * 
 */

public class ejercicio2 {
	
	private static class Pares extends Thread {
		
		public void run() {
			int suma = 0;
			
			for(int i=2; i<11; i+=2) {
				System.out.print(i + " ");
				suma += i;
			}
			
			System.out.println("\nSuma de los elementos pares igual a " + suma);
		}
	}
	
	private static class Impares extends Thread {
		
		public void run() {
			
			for(int i=1; i<10; i+=2)
				System.out.print(i + " ");
			
		}
		
	}
	
	public static void main(String[] args) {
		
		// Creamos los threads
		Pares par = new Pares();
		Impares impar = new Impares();
		
		// Arrancamos los threads
		par.start();
		
		/*
		 * Si ponemos en esta parte par.join(); 
		 * antes de hacer impar.start(), entonces se espera a que termine 
		 * el thread de los pares antes de arrancar la ejecucion del segundo
		 * thread de los impares 
		 * 
		 */
		
		impar.start();
		
		// Nos aseguramos de que los dos threads acaben antes de finalizar el programa principal
		try {
			par.join();
			impar.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
