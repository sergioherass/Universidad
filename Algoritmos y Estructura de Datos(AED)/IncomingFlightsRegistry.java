package aed.airport;


import es.upm.aedlib.Entry;
import es.upm.aedlib.Pair;
import es.upm.aedlib.priorityqueue.*;
import es.upm.aedlib.map.*;
import es.upm.aedlib.positionlist.*;


/**
 * A registry which organizes information on airplane arrivals.
 */
public class IncomingFlightsRegistry {
	
	Map<String, Long> map; 
	PriorityQueue<Long, String> heap;

	/**
	 * Constructs an class instance.
	 */
	public IncomingFlightsRegistry() {
		
		heap = new HeapPriorityQueue<Long,String>();
		map = new HashTableMap<String,Long>();
		
	}

	/**
	 * A flight is predicted to arrive at an arrival time (in seconds).
	 */
	public void arrivesAt(String flight, long time) {
		Long oldTime = map.put(flight,time);

		if(oldTime!=null) { //remplazo el valor de un avion registrado
			for(Entry<Long,String> entry: heap) { //recorro las entradas
				if(flight.equals(entry.getValue())) {
					heap.replaceKey(entry,time);
					break; 
				}
			}
		}

		else 
			heap.enqueue(time,flight); //si el avion no esta registrado entonces lo anado
	}

	/**
	 * A flight has been diverted, i.e., will not arrive at the airport.
	 */
	public void flightDiverted(String flight) {
		if(map.remove(flight) != null) { //si no se puede eliminar
			for(Entry<Long, String> entry: heap) { //recorro entradas
				if(flight.equals(entry.getValue())) { //si se cumple, elimino la entrada
					heap.remove(entry);
					break;
				}
			}
		}
	}

	/**
	 * Returns the arrival time of the flight.
	 * @return the arrival time for the flight, or null if the flight is not predicted
	 * to arrive.
	 */
	public Long arrivalTime(String flight) {
		return map.get(flight);
	}

	/**
	 * Returns a list of "soon" arriving flights, i.e., if any 
	 * is predicted to arrive at the airport within nowTime+180
	 * then adds the predicted earliest arriving flight to the list to return, 
	 * and removes it from the registry.
	 * Moreover, also adds to the returned list, in order of arrival time, 
	 * any other flights arriving withinfirstArrivalTime+120; these flights are 
	 * also removed from the queue of incoming flights.
	 * @return a list of soon arriving flights.
	 */
	public PositionList<FlightArrival> arriving(long nowTime) {
		PositionList<FlightArrival> res = new NodePositionList<FlightArrival>(); //declaro la lista a devolver
		Entry<Long,String> entry;
		nowTime += 180;
		boolean esMenor;

		if(!heap.isEmpty()) { //si esta vacio

			entry = heap.first(); //declaro la entrada al primero
			esMenor = entry.getKey()<=nowTime; 
			nowTime = entry.getKey()+120;

			while(!heap.isEmpty() && esMenor) { //si no esta vacio y esMenor == true

				res.addLast(new FlightArrival(entry.getValue(),entry.getKey())); //anado el ultimo objeto de tipo FlightArrival
				flightDiverted(entry.getValue());

				esMenor = !heap.isEmpty() && (entry = heap.first()) != null && entry.getKey()<=nowTime; //modifico el valor de esMenor al final de cada iteracion
			}
		}

		return res;
	}

}
