package aed.hashtable;

public class PruebaLab4 {

	public static void main(String[] args) {
		HashTable<Integer,String> map = new HashTable<Integer,String>(3);
		map.put(5, "Prats");
		map.get(5);

		map = new HashTable<Integer,String>(2);
		map.put(9, "Perez");
		map.put(2, "Bonet");
		map.put(6, "Echeberria");
		map.put(5, "Lopez");
		map.get(2);
		map.put(6, "Echeberria");
		map.remove(9);
		map.entries();
		map.isEmpty();
		map.remove(5);

	}

}
