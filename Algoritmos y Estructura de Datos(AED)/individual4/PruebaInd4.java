package aed.individual4;

import java.util.Iterator;

import es.upm.aedlib.Pair;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;

public class PruebaInd4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PositionList<Pair<Character,Integer>> list = new NodePositionList<Pair<Character,Integer>>();
		list.addLast(new Pair<>('a',2));
		list.addLast(new Pair<>('b',4));
		list.addLast(new Pair<>('c',1));
		System.out.println(list);
		
		Iterator<Pair<Character,Integer>> it = new MultiSetListIterator(list);
		System.out.println(it.next()); // -> a
		System.out.println(it.next()); // -> a
		it.remove(); // -> list = <a,1>,<b,4>,<c,5>
		System.out.println(list);
		System.out.println(it.next()); // -> b
		it.remove(); // -> list = <a,1>,<b,3>,<c,5>
		System.out.println(list);
		System.out.println(it.next()); // -> b
		it.remove();
		System.out.println(list);
		System.out.println(it.next()); // -> b
		it.remove();
		System.out.println(list);
		System.out.println(it.next()); // -> b
		it.remove();
		System.out.println(list);
		System.out.println(it.next()); // -> c
		// System.out.println(it.next()); // -> NoSuchElementException()
		it.remove();
		System.out.println(list);
		

	}

}
