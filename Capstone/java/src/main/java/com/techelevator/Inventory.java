package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;

import java.sql.Timestamp;
import java.text.DecimalFormat;


public class Inventory {
	
	//Item theItem; //Make item Object
	
	
	//ArrayList<Slot> slots =  new ArrayList();	
	private Map<String, Item> slots = new LinkedHashMap<String, Item>(); //holds item and keeps track of current number of items per slot
	
	private double balance = 0 ;
	
	private DecimalFormat formatter = new DecimalFormat("#.00");

	File audit = new File("log.txt"); // assigning the file
	
	
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
		
		Item anItem = new Item( theValues[0],theValues[1],Double.parseDouble(theValues[2]),theValues[3], 5); //create new item object
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

	
	public void dispenseItem(String location) throws IOException { 
		
		
		Item anItem = slots.get(location); // This gets the item based on the location
									//If the item chosen, doesnt equal the location in inventory, then return
		
		//System.out.println(anItem.getQuantity() - 1);
		if     (anItem.getLocation().equals("A1")  ||  //IF VALID ENTRY
				anItem.getLocation().equals("A2")  ||
				anItem.getLocation().equals("A3")  ||
				anItem.getLocation().equals("A4")  || 
				anItem.getLocation().equals("B1")  ||
				anItem.getLocation().equals("B2")  ||
				anItem.getLocation().equals("B3")  ||
				anItem.getLocation().equals("B4")  ||
				anItem.getLocation().equals("C1")  ||
				anItem.getLocation().equals("C2")  ||
				anItem.getLocation().equals("C3")  ||
				anItem.getLocation().equals("C4")  ||
				anItem.getLocation().equals("D1")  ||
				anItem.getLocation().equals("D2")  ||
				anItem.getLocation().equals("D3")  ||
				anItem.getLocation().equals("D4")    ) {
		//*******************************************************************************************************
		// This adjusts the balance, inventory, and sales report
		//*******************************************************************************************************
			
		
			
			if(anItem.getQuantity() > 0) {  //If the item is in stock

			//PURCHASING
			if(anItem.getPrice() <= balance) { // If the user has enough money to purchase the item, continue 
			
				
				
				anItem.setQuantity(anItem.getQuantity() - 1); //take one away from quantity
				
				//****logPurchase();
				
				balance -= anItem.getPrice(); // The item price is subtracted from the balance
		
			

			//****************************************************************************************************
			//This Displays the message based on Type of product
			//****************************************************************************************************
			String message = "";
			if(anItem.getType().equals("Chip")) {
				message = "Crunch Crunch, Yum!";
			} else if(anItem.getType().equals("Drink")) {
				message = "Glug Glug, Yum!";
			} else if(anItem.getType().equals("Candy")) {
				message = "Munch Munch, Yum!";
			} else if(anItem.getType().equals("Gum")) {
				message = "Chew Chew, Yum!";
			}
		
			System.out.println(message);
			//***************************************************************************************************
		}else {
			System.out.println("Insufficient funds..."); //Display when user doesn't have enough money
		 }
		
		
		//use quantity 
		//if the item balance is ok
		//if the item is not sold out
		
		
		
		
	
		
		} else {//END OF IS ITEM IS IN STOCK
		
			System.out.println("SOLD OUT");
			}
		
	
			} else { //END OF IF PRODUCT CORRECT
			System.out.println("That is not a valid item!");
			}
		
		}// END OF DISPENSE METHOD
	
		
	//***************************************************************************************************
	//CREATE LOG FILE TO AUDIT
	//***************************************************************************************************
	
	
	public void logPurchase() throws IOException {
	
		
	FileWriter aFileWriter = new FileWriter(audit, true);
	BufferedWriter aBufferedWriter= new BufferedWriter(aFileWriter);
	PrintWriter diskFileWriter = new PrintWriter(aBufferedWriter);
	Timestamp timestampNow = Timestamp.valueOf(LocalDateTime.now());  
	
	diskFileWriter.println(timestampNow  );
	
	diskFileWriter.close();
	
	}
	
	//***************************************************************************************************
	
	
	
	public void dispenseChange() {
		
		double quarters = 0.0;
		double dimes = 0.0;
		double nickels = 0.0;
		while (balance > 0.0) {	//while there's still change
			if(balance >= 0.25) {	//if the change is larger than a quarter
				balance -= 0.25;   // subtract a quarter
				quarters++;		// Count the quarters taken away
			} else if (balance >= 0.10) {
				balance -= 0.10;
				dimes++;		
			} else if (balance >= 0.05) {
				balance -= 0.05;
				nickels++;
			} else {
				balance = 0;
				break;
			}
			
		}//end of while
		
		
		System.out.println("Here is your change! Thank you :) ");
		System.out.println("quarters: " + quarters + " | dimes: " + dimes + " | nickels: " + nickels);
		
		balance = 0;
		
		
		
		
		
	}
		
	public void createSalesReport() throws IOException {
		
		Timestamp timestampNow = Timestamp.valueOf(LocalDateTime.now());  
		String thisFileName = "Todays_Report_" + timestampNow;
		File report = new File(thisFileName);
		report.createNewFile(); 
	}
			
	
		
		
		
		
		
		
	}

	
	
	
	
	
	//call these methods in cli. 
	
