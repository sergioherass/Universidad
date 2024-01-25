package aed.individual4;

import java.util.Iterator;
import java.util.NoSuchElementException;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.PositionList;

public class MultiSetListIterator<E> implements Iterator<E> {
	PositionList<Pair<E,Integer>> list;

	Position<Pair<E,Integer>> cursor;
	int contador;
	Position<Pair<E,Integer>> prevCursor;

	public MultiSetListIterator(PositionList<Pair<E,Integer>> list) {
		this.list = list;

		cursor=null;
		prevCursor=null;
		contador=0;
	}

	public boolean hasNext() {
		return !(list.isEmpty() || (cursor==null && prevCursor!=null) || 
				(cursor!=null && contador==cursor.element().getRight() && list.next(cursor)==null));

	}

	public E next() {
		if(list.isEmpty() || (cursor==null && contador!=0))
			throw new NoSuchElementException();
		
		// Para el método remove()
		prevCursor = cursor;
		
		if(cursor==null && contador==0) { // No he entrado nunca
			cursor = list.first();
			return next();
		} else {
			if(contador==cursor.element().getRight()) {
				cursor = list.next(cursor);
				if(cursor != null) 	// Si hay siguiente, pongo el contador a 0
					contador = 0;	// en caso contrario lo dejo para saber que he llegado al final
				return next();
			} else {
				contador++;
				return cursor.element().getLeft();
			}
		}
	}
	
	// list = [(10,2),(20,3),(30,1)];
	// next() -> prevCursor = null, cursor = (10,2), counter = 0 -> 
	// 		  -> prevCursor = (10,2), counter = 1, retorna 10 (el elemento)
	
	// remove() -> prevCursor = (10,2), counter = 1, cursor = (10,2) -> 
	// 			-> prevCursor = (10,1), counter = 0, prevCursor = null
	
	// next() -> prevCursor = (10,1), cursor = (10,1), counter = 0 -> counter = 1, retorna 10 (el elemento)
	
	// remove() -> prevCursor = (10,1), cursor = (10,1), counter = 1 
	// 			-> cursor = (20,3), counter = 0, prevCursor = null
	
	// next() -> prevCursor = (20,3), cursor = (20,3), counter = 0 -> counter = 1, retorna 20 (el elemento)
	
	// next() -> prevCursor = (20,3), cursor = (20,3), counter = 1 -> counter = 2, retorna 20 (el elemento)
	
	// next() -> prevCursor = (20,3), cursor = (20,3), counter = 2 -> counter = 3, retorna 20 (el elemento)
	
	// next() -> prevCursor = (20,3), cursor = (20,3), counter = 3 -> 
	//		  -> cursor = (30,1), counter = 0 -> prevCursor = (30,1), counter = 1, retorna 30 (el elemento)
	
	// next() -> prevCursor = (30,1), cursor = (30,1), counter = 1 -> cursor = null, counter = 1 -> excepcion
	
	// remove() -> prevCursor = (30,1), cursor = null, counter = 1 
	//			-> cursor = null, prevCursor = null, counter = 0

	public void remove() {
		if(prevCursor==null)
			throw new IllegalStateException();
		else {
			if(prevCursor.element().getRight()==1) { // me le quiero cargar de la lista (eliminarle)
				cursor = list.next(cursor);
				list.remove(prevCursor);
			} else { // quiero decrementar en uno su parte derecha
				prevCursor.element().setRight(prevCursor.element().getRight()-1);
			}
			contador--;
			prevCursor = null; // no se permiten dos llamadas seguidas a remove()
		}
	}
}





