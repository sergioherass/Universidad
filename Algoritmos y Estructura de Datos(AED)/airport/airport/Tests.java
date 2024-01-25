package aed.airport;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import es.upm.aedlib.positionlist.*;


public class Tests {

	@Test
	public void testRegistryCancel() {
		IncomingFlightsRegistry airport = new IncomingFlightsRegistry();
		airport.arrivesAt("IBE3835",3600);
		airport.flightDiverted("IBE3835");
		// Check that there is no entry for IBE3835 in registry
		assertEquals(null, airport.arrivalTime("IBE3835"));
	}
	
	@Test
	public void testDelay() {
		IncomingFlightsRegistry airport = new IncomingFlightsRegistry();
		airport.arrivesAt("avion",1050);
		airport.arrivesAt("avion",1200);
		assertEquals(1200, airport.arrivalTime("avion"));
	}
	
	@Test
	public void testArriving() {
		IncomingFlightsRegistry airport = new IncomingFlightsRegistry();
		airport.arrivesAt("avion1",20);
		airport.arrivesAt("avion2",10);
		assertEquals(new FlightArrival("avion2",10), airport.arriving(0).first().element());
	}
	
}

