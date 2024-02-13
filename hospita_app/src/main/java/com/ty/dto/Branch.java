package com.ty.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Branch {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="branch_sequence")
	@SequenceGenerator(name="branch_sequence",initialValue=101,sequenceName = "branch_sequence")
	private int branchId;
    private String branchName;
    private String branchManager;
    private String branchContact;
    private int numberOfBeds;
    
    @ManyToOne
    @JoinColumn
    Hospital hospital;
    
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn
    private Address address;
    
    @OneToMany(mappedBy="branch",cascade=CascadeType.ALL)
    private List<Encounter> encounters;

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchManager() {
		return branchManager;
	}

	public void setBranchManager(String branchManager) {
		this.branchManager = branchManager;
	}

	public String getBranchContact() {
		return branchContact;
	}

	public void setBranchContact(String branchContact) {
		this.branchContact = branchContact;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Encounter> getEncounters() {
		return encounters;
	}

	public void setEncounters(List<Encounter> encounters) {
		this.encounters = encounters;
	}

    
    

}
