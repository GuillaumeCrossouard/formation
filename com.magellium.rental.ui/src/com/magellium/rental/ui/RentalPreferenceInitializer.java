package com.magellium.rental.ui;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.RGB;

public class RentalPreferenceInitializer extends AbstractPreferenceInitializer {

	public RentalPreferenceInitializer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {

		IPreferenceStore store = RentalUIActivator.getDefault().getPreferenceStore();

		store.setDefault(RentalPreferencePage.CUSTOMER_COLOR, StringConverter.asString(new RGB(255, 0, 0)));
		store.setDefault(RentalPreferencePage.OBJECTS_COLOR, StringConverter.asString(new RGB(0, 250, 0)));
		store.setDefault(RentalPreferencePage.RENTAL_COLOR, StringConverter.asString(new RGB(60, 50, 75)));

	}

}
