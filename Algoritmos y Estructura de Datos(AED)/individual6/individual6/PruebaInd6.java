package aed.individual6;

import es.upm.aedlib.graph.DirectedAdjacencyListGraph;
import es.upm.aedlib.graph.DirectedGraph;
import es.upm.aedlib.graph.Vertex;

public class PruebaInd6 {
	
	public static void main(String[] args) {
		DirectedGraph<Integer,Integer> g = new DirectedAdjacencyListGraph<Integer,Integer>();
		Vertex<Integer> v0 = g.insertVertex(1);
		Vertex<Integer> v1 = g.insertVertex(2);
		Vertex<Integer> v2 = g.insertVertex(3);
		Vertex<Integer> v3 = g.insertVertex(4);
		g.insertDirectedEdge(v0,v1,null); // Ponemos null porque no deseamos que las aristas 
		g.insertDirectedEdge(v1,v2,null); // tengan ningun peso asociado a ellas
		g.insertDirectedEdge(v2,v1,null);
		g.insertDirectedEdge(v2,v3,null);
		g.insertDirectedEdge(v0,v0,null);
		System.out.println(g + "\n");
		
		/* TRAZA: dado el siguiente grafo dirigido
		 
		   	v0(1): -->v1,-->v0
		   	v1(2): -->v2
			v2(3): -->v1,-->v3
			v3(4):
			
			Partimos de v0 y le anyadimos al mapa de salida del metodo
		    	(1) mapAux = <v0,1> -> como <v0,1> no estaba en mapAux -> suma = 1 y 
		  			estudio las aristas salientes (en este caso v0-v1 y v0-v0)
		  			Como v0!=v1 -> suma = 1 + sumVerticesAux(g,v1,mapAux) = 1 + 9 = 10
		  			Como v0==v0 -> NO LLAMA AL METODO RECURSIVO
		  			
		  		(2) mapAux = <v0,1>,<v1,2> -> como <v1,2> no estaba en mapAux -> suma = 2 y
		 			estudio las aristas salientes (en este caso v1-v2)
		 			Como v1!=v2 -> suma = 2 + sumVerticesAux(g,v2,mapAux) = 2 + 7 = 9
		 			
		 		(3) mapAux = <v0,1>,<v1,2>,<v2,3> -> como <v2,3> no estaba en mapAux -> suma = 3 y
		 			estudio las aristas salientes (en este caso v2-v1 (4) y v2-v3 (5))
		 			(4) Como v2!=v1 -> suma = 3 + sumVerticesAux(g,v1,mapAux) = 3 + 0 = 3
		 			(5) Como v2!=v3 -> suma = 3 + sumVerticesAux(g,v3,mapAux) = 3 + 4 = 7
		 			
		 		(4) mapAux = <v0,1>,<v1,2>,<v2,3> -> como <v1,2> estaba en mapAux -> suma = 0 y
		 			me salgo del metodo recursivo
		 		
		 		(5) mapAux = <v0,1>,<v1,2>,<v2,3>,<v3,4> -> como <v3,4> no estaba en mapAux -> suma = 4 y
		 			estudio las aristas salientes (en este caso ninguna) -> ¡FIN RECURSIVIDAD! 
		 */
		System.out.println(Suma.sumVertices(g));
	}

}
