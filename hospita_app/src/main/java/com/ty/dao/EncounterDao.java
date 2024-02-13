package com.ty.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.dto.Branch;
import com.ty.dto.Encounter;
import com.ty.dto.MedOrders;
import com.ty.dto.Person;

public class EncounterDao {
	
	static Scanner scan=new Scanner(System.in); 
	static EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("vikas");
	static EntityManager entityManager=entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction=entityManager.getTransaction();

	public Encounter saveEncounter(int branch_id, Encounter encounter) {
		
		Branch branch=entityManager.find(Branch.class, branch_id);
		
		if(branch!=null) {
			System.out.println("Select any choice");
			System.out.println("1.New User");
			System.out.println("2.Existing User");
			int encounter_choice=scan.nextInt();
		switch(encounter_choice) {
		case 1:{
			
			//taking person details
			Person person=PersonDao.savePerson(new Person());
			
			//setting the person to encounter
			encounter.setPerson(person);
			
			//setting the person to encounter list of Person class
			List<Encounter> encounters=person.getEncounters();
			encounters.add(encounter);
			person.setEncounters(encounters);
			
			//taking encounter details
			System.out.println("Enter the Blood Pressure satus");
			String user_blood_pressure=scan.next();
			encounter.setBlood_pressure(user_blood_pressure);
			System.out.println("Enter the body temeperature");
			String user_temperature=scan.next();
			encounter.setBody_temperature(user_temperature);
			System.out.println("Enter the diagnosis required");
			String user_daignosis=scan.next();
			encounter.setDiagnosis(user_daignosis);
			System.out.println("Enter the Doctor to be Consulted");
			String user_consult=scan.next();
			encounter.setDoctor_consulted(user_consult);
			
			//Empty arraylist of medorders should be available
			List<MedOrders> med_orders=new ArrayList<MedOrders>();
			encounter.setMedOrders(med_orders);
			
			//setting the encounter to branch
			encounter.setBranch(branch);
			
			//setting the branch to encounterList of Branch
			List<Encounter> encounter_list=branch.getEncounters();
			encounter_list.add(encounter);
			branch.setEncounters(encounter_list);
			
			//merging only the person because of cascading it can persist the encounter
			entityTransaction.begin();
			entityManager.persist(person);
			entityManager.merge(branch);
			entityTransaction.commit();
			
			System.out.println("Appointment Booked");
		}break;
		case 2:{
			System.out.println("Enter the Person id");
			int user_person_id=scan.nextInt();
			
			Person person_find=entityManager.find(Person.class, user_person_id);
			
			if(person_find!=null) {
				
				//taking encounter details
				System.out.println("Enter the Blood Pressure satus");
				String user_blood_pressure=scan.next();
				encounter.setBlood_pressure(user_blood_pressure);
				System.out.println("Enter the body temeperature");
				String user_temperature=scan.next();
				encounter.setBody_temperature(user_temperature);
				System.out.println("Enter the diagnosis required");
				String user_daignosis=scan.next();
				encounter.setDiagnosis(user_daignosis);
				System.out.println("Enter the Doctor to be Consulted");
				String user_consult=scan.next();
				encounter.setDoctor_consulted(user_consult);
				
				//setting the person to encounter list of Person class
				List<Encounter> encounters=person_find.getEncounters();
				encounters.add(encounter);
				person_find.setEncounters(encounters);
				
				//Empty arrayList of medOrders should be available
				List<MedOrders> med_orders=new ArrayList<MedOrders>();
				encounter.setMedOrders(med_orders);
				
				//setting the encounter to branch
				encounter.setBranch(branch);
				
				//setting the branch to encounterList of Branch
				List<Encounter> encounter_list=branch.getEncounters();
				encounter_list.add(encounter);
				branch.setEncounters(encounter_list);
				
				//setting the encounter to person
				encounter.setPerson(person_find);
				
				//merging the branch means encounter gets persisted
				entityTransaction.begin();
//				entityManager.merge(branch);
				entityManager.merge(person_find);
				entityTransaction.commit();
				
				System.out.println("Appointment Booked");
				
			}else {
				System.out.println("Entered Person Id does not exist");
			}
		}break;
		default: System.out.println("Enter valid choice");
		}
	}else {
		System.out.println("Branch does not exist");
	}
	
	return encounter;
	
}

	public void FindEncounterById(int user_encounter) {
		
		Encounter encounter=entityManager.find(Encounter.class, user_encounter);
		
		System.out.println("Encounter is treated for "+encounter.getPerson());
		System.out.println("Encounter is daignosed by "+encounter.getDoctor_consulted());
		System.out.println("Body Temperature "+encounter.getBody_temperature());
		System.out.println("Blood Pressure "+encounter.getBlood_pressure());
		System.out.println("Diagnosis description "+encounter.getDiagnosis());
		
	}

	public void removeEncounterbyId(int user_encounter) {

		Encounter encounter=entityManager.find(Encounter.class, user_encounter);
		
		if(encounter!=null) {
			 
			//Removing the encounter
			
			entityTransaction.begin();
			entityManager.remove(encounter);
			entityTransaction.commit();
			
			System.out.println("Encounter Removed Sucessfully");
		}else {
			System.out.println("Ecounter not found in database....Removal is not possible");
		}
		
	}

	public void UpdateEncounter(int user_encounter) {
		
		Encounter encounter=entityManager.find(Encounter.class,user_encounter );
		
		
		if(encounter!=null) {
			
			System.out.println("Select any choice");
			System.out.println("1.To Update the blood pressure");
			System.out.println("2.To Update the body temperature");
			System.out.println("3.To Update the diagnosis");
			
			int user_choice=scan.nextInt();
			
			switch(user_choice) {
			case 1:{
				System.out.println("Enter the blood pressure to be updated");
				String user_blood_pressure=scan.next();
				encounter.setBlood_pressure(user_blood_pressure);
			}break;
			case 2:{
				System.out.println("Enter the body temperature to be updated");
				String user_body_temperature=scan.next();
				encounter.setBlood_pressure(user_body_temperature);
			}break;
			case 3:{
				System.out.println("Enter the Diagnosis to be updated");
				String user_daignosis=scan.next();
				encounter.setBlood_pressure(user_daignosis);
			}break;
			default: System.out.println("Enter the valid choice");
			}
			
			System.out.println("Updated successfully");
			
			//merging the encounter
			entityTransaction.begin();
			entityManager.merge(encounter);
			entityTransaction.commit();
		}else {
			
			System.out.println("Encounter does not exist");
		}
	}

	
}
