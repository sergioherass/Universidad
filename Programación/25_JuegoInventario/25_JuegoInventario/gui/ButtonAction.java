package gui;

import java.awt.event.*;

import model.ItemLocation;

public class ButtonAction implements ActionListener {
	
	ItemLocation location;
	int index;
	CharacterWrapper player;
	Principal principal;
	
	public ButtonAction(ItemLocation location, int index, CharacterWrapper player, Principal principal) {
		this.location = location;
		this.index = index;
		this.player = player;
		this.principal = principal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		player.click(location, index);
		principal.update();
	}

}
