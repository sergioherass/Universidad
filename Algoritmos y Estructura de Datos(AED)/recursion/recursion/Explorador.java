package aed.recursion;

import java.util.Iterator;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.*;


public class Explorador {

	public static Pair<Object,PositionList<Lugar>> explora(Lugar inicialLugar) {
		Pair<Object,PositionList<Lugar>> res = null;
		if(inicialLugar!=null) {
			PositionList<Lugar> recorrido = new NodePositionList<Lugar>();
			Object tesoro = exploraRec(inicialLugar, recorrido);
			if(tesoro != null) {
				recorreRecorrido(recorrido, recorrido.last(), 
						recorrido.prev(recorrido.last()));
				res = new Pair<Object,PositionList<Lugar>>(tesoro,recorrido);
			}
		}
		return res;
	}

	public static void recorreRecorrido(PositionList<Lugar> recorrido, 
			Position<Lugar> ultimo, Position<Lugar> anterior) {
		if(anterior!=null) {
			Lugar lN = ultimo.element().ir(PuntoCardinal.NORTE);
			Lugar lS = ultimo.element().ir(PuntoCardinal.SUR);
			Lugar lE = ultimo.element().ir(PuntoCardinal.ESTE);
			Lugar lO = ultimo.element().ir(PuntoCardinal.OESTE);
			if(anterior.element().equals(lN) || 
					anterior.element().equals(lS) ||
					anterior.element().equals(lE) ||
					anterior.element().equals(lO)) {
				recorreRecorrido(recorrido, anterior, recorrido.prev(anterior));
			} else {
				Position<Lugar> tmp = recorrido.prev(anterior);
				recorrido.remove(anterior);
				recorreRecorrido(recorrido, ultimo, tmp);
			}
		}
	}

	/*
	 * [(0,0,tiza), (1,0,tiza), (2,0,tiza), (2,1,tiza), (2,2,tiza), (3,1,tiza), (3,2,tiza), 
	 * (4,2,tiza), (4,3,tiza), (4,4,tiza), (5,4,tiza), (5,3,tiza), (5,2,tiza), (6,2,tiza), 
	 * (6,3,tiza), (6,1,tiza), (5,1,tiza), (5,0,tiza), (6,4,tiza), (3,3,tiza), (2,3,tiza), 
	 * (2,4,tiza), (2,5,tiza), (3,4,tiza), (3,5,tiza), (1,4,tiza), (1,5,tiza), (1,3,tiza), 
	 * (0,3,tiza), (0,4,tiza), (0,5)] 
	*/


	public static Object exploraRec(Lugar lugar, PositionList<Lugar> recorrido) {
		Object tesoro = null;
		if(lugar.tieneTesoro()) {
			recorrido.addLast(lugar);
			return lugar.getTesoro();
		} else {
			if(!lugar.sueloMarcadoConTiza()) {
				recorrido.addLast(lugar);
				lugar.marcaSueloConTiza();
				Iterator<Lugar> it = lugar.caminos().iterator();
				boolean encontrado = false;
				while(it.hasNext() && !encontrado) {
					tesoro = exploraRec(it.next(),recorrido);
					encontrado = tesoro != null;
				}
			} 
		}

		return tesoro;
	}
}

