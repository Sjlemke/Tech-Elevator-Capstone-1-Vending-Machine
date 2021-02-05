package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {
	
	//Item theItem; //Make item Object
	
	
	//ArrayList<Slot> slots =  new ArrayList();	
	private Map<String, Item> slots = new HashMap<String, Item>(); //holds item and keeps track of current number of items per slot
	
	private double balance = 0 ;
	
	
	
	
	//Create a MAP where the key is 5 and item object
	//for loop to cycle through theItems ArrayList and assign each of them to Key value of 5, key values will be subtracted per purchase
	//Map<Item, Int> slots = new HashMap<Item, Int>(); ????????????????
	
	      //item object as the value. do a get. ..key will be chips
	     //create for each loop and create a map entity. for (Map.Entry<String, Item> item:

	
	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}




	public Inventory() throws FileNotFoundException {
	
		this.slots = slots;
		this.readFile();
		

	}
	
	
	
	
	// * *********************************************


	public Map<String, Item> getSlots() {
		return slots;
	}


	public void setSlots(Map<String, Item> slots) {
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
	
	public ArrayList<Item> readFile() throws FileNotFoundException {
		
	ArrayList<Item> theItems = new ArrayList();
		
	File myInventory = new File("vendingmachine.csv"); //Read the text File with Items
	
	Scanner theFile = new Scanner(myInventory);
	
	while(theFile.hasNextLine()) {			// Looping through each line from the Inventory txt File 
		
		String theLine = theFile.nextLine();       //Reading the line then storing each line in theLine
		String[] theValues = theLine.split("\\|"); // Splitting the attribute up. For |, use \\
		Item anItem = new Item( theValues[0],theValues[1],Double.parseDouble(theValues[2]),theValues[3]); //create new item object
		//anItem.setLocation() = theValues[0];
		//anItem.setName() = theValues[1];
		//anItem.price = Double.parseDouble(theValues[2]);
		//anItem.type = theValues[3];
		
		slots.put(theValues[0], anItem);
	}//end of while
	return theItems;
	
	
	}
	
	public Map<String, Item> addToMap() {  //This method takes all the items and assigns them to a value of 5 
		
		
								   //some type of loop
	//	theItem.readFile(); //adding items from item class to this Map
		

		return slots;
	}

	
	public String dispenseItem(String location) { 
		/*
		if     (location.equals("A1")  ||	//This is matching input with the values in map
				location.equals("A2")  ||	//This method will get item from MAP and update the balance
				location.equals("A3")  ||	//Will print out message according to type
				location.equals("A4")  || 	//Subtracted from balance
				location.equals("B1")  ||	//Only will subtract if balance is greater or equal to price
				location.equals("B2")  ||
				location.equals("B3")  ||
				location.equals("B4")  ||
				location.equals("C1")  ||
				location.equals("C2")  ||
				location.equals("C3")  ||
				location.equals("C4")  ||
				location.equals("D1")  ||
				location.equals("D2")  ||
				location.equals("D3")  ||
				location.equals("D4")   ) {
		*/
		
		Item anItem = slots.get(location);
		
		//use quantity 
		//if the item balance is ok
		//if the item is not sold out
		//if the item is certain type return the message
			
		return "hello";
			
			
		}
			
			
	
		
		
		
		
		
		
	}

	
	
	
	
	
	//call these methods in cli. 
	
