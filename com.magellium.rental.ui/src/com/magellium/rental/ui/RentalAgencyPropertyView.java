package com.magellium.rental.ui;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.magellium.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.RentalAgency;

public class RentalAgencyPropertyView extends ViewPart {

	public RentalAgencyPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		TreeViewer treeViewer = new TreeViewer(parent);
		
		RentalProvider rentalProvider = new RentalProvider();
		
		treeViewer.setContentProvider(rentalProvider);
		treeViewer.setLabelProvider(rentalProvider);
		
		RentalAgency agency = RentalCoreActivator.getAgency();
		Collection<RentalAgency> agencies = new ArrayList<RentalAgency>();
		agencies.add(agency);
		
		treeViewer.setInput(agencies);
		treeViewer.expandAll();
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
