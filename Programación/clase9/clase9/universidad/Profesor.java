package clase9.universidad;

public class Profesor extends Persona {

	private String asignatura;
	
	public Profesor(String nombre, int edad, String asignatura) {
		super(nombre,edad);	// construimos el objeto del que hereda (Persona) -> obligatorio si no es el constructor por defecto
		this.asignatura = asignatura;
	}
	
	public String toString() { // con super.toString() hacemos referencia al método toString() del padre (Persona)
		return super.toString() + ", Asignatura: " + asignatura;
	}
	
}
