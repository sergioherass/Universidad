package clase11.lista;

public class ListaTest {

	static Lista<Integer> lista0;
	static Lista<Integer> lista1;
	static Lista<Integer> lista2;
	static Lista<Integer> lista3;

	public static void resetear () {
		lista0 = new Lista<Integer>();
		lista1 = new Lista<Integer>();
		lista2 = new Lista<Integer>();
		lista3 = new Lista<Integer>();
	} 
	public static void mostrar () {
		System.out.println(lista0);
		System.out.println(lista1);
		System.out.println(lista2);
		System.out.println(lista3);
	} 
	public static void prueba_constructor () {
		resetear();  
		lista1 = new Lista<Integer>(5, lista1); // 5, null
		lista2 = new Lista<Integer>(5, new Lista<Integer>(2, lista2)); // 5, 2, null
		lista3 = new Lista<Integer>(9, new Lista<Integer>(6, new Lista<Integer>(4, lista3))); // 9, 6, 4, null
		mostrar();
	} 
	public static void prueba_cons () {
		resetear();  
		lista1 = (Lista<Integer>) lista1.cons(5); // 5
		lista2 = (Lista<Integer>) lista2.cons(5).cons(2); // 2, 5
		lista3 = (Lista<Integer>) lista3.cons(9).cons(6).cons(4); // 4, 6, 9 
		mostrar();
	} 
	public static void prueba_consBestia () {
		resetear();  
		for (int i=0; i<=3000; i++) {
			lista3 = (Lista<Integer>) lista3.cons(i);     
		}
		System.out.println(lista3);
	} 
	static boolean prueba_esVacia () {
		resetear();  
		prueba_constructor();
		boolean resultado =
				lista0.esVacia() == true &&
				lista1.esVacia() == false &&
				lista2.esVacia() == false &&
				lista3.esVacia() == false;
		return resultado; 
	}
	public static void prueba_addFirst () {
		resetear();  
		lista1.addFirst(7);
		lista2.addFirst(9);
		lista2.addFirst(12);
		lista3.addFirst(5);
		lista3.addFirst(15);
		lista3.addFirst(25);
		mostrar();
	} 
	
	public static void prueba_addLast () {
		resetear(); 
		lista1.addLast(7);
		lista2.addLast(9);
		lista2.addLast(12);
		lista3.addLast(5);
		lista3.addLast(15);
		lista3.addLast(25);
		mostrar();
	}
	public static void main (String[] args) {
		System.out.println("Prueba constructor:\n");
		prueba_constructor(); 
		System.out.println("Prueba metodo cons:\n");
		prueba_cons(); 
		System.out.println("Prueba metodo consBestia:\n");
		prueba_consBestia(); 
		System.out.println("prueba_esVacia() = " + prueba_esVacia());
		prueba_addFirst(); 
		System.out.println("\nEl primer elemento de la lista es " + lista1.primero() + 
				"\nEl último elemento de la lista es " + lista1.ultimo()); 
		System.out.println("\nEl primer elemento de la lista es " + lista2.primero() + 
				"\nEl último elemento de la lista es " + lista2.ultimo()); 
		System.out.println("\nEl primer elemento de la lista es " + lista3.primero() + 
				"\nEl último elemento de la lista es " + lista3.ultimo()); 
		prueba_addLast();
	}

}
