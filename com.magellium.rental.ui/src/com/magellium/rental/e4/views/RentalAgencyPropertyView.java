package com.magellium.rental.e4.views;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.magellium.rental.core.RentalCoreActivator;
import com.magellium.rental.ui.RentalPreferencePage;
import com.magellium.rental.ui.RentalProvider;
import com.opcoach.training.rental.RentalAgency;

public class RentalAgencyPropertyView {

	private static final String MENU_ID = "com.magellium.rental.eap.popupmenu.0";

	private TreeViewer treeViewer;

	@Inject
	private ESelectionService selectionService;

	public RentalAgencyPropertyView() {
	}

	// @Override
	@PostConstruct
	public void createPartControl(Composite parent, EMenuService menuService) {
		treeViewer = new TreeViewer(parent);

		RentalProvider rentalProvider = new RentalProvider();

		treeViewer.setContentProvider(rentalProvider);
		treeViewer.setLabelProvider(rentalProvider);

		RentalAgency agency = RentalCoreActivator.getAgency();
		Collection<RentalAgency> agencies = new ArrayList<RentalAgency>();
		agencies.add(agency);

		treeViewer.setInput(agencies);
		treeViewer.expandAll();

		// cette vue fournit la selection
		// le treeViewer implemente ISelectionProvider
		// getSite().setSelectionProvider(treeViewer); // E34 revoir la
		// selection
		provideSelection();

		// autorise le popup sur le tv
		// E34 menuManager
		// MenuManager menuManager = new MenuManager();
		// Menu menu = menuManager.createContextMenu(treeViewer.getControl());
		// treeViewer.getControl().setMenu(menu);
		// getSite().registerContextMenu(menuManager, treeViewer);
		menuService.registerContextMenu(treeViewer.getControl(), MENU_ID);

	}

	// @Override
	@Focus
	public void setFocus() {

	}

	/**
	 * Set the selection in SelectionService
	 */
	private void provideSelection() {
		// attach a selection listener to the viewer
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// get the selection in event
				IStructuredSelection sel = (IStructuredSelection) event.getSelection();
				// set the selection to the service
				selectionService.setSelection(sel.size() == 1 ? sel.getFirstElement() : sel.toArray());

			}
		});
	}

	// E34
	//
	// @Override
	// public void init(IViewSite site) throws PartInitException {
	// super.init(site);
	// RentalUIActivator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
	// }
	//
	// @Override
	// public void dispose() {
	// super.dispose();
	// RentalUIActivator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
	// }
	//
	// @Override
	// public void propertyChange(PropertyChangeEvent event) {
	// treeViewer.refresh();
	// }

	@Inject
	public void refreshTree(@Preference(value = RentalPreferencePage.OBJECTS_COLOR) String objectsCol,
			@Preference(value = RentalPreferencePage.CUSTOMER_COLOR) String custCol,
			@Preference(value = RentalPreferencePage.RENTAL_COLOR) String rentalCol) {
		if (treeViewer != null) {
			treeViewer.refresh();
		}
	}

}
