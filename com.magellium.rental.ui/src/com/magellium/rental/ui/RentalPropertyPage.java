package com.magellium.rental.ui;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbenchPropertyPage;

public class RentalPropertyPage extends FieldEditorPreferencePage implements IWorkbenchPropertyPage {

	public RentalPropertyPage() {
		super(GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
		setDescription("Rental demo prefs page");
	}

	@Override
	public IAdaptable getElement() {
		return null;
	}

	@Override
	public void setElement(IAdaptable element) {
	}

	@Override
	protected void createFieldEditors() {
		addField(new ColorFieldEditor("CUSTOMER_COLOR", "Customer", getFieldEditorParent()));
		addField(new ColorFieldEditor("RENTAL_COLOR", "Rental", getFieldEditorParent()));
		addField(new ColorFieldEditor("OBJECTS_COLOR", "Objects", getFieldEditorParent()));
	}

}
