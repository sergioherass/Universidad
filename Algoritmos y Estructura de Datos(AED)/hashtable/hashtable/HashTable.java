package aed.hashtable;

import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.Arrays;

import es.upm.aedlib.Entry;
import es.upm.aedlib.EntryImpl;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.InvalidKeyException;


/**
 * A hash table implementing using open addressing to handle key collisions.
 */
public class HashTable<K,V> implements Map<K,V> {
	Entry<K,V>[] buckets;
	int size; 	// es el numero de elementos que HEMOS INTRODUCIDO (con el put) en el array buckets 
	// NO LA LONGITUD DEL ARRAY buckets

	public HashTable(int initialSize) {
		this.buckets = createArray(initialSize);
		this.size = 0;
	}

	/**
	 * Add here the method necessary to implement the Map api, and
	 * any auxilliary methods you deem convient.
	 */

	// Examples of auxilliary methods: IT IS NOT REQUIRED TO IMPLEMENT THEM

	@SuppressWarnings("unchecked") 
	private Entry<K,V>[] createArray(int size) {
		Entry<K,V>[] buckets = (Entry<K,V>[]) new Entry[size];
		return buckets;
	}

	// Returns the bucket index of an object
	private int index(Object obj) {
		return obj.hashCode()%buckets.length;
	}

	/*
	 * Ejemplo, tenemos un array con 3 posiciones --> buckets = {null,null,null}
	 * map.put(5,"Esto"); --> como 5%3 = 2, entonces buckets = {null,null,<5,"Esto">}
	 * */

	// Returns the index where an entry with the key is located,
	// or if no such entry exists, the "next" bucket with no entry,
	// or if all buckets stores an entry, -1 is returned.
	private int search(Object obj) {
		int i = index(obj), firstNull = -1, res = -1;
		if(buckets[i]!=null) { // Si no es null, el elemento "puede" estar en el array

			if(!buckets[i].getKey().equals(obj)) { 	// Si es distinto, debo seguir recorriendo el array 
				// de forma circular
				for(int j = i+1; j<buckets.length && res==-1; j++) {
					if(buckets[j] == null && firstNull == -1) 	// busco el primer null por si no esta en el array y 
						firstNull = j;							// tengo que meterlo en el
					if(buckets[j]!=null && buckets[j].getKey().equals(obj)) // hemos encontrado el objeto
						res = j;
				}

				for(int j = 0; j<i && res==-1; j++) {
					if(buckets[j] == null && firstNull == -1) 	// busco el primer null por si no esta en el array y 
						firstNull = j;							// tengo que meterlo en el
					if(buckets[j]!=null && buckets[j].getKey().equals(obj)) // hemos encontrado el objeto
						res = j;
				}

				if(res == -1 && firstNull!=-1) 	// si no esta en el array pero hay hueco
					res = firstNull;			// retorno esta posicion

			}
			else // si son iguales, he encontrado la posicion en la que esta el elemento con clave obj
				res = i;

		}
		// Si es null, entonces NO esta en el array y le puedo meter en esa posicion
		else 
			res = i;

		return res;
	}

	// Doubles the size of the bucket array, and inserts all entries present
	// in the old bucket array into the new bucket array, in their correct
	// places. Remember that the index of an entry will likely change in
	// the new array, as the size of the array changes.
	private void rehash() {
		Entry<K,V> oldBuckets[] = buckets;
		buckets = createArray(buckets.length*2);

		for(Entry<K,V> entry: oldBuckets) {
			// con search obtengo la posicion en la que puedo meter el elemento
			// no vamos a obtener -1 en este caso, porque todos van a caber en algun sitio
			buckets[search(entry.getKey())] = entry; 
		}

		/*
		 * for(int i=0; i<oldBuckets.length; i++)
		 * 	buckets[search(oldBuckets[i].getKey())] = oldBuckets[i];
		 * 
		 * */
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		return entries().iterator();
	}

	@Override
	public boolean containsKey(Object arg0) throws InvalidKeyException {
		int i=search(arg0);
		return i!=-1 && buckets[i]!=null;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> res = new NodePositionList<Entry<K,V>>();

		for(Entry<K,V> entry: buckets) {
			if(entry!=null)
				res.addLast(entry);
		}

		return res;
	}

	@Override
	public V get(K arg0) throws InvalidKeyException {
		if(arg0 == null)
			throw new InvalidKeyException();
		
		int i = search(arg0);
		V oldValue = null;
		
		if(i!=-1 && buckets[i]!=null)
			oldValue = buckets[i].getValue();
		
		
		return oldValue;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> res = new NodePositionList<K>();

		for(Entry<K,V> entry: buckets) {
			if(entry!=null)
				res.addLast(entry.getKey());
		}

		return res;
	}

	@Override
	public V put(K arg0, V arg1) throws InvalidKeyException {
		if(arg0==null)
			throw new InvalidKeyException();
		
		V oldValue = null;
		int i = search(arg0);
		
		// la clave no esta -> tengo hueco, le meto y aumento en 1 el tamano de size, retorno null
		//					no tengo hueco, hacemos rehash(), le meto y aumento en 1 el tamano de size, retorno null
		// la clave ya esta -> NO AUMENTAMOS el tamano de size, retorno oldValue, sustituyo el valor por arg1
		
		if(i == -1) { // no hay hueco en buckets
			size++;
			rehash();
			i = search(arg0);
		}
		
		else {		// si que hay hueco en buckets
			if(buckets[i]==null) // esta libre
				size++;
			
			else // ya estaba la clave arg0 en buckets
				oldValue = buckets[i].getValue();
		}
		
		buckets[i] = new EntryImpl<K,V>(arg0,arg1);
		
		return oldValue;
	}

	@Override
	public V remove(K arg0) throws InvalidKeyException {
		if(arg0 == null) 
			throw new InvalidKeyException();
		
		int i = search(arg0);
		V oldValue = null;
		if(i!=-1 && buckets[i]!=null) {
			
			oldValue = buckets[i].getValue();
			buckets[i] = null;
			size--;
			
			// ALGORITMO PARA COLAPSAR HUECOS
			int index_hueco = i, start = i, index_pref;
			i = (index_hueco+1)%buckets.length;
			
			while(i!=start && buckets[i]!=null) {
				index_pref = index(buckets[i].getKey());
				if((i>=index_pref && index_pref<=index_hueco && index_hueco<=i) ||
						(index_pref > i && (index_hueco>=index_pref || index_hueco<i))) {
					buckets[index_hueco] = buckets[i];
					buckets[i] = null;
					index_hueco = i;
				}
				i = (i+1)%buckets.length;
			}
		}
		
		return oldValue;
	}

	@Override
	public int size() {
		return size;
	}

}

