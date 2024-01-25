package clase4;

import java.util.Iterator;

import es.upm.aedlib.positionlist.PositionList;

public class Clase4 {

	public static int sumaElementsWhile (Iterable<Integer> tad) {
		if(tad == null)
			throw new IllegalArgumentException();

		int suma = 0;

		Iterator<Integer> it = tad.iterator();
		while(it.hasNext()) {
			suma += it.next();
		}


		return suma;
	}

	public static int sumaElementsFor (Iterable<Integer> tad) {
		if(tad==null)
			throw new IllegalArgumentException();

		int suma = 0;

		for(Iterator<Integer> it = tad.iterator(); it.hasNext(); ) {
			suma += it.next();
		}

		return suma;
	}

	public static int sumaElementsForeach (Iterable<Integer> tad) {
		if(tad==null)
			throw new IllegalArgumentException();

		int suma = 0;

		for(int n: tad)
			suma += n;

		return suma;
	}

	public static <E> boolean member(E elem, Iterable<E> tad) {
		boolean esMiembro = false;

		if(tad!=null && elem!=null) {
			Iterator<E> it = tad.iterator();
			while(it.hasNext() && !esMiembro) {
				esMiembro = elem.equals(it.next());
				/*La forma de arriba es equivalente a hacer esto otro:
				if(elem.equals(it.next()))
					esMiembro = true; */
			}
		}

		return esMiembro;
	}

	/* Suponemos que ni l1 ni l2 pueden ser null */
	public static <E> boolean memberAlls(Iterable<E> l1, Iterable<E> l2) {
		if(l1 == null || l2 == null)
			throw new IllegalArgumentException();

		boolean estanTodos = true;

		if(l1 != l2) {
			Iterator<E> it = l1.iterator();
			while(it.hasNext() && estanTodos) {
				estanTodos = member(it.next(),l2);
				/* La forma de arriba es equivalente a hacer esto otro:
				if(!member(it.next(),l2))
					estanTodos = false; */
			}
		}

		return estanTodos;
	}

	public static <E> boolean sonIguales(PositionList<E> l1, PositionList<E> l2) {
		boolean iguales = true;

		if(l1.size() != l2.size())
			iguales = false;
		else {
			Iterator<E> it1 = l1.iterator();
			Iterator<E> it2 = l2.iterator();
			E e1, e2;
			while(it1.hasNext()) {
				e1 = it1.next();
				e2 = it2.next();
				iguales = (e1==null && e2==null) || (e1!=null && e1.equals(e2));

			}
		}

		return iguales;
	}

}
