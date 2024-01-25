package clase10.personas;

public class Persona implements Comparable<Persona> {
	
	private String nombre; 
	private int edad;
	
	public Persona(String nombre, int edad) {
		this.nombre=nombre;
		this.edad=edad;
	}
	
	public String toString() {
		return "Nombre: " + nombre + ", Edad: " + edad;
	}

	public int compareTo(Persona p) {
		return edad-p.edad;
	}

}
