package gui;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;
import model.ItemLocation;

public class Principal implements Runnable, ActionListener {

	static final int TICK_INTERVAL = 200;

	JFrame frame = new JFrame("Character Inventary");

	JPanel startPanel = new JPanel();
	JLabel nameLabel = new JLabel();

	JPanel centerPanel = new JPanel();
	JButton characterButton = new JButton();
	static final String CHARACTER_BUTTON_ACTION = "Change aspect";

	JPanel centerEndPanel = new JPanel();
	ItemPanel[] backpack = new ItemPanel[10];

	JPanel pageEndPanel = new JPanel();

	JPanel pageEndButtons = new JPanel();
	ItemPanel[] itemButton = new ItemPanel[4];

	JTextArea messages = Report.init();

	// Other
	Timer timer = new Timer(TICK_INTERVAL, null);
	CharacterWrapper player = new CharacterWrapper();

	@Override
	public void run() {
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Top
		startPanel.setLayout(new FlowLayout());
		nameLabel.setText(player.getName());
		startPanel.add(nameLabel);

		// Center
		centerPanel.setLayout(new FlowLayout());
		characterButton.setSize(200, 200);
		centerPanel.add(characterButton);
		characterButton.setActionCommand(CHARACTER_BUTTON_ACTION);
		characterButton.addActionListener(this);
		setIcon("blue");

		// Center Right
		final int gap = 3;
		centerEndPanel.setLayout(new GridLayout(5, 2, gap, gap));
		for (int i = 0; i < CharacterWrapper.BACKPACK_CAPACITY; ++i) {
			backpack[i] = new ItemPanel(ItemLocation.BACKPACK, i, player, this);
			centerEndPanel.add(backpack[i]);
		}

		// Bottom
		pageEndPanel.setLayout(new GridLayout(2, 1));
		pageEndButtons.setLayout(new FlowLayout());
		for (int i = 0; i < CharacterWrapper.EQUIPPED_CAPACITY; ++i) {
			itemButton[i] = new ItemPanel(ItemLocation.EQUIPPED, i, player, this);
			pageEndButtons.add(itemButton[i]);
		}
		pageEndPanel.add(pageEndButtons);
		messages.setEnabled(false);
		pageEndPanel.add(messages);

		// Frame
		frame.add(startPanel, BorderLayout.PAGE_START);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(centerEndPanel, BorderLayout.LINE_END);
		frame.add(pageEndPanel, BorderLayout.PAGE_END);
		frame.pack();
		frame.setVisible(true);

		// Timer
		timer.addActionListener(new TimerAction(this));
		timer.start();
	}

	private void setIcon(String color) {
		String path = String.format("/assets/woman-elf-face_%s.png", color);
		try {
			URL url = getClass().getResource(path);
			Image img = ImageIO.read(url);
			Image scaledImg = img.getScaledInstance(256, 256, java.awt.Image.SCALE_SMOOTH);
			characterButton.setIcon(new ImageIcon(scaledImg));
		} catch (Exception ex) {
			Report.print("Error when reading from: '" + path + "'");
			Report.print(ex.getMessage());
		}
	}

	// Drag and Drop
	public void move(String from, ItemLocation itemLocation, int index) {
		Scanner sc = new Scanner(from);
		ItemLocation fromLocation = ItemLocation.valueOf(sc.next());
		int fromIndex = sc.nextInt();
		player.move(fromLocation, fromIndex, itemLocation, index);
		sc.close();
	}

	void update() {
		ItemLocation location = ItemLocation.BACKPACK;
		for (int i = 0; i < CharacterWrapper.BACKPACK_CAPACITY; ++i) {
			String text = player.getText(location, i);
			if (text != null) {
				backpack[i].setText(text);
			}
			Color color = player.getColor(location, i);
			if (color != null) {
				backpack[i].setColor(color);
			}
		}
		
		location = ItemLocation.EQUIPPED;
		for (int i = 0; i < CharacterWrapper.EQUIPPED_CAPACITY; ++i) {
			String text = player.getText(location, i);
			if (text != null) {
				backpack[i].setText(text);
			}
			Color color = player.getColor(location, i);
			if (color != null) {
				backpack[i].setColor(color);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if (actionCommand.equals(CHARACTER_BUTTON_ACTION)) {
			player.changeAspect();
			String color = player.getAspect().toString().toLowerCase();
			setIcon(color);
		}
	}

	public static void main(String[] args) {
		Principal principal = new Principal();
		SwingUtilities.invokeLater(principal);
	}

}
