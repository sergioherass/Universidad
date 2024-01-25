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

	// Si ponemos primero la hora de llegada, me cargo dos posibles vuelos con la misma hora de llegada
	Map<String, Long> map; 
	// Si ponemos primero el nombre del avion, entonces no los tengo ordenados segun la hora de llegada
	PriorityQueue<Long, String> heap;

	/**
	 * Constructs an class instance.
	 */
	public IncomingFlightsRegistry() {
		map = new HashTableMap<String,Long>();
		heap = new HeapPriorityQueue<Long,String>();
	}

	/**
	 * A flight is predicted to arrive at an arrival time (in seconds).
	 */
	public void arrivesAt(String flight, long time) {
		Long oldTime = map.put(flight,time);

		if(oldTime!=null) { // Si ya esta el avion registrado, entonces reemplazo su valor
			for(Entry<Long,String> entry: heap) {
				if(flight.equals(entry.getValue())) {
					heap.replaceKey(entry,time);
					break;
				}
			}
		}

		else		// Si no esta, entonces le anyado
			heap.enqueue(time,flight);
	}

	/**
	 * A flight has been diverted, i.e., will not arrive at the airport.
	 */
	public void flightDiverted(String flight) {
		if(map.remove(flight)!=null) {
			for(Entry<Long, String> entry: heap) {
				if(flight.equals(entry.getValue())) {
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
		PositionList<FlightArrival> res = new NodePositionList<FlightArrival>();
		Entry<Long,String> entry;
		nowTime += 180;
		boolean esMenor;

		if(!heap.isEmpty()) {

			entry = heap.first();
			esMenor = entry.getKey()<=nowTime;
			nowTime = entry.getKey()+120;

			while(!heap.isEmpty() && esMenor) {

				res.addLast(new FlightArrival(entry.getValue(),entry.getKey()));
				flightDiverted(entry.getValue());

				esMenor = !heap.isEmpty() && (entry = heap.first()) != null && entry.getKey()<=nowTime;
			}
		}

		return res;
	}

}
