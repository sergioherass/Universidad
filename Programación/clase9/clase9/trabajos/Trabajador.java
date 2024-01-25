package clase9.trabajos;

public abstract class Trabajador extends Persona {
	
	String numSegSocial;
	
	public Trabajador(String nombre, int edad, String numSegSocial) {
		super(nombre,edad);
		this.numSegSocial=numSegSocial;
	}
	
	public String toString() {
		return super.toString() + ", Número seguridad social: " + numSegSocial;
	}
	
	public abstract double calcularSueldoMensual();

}
