package clase11.lista;

public class ListaOpsTest
{
  static ILista<Integer> lista0;
  static ILista<Integer> lista1;
  static ILista<Integer> lista2;
  static ILista<Integer> lista3;
  static ILista<Integer> lista33;
  static ILista<Integer> lista32;
  
  public static void resetear ()
  {
    lista0 = new Lista<Integer>();
    lista1 = new Lista<Integer>();
    lista2 = new Lista<Integer>();
    lista3 = new Lista<Integer>();
    lista33 = new Lista<Integer>();
    lista32 = new Lista<Integer>();
  } 
  public static void mostrar ()
  {
    System.out.println("lista0 = " + lista0);
    System.out.println("lista1 = " + lista1);
    System.out.println("lista2 = " + lista2);
    System.out.println("lista3 = " + lista3);
  } 
  public static void cargar ()
  {
    lista1 = lista1.cons(1);
    lista2 = lista2.cons(2).cons(20);
    lista3 = lista3.cons(3).cons(30).cons(300);
  }
  public static boolean prueba_suma ()
  {
    resetear();
    cargar();
    IListaOps<Integer> listaOps = new ListaOps<Integer>();
    boolean resultado =
      listaOps.suma(lista0) == 0 &&
      listaOps.suma(lista1) == 1 &&
      listaOps.suma(lista2) == 22 &&
      listaOps.suma(lista3) == 333;
    return resultado; 
  }
  public static boolean prueba_posicion ()
  {
    resetear();  
    cargar();
    IListaOps<Integer> listaOps = new ListaOps<Integer>();
    boolean resultado =
      listaOps.posicion(10, lista0) == -1 &&
      listaOps.posicion(10, lista1) == -1 &&
      listaOps.posicion(20, lista2) == 0 &&
      listaOps.posicion(3, lista3) == 2;
    return resultado; 
  }
  public static boolean prueba_esIgual ()
  {
    resetear();  
    cargar();
    lista33 = lista33.cons(3).cons(30).cons(300);
    lista32 = lista32.cons(30).cons(300);
    System.out.println(" lista33 = " + lista33);
    System.out.println(" lista32 = " + lista32);
    IListaOps<Integer> listaOps = new ListaOps<Integer>();
    boolean resultado =
      listaOps.esIgual(lista0,listaOps.clon(lista0)) == true &&
      listaOps.esIgual(lista1,lista2) == false &&
      listaOps.esIgual(lista3,lista33) == true &&
      listaOps.esIgual(lista3,lista32) == false;
    return resultado; 
  } 
  public static void prueba_addLast ()
  {
    resetear();
    IListaOps<Integer> listaOps = new ListaOps<Integer>();
    listaOps.addLast(11, lista1);
    listaOps.addLast(22, lista2);
    listaOps.addLast(222, lista2);
    listaOps.addLast(33, lista3);
    listaOps.addLast(333, lista3);
    listaOps.addLast(3333, lista3);
    mostrar();
  }  
  public static void prueba_clon ()
  {
    resetear();
    cargar();
    IListaOps<Integer> listaOps = new ListaOps<Integer>();
    System.out.println(listaOps.clon(lista0));
    System.out.println(listaOps.clon(lista1));
    System.out.println(listaOps.clon(lista2));
    System.out.println(listaOps.clon(lista3));
  }  
  public static void prueba_addOrd ()
  {
    resetear();
    lista1 = lista1.cons(1);
    lista2 = lista2.cons(20).cons(2);
    lista3 = lista3.cons(300).cons(30).cons(3);
    IListaOps<Integer> listaOps = new ListaOps<Integer>();
    listaOps.addOrd(11, lista1);
    listaOps.addOrd(22, lista2);
    listaOps.addOrd(222, lista2);
    listaOps.addOrd(33, lista3);
    listaOps.addOrd(333, lista3);
    listaOps.addOrd(3333, lista3);
    mostrar();
  } 
  public static void prueba_eliminar ()
  {
    resetear();
    lista1 = lista1.cons(1);
    lista2 = lista2.cons(2).cons(20);
    lista3 = lista3.cons(3).cons(30).cons(300);    
    IListaOps<Integer> listaOps = new ListaOps<Integer>();
    listaOps.eliminar(11, lista1);
    listaOps.eliminar(1, lista1);
    listaOps.eliminar(2, lista2);
    listaOps.eliminar(3, lista3);
    listaOps.eliminar(30, lista3);
    listaOps.eliminar(300, lista3);
    mostrar();
  } 
  public static void main (String[] args)
  {
    resetear();  
    cargar();
    mostrar();

    System.out.println("prueba_suma() = " + prueba_suma());

    System.out.println("prueba_posicion() = " + prueba_posicion());

    System.out.println("prueba_esIgual() = " + prueba_esIgual());
    
    System.out.println("prueba_addLast();");
    prueba_addLast();  
       
    System.out.println("prueba_clon();");
    prueba_clon();
 
    System.out.println("prueba_addOrd();");
    prueba_addOrd();
    
    System.out.println("prueba_eliminar();");
    prueba_eliminar();
    
  }
  
}
