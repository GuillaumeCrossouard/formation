package com.magellium.rental.e4.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.magellium.rental.ui.RentalUIActivator;
import com.opcoach.training.rental.Customer;

public class CustomerCopyHandler {

	// @Override
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) @Optional Object myObject) {

		Clipboard clipboard = new Clipboard(Display.getCurrent());

		String textData = ((Customer) myObject).getDisplayName();
		String rtfData = "{\\rtf1\\b\\i " + textData + "}";
		Image image = RentalUIActivator.getDefault().getImageRegistry().get(RentalUIActivator.IMG_CUSTOMER);

		Transfer[] transfers = new Transfer[] { TextTransfer.getInstance(), RTFTransfer.getInstance(),
				ImageTransfer.getInstance() };

		Object[] data = new Object[] { textData, rtfData, image.getImageData() };

		clipboard.setContents(data, transfers);
		clipboard.dispose();
	}

	@CanExecute
	public boolean canExecuteTheHandler(@Named(IServiceConstants.ACTIVE_SELECTION) @Optional Object myObject) {
		if (myObject instanceof Customer) {
			return true;
		}
		return false;
	}
}
