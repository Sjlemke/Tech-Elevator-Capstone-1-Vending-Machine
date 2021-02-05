package com.techelevator;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {
	
	//ArrayList<Slot> slots =  new ArrayList();	
	private Map<String, Item> slots = new HashMap<String, Item>(); //holds item and keeps track of current number of items per slot
	
	
	//Create a MAP where the key is 5 and item object
	//for loop to cycle through theItems ArrayList and assign each of them to Key value of 5, key values will be subtracted per purchase
	//Map<Item, Int> slots = new HashMap<Item, Int>(); ????????????????
	public void assignToMap() {      
		for (int i =0; i < aVendingMachine.getList().size(); i++) {
			
		System.out.println(aVendingMachine.getList().get(i).getLocation() + "|" +
				           aVendingMachine.getList().get(i).getName() + "|" +
				           aVendingMachine.getList().get(i).getPrice() + "|" +
				           aVendingMachine.getList().get(i).getType() + "|" );
		}
	      //item object as the value. do a get. ..key will be chips
	     //create for each loop and create a map entity. for (Map.Entry<String, Item> item:
	// * *********************************************
	public Map<Item, Integer> getSlots() {
		return slots;
	}


	public void setSlots(Map<Item, Integer> slots) {
		this.slots = slots;
	}


	
	
	
	
	//****************For Sales Report*************************************************************************************************************************************
	// File outputFile = new File("program.out"); Need to output Sales Report With all items sold PrintWriter*****
	
	// Create the file on the disk - file must exist before we can write to it
	//outputFile.createNewFile();  // Will destroy any existing copy of the file

	// Define a PrintWriter Object for the output file
	//		PrintWriter fileWriter = new PrintWriter(outputFile);  // If the file already exists, it will overlay the existing data
	
	//****************For Sales Report*************************************************************************************************************************************

	
	
	
	//**************** Methods ********************************************************************************************************************************************
	
	
	public void writeSales() {
		
		
		
	}
	
	//use if statements, if item doesnt exist, tell user. If price is more than what person has, 
	//tell them that. If else, then subtract price from that item, then return glug glug
	//
	public void dispenseItem(String location) {
		
	}

	
	
	
	
	
	//call these methods in cli. 
	
}