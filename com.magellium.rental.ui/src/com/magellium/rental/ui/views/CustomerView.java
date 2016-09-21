package com.magellium.rental.ui.views;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.magellium.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;

public class CustomerView extends ViewPart implements ISelectionListener {

	private Label customerLabel;

	public CustomerView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {

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

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public void setCustomerLabel(Customer c) {
		if (c == null){
			customerLabel.setText("Select a customer or rental");
		} else {
			customerLabel.setText(c.getFirstName() + " " + c.getLastName());
		}
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {

		if (selection.isEmpty()) {
			return;
		}

		if (selection instanceof IStructuredSelection) {
			Object select = ((IStructuredSelection) selection).getFirstElement();

			Customer c = Platform.getAdapterManager().getAdapter(select, Customer.class);
			setCustomerLabel(c);

		}
	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		site.getPage().addSelectionListener(this);
	}

}
