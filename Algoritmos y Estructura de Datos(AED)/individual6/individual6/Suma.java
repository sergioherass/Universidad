package aed.individual6;

import es.upm.aedlib.graph.Edge;
import es.upm.aedlib.graph.Vertex;

import java.util.Iterator;

import es.upm.aedlib.graph.DirectedGraph;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.map.HashTableMap;


public class Suma {
	
	// METODOS HECHOS CON FOR-EACH
	
	public static <E> Map<Vertex<Integer>,Integer> sumVertices(DirectedGraph<Integer,E> g) {
		Map<Vertex<Integer>,Integer> map = new HashTableMap<Vertex<Integer>,Integer>();
		
		for(Vertex<Integer> v: g.vertices()) 
			map.put(v,sumVerticesAux(g,v,new HashTableMap<Vertex<Integer>,Integer>()));
		
		return map;
	}
	
	/* 
	 * Metodo auxiliar en el que calculamos la suma asociada a cada vertice en concreto
	 * del grafo
	 */
	public static <E> int sumVerticesAux(DirectedGraph<Integer,E> g, Vertex<Integer> v,
			Map<Vertex<Integer>,Integer> map) {
		int suma = 0;
		
		if(map.put(v,v.element())==null) { // si no he estudiado aun el vertice, le anyado y hago cosas
			suma = v.element();
			
			Vertex<Integer> vActual;
			for(Edge<E> e: g.outgoingEdges(v)) {
				// Contemplamos el caso de no encontrarnos con un bucle
				if(g.startVertex(e) != (vActual=g.endVertex(e))) 
					suma += sumVerticesAux(g,vActual,map);
			}
		}
		
		return suma;
	}
	
	/*
	 * METODOS HECHOS CON ITERADORES
	 * 
	 
	public static <E> Map<Vertex<Integer>,Integer> sumVertices(DirectedGraph<Integer,E> g) {
		Map<Vertex<Integer>,Integer> map = new HashTableMap<Vertex<Integer>,Integer>();
		
		Iterator<Vertex<Integer>> it = g.vertices().iterator();
		Vertex<Integer> v;
		while(it.hasNext()) {
			v = it.next();
			map.put(v,sumVerticesAux(g,v,new HashTableMap<Vertex<Integer>,Integer>()));
		}
		return map;
	}
	*/
	/* 
	 * Metodo auxiliar en el que calculamos la suma asociada a cada vertice en concreto
	 * del grafo
	 */
	/*
	public static <E> int sumVerticesAux(DirectedGraph<Integer,E> g, Vertex<Integer> v,
			Map<Vertex<Integer>,Integer> map) {
		int suma = 0;
		
		if(map.put(v,v.element())==null) { // si no he estudiado aun el vertice, le anyado y hago cosas
			suma = v.element();
			
			Iterator<Edge<E>> it = g.outgoingEdges(v).iterator();
			Edge<E> e;
			Vertex<Integer> vActual;
			
			while(it.hasNext()) {
				e = it.next();
				
				// Contemplamos el caso de no encontrarnos con un bucle
				if(g.startVertex(e) != (vActual=g.endVertex(e))) 
					suma += sumVerticesAux(g,vActual,map);
				
			}
		}
		
		return suma;
	}*/
}
