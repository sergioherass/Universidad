package clase9.universidad;

import java.util.Scanner;

public class Libre extends Alumno { // extiende de una clase abstracta -> DEBE implementar TODOS sus métodos abstractos
									// Además, también debemos añadir el constructor con los parámetros correspondientes
	private String[] listaDeAsignaturas;
	private static float precioPorHora = 10;
	private int noHorasDiarias;
	
	public Libre(String nombre, int edad, int curso, String nivelAcademico, int horas) {
		super(nombre,edad,curso,nivelAcademico);
		noHorasDiarias=horas;
		listaDeAsignaturas = new String[5];  
		pedirAsignaturas();
	}
	
	private void pedirAsignaturas() {
		Scanner sc = new Scanner(System.in);
		int i=0;
		while(i<5) {
			listaDeAsignaturas[i++] = sc.nextLine();
		}
		sc.close();
	}
	
	public double pagoMensual() {
		return precioPorHora*noHorasDiarias*listaDeAsignaturas.length;
	}
	
	public String getAsignaturas() {
		String asig = "";
		for(String a: listaDeAsignaturas) 
			asig += a + "\n";

		return asig;
	}

}
