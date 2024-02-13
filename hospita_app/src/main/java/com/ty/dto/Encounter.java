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
import javax.persistence.SequenceGenerator;

@Entity
public class Encounter {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="encounter_sequence")
	@SequenceGenerator(name="encounter_sequence",initialValue=1,sequenceName = "encounter_sequence")
	private int encounterId;
    private String blood_pressure;
    private String doctor_consulted;
    private String body_temperature;
    private String diagnosis;
    
    @ManyToOne(cascade= {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn
    Branch branch;
    
    @ManyToOne
    @JoinColumn
    private Person person;
    
    
    @OneToMany(mappedBy="encounters",cascade=CascadeType.ALL)
    private List<MedOrders> medOrders;


	public int getEncounterId() {
		return encounterId;
	}


	public void setEncounterId(int encounterId) {
		this.encounterId = encounterId;
	}


	public String getBlood_pressure() {
		return blood_pressure;
	}


	public void setBlood_pressure(String blood_pressure) {
		this.blood_pressure = blood_pressure;
	}


	public String getDoctor_consulted() {
		return doctor_consulted;
	}


	public void setDoctor_consulted(String doctor_consulted) {
		this.doctor_consulted = doctor_consulted;
	}


	public String getBody_temperature() {
		return body_temperature;
	}


	public void setBody_temperature(String body_temperature) {
		this.body_temperature = body_temperature;
	}


	public String getDiagnosis() {
		return diagnosis;
	}


	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}


	public Branch getBranch() {
		return branch;
	}


	public void setBranch(Branch branch) {
		this.branch = branch;
	}


	public Person getPerson() {
		return person;
	}


	public void setPerson(Person person) {
		this.person = person;
	}


	public List<MedOrders> getMedOrders() {
		return medOrders;
	}


	public void setMedOrders(List<MedOrders> medOrders) {
		this.medOrders = medOrders;
	}
    
    
    

}
