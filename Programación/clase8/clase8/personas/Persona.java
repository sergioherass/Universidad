package clase8.personas;

public class Persona {
	
	private String nombre; 
	private int edad;
	
	public Persona(String nombre, int edad) {
		this.nombre=nombre;
		this.edad=edad;
	}
	
	public String toString() {
		return "Nombre: " + nombre + ", Edad: " + edad;
	}

}
