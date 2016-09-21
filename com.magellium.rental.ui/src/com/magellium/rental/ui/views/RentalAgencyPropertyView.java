package com.magellium.rental.ui.views;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.magellium.rental.core.RentalCoreActivator;
import com.magellium.rental.ui.RentalProvider;
import com.magellium.rental.ui.RentalUIActivator;
import com.opcoach.training.rental.RentalAgency;

public class RentalAgencyPropertyView extends ViewPart implements IPropertyChangeListener {

	private TreeViewer treeViewer;

	public RentalAgencyPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
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
		getSite().setSelectionProvider(treeViewer);
		
		// autorise le popup sur le tv
		MenuManager menuManager = new MenuManager();
		Menu menu = menuManager.createContextMenu(treeViewer.getControl());
		treeViewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuManager, treeViewer);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		RentalUIActivator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
	}

	@Override
	public void dispose() {
		super.dispose();
		RentalUIActivator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		treeViewer.refresh();
	}

}
