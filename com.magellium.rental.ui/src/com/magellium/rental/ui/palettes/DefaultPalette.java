package com.magellium.rental.ui.palettes;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.magellium.rental.ui.Node;
import com.magellium.rental.ui.RentalPreferencePage;
import com.magellium.rental.ui.RentalUIActivator;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class DefaultPalette implements IColorProvider {

	public DefaultPalette() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getForeground(Object element) {

		if (element instanceof RentalAgency) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
		}
		if (element instanceof Rental) {
			// return Display.getCurrent().getSystemColor(SWT.COLOR_GRAY);
			String objectColorString = RentalUIActivator.getDefault().getPreferenceStore()
					.getString(RentalPreferencePage.RENTAL_COLOR);
			return getAColor(objectColorString);
		}
		if (element instanceof RentalObject) {
			// return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
			String objectColorString = RentalUIActivator.getDefault().getPreferenceStore()
					.getString(RentalPreferencePage.OBJECTS_COLOR);
			return getAColor(objectColorString);
		}
		if (element instanceof Customer) {
			// recup de la pref
			String customerColorString = RentalUIActivator.getDefault().getPreferenceStore()
					.getString(RentalPreferencePage.CUSTOMER_COLOR);
			return getAColor(customerColorString);
		}
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		if (element instanceof Node) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW);
		}
		return null;
	}

	// cf page 84
	private Color getAColor(String rgbKey) {
		ColorRegistry cr = JFaceResources.getColorRegistry();
		Color col = cr.get(rgbKey);
		if (col == null) {
			cr.put(rgbKey, StringConverter.asRGB(rgbKey));
			col = cr.get(rgbKey);
		}
		return col;
	}

}
