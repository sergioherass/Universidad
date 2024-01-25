package clase8.herenciaAnimales;

public class Animal {

	// Como queremos que todos los animales tengan nombre y edad
	// los introducimos en la superclase (o clase padre)
	private String nombre;
	private int edad;
	private String sonido;

	public Animal(String nombre, int edad) {
		this.nombre=nombre;
		this.edad=edad;

		if(this instanceof Perro) { 		// a instanceof b siempre retornará true/false
			sonido="guau";					// donde a se refiere a un objeto de la clase padre
		} else if(this instanceof Gato) {	// y b se refiere a una clase hija existente (NO A OBJETOS, 
			sonido="miau";					// si no a la clase en si)
		} else if(this instanceof Oveja) {
			sonido="bee";
		}
	}

	public void setSonido(String sonido) {
		this.sonido=sonido;
	}

	public void emitirSonido() {
		System.out.println(nombre + " hace " + sonido);
	}

	// De esta forma, cualquier animal puede emitir cualquier sonido (aunque esto no es correcto)
	/*
	public void emitirSonido(String sonido) {
		System.out.println(nombre + " hace " + sonido);
	}*/

	public String toString() {
		return "Nombre: "+ this.nombre+" Edad: "+ this.edad+ 
				" Sonido: " +this.sonido; 
	}




}
