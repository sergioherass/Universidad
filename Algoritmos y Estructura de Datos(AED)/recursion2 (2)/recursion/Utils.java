package aed.recursion;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.indexedlist.*;
import es.upm.aedlib.positionlist.*;


public class Utils {

	public static int multiply(int a, int b) {
		int sign = (a<0)?-1:1;

		int sum = multiplyRec(a,b,0);

		return sign*sum;
	}

	public static int multiplyRec(int a, int b, int sum) {
		if(a==0)
			return sum;

		if(a%2!=0)
			sum += b;

		return multiplyRec(a/2,b*2,sum);

	}

	public static <E extends Comparable<E>> int findBottom(IndexedList<E> l) {
		int resultado = -1;

		if(l!=null && !l.isEmpty()) {
			resultado = findBottomRec(l,0,l.size()-1);
		}

		return resultado;
	}

	public static <E extends Comparable<E>> int findBottomRec(IndexedList<E> l, int starts, int ends) {

		if(starts==ends)  /* Hay un solo elemento */
			return starts;

		else if(ends-starts==1)  /* Hay dos elementos */
			return (l.get(starts).compareTo(l.get(ends))<0)?starts:ends;

		else { /* Hay 3 o mas elementos */
			int medio = (starts+ends)/2;

			if(l.get(medio).compareTo(l.get(medio-1))<=0) { /* Si es mas pequeño que su anterior */

				if(l.get(medio).compareTo(l.get(medio+1))<=0) /* Si es mas pequeño que su siguiente */
					return medio;								/* Es un hoyo */

				else /* Desprecio lo siguiente porque es mas grande */
					return findBottomRec(l,medio,ends);

			} 

			else /* Desprecio lo siguiente porque es mas grande */
				return findBottomRec(l,starts,medio);

		}

	}

	/*
	 * [1, 2, 3, 4]
	 * 
	 * [1 3 3 2 6]
	 * 
	 */

	public static <E extends Comparable<E>> NodePositionList<Pair<E,Integer>>
	joinMultiSets(NodePositionList<Pair<E,Integer>> l1,
			NodePositionList<Pair<E,Integer>> l2) {
		
		NodePositionList<Pair<E,Integer>> res = null;
		
		if(l2==null && l1!=null && !l1.isEmpty()) {
			res = new NodePositionList<Pair<E,Integer>>();
			joinRec(l1,l1.first(),l2,null,res);
		}
		
		else if(l1==null && l2!=null && !l2.isEmpty()) {
			res = new NodePositionList<Pair<E,Integer>>();
			joinRec(l1,null,l2,l2.first(),res);
		}
		
		else if(l1!=null && !l1.isEmpty() && l2!=null && !l2.isEmpty()) {
			res = new NodePositionList<Pair<E,Integer>>();
			joinRec(l1,l1.first(),l2,l2.first(),res);
		}
			
		return res;
	}
	
	public static <E extends Comparable<E>> void joinRec(NodePositionList<Pair<E,Integer>> l1, 
			Position<Pair<E,Integer>> cursor1,
			
			NodePositionList<Pair<E,Integer>> l2, 
			Position<Pair<E,Integer>> cursor2, 
			NodePositionList<Pair<E,Integer>> res) {
		
		if(cursor1!=null && cursor2!=null) { /* Ambas listas tienen elementos */
			
			Pair<E,Integer> e1 = cursor1.element(), e2 = cursor2.element();
			
			if(e1.getLeft().compareTo(e2.getLeft())==0) {  /* Son el mismo elemento */
				
				Pair<E,Integer> nuevoElemento = new Pair<E,Integer>(e1.getLeft(),
														e1.getRight()+e2.getRight());
				res.addLast(nuevoElemento);
				joinRec(l1,l1.next(cursor1),l2,l2.next(cursor2),res);
				
			} 
			
			else if(e1.getLeft().compareTo(e2.getLeft())<0) { /* El de la lista l1 es mas peque */
				res.addLast(e1);
				joinRec(l1,l1.next(cursor1),l2,cursor2,res);
			} 
			
			else {
				res.addLast(e2);
				joinRec(l1,cursor1,l2,l2.next(cursor2),res);
			}
			
			
		} else if(cursor1!=null) { /* Se ha llegado al final de la lista l2 */
			res.addLast(cursor1.element()); /* Copio lo que me queda de l1 */
			joinRec(l1,l1.next(cursor1),l2,cursor2,res);
		} 
		
		else if(cursor2!=null) { /* Se ha llegado al final de la lista l1*/
			res.addLast(cursor2.element()); /* Copio lo que me queda de l2 */
			joinRec(l1,cursor1,l2,l2.next(cursor2),res);
		}
		
	}
	
	/**public static <E> void copyRec(NodePositionList<Pair<E,Integer>> l, Position<Pair<E,Integer>> cursor,
			NodePositionList<Pair<E,Integer>> res) {
		
		if(cursor!=null) {
			res.addLast(cursor.element());
			copyRec(l,l.next(cursor),res);
		}
	}*/
}
