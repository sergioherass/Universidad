package clase8.personas;

public class Trabajador extends Persona {
	
	String numSegSocial;
	
	public Trabajador(String nombre, int edad, String numSegSocial) {
		super(nombre,edad);
		this.numSegSocial=numSegSocial;
	}
	
	public String toString() {
		return super.toString() + ", Número seguridad social: " + numSegSocial;
	}

}
