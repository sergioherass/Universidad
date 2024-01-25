package aed.tries;

import java.util.Iterator;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.tree.GeneralTree;
import es.upm.aedlib.tree.LinkedGeneralTree;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.positionlist.NodePositionList;


public class DictImpl implements Dictionary {
	// A boolean because we need to know if a word ends in a node or not
	GeneralTree<Pair<Character,Boolean>> tree;

	public DictImpl() {
		tree = new LinkedGeneralTree<>();
		tree.addRoot(new Pair<Character,Boolean>(null,false));
	}

	/*
	 * add("hola"); 
	 * 				(null,false)
	 * 					|- (h,false)
	 * 						|-(o,false)
	 * 							|- (l,false)
	 * 								|- (a,true)
	 *  add("hola_todos")
	 *  								|- (_, false)
	 *  									|- (a, false) 
	 *  										|- (...)
	 * 	add("hola_amigos")					|- (t,false)
	 * 											|- (o,false)
	 * 												|- (d,false)
	 * 													|- (o,false)
	 * 														|- (s,true)
	 * 
	 * 
	 * */

	// Devuelve el hijo del nodo pos que contiene el caracter ch
	private Position<Pair<Character,Boolean>> searchChildLabelledBy(char ch,
			Position<Pair<Character,Boolean>> pos) {
		
		Position<Pair<Character,Boolean>> result = null;
		Iterator<Position<Pair<Character,Boolean>>> it = tree.children(pos).iterator();
		while(it.hasNext() && result==null) {
			result = it.next();
			if(result.element().getLeft()!=ch)
				result = null;
		}
		
		return result;	
	}

	// Devuelve el nodo cuyo camino desde la raiz contiene
	// la palabra prefix. Si no existe, el metodo devuelve null
	private Position<Pair<Character,Boolean>> findPos(String prefix) {
		
		Position<Pair<Character,Boolean>> pos = tree.root();
		for(int i=0; i<prefix.length() && pos!=null; i++) {
			pos = searchChildLabelledBy(prefix.charAt(i),pos);
		}
		
		return pos;

	}

	// Anade un hijo al nodo pos conteniendo el elemento pair,
	// respetando el orden alfabetico de los hijos
	private Position<Pair<Character,Boolean>> addChildAlphabetically(Pair<Character,Boolean> pair,
			Position<Pair<Character,Boolean>> pos) {
		
		Iterator<Position<Pair<Character,Boolean>>> it = tree.children(pos).iterator();
		Position<Pair<Character,Boolean>> elem, result = null;
		
		while(it.hasNext() && result==null) {
			elem = it.next();
			if(pair.getLeft()<elem.element().getLeft()) 
				result = tree.insertSiblingBefore(elem,pair);
			else if(pair.getLeft()==elem.element().getLeft()) {
				result = elem;
			}	
		}
		
		if(result==null)
			result = tree.addChildLast(pos, pair);
		
		return result;
	}

	public void add(String word) { 
		if(word==null || word.length()==0)
			throw new IllegalArgumentException();
		
		Position<Pair<Character,Boolean>> pos = findPos(word);
		if(pos!=null)
			pos.element().setRight(true);
		
		else {
			pos = tree.root();
			Pair<Character,Boolean> par;
			for(int i=0; i<word.length(); i++) {
				if(i==word.length()-1)
					par = new Pair<Character,Boolean>(word.charAt(i),true);
				else
					par = new Pair<Character,Boolean>(word.charAt(i),false);
				pos = addChildAlphabetically(par, pos);
			}
		}
		
	}

	public void delete(String word) { 
		if(word==null || word.length()==0)
			throw new IllegalArgumentException();
		
		Position<Pair<Character,Boolean>> pos = findPos(word);
		if(pos!=null)
			pos.element().setRight(false);
	}

	public boolean isIncluded(String word) { 
		if(word==null || word.length()==0)
			throw new IllegalArgumentException();
		
		Position<Pair<Character,Boolean>> pos = findPos(word);
		
		return pos!=null && pos.element().getRight(); 
	}

	private void preorden(Position<Pair<Character,Boolean>> pos, 
			PositionList<String> result, String word, boolean first) {
		
		// Hacemos cosas con el nodo
		if(pos.element().getLeft()!=null) {
			if(!first)
				word += pos.element().getLeft();
			if(pos.element().getRight())
				result.addLast(word);
		}
		
		// Recorremos el resto de nodos
		for(Position<Pair<Character,Boolean>> p: tree.children(pos)) {
			preorden(p,result, word, false);
		}
	}
	
	public PositionList<String> wordsBeginningWithPrefix(String prefix) { 
		if(prefix==null)
			throw new IllegalArgumentException();
		
		PositionList<String> result = new NodePositionList<String>();
		Position<Pair<Character,Boolean>> pos = findPos(prefix);
		
		if(pos!=null) 
			preorden(pos,result,prefix,true);
		
		return result; 
	}

}
