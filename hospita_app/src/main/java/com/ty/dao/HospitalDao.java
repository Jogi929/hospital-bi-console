package com.ty.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.dto.Branch;
import com.ty.dto.Hospital;

public class HospitalDao {

	static Scanner scan=new Scanner(System.in); 
	static EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("vikas");
	static EntityManager entityManager=entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction=entityManager.getTransaction();
	
	//Method which saves the hospital details into database
	public Hospital saveHospital(Hospital hospital) {
		
		//Enter the hospital details
		
		System.out.println("Enter the Hospiital name");
		String user_hospitalName=scan.next();
		hospital.setHospitalName(user_hospitalName);
		System.out.println("Enter the Hospital Type");
		String user_hospitalType=scan.next();
		hospital.setHospitalType(user_hospitalType);
		System.out.println("Enter the Hospital Accredation");
		String user_hospitalAccredation=scan.next();
		hospital.setAccreditation(user_hospitalAccredation);
		System.out.println("Enter the Hospital Founding year");
		int user_founding_year=scan.nextInt();
		hospital.setFoundingYear(user_founding_year);
		
		//set the arrayList of branches 
		List<Branch> branches=new ArrayList<Branch>();
		hospital.setBranches(branches);
		
		//Saving the hospital details into database 
		entityTransaction.begin();
		entityManager.persist(hospital);
		entityTransaction.commit();
		
		System.out.println("Hospital details added to the database successfully....");
		
		return hospital;
		
	}

	public void FindHospitalByBranchId(int user_branch_id) {
		
		//finding the branch
		Branch branch_find=entityManager.find(Branch.class, user_branch_id);
		
		//validating the branch 
		if(branch_find!=null) {
			
		//getting the hospital object form branch 
		Hospital hospital=branch_find.getHospital();
		
		//hospital details are printing
		System.out.println("Branch is Registered Under :"+hospital.getHospitalName());
		System.out.println( "--------------"+hospital.getHospitalName()+" details are --------------");
		System.out.println("Hospital Id is : "+hospital.getHospitalId());
		System.out.println("Hospital Accrediation : "+hospital.getAccreditation());
		System.out.println("The Category of Hospital is : "+hospital.getHospitalType());
		System.out.println("The Hospital Is Established in : "+hospital.getFoundingYear());
		
		}else {
			System.out.println("Branch Does Not Exist");
		}
		
	}

	public void UpdateHospital(int user_hospital_id) {
		
		Hospital hospital=entityManager.find(Hospital.class, user_hospital_id);
		
		if(hospital!=null) {
			
			System.out.println("Select any Choice");
			System.out.println("1.To Update the Category of hospital");
			System.out.println("2.To Update the Accredation of hospital");
			System.out.println("3.To Update the Name of hospital");
			
			int user_choice=scan.nextInt();
			
			switch(user_choice) {
			case 1:{
				System.out.println("Enter the Category to be updated");
				String user_Category=scan.next();
				hospital.setHospitalType(user_Category);
			}break;
			case 2:{
				System.out.println("Enter the Accredation to be updated");
				String user_Accredation=scan.next();
				hospital.setHospitalType(user_Accredation);
			}break;
			case 3:{
				System.out.println("Enter the Name to be updated");
				String user_Name=scan.next();
				hospital.setHospitalType(user_Name);
			}break;
			default:System.out.println("Enter the valid choice");
			}
			
			//updating the details
			entityTransaction.begin();
			entityManager.merge(hospital);
			entityTransaction.commit();
			
			System.out.println("Udated Successfully");
		}else {
			System.out.println("Hospital Does Not exist");
		}
		
	}

	
	public void removeHospital(int user_hospital_id) {
		
		Hospital hospital=entityManager.find(Hospital.class, user_hospital_id);
		
		if(hospital!=null) {
			
			//remove the hospital
			entityTransaction.begin();
			entityManager.remove(hospital);
			entityTransaction.commit();
			
			System.out.println("Hospital Removed Sucessfully");
		}else {
			System.out.println("Removing hospitalis not possible");
		}
		
	}

}
