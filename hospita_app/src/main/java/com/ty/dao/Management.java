package com.ty.dao;

import java.util.Scanner;

public class Management {
	
	
	//scanner  class to receive user's choice
	static Scanner scan=new Scanner(System.in);
	
	
	//static method to select the user choice with respect to employee or patient
	public static int selectChoice() {
		System.out.println("1.For Employee");
		System.out.println("2.For Patient");
		System.out.println("3.Exit");
		
		System.out.println("Enter the choice");
		int choice=scan.nextInt();
		return choice;
	}


	public static int selectEmployeeChoice() {
		System.out.println("1. Save Hospital");
		System.out.println("2. Save Branch");
		System.out.println("3. To know the branch is Registered Under ");
		System.out.println("4. To Know the Hospital branches Details");
		System.out.println("5. To Update the Detalis of Hospital");
		System.out.println("6. To Update the Detais of Branch");
		System.out.println("7. To Remove the Hospital");
		System.out.println("8. To Remove the Branch");
		System.out.println("9. Eixt");
		
		System.out.println("Enter the employee choice");
		int choice=scan.nextInt();
		return choice;
	}
	
	public static int selectPatientChoice() {
		System.out.println("1.Save Encounter");
		System.out.println("2.Save MedicalOrders");
		System.out.println("3.Find Medical Order by Id");
		System.out.println("4.Find Encounter by Id");
		System.out.println("5.Find Item by Id");
		System.out.println("6.Find Person By id");
		System.out.println("7.Remove Medical order by id ");
		System.out.println("8.Remove Encounter by id ");
		System.out.println("9.Remve Person by id ");
		System.out.println("10.Remove Item by id");
		System.out.println("11.Update Medical order by id ");
		System.out.println("12.Update Encounter by id ");
		System.out.println("13.Update Item by id ");
		System.out.println("14.Update Person by id");
		System.out.println("15.Exit");
		System.out.println("Enter the Patient choice");
		int choice=scan.nextInt();
		return choice;
	}
}
