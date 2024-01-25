package clase11.lista;

import clase11.tads.Node;

public class ListaOps<E> implements IListaOps<E> {

	@Override
	public int suma(ILista<Integer> lista) {
		int suma = 0;
		Node<Integer> aux = lista.inicio();
		while(aux!=null) {
			suma += aux.element;
			aux = aux.next;
		}
		return suma;
	}

	@Override
	public int posicion(E elemento, ILista<E> lista) {
		int pos = 0;
		Node<E> aux = lista.inicio();
		boolean encontrado = false;
		while(aux!=null && !encontrado) {
			if(aux.element.equals(elemento)) {
				encontrado = true;
			} else {
				pos++;
				aux = aux.next;
			}
		}
		if(!encontrado)
			pos = -1;
		return pos;
	}
	
	public int posicion2(E elemento, ILista<E> lista) {
		int pos = 0;
		Node<E> aux = lista.inicio();
		while(aux!=null) {
			if(aux.element.equals(elemento)) {
				return pos;
			} else {
				pos++;
				aux = aux.next;
			}
		}
		return -1;
	}

	@Override
	public boolean esIgual(ILista<E> lista1, ILista<E> lista2) {
		if(lista1==null && lista2 == null)						// las dos son null
			return true;
		else if(lista1!=null && lista2!=null) {					// las dos son distintas de null
			if(lista1.size()==lista2.size()) {						// las listas tienen la misma longitud
				Node<E> aux1 = lista1.inicio(), aux2 = lista2.inicio();
				while(aux1!=null) {
					if(!aux1.element.equals(aux2.element))				// si un elemento de la lista1 no coincide con el 
						return false;									// la lista2, retorno false
					aux1 = aux1.next; aux2 = aux2.next;					// en caso contrario, avanzo 
				}
			} else													// las listas no tienen la misma longitud
				return false;
			
		} else													// una es null y la otra no
			return false;
		
		return true;									// si no ha llegado a fallar en ninguno de los anteriores,
	}													// entonces las listas son iguales

	@Override
	public void addLast(E elemento, ILista<E> lista) {
		lista.addLast(elemento);
		
	}

	@Override
	public ILista<E> clon(ILista<E> lista) {
		ILista<E> copia = new Lista<E>();
		Node<E> aux = lista.inicio();
		while(aux!=null) {
			copia.addLast(aux.element);
			aux = aux.next;
		}
		return copia;
	}

	@Override
	public void addOrd(int elemento, ILista<Integer> lista) {
		if(lista==null) {
			lista = new Lista<Integer>();
			lista.addFirst(elemento);
		} else {
			if(lista.esVacia()||lista.primero()>elemento) {
				lista.addFirst(elemento);
			} else {
				boolean insertado = false; 
				Node<Integer> aux = lista.inicio();
				while(aux!=null && !insertado) {
					if(aux.element<elemento&&aux.next!=null&&aux.next.element>elemento) {
						// le meto entre los dos 
						Node<Integer> tmp = aux.next;
						aux.next = new Node<Integer>(elemento, tmp); 
						insertado = true;
					} else {
						aux = aux.next;
					}
				}
				if(!insertado)
					lista.addLast(elemento);
			}
		}
	}

	@Override
	public void eliminar(E elemento, ILista<E> lista) {
		Node<E> actual = lista.inicio(), anterior = lista.inicio();
		if(lista.primero().equals(elemento)) {
			lista.setInicio(actual.next);
		} else {
			boolean borrado = false;
			while(actual!=null && !borrado) {
				anterior = actual;
				actual = actual.next;
				if(actual!=null&&actual.element.equals(elemento)) {
					borrado=true;
					if(actual.next!=null) {
						anterior.next = actual.next;
					} else {
						anterior.next = null;
					}
				}
			}
		}
	}

}
