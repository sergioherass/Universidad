package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import model.ItemLocation;

@SuppressWarnings("serial")
public class ItemPanel extends JPanel {
	
	private JButton button;
	private JLabel label;

	public ItemPanel(ItemLocation location, int index, CharacterWrapper player, Principal principal) {
		super(new GridLayout(2,1), true);
		button = new JButton(location.toString().toLowerCase() + " " + index);
		button.addActionListener(new ButtonAction(location, index, player, principal));
		this.add(button);
		
		label = new JLabel("Arrastra y Suelta");
		label.setTransferHandler(new ItemTransferHandler(principal, location, index));		
		label.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
            	JLabel fuente = (JLabel) e.getSource();
                TransferHandler handle = fuente.getTransferHandler();
                handle.exportAsDrag(fuente, e, TransferHandler.COPY);
            }
        });
		this.add(label);
	}

	public void setText(String text) {
		// TODO: write here your code
		// Nota: Modificar esto es opcional, puede servir para cambiar el texto de 
		// los botones (lo que hay implementado) o para poner un icono en el JLabel
		button.setText(text);		
	}

	public void setColor(Color color) {
		// TODO: write here your code
		// Nota: Modificar esto es opcional, puede servir para cambiar el color
		// del fondo del botón o del label o para escoger un icono concreto 
		// para el JLabel
	}

}
