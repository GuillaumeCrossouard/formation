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

	
	
	// hash code & equals pour reexpand
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agency == null) ? 0 : agency.hashCode());
		result = prime * result + ((nodeLabel == null) ? 0 : nodeLabel.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (agency == null) {
			if (other.agency != null)
				return false;
		} else if (!agency.equals(other.agency))
			return false;
		if (nodeLabel == null) {
			if (other.nodeLabel != null)
				return false;
		} else if (!nodeLabel.equals(other.nodeLabel))
			return false;
		return true;
	}
}
