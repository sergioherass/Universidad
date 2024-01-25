package aed.orderedmap;

import java.util.Comparator;

import es.upm.aedlib.Entry;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.positionlist.NodePositionList;

public class PositionListOrderedMap<K,V> implements OrderedMap<K,V> {
	private Comparator<K> cmp;
	private PositionList<Entry<K,V>> elements;

	/* Acabar de codificar el constructor */
	public PositionListOrderedMap(Comparator<K> cmp) {
		this.cmp = cmp;
		elements = new NodePositionList<Entry<K,V>>(); // anadido
	}

	/* Ejemplo de un posible método auxiliar: */

	/* If key is in the map, return the position of the corresponding
	 * entry.  Otherwise, return the position of the entry which
	 * should follow that of key.  If that entry is not in the map,
	 * return null.  Examples: assume key = 2, and l is the list of
	 * keys in the map.  For l = [], return null; for l = [1], return
	 * null; for l = [2], return a ref. to '2'; for l = [3], return a
	 * reference to [3]; for l = [0,1], return null; for l = [2,3],
	 * return a reference to '2'; for l = [1,3], return a reference to
	 * '3'. */

	private Position<Entry<K,V>> findKeyPlace(K key) {
		Position<Entry<K,V>> nodo = elements.first();
		boolean encontrado = false;
		
		while(nodo!=null && !encontrado) {
			if(key.equals(nodo.element().getKey()))
				encontrado = true;
			else
				nodo = elements.next(nodo);
		}
		
		return nodo;
	}

	/* Podéis añadir más métodos auxiliares */

	public boolean containsKey(K key) {
		if(key==null)
			throw new IllegalArgumentException();
		
		return findKeyPlace(key)!=null;
	}

	public V get(K key) {
		if(key==null)
			throw new IllegalArgumentException();
		
		Position<Entry<K,V>> position = findKeyPlace(key);
		
		return (position==null)?null:position.element().getValue();
	}

	public V put(K key, V value) {
		if(key==null)
			throw new IllegalArgumentException();
		
		V oldValue = null;
		if(elements.isEmpty())
			elements.addLast(new EntryImpl<K,V>(key,value));
		
		else {
			Position<Entry<K,V>> nodo = findKeyPlace(key);
			if(nodo!=null) {
				oldValue = nodo.element().getValue();
				elements.set(nodo, new EntryImpl<K,V>(key,value));
			} else {
				nodo = elements.first();
				boolean insertado = false;
				while(nodo!=null && !insertado) {
					if(cmp.compare(key,nodo.element().getKey())<=0) {
						insertado = true;
						elements.addBefore(nodo, new EntryImpl<K,V>(key,value));
					} else 
						nodo = elements.next(nodo);
				}
				if(!insertado)
					elements.addLast(new EntryImpl<K,V>(key,value));
			}
		}
		
		return oldValue;
	}

	public V remove(K key) {
		if(key==null)
			throw new IllegalArgumentException();
		
		Position<Entry<K,V>> nodo = findKeyPlace(key);
		V oldValue = null;
		if(nodo!=null) {
			oldValue = nodo.element().getValue();
			elements.remove(nodo);
		}
		
		return oldValue;
	}

	public int size() {
		int tam = 0;
		Position<Entry<K,V>> nodo = elements.first();
		while(nodo!=null) {
			tam++;
			nodo = elements.next(nodo);
		}
		return tam;
	}

	public boolean isEmpty() {
		return size()==0;
	}

	public Entry<K,V> floorEntry(K key) {
		return null;
	}

	public Entry<K,V> ceilingEntry(K key) {
		if(key==null)
			throw new IllegalArgumentException();
		
		Position<Entry<K,V>> nodo = elements.first();
		Entry<K,V> res = null;
		boolean encontrado = false;
		while(nodo!=null && !encontrado) {
			if(cmp.compare(key,nodo.element().getKey())<=0) {
				encontrado = true;
				res = nodo.element();
			} else 
				nodo = elements.next(nodo);
		}
			
		return res;
	}

	public Iterable<K> keys() {
		PositionList<K> res = new NodePositionList<K>();
		
		Position<Entry<K,V>> nodo = elements.first();
		while(nodo!=null) {
			res.addLast(nodo.element().getKey());
			nodo = elements.next(nodo);
		}
		
		return res;
	}

	public String toString() {
		return elements.toString();
	}


}
