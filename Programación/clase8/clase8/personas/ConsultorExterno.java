package clase8.personas;

public class ConsultorExterno extends Trabajador {
	
	private double tarifa;
	private double horas;
	private String empresa;
	
	public ConsultorExterno(String nombre, int edad, String nss, double tarifa, double horas, String empresa) {
		super(nombre,edad,nss);
		this.tarifa=tarifa;
		this.horas=horas;
		this.empresa=empresa;
	}
	
	public String toString() {
		return super.toString() + ", Tarifa: " + tarifa + ", Horas: " + horas + ", Empresa: " + empresa;
	}

}
