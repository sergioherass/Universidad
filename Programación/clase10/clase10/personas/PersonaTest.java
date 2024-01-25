package clase10.personas;

import java.util.Arrays;

public class PersonaTest {
	
	public static void main(String []args) {
		Persona p1 = new Persona("Marcos", 26);
		System.out.println(p1);
		
		Persona p2 = new Persona("Alberto", 12);
		System.out.println(p2);
		
		Persona p3 = new Persona("Juan", 42);
		System.out.println(p3);
		
		Persona p4 = new Persona("José", 67);
		System.out.println(p4);
		
		Persona p5 = new Persona("Irene", 4);
		System.out.println(p5);
		
		System.out.println("\n --- PROBANDO LA FUNCIÓN ARRAYS.SORT() ---\n");
		Persona[] personas = {p1,p2,p3,p4,p5};
		Arrays.sort(personas); // NECESITAMOS TENER IMPLEMENTADO EL MÉTODO compareTo
		for(int i=0; i<personas.length; i++) {
			System.out.println(personas[i]);
		}
		
	}

}
