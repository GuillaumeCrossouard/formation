package com.magellium.rental.ui;

import com.opcoach.training.rental.RentalAgency;

public class Node {
	
	public static final String NODE_CUSTOMER = "Customer";
	public static final String NODE_RENTAL_OBJECTS = "Rental objects";
	public static final String NODE_RENTALS = "Rentals";

	private String nodeLabel;
	
	// ref vers le parent plutot que de lister tous les objets fils, ce qui chargerait tout en mémoire
	private RentalAgency agency;

	public Node(String nl , RentalAgency ra) {
		setNodeLabel(nl);
		setAgency(ra);
	}
	
	@Override
	public String toString() {
		return nodeLabel;
	}
	
	public Object[] getChildren(){
		
		if (getNodeLabel() == NODE_CUSTOMER){
			return getAgency().getCustomers().toArray();
		}
		if (getNodeLabel() == NODE_RENTAL_OBJECTS){
			return getAgency().getObjectsToRent().toArray();
		}
		if (getNodeLabel() == NODE_RENTALS){
			return getAgency().getRentals().toArray();
		}
		return null;
	}
	
	
	// getters
	
	public String getNodeLabel() {
		return nodeLabel;
	}

	public void setNodeLabel(String nodeLabel) {
		this.nodeLabel = nodeLabel;
	}

	public RentalAgency getAgency() {
		return agency;
	}

	public void setAgency(RentalAgency agency) {
		this.agency = agency;
	}

}
