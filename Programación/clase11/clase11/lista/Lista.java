package clase11.lista;

import clase11.tads.Node;

public class Lista<E> implements ILista<E>{

	private Node<E> inicio;
	@SuppressWarnings("unused")
	private ILista<E> resto;

	public Lista() {
		this.inicio=null;
		this.resto=null;
	}

	public Lista(E inicio, ILista<E> resto) {
		// creo un nodo que enlace el inicio con el primer elemento de la lista resto
		if(resto!=null)
			this.inicio=new Node<E>(inicio,resto.inicio());
		else 
			this.inicio=new Node<E>(inicio,null);
		this.resto=resto;
	}

	@Override
	public boolean esVacia() {
		return inicio==null;
	}

	@Override
	public ILista<E> cons(E elemento) {
		addFirst(elemento);
		return this;
	}

	@Override
	public void addFirst(E elemento) {
		Node<E> segundoElem = inicio;
		inicio = new Node<E>(elemento,segundoElem);
	}

	@Override
	public int size() {
		int cont = 0;
		Node<E> aux = inicio;
		while(aux!=null) {
			cont++;
			aux = aux.next;
		}
		return cont;
	}

	public int size2() {
		int cont = 0;
		for(Node<E> aux=inicio;aux!=null;aux=aux.next) 
			cont++;
		return cont;
	}

	@Override
	public Node<E> inicio() {
		return inicio;
	}

	@Override
	public void setInicio(Node<E> inicio) {
		this.inicio = inicio; 

	}

	@Override
	public void addLast(E elemento) {
		if(esVacia())
			inicio=new Node<E>(elemento,null);
		else {
			Node<E> aux = inicio, anterior=null;
			while(aux!=null) {
				anterior = aux;
				aux = aux.next;
			}
			anterior.next = new Node<E>(elemento,null);
		}
	}

	@Override
	public E primero() {
		return inicio.element;
	}

	@Override
	public E ultimo() {
		if(esVacia())
			return null;
		else {
			Node<E> aux = inicio, anterior=null;
			while(aux!=null) {
				anterior = aux;
				aux = aux.next;
			}
			return anterior.element;
		}
	}

	public String toString () {
		String res = "[";
		Node<E> aux = inicio;
		while(aux!=null) {
			res += aux.element;
			aux = aux.next;
			if(aux!=null)
				res += ", ";
		}
		return res + "]";
	}

}
