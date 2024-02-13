package com.ty.dco;

import com.ty.dao.Management;
import com.ty.dao.ManagementEmployee;
import com.ty.dao.ManagementPatient;

public class hospital_app_controller {
	

	
	public static void main(String[] args) {
		
		while(true) {
		int choice=Management.selectChoice();
		switch(choice) {
		case 1:{
			boolean flag=true;
			while(flag) {
				int employee_choice=Management.selectEmployeeChoice();
				switch(employee_choice) {
				case 1:{
					ManagementEmployee.saveHospital();
				}break;
				case 2:{
					ManagementEmployee.saveBranch();
				}break;
				case 3:{
					ManagementEmployee.FindHospitalByBranchId();
				}break;
				case 4:{
					ManagementEmployee.FindBrachByHospitalId();
				}break;
				case 5:{
					ManagementEmployee.UpdateHospital();
				}break;
				case 6:{
					ManagementEmployee.UpdateBranch();
				}break;
				case 7:{
						ManagementEmployee.RemoveHospital();
				}break;
				case 8:{
					ManagementEmployee.RemoveBranch();
				}break;
				case 9:{
						flag=false;
				}break;
				default:System.out.println("Enter Valid Choice");
				}
			}
		}break;
		case 2:{
			boolean flag=true;
			while(flag) {
				int patient_Choice=Management.selectPatientChoice();
				switch(patient_Choice) {
				case 1:{
					ManagementPatient.saveEncounter();
				}break;
				case 2:{
					ManagementPatient.saveMedicalOrder();				
				}break;
				case 3:{
					ManagementPatient.FindMedicalOrderById();
				}break;
				case 4:{
					ManagementPatient.FindEncounterbyId();
				}break;
				case 5:{
					ManagementPatient.FindItembyId();
				}break;
				case 6:{
					ManagementPatient.FindPersonbyId();
				}break;
				case 7:{
					ManagementPatient.removeMedicalOrderbyId();				
				}break;
				case 8:{
					ManagementPatient.removeEncounterbyId();
				}break;
				case 9:{
					ManagementPatient.removePersonById();
				}break;
				case 10:{
					ManagementPatient.removeitemById();
				}break;
				case 11:{
					ManagementPatient.UpdateMedicalOrder();
				}break;
				case 12:{
					ManagementPatient.UpdateEncounter();
				}break;
				case 13:{
					ManagementPatient.UpdateItem();
				}break;
				case 14:{
					ManagementPatient.UpdatePerson();
				}break;
				case 15:{
					flag=false;
				}break;
				default:System.out.println("Enter valid choice");
				}
			}
			break;
		}
		case 3:{
			return;
		}
		default:System.out.println("Invalid choice");
		}
		
		}
		
	}

}
