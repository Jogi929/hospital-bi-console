package com.ty.dao;

import java.util.Scanner;

import com.ty.dto.Address;

public class AddressDao {
	
	static Scanner scan=new Scanner(System.in); 

	public static Address saveAddress(Address address) {
		
		
		System.out.println("Enter the City");
		String user_city=scan.next();
		address.setCity(user_city);
		System.out.println("Enter the Street");
		String user_street=scan.next();
		address.setStreet(user_street);
		System.out.println("Enter the state");
		address.setState(scan.next());
		System.out.println("Enter the pincode");
		String user_pincode=scan.next();
		address.setPinCode(user_pincode);
		
		return address;
	}

	public static Address updateAddress(Address address) {

		
		System.out.println("Select Any Choice");
		System.out.println("1.To Update Street");
		System.out.println("2. To Update pincode");
		
		int user_choice=scan.nextInt();
		
		switch(user_choice) {
		case 1:{
			System.out.println("Enter the Street name to be updated");
			String user_street=scan.next();
			address.setStreet(user_street);
		}break;
		case 2:{
			System.out.println("Enter the Pincode name to be updated");
			String user_pincode=scan.next();
			address.setStreet(user_pincode);
		}break;
		default:System.out.println("Enter valid choice");
		}
		
		
		
		return address;
	}


}
