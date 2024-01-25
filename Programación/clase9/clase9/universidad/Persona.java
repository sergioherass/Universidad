package clase9.universidad;

public class Persona {
	
	private String nombre;
	private int edad;
	
	public Persona(String nombre, int edad) { // todos los hijos deben implementar, al menos, este constructor
		this.nombre=nombre;
		this.edad=edad;
	}
	
	public String toString() {
		return "Nombre: " + nombre + ", Edad: " + edad;
	}
	
	public void setEdad(int edad) {
		this.edad = edad; 
	}

}
