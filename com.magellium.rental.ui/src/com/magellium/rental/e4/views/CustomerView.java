package com.magellium.rental.e4.views;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.adapter.Adapter;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.magellium.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalObject;

public class CustomerView {

	private Label customerLabel;

	@Inject
	public CustomerView(Composite parent) {

		parent.setLayout(new GridLayout(1, false));

		Group infoGroup = new Group(parent, SWT.NONE);
		GridData gd_infoGroup = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_infoGroup.widthHint = 340;
		infoGroup.setLayoutData(gd_infoGroup);
		infoGroup.setText("Customer name");
		infoGroup.setLayout(new GridLayout(3, false));

		customerLabel = new Label(infoGroup, SWT.NONE);
		GridData gd2 = new GridData();
		gd2.horizontalSpan = 2;
		gd2.horizontalAlignment = SWT.FILL;
		customerLabel.setLayoutData(gd2);

		// init
		setCustomerLabel(RentalCoreActivator.getAgency().getRentals().get(1).getCustomer());
	}

	// @Override
	@Focus
	public void setFocus() {

	}

	public void setCustomerLabel(Customer c) {
		if (c == null) {
			customerLabel.setText("Select a customer or rental");
		} else {
			customerLabel.setText(c.getFirstName() + " " + c.getLastName());
		}
	}

	// E34
	// @Override
	// public void selectionChanged(IWorkbenchPart part, ISelection selection) {
	//
	// if (selection.isEmpty()) {
	// return;
	// }
	//
	// if (selection instanceof IStructuredSelection) {
	// Object select = ((IStructuredSelection) selection).getFirstElement();
	//
	// Customer c = Platform.getAdapterManager().getAdapter(select,
	// Customer.class);
	// setCustomerLabel(c);
	//
	// }
	// }
	//
	// @Override
	// public void init(IViewSite site) throws PartInitException {
	// super.init(site);
	// site.getPage().addSelectionListener(this);
	// }

//	@Inject
//	@Optional
//	public void receiveSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Rental r) {
//		if (r != null) {
//			// Customer c = Platform.getAdapterManager().getAdapter(r,
//			// Customer.class);
//			Customer c = r.getCustomer();
//			setCustomerLabel(c);
//		}
//	}
//
//	@Inject
//	@Optional
//	public void receiveSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Customer c) {
//		if (c != null) {
//			setCustomerLabel(c);
//		}
//	}
//
//	@Inject
//	@Optional
//	public void receiveSelection(@Named(IServiceConstants.ACTIVE_SELECTION) RentalObject c) {
//		setCustomerLabel(null);
//	}
	// avec adapter : remplace les 3 methodes ci dessus
	// Attention : il faut prendre l'adapter dans org.eclipse.e4.core.services
	@Inject
	@Optional
	public void receiveSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Object o, Adapter adapter) {
		if (o != null){
			Customer c = Platform.getAdapterManager().getAdapter(o, Customer.class);
			setCustomerLabel(c);
		} else {
			setCustomerLabel(null);
		}
	}
	
	//mutiselection
	@Inject
	@Optional
	public void receiveSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Object[] o) {
		if (o != null){
			customerLabel.setText("*** multi selection ***");
		}
	}

}
