package com.ty.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.dto.Encounter;
import com.ty.dto.Item;
import com.ty.dto.MedOrders;

public class ItemDao {

	
	static Scanner scan=new Scanner(System.in); 
	static EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("vikas");
	static EntityManager entityManager=entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction=entityManager.getTransaction();
	
	public static Item saveItem(Item item) {
		
		
		
		System.out.println("Enter the item name");
		String item_name=scan.next();
		item.setItemName(item_name);
		
		System.out.println("Enter the item quantity");
		int item_quantity=scan.nextInt();
		item.setQuantity(item_quantity);
		
		System.out.println("Enter the Price");
		double price=scan.nextDouble();
		item.setUnitPrice(price);
		
		System.out.println("Enter the Supplier name");
		String supplier=scan.next();
		item.setSupplier(supplier);
		
		//Aavailable of empty aaraylist of medorders
		List<MedOrders> medorder_list=new ArrayList<MedOrders>();
		item.setMedOrders(medorder_list);
		
		//saving into database
		entityTransaction.begin();
		entityManager.persist(item);
		entityTransaction.commit();
		
		return item;
	}

	public static void FinditemById(int user_item) {
		
		Item item=entityManager.find(Item.class,user_item);
		
		System.out.println("Item name is "+item.getItemName());
		System.out.println("Item Quantity is "+item.getQuantity());
		System.out.println("Item Suppplier is "+item.getSupplier());
		System.out.println("Item Unit Price is "+item.getUnitPrice());
		System.out.println("Number of Medical Orders for this item "+item.getMedOrders().size());
		
	}

	public static void removeItemById(int user_item) {

		Item item=entityManager.find(Item.class, user_item);
		
		//fetching the medical orders list of item
		List<MedOrders> medicalorder_list=item.getMedOrders();
		Iterator i=medicalorder_list.iterator();
		
		//iterating the medical order list to the individual medical order 
		while(i.hasNext()) {
			MedOrders medorder=(MedOrders)i.next();
			
			//fetching the item list of medicalorder
			List<Item> item_list=medorder.getItems();
			
			//removing the medical order of specified item
			for(int i1=0;i1<item_list.size();i1++) {
				
				if(item_list.get(i1).getItemId()==user_item) {
					
					item_list.remove(i1);
					break;
				}
			}
			
		}
		
		//removing the item object
		entityTransaction.begin();
		entityManager.remove(item);
		entityTransaction.commit();
		
	}

	public static Item UpdateItem(int user_item) {
		
		Item item=entityManager.find(Item.class,user_item );
		
		if(item!=null) {
			
			System.out.println("Select any choice");
			System.out.println("1.To Update the quantity");
			System.out.println("2.To Update the price");
			System.out.println("3.To Update the Item name");
			
			int user_choice=scan.nextInt();
			
			switch(user_choice) {
			case 1:{
				System.out.println("Enter the quantity to be updated");
				int user_qunatity=scan.nextInt();
				item.setQuantity(user_qunatity);
			}break;
			case 2:{
				System.out.println("Enter the price to be updated");
				double user_unit_price=scan.nextDouble();
				item.setUnitPrice(user_unit_price);
			}break;
			case 3:{
				System.out.println("Enter the name to be updated");
				String user_name=scan.next();
				item.setItemName(user_name);
			}break;
			default: System.out.println("Enter the valid choice");
			}
			
			System.out.println("Updated successfully");
			
			//merging the encounter
			entityTransaction.begin();
			entityManager.merge(item);
			entityTransaction.commit();
			
			System.out.println("Updated successfully");
		}else {
			
			System.out.println("Item does not exist");
		}
		
		return item;
		}
}
