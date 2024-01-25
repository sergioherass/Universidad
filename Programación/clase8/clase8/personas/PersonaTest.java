package clase8.personas;

public class PersonaTest {
	
	public static void main(String []args) {
		Persona p = new Persona("Aida", 26);
		System.out.println(p);
		
		Trabajador t = new Trabajador("Pepe", 31, "xxx1111");
		System.out.println(t);
		
		Empleado e1 = new Empleado("Maria", 47, "yyy2222", 1234.98);
		System.out.println(e1);
		
		Empleado e2 = new Empleado("Juan", 53, "uuu5555", 2345);
		System.out.println(e2);
		
		Directivo d = new Directivo("Roberto", 68, "zzz3333", 3456.78, 90);
		System.out.println(d);
		
		ConsultorExterno ce = new ConsultorExterno("Lucía", 23, "ttt4444", 234, 15, "Indra");
		System.out.println(ce);
		
		System.out.println("\n");
		
		Persona[] personas = new Persona[4];
		personas[0] = d;
		personas[1] = e1;
		personas[2] = e2;
		personas[3] = ce;
		for(int i=0; i<personas.length; i++)
			System.out.println(personas[i]);
		
		System.out.println("\n");
		
		Trabajador[] trabajadores = {d,e1,e2,ce};
		for(int i=0; i<trabajadores.length; i++)
			System.out.println(trabajadores[i]);
		
		/* Lo que no podemos hacer es poner el vector anterior de tipo Empleado, ya que aunque d, e1 y e2 
		 * si que tienen a Empleado por padre, ce NO -> necesitamos que el Objeto sea padre de todos los elementos
		 * de dicho vector
		 */
		
	}

}
