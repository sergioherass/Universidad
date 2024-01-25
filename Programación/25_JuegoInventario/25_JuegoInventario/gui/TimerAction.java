package gui;

import java.awt.event.*;

public class TimerAction implements ActionListener {
	Principal principal;
	
	public TimerAction(Principal principal) {
		this.principal = principal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ( principal.player.tick(e.getWhen()) ) {
			principal.update();
		}
	}

}
