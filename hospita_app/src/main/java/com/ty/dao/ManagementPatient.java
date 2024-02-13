package com.ty.dao;

import java.util.Scanner;

import com.ty.dto.Encounter;
import com.ty.dto.MedOrders;

public class ManagementPatient {

	static EncounterDao encounterDao=new EncounterDao();
	static MedOrdersDao medorderDao=new MedOrdersDao();
	static Scanner scan=new Scanner(System.in); 
	
	public static void saveEncounter() {
		System.out.println("Enter the Branch id :");
		int user_branch=scan.nextInt();
		encounterDao.saveEncounter(user_branch,new Encounter());
	}

	public static void saveMedicalOrder() {
		System.out.println("Enter the Encounter id:");
		int user_encounter=scan.nextInt();
		medorderDao.saveMedicalOrder(user_encounter,new MedOrders());
	}

	

	public static void FindMedicalOrderById() {
		System.out.println("Enter the Medical order Id :");
		int user_medicalOrder=scan.nextInt();
		medorderDao.FindMedicalOrderById(user_medicalOrder);
		
	}

	public static void FindEncounterbyId() {
		
		System.out.println("Enter the Encounter id:");
		int user_encounter=scan.nextInt();
		encounterDao.FindEncounterById(user_encounter);
	}

	public static void FindItembyId() {
		
		System.out.println("Enter the item id:");
		int user_item=scan.nextInt();
		ItemDao.FinditemById(user_item);
	}

	public static void FindPersonbyId() {
		
		System.out.println("Enter the Person id : ");
		int user_person=scan.nextInt();
		PersonDao.FindpersonById(user_person);
	}

	public static void removeMedicalOrderbyId() {
		System.out.println("Enter the Medical order Id :");
		int user_medicalOrder=scan.nextInt();
		medorderDao.removeMedicalOrderById(user_medicalOrder);
		
	}

	public static void removeEncounterbyId() {
		System.out.println("Enter the Encounter id:");
		int user_encounter=scan.nextInt();
		encounterDao.removeEncounterbyId(user_encounter);
	}

	public static void removePersonById() {
		System.out.println("Enter the Person id : ");
		int user_person=scan.nextInt();
		PersonDao.removepersonById(user_person);
		
	}

	public static void removeitemById() {
		
		System.out.println("Enter the item id:");
		int user_item=scan.nextInt();
		ItemDao.removeItemById(user_item);
	}

	public static void UpdateMedicalOrder() {
		
		System.out.println("Enter the Medical Order id:");
		int user_id=scan.nextInt();
		medorderDao.UpdateMedicalOrder(user_id);
		
	}

	public static void UpdateEncounter() {
		
		System.out.println("Enter the Encounter id:");
		int user_encounter=scan.nextInt();
		encounterDao.UpdateEncounter(user_encounter);
		
	}

	public static void UpdateItem() {
		
		System.out.println("Enter the item id:");
		int user_item=scan.nextInt();
		ItemDao.UpdateItem(user_item);
		
	}

	public static void UpdatePerson() {
		
		System.out.println("Enter the Person id : ");
		int user_person=scan.nextInt();
		PersonDao.UpdatePerson(user_person);
	}

	
	
	
	
	

}
