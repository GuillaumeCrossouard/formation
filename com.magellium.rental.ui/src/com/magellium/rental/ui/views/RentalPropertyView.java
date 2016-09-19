package com.magellium.rental.ui.views;


import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import com.magellium.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;

public class RentalPropertyView extends ViewPart {

	private Label rentedObjectLabel;
	private Label customerLabel;
	private Label startDateLabel;
	private Label endDateLabel;


	public RentalPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Group infoGroup = new Group(parent, SWT.NONE);
		infoGroup.setText("Informations");
		infoGroup.setLayout(new GridLayout(2, false));
		
		rentedObjectLabel = new Label(infoGroup, SWT.BORDER);
		GridData gd = new GridData();
		gd.horizontalSpan = 2; // occupe les 2 premières colonnes
		gd.horizontalAlignment = SWT.FILL;
		rentedObjectLabel.setLayoutData(gd);
		
		customerLabel = new Label(infoGroup, SWT.BORDER);
		GridData gd2 = new GridData();
		gd2.horizontalAlignment = SWT.FILL;
		customerLabel.setLayoutData(gd);
		
		startDateLabel = new Label(infoGroup, SWT.BORDER);
		GridData gd3 = new GridData();
		gd3.horizontalAlignment = SWT.FILL;
		startDateLabel.setLayoutData(gd);
		
		endDateLabel = new Label(infoGroup, SWT.BORDER);
		GridData gd4 = new GridData();
		gd4.horizontalAlignment = SWT.FILL;
		endDateLabel.setLayoutData(gd);
		
		setRental(RentalCoreActivator.getAgency().getRentals().get(1));
		
		Group dateGroup = new Group(parent, SWT.NONE);
		dateGroup.setText("Dates");
		dateGroup.setLayout(new GridLayout(2, false));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	
	public void setRental(Rental r){
		rentedObjectLabel.setText(r.getRentedObject().getName());
		customerLabel.setText(r.getCustomer().getFirstName() + " " + r.getCustomer().getLastName());
		startDateLabel.setText(r.getStartDate().toString());
		endDateLabel.setText(r.getEndDate().toString());
	}
	
}
