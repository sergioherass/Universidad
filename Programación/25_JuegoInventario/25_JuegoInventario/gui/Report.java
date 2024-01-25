package gui;

import java.awt.Color;

import javax.swing.*;
import javax.swing.text.*;

public class Report {

	private Report() {
		throw new UnsupportedOperationException();
	}

	static final int MAX_LINES = 5;
	static final int MAX_LINE_LENGTH = 120;
	static int numberOfLines = 0;
	static int count = 1;
	static JTextArea textArea = null;
	static PlainDocument docModel = null;

	static JTextArea init() {
		docModel = new PlainDocument();
		textArea = new JTextArea(docModel, null, 6, 70);
		textArea.setForeground(Color.GRAY);
		textArea.setBackground(Color.BLACK);
		return textArea;
	}

	public static void print(String msg) {
		try {
			if (numberOfLines >= MAX_LINES) {
				docModel.remove(0, MAX_LINE_LENGTH);
				--numberOfLines;
			}
			String preOutMsg = String.format("%d: '%s'", count, msg);
			if ( preOutMsg.length() > MAX_LINE_LENGTH - 2) {
				preOutMsg = preOutMsg.substring(0, MAX_LINE_LENGTH - 2);
			}
			String outMsg = String.format("%-118s%n", preOutMsg);
			Position end = docModel.getEndPosition();
			int endOffset = end.getOffset() - 1;
			docModel.insertString(endOffset, outMsg, null);
			++numberOfLines;
			++count;
		} catch (BadLocationException e) {
			throw new IllegalArgumentException("Bad Location");
		} catch (Exception e) {
			throw new IllegalArgumentException("Bad insertion");
		}
	}
}
