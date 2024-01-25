package aed.filter;

import java.util.Iterator;
import java.util.function.Predicate;

import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;


public class Utils {

	// USANDO INDEXEDLIST
	public static <E> Iterable<E> filter(Iterable<E> d, Predicate<E> pred) {
		if(d==null)
			throw new IllegalArgumentException();

		IndexedList<E> r = new ArrayIndexedList<E>();
		Iterator<E> it = d.iterator();
		E e; int pos = 0;
		
		while(it.hasNext()) {
			e = it.next();
			if(e != null && pred.test(e)) 
				r.add(pos++, e);
				// Otra opcion es hacer 
				// r.add(pos, e);
				// pos++;
		}
		
		return r;
	}

	// USANDO POSITIONLIST
	public static <E> Iterable<E> filter2(Iterable<E> d, Predicate<E> pred) {
		if(d==null)
			throw new IllegalArgumentException();

		PositionList<E> r = new NodePositionList<E>();
		Iterator<E> it = d.iterator();
		E e;
		
		while(it.hasNext()) {
			e = it.next();
			if(e != null && pred.test(e)) 
				r.addLast(e);
		}
		
		return r;
	}


}

