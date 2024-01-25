package clase8.personas;

public class Empleado extends Trabajador {
	
	private double sueldo;
	
	public Empleado(String nombre, int edad, String nss, double sueldo) {
		super(nombre,edad,nss);
		this.sueldo=sueldo;
	}
	
	public String toString() {
		return super.toString() + ", Sueldo: " + sueldo;
	}

}
