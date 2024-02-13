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
import com.ty.dto.MedOrders;

public class MedOrdersDao {

	static Scanner scan=new Scanner(System.in); 
	static EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("vikas");
	static EntityManager entityManager=entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction=entityManager.getTransaction();
	static boolean flag=true;

	
	public MedOrders saveMedicalOrder(int user_encounterId, MedOrders medOrders) {
		
		Encounter encounter_find=entityManager.find(Encounter.class, user_encounterId);
		
		if(encounter_find!=null) {
						
			System.out.println("Enter the Payment Mode");
			String user_payment=scan.next();
			medOrders.setPayment_mode(user_payment);
			
			System.out.println("Enter the Delivery Status");
			String user_delivery=scan.next();
			medOrders.setDelivery_satus(user_delivery);
			
			System.out.println("Enter the Pharmacy Name");
			String user_pharmacy=scan.next();
			medOrders.setPharmacy_name(user_pharmacy);
			
			System.out.println("Enter one choice to add items");
			List<Item> items=new ArrayList<Item>();
			while(flag) {
				System.out.println("1.Add Item to Medical Order");
				System.out.println("2.Exit");
				int item_choice=scan.nextInt();
				
				switch(item_choice) {
				case 1:{
					Item item=ItemDao.saveItem(new Item());
					items.add(item);
				}break;
				case 2:{
					flag=false;
				}break;
				default: System.out.println("Invalid choice");
				}
			}
			medOrders.setItems(items);
			
			System.out.println("Enter the Prescription Status");
			String perscription_status=scan.next();
			medOrders.setPrescriptionStatus(perscription_status);
			
			List<MedOrders> orderList=encounter_find.getMedOrders();
			orderList.add(medOrders);
			encounter_find.setMedOrders(orderList);
			
			medOrders.setEncounters(encounter_find);
			
			//saving into database
			entityTransaction.begin();
			entityManager.persist(medOrders);
			entityTransaction.commit();
			
			
			//updating the Encounter
			entityTransaction.begin();
			entityManager.merge(encounter_find);
			entityTransaction.commit();
			
			System.out.println("Saving Medical Order");
			return medOrders;
		}else {
			System.out.println("Prescription is Mandatory");
		}
		
		return null;
	}


	public void FindMedicalOrderById(int user_medicalOrder) {
		
		MedOrders medOrders=entityManager.find(MedOrders.class, user_medicalOrder);
		
		System.out.println("Medical order Payment mode is "+medOrders.getPayment_mode());
		System.out.println("Medical Order Payment mode is "+medOrders.getPharmacy_name());
		System.out.println("Medical Order Delivery Status is "+medOrders.getPrescriptionStatus());
		System.out.println("Medical Order is Sent by "+medOrders.getPharmacy_name());
		System.out.println("The items Under this Medical Order is ");
		List<Item> item_list =medOrders.getItems();
		
		System.out.println("--------------------------------------------");
		for(Item item:item_list) {
			System.out.println("Item name is "+item.getItemName());
			System.out.println("Item Quantity "+item.getQuantity());
			System.out.println("Item Supplier is "+item.getSupplier());
			System.out.println("Item Unit price is "+item.getUnitPrice());
			System.out.println("-------------------------------------------------");
		}
	}


	public void removeMedicalOrderById(int user_medicalOrder) {

		MedOrders medOrders=entityManager.find(MedOrders.class, user_medicalOrder);
		
		if(medOrders!=null) {
			
			//removing the medical order
			entityTransaction.begin();
			entityManager.remove(medOrders);
			entityTransaction.commit();
			
			System.out.println("Medical Order Removed Sucessfully");
		}else {
			System.out.println("Medical Order is not available in database....Removal is not possible");
		}
		
	}


	public MedOrders UpdateMedicalOrder(int user_id) {
		
		MedOrders medorder=entityManager.find(MedOrders.class, user_id);
		
		
		if(medorder!=null){
			
			System.out.println("Select Any choice");
			System.out.println("1.To Update Item Quantity");
			System.out.println("2.To Update Delivery status");
			System.out.println("3.To Update Prescription status");
			System.out.println("4.To Update medical Order paymentmode");
			
			int user_choice=scan.nextInt();
			
			switch(user_choice) {
			case 1:{
				System.out.println("Number of items available in medical order are "+medorder.getItems().size());
				System.out.println("Number of items you want to update should be lessthan or equal to number of items available");
				int number=scan.nextInt();
				for(int i=0;i<number;i++) {
				System.out.println("Enter the Item id to be Updated ");
				int user_item=scan.nextInt();
				List<Item> item_list=medorder.getItems();
				for(Item item:item_list) {
					if(item.getItemId()==user_item) {
						System.out.println("Enter the quantity to be updated");
						int user_quantity=scan.nextInt();
						item.setQuantity(user_quantity);
						
						//merging the item
						entityTransaction.begin();
						entityManager.merge(item);
						entityTransaction.commit();
						
						break;
					}
				}
			}
				
			}break;
			case 2:{
				
				System.out.println("Enter the delivery status to be updated ");
				String user_delivery=scan.next();
				medorder.setDelivery_satus(user_delivery);
				
			}break;
			case 3:{
				
				System.out.println("Enter the prescription status to be updated ");
				String user_prescription=scan.next();
				medorder.setPrescriptionStatus(user_prescription);
	
			}break;
			case 4:{
				
				System.out.println("Enter the payment mode to be updated ");
				String user_paymnet=scan.next();
				medorder.setPayment_mode(user_paymnet);
				
			}break;
			default:System.out.println("Enter valid choice");
			}
			System.out.println("Updated sucessfully");
			
			//merging the medical order 
			entityTransaction.begin();
			entityManager.merge(medorder);
			entityTransaction.commit();
			
			return medorder;
			
		}else {
			System.out.println("Medical Order does not exist");
		}
		return null;
	}

}
