package gui;

import java.awt.*;
import model.*;

public class CharacterWrapper {

	public static final int BACKPACK_CAPACITY = 10;
	public static final int EQUIPPED_CAPACITY = 4;

	Player player = new Player();

	boolean tick(long time) {
		// Devuelve verdadero si es necesario hacer un update, falso en caso contrario
		return player.tick(time);
	}

	PlayerAspect getAspect() {
		return player.getAspect();
	}

	void changeAspect() {
		player.changeAspect();
	}

	void click(ItemLocation location, int index) {
		player.click(location, index);
	}

	public String getText(ItemLocation location, int i) {
		// TODO: write here your code
		return null;
	}

	public Color getColor(ItemLocation location, int i) {
		// TODO: write here your code
		return null;
	}
	
	public void move(ItemLocation fromLocation, int fromIndex, ItemLocation toLocation, int toIndex) {
		player.move(fromLocation, fromIndex, toLocation, toIndex);
	}

	public String getName() {
		return player.getName();
	}
}
