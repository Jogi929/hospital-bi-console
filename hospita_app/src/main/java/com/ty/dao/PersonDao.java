package com.ty.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.dto.Encounter;
import com.ty.dto.Item;
import com.ty.dto.Person;

public class PersonDao {
	static Scanner scan=new Scanner(System.in); 
	static EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("vikas");
	static EntityManager entityManager=entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction=entityManager.getTransaction();
	
	public static Person savePerson(Person person) {
		
		//Entering the person details
		System.out.println("Enter the FirstName");
		String person_first_name=scan.next();
		person.setFirstName(person_first_name);
		System.out.println("Enter the Last Name");
		String person_last_name=scan.next();
		person.setLastName(person_last_name);
		System.out.println("Enter the contact Number");
		String person_contact_number=scan.next();
		person.setContact(person_contact_number);
		System.out.println("Enter the Gender");
		String gender=scan.next();
		person.setGender(gender);
		
		//Empty ArrayList should be available for adding the encounters
		List<Encounter> encounter_list=new ArrayList<Encounter>();
		person.setEncounters(encounter_list);
		
		return person;
		
	}

	public static void FindpersonById(int user_person) {

		Person person=entityManager.find(Person.class,user_person);
		
		System.out.println("Person Frist name is "+person.getFirstName());
		System.out.println("Person Last Name is "+person.getLastName());
		System.out.println("Person Gender is "+person.getGender());
		System.out.println("Person Contact number is "+person.getContact());
		System.out.println("Number of encounters faced by the person "+person.getEncounters());
		
	}

	public static void removepersonById(int user_person) {
		
		Person person=entityManager.find(Person.class, entityManager);
		
		if(person!=null) {
			
			//Removing the person
			entityTransaction.begin();
			entityManager.remove(person);
			entityTransaction.commit();
			
			System.out.println("E");
		}
	}

	public static void UpdatePerson(int user_person) {
		
		Person person=entityManager.find(Person.class,user_person);
		
		if(person!=null) {
			
			System.out.println("Select any choice");
			System.out.println("1.To Update the contact");
			System.out.println("2.To Update the gender");
			System.out.println("3.To Update the First name");
			System.out.println("4.To Update the Last name");
			
			int user_choice=scan.nextInt();
			
			switch(user_choice) {
			case 1:{
				System.out.println("Enter the contact to be updated");
				String user_contact=scan.next();
				person.setContact(user_contact);
			}break;
			case 2:{
				System.out.println("Enter the gender to be updated");
				String user_gender=scan.next();
				person.setGender(user_gender);
			}break;
			case 3:{
				System.out.println("Enter the First name to be updated");
				String user_first_name=scan.next();
				person.setFirstName(user_first_name);
			}break;
			case 4:{
				System.out.println("Enter the Last name to be updated");
				String user_last_name=scan.next();
				person.setFirstName(user_last_name);
			}break;
			default: System.out.println("Enter the valid choice");
			}
			
			System.out.println("Updated successfully");
			
			//merging the encounter
			entityTransaction.begin();
			entityManager.merge(person);
			entityTransaction.commit();
			
			System.out.println("Updated successfully");
		}else {
			
			System.out.println("Item does not exist");
		}
		
	}
}
