package clase4;

import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;

public class PruebaClase4 {
	
	public static void main(String []args) {
		
		PositionList<Integer> l1 = new NodePositionList<Integer>();
		l1.addLast(1);
		l1.addLast(2);
		l1.addLast(3);
		l1.addLast(null);
		l1.addLast(5);
		l1.addLast(10);
		
		//System.out.println("USANDO UN BUCLE WHILE: " + Clase4.sumaElementsWhile(l1));
		// System.out.println("USANDO UN BUCLE FOR: " + Clase4.sumaElementsFor(l1));
		// System.out.println("USANDO UN BUCLE FOR-EACH: " + Clase4.sumaElementsForeach(l1));
		
		PositionList<Integer> l3 = new NodePositionList<Integer>();
		l3.addLast(1);
		l3.addLast(2);
		l3.addLast(3);
		l3.addLast(null);
		l3.addLast(5);
		l3.addLast(10);
		
		System.out.println(Clase4.sonIguales(l1,l3));
		
		IndexedList<Integer> l2 = new ArrayIndexedList<Integer>();
		l2.add(0,1);
		l2.add(1,2);
		l2.add(2,3);
		l2.add(3,4);
		l2.add(4,-5);
		
		l1 = new NodePositionList<Integer>();
		l1.addLast(1);
		l1.addLast(-5);
		
		System.out.println("Los elementos de l1 están en l2 -> " + Clase4.memberAlls(l1,l2));
		System.out.println("Los elementos de l2 están en l1 -> " + Clase4.memberAlls(l2,l1));
		System.out.println("Los elementos de l1 están en l1 ->" + Clase4.memberAlls(l1,l1));
		System.out.println("Los elementos de l2 están en l2 ->" + Clase4.memberAlls(l2,l2));
		// System.out.println("Los elementos de l1 están en null -> " + Clase4.memberAlls(l1,null));
		// System.out.println("Los elementos de null están en l1 -> " + Clase4.memberAlls(null,l1));
		
		System.out.println("USANDO UN BUCLE WHILE: " + Clase4.sumaElementsWhile(l2));
		System.out.println("USANDO UN BUCLE FOR: " + Clase4.sumaElementsFor(l2));
		System.out.println("USANDO UN BUCLE FOR-EACH: " + Clase4.sumaElementsForeach(l2));
		
		System.out.println("4 está en la lista [1, 2, 3, 4, -5] -> " + Clase4.member(4,l2));
		System.out.println("-5 está en la lista [1, 2, 3, 4, -5] -> " + Clase4.member(-5,l2));
		System.out.println("null está en la lista [1, 2, 3, 4, -5] -> " + Clase4.member(null,l2));
		System.out.println("4 está en la lista null -> " + Clase4.member(4,null));
		System.out.println("0 está en la lista [1, 2, 3, 4, -5] -> " + Clase4.member(0,l2));
		
		l2 = new ArrayIndexedList<Integer>();
		
		System.out.println("USANDO UN BUCLE WHILE: " + Clase4.sumaElementsWhile(l2));
		System.out.println("USANDO UN BUCLE FOR: " + Clase4.sumaElementsFor(l2));
		System.out.println("USANDO UN BUCLE FOR-EACH: " + Clase4.sumaElementsForeach(l2));
		
		l2 = null;
		
		// System.out.println("USANDO UN BUCLE WHILE: " + Clase4.sumaElementsWhile(l2));
		// System.out.println("USANDO UN BUCLE FOR: " + Clase4.sumaElementsFor(l2));
		// System.out.println("USANDO UN BUCLE FOR-EACH: " + Clase4.sumaElementsForeach(l2));
		
		
		
		
	}

}
