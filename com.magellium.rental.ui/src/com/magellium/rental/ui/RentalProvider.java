package com.magellium.rental.ui;

import java.util.Collection;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider {

	private static final Object[] EMPTY_RESULT = new Object[0];

	@Override
	public Object[] getElements(Object inputElement) {

		if (inputElement instanceof Collection<?>) {
			return ((Collection<?>) inputElement).toArray();
		}
		return EMPTY_RESULT;
	}

	@Override
	public Object[] getChildren(Object parentElement) {

		if (parentElement instanceof RentalAgency) {
//			return ((RentalAgency) parentElement).getCustomers().toArray();
			
			Node nodeCustomer = new Node(Node.NODE_CUSTOMER, (RentalAgency) parentElement);
			Node nodeObjects = new Node(Node.NODE_RENTAL_OBJECTS, (RentalAgency) parentElement);
			Node nodeRentals = new Node(Node.NODE_RENTALS, (RentalAgency) parentElement);
			
			return new Node[]{nodeCustomer, nodeObjects, nodeRentals};
			
		} else if (parentElement instanceof Node){
			return ((Node) parentElement).getChildren();
		}
			
		return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return (element instanceof RentalAgency || element instanceof Node);
	}

	@Override
	public String getText(Object element) {
		if (element instanceof RentalAgency) {
			return "Agence de " + ((RentalAgency) element).getName();
		}
		if (element instanceof Node) {
			return ((Node) element).toString();
		}
		if (element instanceof RentalObject) {
			return ((RentalObject) element).getName();
		}
		if (element instanceof Customer) {
			return ((Customer) element).getFirstName() + " " + ((Customer) element).getLastName();
		}
		return super.getText(element);
	}

	@Override
	public Color getForeground(Object element) {
		if (element instanceof RentalAgency) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
		}
		if (element instanceof Rental) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_GRAY);
		}
		if (element instanceof RentalObject) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
		}
		if (element instanceof Customer) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_MAGENTA);
		}
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		if (element instanceof Node) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW);
		}
		return null;
	}
	
	@Override
	public Image getImage(Object element) {
		
		ImageRegistry imageRegistry = RentalUIActivator.getDefault().getImageRegistry();
		
		if (element instanceof Customer) {
			return imageRegistry.get(RentalUIActivator.IMG_CUSTOMER);
		}
		if (element instanceof RentalAgency) {
			return imageRegistry.get(RentalUIActivator.IMG_AGENCY);
		}
		if (element instanceof Rental) {
			return imageRegistry.get(RentalUIActivator.IMG_RENTAL);
		}
		if (element instanceof RentalObject) {
			return imageRegistry.get(RentalUIActivator.IMG_OBJECT);
		}
		return super.getImage(element);
	}

}
