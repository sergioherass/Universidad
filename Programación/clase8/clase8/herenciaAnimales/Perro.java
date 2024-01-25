package clase8.herenciaAnimales;

public class Perro  extends Animal {

	private String amo;

	public Perro(String nombre, int edad, String amo) {
		super(nombre,edad);
		this.amo=amo;
	}

	// Suponiendo que la clase padre no tuviera atributos y la clase hija si, entonces debemos crearnos un constructor
	// que inicialice la clase padre con super(); y luego ya inicialice los parametros de la subclase
	/*
	 * public Perro(String amo){
	 * 		super();
	 * 		this.amo=amo;
	 * }
	 */

	public void lamerHueso() {
		System.out.println("lamiendo hueso");
	}

	public String toString() {	// Sobrescribimos el método toString() de la clase padre
		return super.toString() + " Amo: " + this.amo; // Usamos la clase padre
	}


}
