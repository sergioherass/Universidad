package clase8.personas;

public class Directivo extends Empleado {
	
	private double bonus;
	
	public Directivo (String nombre, int edad, String nss, double sueldo, double bonus) {
		super(nombre,edad,nss,sueldo);
		this.bonus=bonus;
	}
	
	public String toString() {
		return super.toString() + ", Bonus: " + bonus;
	}

}
