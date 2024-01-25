/**
 * 
 */
package clase11.ClubDelLector.test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import clase11.ClubDelLector.ee1.Lector;
import clase11.ClubDelLector.ee1.Libro;
import fecha.Fecha;
import java.util.ArrayList;

/**
 * @author agonzalez
 *
 */
public class TestLector {
	@Rule //Se establece un time out general para todos los tests. Se debe comentar esta línea y la de abajo para depurar
    public TestRule  globalTimeout = new DisableOnDebug(Timeout.millis(100)); // 100 milisegundos máximos por test
	Libro libro1;
	Libro libro2;
	Lector lector;
	Lector lector2;
		
	/**
	 * Construye las instancias que se van a usar durante las pruebas
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		libro1 = new Libro("El quijote", 
				"Miguel de Cervantes", new Fecha("16/01/1605"));
		libro2 = new Libro("El quijote 2ª edición", 
				"Miguel de Cervantes", new Fecha("16/01/1625"));
		lector = new Lector(300,"Pepe White");
		lector2 = new Lector(300,"Pepe Black");
	}

	/**
	 * Este test comprueba que se use list.ArrayList visto en la asignatura como atributo y que los
	 * atributos estén definidos tal y como se indica en el enunciado
	 */
	@Test
	public void testAtributos() {
		Class<?> className = Lector.class;
		ArrayList<Atributo> atributos = new ArrayList<>();
		atributos.add(0, new Atributo("nSocio", "int"));
		atributos.add(1, new Atributo("nombre", "java.lang.String"));
		atributos.add(2, new Atributo("historicoLectura", "java.util.ArrayList"));
		for (int i = 0; i<atributos.size(); i++){
			testAtributo(className, atributos.get(i)); 
		}
	}
	
	/**
	 * Test method for {@link ee1.Lector#esIgual(ee1.Lector)}.
	 * Se comprueba la igualdad cuando los históricos están vacíos. Dos lectores son iguales
	 * si y sólo si cuando coincide el número de socio y los libros leídos que hay en el
	 * histórico son los mismos y están en el mismo orden
	 */
	@Test
	public void testEsIgual() {
		assertTrue ("Igualdad cuando el histórico está vacio. Dos lectores son iguales si tienen el mismo número de socio y han leido los mismos libros y en el mismo orden."
				+ " Verifique que en esIgual no se use el nombre",
				lector.equals(lector2));
	}
	
	/**
	 * Test method for {@link ee1.Lector#esIgual(ee1.Lector)}.
	 * Se comprueba la igualdad cuando un lector ha leido un libro más que el otro y el resto son iguales. Dos lectores son iguales
	 * si y sólo si cuando coincide el número de socio y los libros leídos que hay en el
	 * histórico son los mismos y están en el mismo orden
	 */
	@Test
	public void testEsIgual1(){
		Libro []librosLeidos = {libro2,libro2,libro1,libro1,libro1,libro1,libro1,libro1,libro1,libro2};
		//Vamos a llenar el histórico de lectura del lector 2
		//Este lector tiene en este punto dos veces leido el libro1
		for (int i=0; i<librosLeidos.length-1;i++){
				lector2.leerLibro(librosLeidos[i]);
				lector.leerLibro(librosLeidos[i]);
			}
		lector.leerLibro(libro1);
		
		assertFalse ("Dos lectores son iguales si tienen el mismo número de socio y han leído los mismos libros y en el mismo orden. "
				+ "Verifique que se están comprobando correctamente los historiales de lectura",
				lector.equals(lector2));
	}
	
	/**
	 * Test method for {@link ee1.Lector#esIgual(ee1.Lector)}.
	 * Se comprueba la igualdad cuando los dos lectores han leído el mismo número de libros, pero difieren en el último libro leído. Dos lectores son iguales
	 * si y sólo si cuando coincide el número de socio y los libros leídos que hay en el
	 * histórico son los mismos y están en el mismo orden
	 */
	@Test
	public void testEsIgual2(){
		Libro []librosLeidos = {libro2,libro2,libro1,libro1,libro1,libro1,libro1,libro1,libro1,libro2};
		//Vamos a llenar el histórico de lectura del lector 2
		//Este lector tiene en este punto dos veces leido el libro1
		for (int i=0; i<librosLeidos.length-3;i++){
				lector2.leerLibro(librosLeidos[i]);
				lector.leerLibro(new Libro (librosLeidos[i]));
			}
		lector.leerLibro(libro1);
		lector2.leerLibro(new Libro(libro2));
		
		assertFalse ("Dos lectores son iguales si tienen el mismo número de socio y han leído los mismos libros y en el mismo orden. "
				+ "Verifique que se están comprobando correctamente los historiales de lectura",
				lector.equals(lector2));
		assertFalse ("Dos lectores son iguales si tienen el mismo número de socio y han leído los mismos libros y en el mismo orden. "
				+ "Verifique que se están comprobando correctamente los historiales de lectura",
				lector2.equals(lector));
		
	}
	
	/**
	 * Test method for {@link ee1.Lector#esIgual(ee1.Lector)}.
	 * Se comprueba la igualdad cuando los dos lectores han leído el mismo número de libros y en el mismo orden
	 * sin llenar el histórico
	 */
	@Test
	public void testEsIgual3(){
		Libro []librosLeidos = {libro2,libro2,libro1,libro1,libro1,libro1,libro1,libro1,libro1,libro2};
		//Vamos a llenar el histórico de lectura del lector 2
		//Este lector tiene en este punto dos veces leido el libro1
		for (int i=0; i<librosLeidos.length-3;i++){
				lector2.leerLibro(librosLeidos[i]);
				lector.leerLibro(new Libro (librosLeidos[i]));
			}
		
		assertTrue ("Dos lectores son iguales si tienen el mismo número de socio y han leído los mismos libros y en el mismo orden. "
				+ "Verifique que se están comprobando correctamente los historiales de lectura",
				lector.equals(lector2));
	}

	/**
	 * Test method for {@link ee1.Lector#leerLibro(ee1.Libro)}.
	 * Se comprueba que leerLibro actualiza correctamente el estado del lector y del libro que se ha leio
	 */
	@Test
	public void testLeerLibro() {
		lector.leerLibro(libro1);
		assertTrue("Después de que el lector lee un libro se debe incrementar en una unidad las veces que ha sido prestado el libro. "
				+ "Primero verifique que libor ha pasado todas las pruebas de TestLibro, si los pasa, "
				+ "verifique que el método leerLibro le pide a libro que actualice el número de veces que ha sido prestado.",
				libro1.getNVecesPrestado() == 1);
		//Ahora se comprueba que el primer libro del histórico sea el libro dado
		Libro libro = lector.getLibroLeido(1);
		assertTrue ("El primer libro que se lee debe estar en la primera posición del histórico hasta que el histórico se llene",
				libro != null && libro.equals(libro1));

	}

	
	/**
	 * Test method for {@link ee1.Lector#getLibroLeido(int)}.
	 * Este método prueba getLibro cuando la posición del histórico que se pide supera el número de elementos que hay en el histórico
	 */
	@Test
	public void testGetLibroLeido0() {
		//Se va a probar que se retorne null cuando se pide un libro fuera del rango
		Libro leido = lector.getLibroLeido(1); //Ahora está vacío
		assertTrue ("Si no se ha leído libro alguno se debe retornar null", leido == null);
		lector.leerLibro(libro1);
		lector.leerLibro(libro2);
		leido = lector.getLibroLeido(3); //Ahora está vacío
		assertTrue ("Si sólo se han leido dos libros y se pide el tercero, se debe retornar null", leido == null);
		leido = lector.getLibroLeido(11); //Ahora está vacío
		assertTrue ("Si se pide un valor fuera de rango se debe retornar null", leido == null);
	}
	
	/**
	 * Test method for {@link ee1.Lector#getLibroLeido(int)}.
	 * Esta prueba coniste en que el lector lee dos libros y se verifica que los libros están correctamente hubicados en el histórico
	 */
	@Test
	public void testGetLibroLeido() {
		lector.leerLibro(libro2);
		lector.leerLibro(libro1);
		Libro leido1 = lector.getLibroLeido(1);
		Libro leido2 = lector.getLibroLeido(2);
		assertTrue ("getLibroLeido sólo retorna null cuando el número de libros leidos en el historial es menor al que se pide",
				leido1 !=null && leido2 !=null);
		assertTrue("Tras leer dos libros estos deberían aparecer en el histórico en el orden en que se han leido", 
				leido1.equals(libro2) && leido2.equals(libro1));
	}
	
	/**
	 * Test method for {@link ee1.Lector#GetLibroLiedo(int)}.
	 * Este test prueba a llenar la capacidad del histórico
	 */
	@Test
	public void testGetLibroLeido2() {
		Libro []librosLeidos = {libro2,libro2,libro1,libro1,libro1,libro1,libro1,libro1,libro1,libro2};
		//Vamos a llenar el histórico de lectura del lector 2
		//Este lector tiene en este punto dos veces leido el libro1
		for (int i=0; i<librosLeidos.length;i++){
			lector2.leerLibro(librosLeidos[i]);
		}
		
		//Recorremos el historial y verificamos
		for (int i =1; i <= librosLeidos.length;i++){
			Libro leido = lector2.getLibroLeido(i);
			assertTrue ("El libro de la posición del historial "+i+" no es el que debiera ser", 
					leido != null && leido.equals(librosLeidos[i-1]));
		}
	}

	/**
	 * Test method for {@link ee1.Lector#GetLibroLiedo(int)}.
	 * Este test prueba a llenar la capacidad del histórico e introducir dos libros más obligando a olvidar los dos primeros
	 */
	@Test
	public void testGetLibroLeido3() {
		Libro []librosLeidos = {libro2,libro2,libro1,libro1,libro1,libro1,libro1,libro1,libro1,libro2,libro1,libro2};
		//Vamos a llenar el histórico de lectura del lector 2
		//Este lector tiene en este punto dos veces leido el libro1
		for (int i=0; i<librosLeidos.length;i++){
			lector2.leerLibro(librosLeidos[i]);
		}
		
		//Recorremos el historial y verificamos
		for (int i =3; i <= librosLeidos.length;i++){
			Libro leido = lector2.getLibroLeido(i-2);
			assertTrue ("El libro de la posición del historial "+(i-2)+" no es el que debiera ser", 
					leido != null && leido.equals(librosLeidos[i-3]));
		}
	}
	
	/**
	 * Test method for {@link ee1.Lector#GetLibroLiedo(int)}.
	 * Esta prueba coniste en solicitar el elemento de la posición 0 del histórico. Dado que la posición 0 no es válida se debería
	 * retornar null
	 */
	@Test
	public void testGetLibroLeido4() {
		//Se va a probar que se retorne null cuando se pide un libro fuera del rango
		Libro leido = lector.getLibroLeido(0); //Ahora está vacío
		assertTrue ("Si no se ha leído libro alguno se debe retornar null", leido == null);
	}
	
	/**
	 * Test method for {@link ee1.Lector#getNSocio()}.
	 */
	@Test
	public void testGetnSocio() {
		assertTrue ("getNSocio no retorna el número de socio esperado, verifique el constructor y getNSocio", lector.getNSocio() == 300);
	}

	/**
	 * Test method for {@link ee1.Lector#getNombre()}.
	 */
	@Test
	public void testGetNombre() {
		assertEquals("getNombre no retorna el nombre que se pasó en el constructor revise el constructor y getNombre",
				lector.getNombre(),"Pepe White");
	}
	
	//Definición de métodos auxiliares
	private void testAtributo (Class<?> className, Atributo atributo) {
		Field field=null;
		try {
			field = className.getDeclaredField(atributo.getNombre());
			
			if (!atributo.getTipoConvertido().equals(field.getType().getName())) {
				fail("Se ha encontrado el atributo " + atributo.getNombre() + " pero no coincide el tipo esperado (" + atributo.getTipo()+")"); 
			}
			
		} catch (IllegalArgumentException | NoSuchFieldException | SecurityException e) {
			fail("No se ha podido acceder al atributo " + atributo + " en la clase " + className);
		}
	}
	
	//Definición de clases auxiliares para verificar que los atributos estén bien definidos
	//Eso se basa en la técnica de reflexión que permite saber como está formado una clase sin tener su código fuente
	static class Miembro {
		private String nombre;
		
		public Miembro (String nombre) {
			this.nombre= nombre; 
		}
		
		public String getNombre () {
			return nombre; 
		}
	}
	
	static class Atributo extends Miembro {
		
		private String tipo;

		public Atributo(String nombre, String tipo) {
			super(nombre);
			this.tipo = tipo;
		}
		
		public String getTipo () {
			return tipo; 
		}
		
		public String getTipoConvertido() {
			return convertirTipo(this.tipo);
		} 

		@Override
		public String toString() {
			return "<" + tipo + " " + getNombre() + ">";   
		}
	}
	
	private static String convertirTipo (String tipo) {
		if (tipo.endsWith("[]")){
			return "[" + getTipo(tipo.substring(0,tipo.length()-2)); 
		}
		else {
			return tipo;
		}
	}
	
	private static String getTipo (String tipo) {
		if (tipo.equals("int")) return "I"; 
		else if (tipo.equals("long")) return "J"; 
		else if (tipo.equals("float")) return "F"; 
		else if (tipo.equals("double")) return "D"; 
		else if (tipo.equals("char")) return "C"; 
		else if (tipo.equals("byte")) return "B"; 
		else if (tipo.equals("void")) return "V";
		else return "L" + tipo + ";"; 
		
	}

}
