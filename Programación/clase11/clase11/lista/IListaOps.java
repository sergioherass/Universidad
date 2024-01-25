package clase11.lista;
/**
 * Especificaci�n de la AF IListaOps que es un repertorio de 
 * operaciones sobre una lista enlazada gen�rica Ilista.
 *
 * @author Javier Galve
 */

public interface IListaOps<E> {
  /**
   * POST: resultado es la suma de los elementos de {@code lista}.
   */
  public int suma (ILista<Integer> lista);
  /**
   * POST: resultado es la posicion donde esta la primera aparicion de
   *       {@code elemento} en {@code lista} y es -1 si no hay ninguna aparicion.
   */
  public int posicion (E elemento, ILista<E> lista);
  /**
   * POST: Determina si dos listas son iguales.  
   */
  public boolean esIgual (ILista<E> lista1, ILista<E> lista2);
  /**
   * Inserta {@code elemento} al final de la lista.
   */
  public void addLast (E elemento, ILista<E> lista);
  /**
   * POST: resultado es la copia de la lista.
   */
  public ILista<E> clon (ILista<E> lista);
  /**
   * A�ade {@code elemento} a {@code lista} manteniendo el orden creciente.
   */    
  public void addOrd (int elemento, ILista<Integer> lista);
  /**
   * Elimina {@code elemento} de {@code lista}.
   */    
  public void eliminar (E elemento, ILista<E> lista);

}