package aed.multisets;

import java.util.Iterator;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.positionlist.NodePositionList;


/**
 * An implementation of a multiset using a positionlist.
 */
public class MultiSetList<Element> implements MultiSet<Element> {

	/**
	 * Datastructure for storing the multiset.
	 */
	private PositionList<Pair<Element,Integer>> elements;

	private int size;


	/**
	 * Constructs an empty multiset.
	 */
	public MultiSetList() {
		this.elements = new NodePositionList<Pair<Element,Integer>>();
	}

	private Position<Pair<Element,Integer>> buscaNodo(Element elem){
		Position<Pair<Element,Integer>> nodo = elements.first();
		boolean encontrado = false;
		Element e;
		while(nodo!=null && !encontrado) {
			e = nodo.element().getLeft();
			if((elem == null && e==null) || 
					(elem != null && elem.equals(e))) {
				encontrado = true;
			} else 
				nodo = elements.next(nodo);	
		}

		return nodo;
	}

	@Override
	public void add(Element elem, int n) {
		// Si n<0 -> excepcion
		if(n<0)
			throw new IllegalArgumentException();
		// Si n==0 -> no hago nada
		// Si n>0 -> inserto el elemento elem
		if(n>0) {
			Position<Pair<Element,Integer>> nodo = buscaNodo(elem);
			if(nodo == null) {
				elements.addLast(new Pair<Element,Integer>(elem,n));
			} else {
				nodo.element().setRight(nodo.element().getRight()+n);
			}
			size += n;
		}
	}


	@Override
	public void remove(Element elem, int n) {
		if(n<0)
			throw new IllegalArgumentException();
		if(n>0) {
			Position<Pair<Element,Integer>> nodo = buscaNodo(elem);
			if(nodo==null)
				throw new IllegalArgumentException();
			int numElements = nodo.element().getRight();
			if(numElements<n)
				throw new IllegalArgumentException();
			else if(numElements==n)
				elements.remove(nodo);
			else
				nodo.element().setRight(nodo.element().getRight()-n);

			size -= n;
		}
	}


	@Override
	public int count(Element elem) {
		int nVeces = 0;
		Position<Pair<Element,Integer>> nodo = buscaNodo(elem);

		if(nodo!=null)
			nVeces = nodo.element().getRight();

		return nVeces;
	}


	@Override
	public int size() {
		return size;
	}


	@Override
	public boolean isEmpty() {
		return size==0;
	}


	@Override
	public PositionList<Element> allElements() {
		Position<Pair<Element,Integer>> nodo = elements.first();
		PositionList<Element> resultado = new NodePositionList<>();
		int nVeces;
		Element e;
		while(nodo != null) {
			nVeces = nodo.element().getRight();
			e = nodo.element().getLeft();
			while(nVeces!=0) {
				resultado.addLast(e);
				nVeces--;
			}
			nodo = elements.next(nodo);
		}
		return resultado;
	}


	@Override
	public MultiSet<Element> intersection(MultiSet<Element> s) {
		MultiSet<Element> interseccion = new MultiSetList<Element>();
		Position<Pair<Element,Integer>> nodo = elements.first();
		int nVecesThis, nVecesS, min;
		while(nodo != null) {
			Element elem = nodo.element().getLeft();
			nVecesThis = count(elem);
			nVecesS = s.count(elem);
			// Operador de seleccion
			// (condicion)? valorTrue : valorFalse;
			min = (nVecesThis<nVecesS)?nVecesThis:nVecesS;
			// Intuitivamente hace esto
			// (nVecesThis<nVecesS)?(min = nVecesThis):(min = nVecesS);
			if(min>0)
				interseccion.add(elem,min);

			nodo = elements.next(nodo);
		}
		return interseccion;
	}

	private PositionList<Pair<Element,Integer>> getElements() {
		return elements;
	}
	
	
	@Override
	// Como concepto opcional tenemos que hacer:
	// intersection(s)*2 + s.minus(this) + minus(s)
	
	public MultiSet<Element> sum(MultiSet<Element> s) {
		MultiSet<Element> sum = new MultiSetList<Element>();
		// NO SE PUEDE HACER:
		// MultiSet<Element> sum = this; 	-> si modificamos sum, se modifica this
		// MultiSet<Element> sum = s; 		-> si modificamos sum, se modifica s
		Position<Pair<Element,Integer>> nodoThis = elements.first();
		while(nodoThis != null) {
			sum.add(nodoThis.element().getLeft(),nodoThis.element().getRight());
			nodoThis = elements.next(nodoThis);
		}
		
		// OPCION 1: utilizando Position<Element>
		PositionList<Element> lista = s.allElements();
		Position<Element> nodoS = lista.first();
		while(nodoS != null) {
			sum.add(nodoS.element(),1);
			nodoS = lista.next(nodoS);
		}
		
		// OPCION 2: utilizando Iterator<Element>
//		Iterator<Element> it = s.allElements().iterator();
//		while(it.hasNext())
//			sum.add(it.next(), 1);
		
		return sum;
	}

	@Override
	public MultiSet<Element> minus(MultiSet<Element> s) {
		MultiSet<Element> minus = new MultiSetList<Element>();
		Position<Pair<Element,Integer>> nodo = elements.first();
		int resta;
		while(nodo != null) {
			Element elem = nodo.element().getLeft();
			resta = count(elem) - s.count(elem);
			if(resta>0)
				minus.add(elem,resta);

			nodo = elements.next(nodo);
		}
		return minus;
	}
	
	public String toString() {
		String res = "[ ";
		Iterator<Element> it  = allElements().iterator();
		while(it.hasNext())
			res += it.next() + " ";
		return res + "]";
	}
}
