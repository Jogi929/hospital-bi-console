package com.ty.dao;

import java.util.Scanner;

import com.ty.dto.Branch;
import com.ty.dto.Hospital;

//Helper class which provides functionality of the Employee actions
public class ManagementEmployee {
static HospitalDao hospitalDao=new HospitalDao();
static BranchDao branchDao=new BranchDao();
static Scanner scan=new Scanner(System.in); 
	
	
	public static void saveHospital() {
		hospitalDao.saveHospital(new Hospital());
	}


	public static void saveBranch() {
		branchDao.saveBranch(new Branch());
	}

	public static void FindHospitalByBranchId() {
		System.out.println("Enter the branch_id :");
		int user_branch_id=scan.nextInt();
		hospitalDao.FindHospitalByBranchId(user_branch_id);
		
	}

	public static void FindBrachByHospitalId() {
		System.out.println("Enter the HospitalId");
		int user_hospital_id=scan.nextInt();
		branchDao.FindBrachByHospitalId(user_hospital_id);
		
	}


	public static void UpdateHospital() {
		
		System.out.println("Enter the HospitalId");
		int user_hospital_id=scan.nextInt();
		hospitalDao.UpdateHospital(user_hospital_id);
		
	}


	public static void UpdateBranch() {
		
		
		System.out.println("Enter the branch_id :");
		int user_branch_id=scan.nextInt();
		branchDao.UpdateBranch(user_branch_id);
		
	}


	public static void RemoveHospital() {
		
		System.out.println("Enter the HospitalId");
		int user_hospital_id=scan.nextInt();
		hospitalDao.removeHospital(user_hospital_id);
		
	}


	public static void RemoveBranch() {
		
		System.out.println("Enter the branch_id :");
		int user_branch_id=scan.nextInt();
		branchDao.removeBranch(user_branch_id);
		
	}


	
	
	

}
