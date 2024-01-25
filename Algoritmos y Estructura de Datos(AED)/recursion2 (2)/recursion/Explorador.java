package aed.recursion;

import java.util.Iterator;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.*;


public class Explorador {

	public static Pair<Object,PositionList<Lugar>> explora(Lugar inicialLugar) {
		Pair<Object,PositionList<Lugar>> resultado = null;
		Object tesoro = null;
		PositionList<Lugar> recorrido = new NodePositionList<Lugar>();

		if(inicialLugar!=null) {
			tesoro = exploraRec(inicialLugar,recorrido);
			if(tesoro!=null) {
				recorreRecorrido(recorrido, recorrido.prev(recorrido.last()), recorrido.last());
				resultado = new Pair<Object,PositionList<Lugar>>(tesoro,recorrido);
			}
		}

		return resultado;
	}

	public static void recorreRecorrido(PositionList<Lugar> recorrido, Position<Lugar> anterior, 
			Position<Lugar> ultimo) {
		Lugar lN = ultimo.element().ir(PuntoCardinal.NORTE);
		Lugar lS = ultimo.element().ir(PuntoCardinal.SUR);
		Lugar lE = ultimo.element().ir(PuntoCardinal.ESTE);
		Lugar lO = ultimo.element().ir(PuntoCardinal.OESTE);

		if(anterior!=null) {
			if(anterior.element().equals(lN) ||
					anterior.element().equals(lS) ||
					anterior.element().equals(lE) ||
					anterior.element().equals(lO)) {
				recorreRecorrido(recorrido,recorrido.prev(anterior),anterior);
			} else {
				Position<Lugar> tmp = recorrido.prev(anterior);
				recorrido.remove(anterior);
				recorreRecorrido(recorrido,tmp,ultimo);
			}
		}
	}

	/*
	 * [(0,0,tiza), (0,1,tiza), (0,2,tiza), (1,1,tiza), (1,2,tiza), (2,2,tiza), (1,0,tiza), (2,0,tiza), 
	 * (2,1,tiza), (3,1,tiza), (3,0,tiza), (4,0,tiza), (4,1,tiza), (4,2,tiza), (3,2)] 
	 * 
	 * which cannot be taken; cannot find a valid path between (0,2,tiza) and (1,1,tiza)
	 */

	public static Object exploraRec(Lugar lugar, PositionList<Lugar> recorrido) {
		Object tesoro = null;

		if(lugar.tieneTesoro()) {
			recorrido.addLast(lugar);
			tesoro = lugar.getTesoro();
		} 

		else {
			if(!lugar.sueloMarcadoConTiza()) {
				lugar.marcaSueloConTiza();
				recorrido.addLast(lugar);
				Iterator<Lugar> it = lugar.caminos().iterator();
				boolean encontrado = false;

				while(it.hasNext() && !encontrado) {
					tesoro = exploraRec(it.next(),recorrido);
					encontrado = tesoro!=null;
				}
			}
		}

		return tesoro;
	}
}
