package aed.multisets;

public class PruebaLab2 {

	public static void main(String[] args) {
		MultiSet<Integer> s = new MultiSetList<Integer>();
		s.add(0,1);	System.out.println(s);
		s.add(0,1); System.out.println(s);
		s.add(1,5); System.out.println(s);
		s.add(1,-1); 							// lanza IllegalArgumentException
		System.out.println(s.size()); 			// 7 → {0,0,1,1,1,1,1}
		System.out.println(s.count(0)); 		// 2 → contiene dos 0
		s.remove(0,1); System.out.println(s);	// se borra un 0
		s.remove(0,1); System.out.println(s);	// se borra un 0
		s.remove(0,1); System.out.println(s);	// lanza IllegalArgumentException
		s.remove(1,3); System.out.println(s);
		System.out.println(s.count(1));		 	// 2 → {1,1}
		s.allElements(); System.out.println(s);	// [1,1] → Una lista con los elementos 1 y 1

	}

}
