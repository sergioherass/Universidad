package gui;

import java.awt.datatransfer.*;
import java.awt.dnd.DnDConstants;

import javax.swing.*;

import model.ItemLocation;

@SuppressWarnings("serial")
public class ItemTransferHandler extends TransferHandler {
	
	// Export:
	private String itemDescription;

	// Import:
	private static final DataFlavor DATA_TYPE = DataFlavor.stringFlavor;
	private transient Principal principal;
	private ItemLocation itemLocation;
	private int index;
	
	public ItemTransferHandler(Principal principal, ItemLocation itemLocation, int index) {
		this.principal = principal;
		this.itemLocation = itemLocation;
		this.index = index;
		this.itemDescription = itemLocation.toString() + " " + index;
	}

	@Override
	public boolean canImport(TransferHandler.TransferSupport support) {
		return support.isDataFlavorSupported(DATA_TYPE);
	}

	@Override
	public boolean importData(TransferHandler.TransferSupport support) {
		boolean accept = false;
		if (canImport(support)) {
			try {
				Transferable t = support.getTransferable();
				Object value = t.getTransferData(DATA_TYPE);
				if (value instanceof String) {
					principal.move((String)value, itemLocation, index);
					accept = true;
				}
			} catch (Exception exp) {
				exp.printStackTrace();
			}
		}
		return accept;
	}
	
    @Override
    public int getSourceActions(JComponent c) {
        return DnDConstants.ACTION_COPY_OR_MOVE;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        return new StringSelection(itemDescription);
    }
	
}
