package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Item {
	
	private String location;
	private String name;
	private double price;
	private String type;
	
	
	
	
	
	
//***********************************************************************************************************************************************
	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

//*******************************************************************************************************************************************************

	public Item() throws FileNotFoundException {
//		readFile();
	}
	
	
	
	public static ArrayList<Item> readFile() throws FileNotFoundException {
	
	ArrayList<Item> theItems = new ArrayList();
		
	File myInventory = new File("vendingmachine.csv"); //Read the text File with Items
	
	Scanner theFile = new Scanner(myInventory);
	
	while(theFile.hasNextLine()) {			// Looping through each line from the Inventory txt File 
		
		String theLine = theFile.nextLine();       //Reading the line then storing each line in theLine
		String[] theValues = theLine.split("\\|"); // Splitting the attribute up. For |, use \\
		Item anItem = new Item(); //create new item object
		anItem.location = theValues[0];
		anItem.name = theValues[1];
		anItem.price = Double.parseDouble(theValues[2]);
		anItem.type = theValues[3];
		
		theItems.add(anItem);
	}//end of while
	return theItems;
	
	
	}
	
}
