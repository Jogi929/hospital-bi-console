package com.ty.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.dto.Address;
import com.ty.dto.Branch;
import com.ty.dto.Encounter;
import com.ty.dto.Hospital;

public class BranchDao {
	static Scanner scan = new Scanner(System.in);
	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("vikas");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	// method which saves the branch details to particular hospital
	public Branch saveBranch(Branch branch) {

		// finding the hospital
		System.out.println("Enter the hospital Id to save the branch");
		int user_hospital_id = scan.nextInt();
		Hospital hospital_find = entityManager.find(Hospital.class, user_hospital_id);

		// Entering the details of branch to get added to the hospital by checking
		// the hospital exist or not
		if (hospital_find != null) {

			System.out.println("Enter the Branch Name");
			String user_branch_name = scan.next();
			branch.setBranchName(user_branch_name);
			System.out.println("Enter the Branch Manager");
			String user_branch_manager = scan.next();
			branch.setBranchManager(user_branch_manager);
			System.out.println("Enter the Branch Contact");
			String user_branch_contact = scan.next();
			branch.setBranchContact(user_branch_contact);
			System.out.println("Enter the number of beds");
			int user_numberOfBeds = scan.nextInt();
			branch.setNumberOfBeds(user_numberOfBeds);
			
			//setting the address
			System.out.println("Enter the Branch Address Details");
			Address address_details = AddressDao.saveAddress(new Address());
			branch.setAddress(address_details);

			//updating the hospital
			List<Branch> branches = hospital_find.getBranches();
			branches.add(branch);
			hospital_find.setBranches(branches);
			
			//setting the hospital to branch
			branch.setHospital(hospital_find);
			
			//empty Encounter List should be available
			List<Encounter> encounters=new ArrayList<Encounter>();
			branch.setEncounters(encounters);
			
			/*
			 * Merging the hospital can save the branch and its address
			 */
			
			//updating hospital details
			entityTransaction.begin();
			entityManager.merge(hospital_find);
			entityTransaction.commit();
			
			System.out.println("Branch Saved Sucessfully");
			return branch;
		} else {
			System.out.println("Not Possible to add the branch because entered hospital id does not exist");
		}
		return null;

	}

	public void FindBrachByHospitalId(int user_hospital_id) {
		
		Hospital hospital=entityManager.find(Hospital.class, user_hospital_id);
		
		if(hospital!=null) {
			List<Branch> branches=hospital.getBranches();
			
			for(Branch branch:branches) {
				System.out.println("------Branch Details are-----------------");
				System.out.println("Branch Name is : "+branch.getBranchId());
				System.out.println("Branch Manager name is : "+branch.getBranchManager());
				System.out.println("Branch Contact is : "+branch.getBranchContact());
				System.out.println("Number of beds in Branch : "+branch.getNumberOfBeds());
				System.out.println("Branch Address is : ");
				Address address=branch.getAddress();
				System.out.println(address.getCity()+","+address.getStreet()+","+address.getState()+","+address.getPinCode());
				
				System.out.println("-----------------------------------------------");
			}
		}else {
			System.out.println("Hospital Does not exist");
		}
	}

	public void UpdateBranch(int user_branch_id) {
		
		Branch branch=entityManager.find(Branch.class, user_branch_id);
		if(branch!=null) {
			System.out.println("Select any choice");
			System.out.println("1. To Update the Address");
			System.out.println("2. To Update the Branch name");
			System.out.println("3. To Update the Branch Manager");
			System.out.println("4. To Update the Branch Contact");
			
			int user_choice=scan.nextInt();
			
			switch(user_choice) {
			case 1:{
				Address address=branch.getAddress();
				if(address!=null) {
						branch.setAddress( AddressDao.updateAddress(address));
				}else {
						System.out.println("Not possible to update the address...Address not assigned to branch");
				}
			}break;
			case 2:{
				System.out.println("Enter the Branch name to be updated");
				String user_branch=scan.next();
				branch.setBranchName(user_branch);
			}break;
			case 3:{
				System.out.println("Enter the Branch Manager to be updated");
				String branch_manager=scan.next();
				branch.setBranchManager(branch_manager);
			}break;
			case 4:{
				System.out.println("Enter the Branch Contatct to be updated");
				String branch_manager=scan.next();
				branch.setBranchManager(branch_manager);
			}break;
			default:System.out.println("Enter the valid choice");
			}
			
			//updating the branch
			entityTransaction.begin();
			entityManager.merge(branch);
			entityTransaction.commit();
			
			System.out.println("Updated Sucessfully");
		}else {
			System.out.println("Updation not possible");
		}
		
	}

	public void removeBranch(int user_branch_id) {

			Branch branch=entityManager.find(Branch.class,user_branch_id);
			
			if(branch!=null) {
				
				//removing the branch directly
				entityTransaction.begin();
				entityManager.remove(branch);
				entityTransaction.commit();

				System.out.println("Branch Removed Sucessfully");
			}else {
				System.out.println("Removal of Branch is not possible");
			}
	}

	

}
