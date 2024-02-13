package com.ty.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class MedOrders {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="medorder_sequence")
	@SequenceGenerator(name="medorder_sequence",initialValue=101,sequenceName = "medorder_sequence")
	private int orderId;
    private String  payment_mode;
    private String delivery_satus;
    private String prescriptionStatus;
    private String pharmacy_name;
    
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn,inverseJoinColumns = @JoinColumn)
    private List<Item> items;
    
    @ManyToOne
    @JoinColumn
    Encounter encounters;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getPayment_mode() {
		return payment_mode;
	}

	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}

	public String getDelivery_satus() {
		return delivery_satus;
	}

	public void setDelivery_satus(String delivery_satus) {
		this.delivery_satus = delivery_satus;
	}

	public String getPrescriptionStatus() {
		return prescriptionStatus;
	}

	public void setPrescriptionStatus(String prescriptionStatus) {
		this.prescriptionStatus = prescriptionStatus;
	}

	public String getPharmacy_name() {
		return pharmacy_name;
	}

	public void setPharmacy_name(String pharmacy_name) {
		this.pharmacy_name = pharmacy_name;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Encounter getEncounters() {
		return encounters;
	}

	public void setEncounters(Encounter encounters) {
		this.encounters = encounters;
	}
    
    


}
