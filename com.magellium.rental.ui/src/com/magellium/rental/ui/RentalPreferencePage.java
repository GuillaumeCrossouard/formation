package com.magellium.rental.ui;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class RentalPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public static final String OBJECTS_COLOR = "OBJECTS_COLOR";
	public static final String RENTAL_COLOR = "RENTAL_COLOR";
	public static final String CUSTOMER_COLOR = "CUSTOMER_COLOR";

	public RentalPreferencePage() {
		super(GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
		setDescription("Rental demo prefs page");
	}

	@Override
	protected void createFieldEditors() {
		addField(new ColorFieldEditor(CUSTOMER_COLOR, "Customer", getFieldEditorParent()));
		addField(new ColorFieldEditor(RENTAL_COLOR, "Rental", getFieldEditorParent()));
		addField(new ColorFieldEditor(OBJECTS_COLOR, "Objects", getFieldEditorParent()));
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

}
