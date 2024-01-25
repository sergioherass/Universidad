package aed.airport;

public class PruebaLab6 {
	
	public static void main(String args[]) {
		
		IncomingFlightsRegistry aeropuerto = new IncomingFlightsRegistry();
		
		// Vuelo "IBE123" aterriza en la hora 1000
		aeropuerto.arrivesAt("IBE123",1000);
		aeropuerto.arrivalTime("IBE123"); // => 1000
		
		// No hay info sobre KLM123
		aeropuerto.arrivalTime("KLM123"); 	// => null
		
		// Vuelo "IBE123" aterriza en la hora 1200 (retraso)
		aeropuerto.arrivesAt("IBE123",1200);
		aeropuerto.arrivalTime("IBE123"); 	// => 1200
		
		// Vuelo "IBE123" mandado a otro aeropuerto. Despues no se saba nada de "IBE123"
		aeropuerto.flightDiverted("IBE123");
		aeropuerto.arrivalTime("IBE123"); 	// => null
		
		// Vuelo "IBE345" aterriza en la hora 1000, y KLM111 1400.
		aeropuerto.arrivesAt("IBE345",1000);
		aeropuerto.arrivesAt("KLM111",1400);
		
		// Vuelo "IBE789" aterriza en la hora 10500
		aeropuerto.arrivesAt("IBE789",1050);					// 	   [FlightArrival("IBE345",1000),
																// 		FlightArrival("IBE789",1050),
																//		FlightArrival("KLM111,1400)]
		
		// Ningun vuelo llega en el interval 400..580
		aeropuerto.arriving(400); 			// => []
		
		// Cuando la hora es 990, hay un avion IBE345 que aterriza proximamente.
		// Y hay otro avion IBE789 en conflicto.
		aeropuerto.arriving(990); 			// => [FlightArrival("IBE345",1000),
											// 		FlightArrival("IBE789",1050)])
		
		// Despues de llamar a arriving Solo queda KLM111:
		aeropuerto.arrivalTime("IBE345"); 	// => null
		aeropuerto.arrivalTime("IBE789"); 	// => null
		aeropuerto.arrivalTime("KLM111"); 	// => 1400
	}

}
