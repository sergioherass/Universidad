package model;

import gui.Report;

public class Player {
	// Nota: la GUI no utiliza este valor para inicializarse, siempre será blue
	// aunque este valor inicial se cambie.
	PlayerAspect aspect = PlayerAspect.BLUE; 
	
	// TODO: Complete the attributes and methods
	// You will need to write some other classes
	
	public Player() {
		// TODO: write here your code
		// Da unos cuantos objetos al personaje y colocalos en la mochila
		// o equipados, hazlo a tu gusto
	}
	
	public String getName() {
		// TODO: write your code here
		return "El nombre del personaje";
	}
	
	public boolean tick(long time) {
		// TODO: write here your code
		return false; // Solo será verdadero si hay que cambiar algo en la GUI
	}

	public PlayerAspect getAspect() {
		return aspect; // No cambiar
	}

	public void changeAspect() {
		// TODO: write here your code
		Report.print("Cambiando aspecto"); // Quitar cuando se implemente
	}

	public void click(ItemLocation location, int index) {
		// TODO: write here your code
		Report.print("Click en " + location + ", index: " + index); // Quitar cuando se implemente
	}
	
	public void move(ItemLocation fromLocation, int fromIndex, ItemLocation toLocation, int toIndex) {
		// TODO: write here your code
		Report.print(String.format("From: %s %d to %s %d", fromLocation, fromIndex, toLocation, toIndex)); // Quitar cuando se implemente
	}
}
