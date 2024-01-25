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
		// Inicializamos elements
		elements = new NodePositionList<Entry<K,V>>();
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
		Position<Entry<K,V>> cursor = null;

		if(!elements.isEmpty()) {
			cursor = elements.first();
			boolean encontrado = false;
			while(cursor!=null && !encontrado) {
				if(cmp.compare(key,cursor.element().getKey())<=0) 
					encontrado = true;
				else 
					cursor = elements.next(cursor);
			}
		}

		return cursor;
	}

	/* Podéis añadir más métodos auxiliares */
	private void throwException(K key) {
		if(key==null)
			throw new IllegalArgumentException();
	}

	private Position<Entry<K,V>> keyPosition(K key) {
		Position<Entry<K,V>> cursor = findKeyPlace(key);

		if(cursor!=null && !key.equals(cursor.element().getKey()))
			cursor = null;

		return cursor;
	}

	public boolean containsKey(K key) {
		throwException(key);

		return keyPosition(key)!=null;
	}

	public V get(K key) {
		throwException(key);

		V oldValue = null;
		Position<Entry<K,V>> cursor = keyPosition(key);
		if(cursor!=null)
			oldValue = cursor.element().getValue();
		
		return oldValue;
	}

	public V put(K key, V value) {
		throwException(key);
		
		Position<Entry<K,V>> cursor = findKeyPlace(key);
		Entry<K,V> elem = new EntryImpl<K,V>(key, value);
		V oldValue = null;
		if(cursor == null) {
			// No le he encontrado (o la lista esta vacia) -> le anado al final
			// key = 2 y l = [] o l = [0,1] -> findKeyPlace = null
			elements.addLast(elem);
		} else {
			if(key.equals(cursor.element().getKey())){
				// El elemento ya esta -> le modifico
				// key = 2 y l = [2] o l = [1,2] o l = [1,2,3] -> findKeyPlace = ref.[2]
				oldValue = cursor.element().getValue();
				elements.set(cursor,elem);
			} else {
				// El elemento no esta -> le anado antes del cursor
				// key = 2 y l = [3] o l = [0,1,3,4] -> findKeyPlace = ref [3]
				elements.addBefore(cursor,elem);
			}
		}
		
		return oldValue;
	}

	public V remove(K key) {
		throwException(key);
		
		Position<Entry<K,V>> cursor = keyPosition(key);
		V oldValue = null;
		
		if(cursor!=null) {
			oldValue = cursor.element().getValue();
			elements.remove(cursor);
		}
		
		return oldValue;
	}

	public int size() {
		return elements.size();
	}

	public boolean isEmpty() {
		return size()==0;
	}

	public Entry<K,V> floorEntry(K key) {
		throwException(key);
		
		// Ejemplos: 
		// key = abraham y l = [carlos, jose, miriam] -> null
		// key = manuel y l = [abraham, aida, alberto, carlos, damian, jose, juan, miriam] -> juan
		
		Entry<K,V> elem = null;
		if(!elements.isEmpty()) {
			Position<Entry<K,V>> cursor = elements.last();
			while(cursor!=null && elem==null) {
				if(cmp.compare(cursor.element().getKey(),key)<=0)
					elem = cursor.element();
				else 
					cursor = elements.prev(cursor);
			}
		}
		
		return elem;
	}

	public Entry<K,V> ceilingEntry(K key) {
		throwException(key);
		
		Position<Entry<K,V>> cursor = findKeyPlace(key);
		Entry<K,V> elem = null;
		if(cursor!=null)
			elem = cursor.element();
		
		return elem;
	}

	public Iterable<K> keys() {
		PositionList<K> iterable = new NodePositionList<K>();
		
		if(!elements.isEmpty()) {
			Position<Entry<K,V>> cursor = elements.first();
			while(cursor != null) {
				iterable.addLast(cursor.element().getKey());
				cursor = elements.next(cursor);
			}
		}
			
		return iterable;
	}

	public String toString() {
		return elements.toString();
	}

}
