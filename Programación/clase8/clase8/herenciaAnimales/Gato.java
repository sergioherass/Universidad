package clase8.herenciaAnimales;

public class Gato extends Animal {

	public Gato(String nombre, int edad) {
		super(nombre,edad);
	}

	// Si ninguna de las clases (ni el padre ni el hijo) tiene atributos, entonces no es necesario indicar ningún constructor
	// ya que tenemos el de por defecto, que sería como sigue:
	/*
	 * public Gato(){
	 * 		super();
	 * }
	 */


	public void jugarConOvillo() {
		System.out.println("juego con el ovillo");
	}


}
