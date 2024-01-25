package aed.loops;

public class PruebaInd1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] a1 = new Integer[0];
		Integer[] a2 = {1};
		Integer[] a3 = {1,4,3};
		Integer[] a4 = {1,4,3,4,4,3};
		Integer[] a5 = {1,4,4,4,3};
		Integer[] a6 = {1,4,3,4,3,4};
		Integer[] a7 = {1,1,1};
		Integer[] a8 = {1,1,1,1,2,1,3,3,1,3,3,3,3};
		Integer[] a9 = new Integer[] { new Integer(1),new Integer(3),new Integer(2),new Integer(2),new Integer(2),new Integer(2),new Integer(1),new Integer(2) };
		
		
		System.out.println(Utils.maxNumRepeated(a1,4));		// --> 0, array vacio
		System.out.println(Utils.maxNumRepeated(a2,4));		// --> 0, 4 no ocurre en [1]
		System.out.println(Utils.maxNumRepeated(a3,4));		// --> 1
		System.out.println(Utils.maxNumRepeated(a4,4));		// --> 2, debido a 4,4 en el array
		System.out.println(Utils.maxNumRepeated(a5,4));		// --> 3, debido a 4,4,4 en el array
		System.out.println(Utils.maxNumRepeated(a6,4));		// --> 1, 4 solo aparece aislado
		System.out.println(Utils.maxNumRepeated(a7,1));		// --> 3, debido a 1,1,1 en el array
		System.out.println(Utils.maxNumRepeated(a8,1));		// --> 4, debido a 1,1,1,1 en el array
		System.out.println(Utils.maxNumRepeated(a9, new Integer(2))); // --> 4, debido a 2,2,2,2 en el array
		
	}

}
