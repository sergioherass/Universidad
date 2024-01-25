package aed.orderedmap;

public class PruebaLab2 {

	public static void main(String[] args) {

		OrderedMap<String, Integer> m = new PositionListOrderedMap<String,Integer>(new StringComparator());
		
		System.out.println(m.put("Jose", 23)); 		// returns null
		System.out.println(m);						// m = [(Jose,23)]
		System.out.println(m.put("Carlos", 35)); 	// returns null
		System.out.println(m);						// m = [(Carlos,35),(Jose,23)]
		System.out.println(m.put("Miriam", 25)); 	// returns null
		System.out.println(m);						// m = [(Carlos,35),(Jose,23),(Miriam,25)]
		System.out.println(m.remove("Jose")); 		// returns 23; 
		System.out.println(m);						// m = [(Carlos,35),(Miriam,25)]
		System.out.println(m.remove("Jose")); 		// returns null; 
		System.out.println(m);						// m = [(Carlos,35),(Miriam,25)]
		System.out.println(m.put("Jose", 27)); 		// returns null
		System.out.println(m);						// m = [(Carlos,35),(Jose,27),(Miriam,25)]
		
		
		System.out.println(m.floorEntry("Carlos")); 	// returns (Carlos,35)
		System.out.println(m.floorEntry("Carolina")); 	// returns (Carlos,35)
		System.out.println(m.floorEntry("Blas")); 		// returns null
		System.out.println(m.ceilingEntry("Leo")); 		// returns (Miriam,25)
		System.out.println(m.ceilingEntry("Miriam")); 	// returns (Miriam,25)
		System.out.println(m.ceilingEntry("Nando")); 	// returns null

	}

}
