package com.magellium.rental.ui;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class RentalPerspective implements IPerspectiveFactory {
	
	public static final String RENTAL_PERSPECTIVE_ID = "com.magellium.rental.ui.RentalPerspective";

	/**
	 * Creates the initial layout for a page.
	 */
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		String editorArea = layout.getEditorArea();
		addFastViews(layout);
		addViewShortcuts(layout);
		addPerspectiveShortcuts(layout);
		layout.addView("com.magellium.rental.ui.viewrental", IPageLayout.BOTTOM, 0.5f, "com.magellium.rental.ui.view1");
		layout.addView("com.magellium.rental.ui.rentalAgencyView", IPageLayout.LEFT, 0.5f, IPageLayout.ID_EDITOR_AREA);
		layout.addView("com.magellium.rental.ui.customerView", IPageLayout.RIGHT, 0.5f, "com.magellium.rental.ui.rentalAgencyView");
	}

	/**
	 * Add fast views to the perspective.
	 */
	private void addFastViews(IPageLayout layout) {
	}

	/**
	 * Add view shortcuts to the perspective.
	 */
	private void addViewShortcuts(IPageLayout layout) {
	}

	/**
	 * Add perspective shortcuts to the perspective.
	 */
	private void addPerspectiveShortcuts(IPageLayout layout) {
	}

}
