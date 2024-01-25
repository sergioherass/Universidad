package clase8.herenciaAnimales;

public class AnimalTest {

	public static void main(String[] args) {
		
		Perro perro = new Perro("Toby",1,"Juan");
		// perro.setSonido("muau");
		perro.emitirSonido();
		System.out.println(perro);
		
		Animal flora = new Oveja("Flora",3,"Pedro"); // UPCASTING
		flora.emitirSonido();
		Oveja floraDuplicada = (Oveja) flora; // DOWNCASTING
		floraDuplicada.setSonido("beeeeeeeee");
		floraDuplicada.emitirSonido();
		/*
		Perro floraPerruna = (Perro) flora; // DOWNCASTING -> NO ES CORRECTO
		floraPerruna.emitirSonido();
		*/
		
		Animal oliver = new Animal("Oliver", 2);
		oliver.setSonido("guau");
		oliver.emitirSonido();
		/*
		Perro oliverDuplicado = (Perro) oliver; // DOWNCASTING -> NO ES CORRECTO 
		oliverDuplicado.emitirSonido();
		*/
		
		Animal animales[] = {perro, flora, floraDuplicada, oliver};
		for(int i=0; i<animales.length; i++) 
			System.out.println(animales[i]);
		
		
	}

}
