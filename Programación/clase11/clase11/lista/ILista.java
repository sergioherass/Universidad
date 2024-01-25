package clase11.lista;

import clase11.tads.Node;

/**
 * TAD Lista enlazada.
 *
 * @author Javier Galve
 */
public interface ILista<E> 
{
  /**
   * POST: Determina si esta lista est� vac�a o no.
   */
  public boolean esVacia ();
  /**
   * POST: A�ade el elemento al principio de esta lista.
   */
  public ILista<E> cons (E elemento);       
  /**
   * A�ade el elemento al principio de esta lista.
   */
  public void addFirst (E elemento);
  /**
   * POST: Devuelve el n�mero de elementos de esta lista.
   */
  public int size ();
  /**
   * Visualizador de la lista de enteros.
   */
  public String toString ();
  /**
   * Devuelve el principio de esta lista enlazada.
   */
  public Node<E> inicio ();
 /**
   * Pone inicio como principio de la lista enlazada.
   */
  public void setInicio (Node<E> inicio);
  /**
   * A�ade el elemento al final de esta lista.
   */
  public void addLast (E elemento);
  /**
   * Devuelve el primer elemento de la lista.
   */
  public E primero (); 
  /**
   * Devuelve el �ltimo elemento de la lista.
   */
  public E ultimo ();
}
