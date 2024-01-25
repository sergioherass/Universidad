package clase9.trabajos;

public class PersonaTest {
	
	public static void main(String []args) {
		Persona p = new Persona("Aida", 26);
		System.out.println(p);
		
		Empleado e1 = new Empleado("Maria", 47, "yyy2222", 50000);
		System.out.println(e1);
		
		Empleado e2 = new Empleado("Juan", 53, "uuu5555", 35000);
		System.out.println(e2);
		
		Directivo d = new Directivo("Roberto", 68, "zzz3333", 60000, 90);
		System.out.println(d);
		
		ConsultorExterno ce = new ConsultorExterno("Lucía", 23, "ttt4444", 15, 40, "Indra");
		System.out.println(ce);
		
		System.out.println("\n");
		
		Trabajador[] trabajadores = {d,e1,e2,ce};
		// Calcular el sueldo mensual de todos los trabajadores y mostrarlo por pantalla:
		for(int i=0; i<trabajadores.length; i++) {
			System.out.println(trabajadores[i].getNombre() + " cobra " + 
							trabajadores[i].calcularSueldoMensual() + " € mensualmente");
		}
			
		
		
		
		
	}

}
