package com.magellium.rental.ui;

import java.util.Collection;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider {

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

}
