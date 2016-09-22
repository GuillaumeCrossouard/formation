package com.magellium.rental.ui;

import java.util.Map;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.magellium.rental.ui.paletteManagement.PaletteDescriptor;

public class PalettePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public static final String PALETTE = "palette";

	public PalettePreferencePage() {
		super(GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
		setDescription("Rental demo prefs page");
	}

	@Override
	public void init(IWorkbench workbench) {}

	@Override
	protected void createFieldEditors() {

		Map<String, PaletteDescriptor> paletteManager = RentalUIActivator.getDefault().getPaletteManager();
		String[][] comboValues = new String[paletteManager.size()][2];

		int i = 0;
		for (PaletteDescriptor p : paletteManager.values()) {
			comboValues[i][0] = p.getName();
			comboValues[i][1] = p.getId();
			i++;
		}

		addField(new ComboFieldEditor(PALETTE, "Palette appliquée", comboValues, getFieldEditorParent()));
	}

}
