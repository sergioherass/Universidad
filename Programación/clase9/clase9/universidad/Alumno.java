package clase9.universidad;

public abstract class Alumno extends Persona {
	
	private int curso;
	private String nivelAcademico;
	
	public Alumno (String nombre, int edad, int curso, String nivelAcademico) {
		super(nombre,edad);
		this.curso=curso;
		this.nivelAcademico=nivelAcademico;
	}
	
	public String toString() {
		return super.toString() + ", Curso: " + curso + ", Nivel académico: " + nivelAcademico;
	}
	
	public int getCurso() {
		return curso;
	}
	
	public abstract double pagoMensual();
	public abstract String getAsignaturas();

}
