package superheroes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main (String[] args) {
		
		Scanner sc = new Scanner(System.in);

		SuperheroesDatabase superheroes = new SuperheroesDatabase();
		
		char menuOption = 'a';

		// Main menu loop
		do {
			System.out.println("Escoja una opci�n: ");
			System.out.println("  1) Crear las tablas \"escena\" y \"rival\".");
			System.out.println("  2) Cargar datos  de las tablas \"escena\", \"protagoniza\" y \"rival\".");
			System.out.println("  3) Mostrar el cat�logo.");
			System.out.println("  4) Obtener la duraci�n de una pel�cula.");
			System.out.println("  5) Obtener las escelas de las pel�culas en las que aparece un villano.");
			System.out.println("  6) Desenmascarar a un superheroe.");
			System.out.println("  0) Salir de la aplicaci�n.");

			// Read user's option and check that it is a valid option
			menuOption = 'a';
			do {
				String line = sc.nextLine();
				if (line.length()==1) {
					menuOption = line.charAt(0);
				}
				if (menuOption<'0' || menuOption>'6') {
					System.out.println("Opci�n incorrecta.");
				}
			} while (menuOption<'0' || menuOption>'6');

			// Call a specific method depending on the option
			switch (menuOption) {
			case '1':
				System.out.println("Creando tabla \"escena\"...");
				superheroes.createTableEscena();
				System.out.println("Creando tabla \"rival\"...");
				superheroes.createTableRival();
				break;
			case '2':
				System.out.println("Cargando datos de la tabla \"escena\"...");
				int n = superheroes.loadEscenas("escenas.csv");
				System.out.println("Se han cargado " + n + " entradas.");
				System.out.println("Cargando datos de la tabla \"protagoniza\" y \"rival\"...");
				n = superheroes.loadProtagoniza("protagonistas.csv");
				System.out.println("Se han cargado " + n + " entradas.");
				break;
			case '3':
				System.out.println("Cat�logo disponible:");
				System.out.println(superheroes.catalogo());
				break;
			case '4':
				System.out.println("�De qu� pel�cula quieres saber la duraci�n?");
				String nombre_pelicula = sc.nextLine();
				System.out.println("Duraci�n total de " + nombre_pelicula + ":");
				System.out.println(superheroes.duracionPelicula(nombre_pelicula) + " minutos.");
				break;
			case '5':
				System.out.println("�De qu� villano quieres consultar las escenas de sus pel�culas?");
				String villano = sc.nextLine();
				System.out.println("Escenas de las pel�culas en las que participa " + villano + ":");
				System.out.println(superheroes.getEscenas(villano));
				break;
			case '6':
				System.out.println("�Cu�l es el nombre del superh�roe al que quieres desenmascarar?");
				String nombre = sc.nextLine();
				System.out.println("�Y su apellido?");
				String apellido = sc.nextLine();
				if (superheroes.desenmascara(nombre, apellido, "avatar_desenmascarado.jpg"))
					System.out.println("�" + nombre + " " + apellido + " ha sido desenmascarado!");
				else
					System.out.println("La identidad secreta de " + nombre + " " + apellido + " sigue siendo todo un misterio.");
				break;
			}

			if (menuOption!='0')
				System.out.println("�Qu� m�s desea hacer?");
			else
				System.out.println("�Hasta pronto!");
		} while (menuOption!='0');

		sc.close();
		
		superheroes.closeConnection();
		
	}

}
