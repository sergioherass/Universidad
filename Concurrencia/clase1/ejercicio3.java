package clase1;

/*
 * Simular con procesos diferentes los estudiantes de una clase que llegan al aula (indicarlo) 
 * y se sientan (indicarlo). ¿Como implementariamos la llegada del profesor (otro proceso) 
 * despues de llegar todos los estudiantes?
 * 
 */

public class ejercicio3 {
	
	private static class Alumno extends Thread {
		
		int id;
		
		public Alumno(int id) {
			this.id = id;
		}
		
		public void run() {
			System.out.println("El alumno " + id + " ha llegado al aula.");
			
			try {
				sleep((long)Math.random() * 5000);
			} catch (InterruptedException e) {
				System.err.println("El alumno " + id + " ha sido interrumpido.");
			}
			
			System.out.println("El alumno " + id + " se ha sentado.");
		}
		
	}
	
	private static class Profesor extends Thread {
		
		public void run() {
			System.out.println("Ha llegado el profesor.");
		}
		
	}
	
	public static void main(String[] args) {
		
		int N = 12;
		
		// Creamos los threads
		Alumno[] alums = new Alumno[N];
		Profesor prof = new Profesor();
		
		for(int i=0; i<N; i++) 
			alums[i] = new Alumno(i+1);
		
		// Arrancamos los threads
		for(int i=0; i<N; i++)
			alums[i].start();
		
		// Me espero a que todos los alumnos hayan llegado para que llegue el profesor
		for(int i=0; i<N; i++)
			try {
				alums[i].join();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		
		prof.start();
		
		// Me aseguro de que el proceso Profesor haya terminado antes de finalizar el programa
		try {
			prof.join();
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
		
	}

}
