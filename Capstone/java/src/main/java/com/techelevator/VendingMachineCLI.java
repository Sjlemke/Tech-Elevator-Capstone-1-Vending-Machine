package com.techelevator;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;

import java.sql.Timestamp;

/**************************************************************************************************************************
*  This is your Vending Machine Command Line Interface (CLI) class
*
*  It is the main process for the Vending Machine
*
*  THIS is where most, if not all, of your Vending Machine interactions should be coded
*  
*  It is instantiated and invoked from the VendingMachineApp (main() application)
*  
*  Your code vending machine related code should be placed in here
***************************************************************************************************************************/
import com.techelevator.view.Menu;         // Gain access to Menu class provided for the Capstone

public class VendingMachineCLI {

    // Main menu options defined as constants

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE      = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT          = "Exit";
	private static final String MAIN_MENU_OPTION_REPORT	       = "Sales Report";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													    MAIN_MENU_OPTION_PURCHASE,
													    MAIN_MENU_OPTION_EXIT,
											 		    MAIN_MENU_OPTION_REPORT
													    };
	VendingMachine aVendingMachine;
	Inventory theInventory; //Create new inventory Object
	
	private Menu vendingMenu;              // Menu object to be used by an instance of this class
	private Item vendingItem;
	public VendingMachineCLI(Menu menu) throws FileNotFoundException {  // Constructor - user will pas a menu for this class to use
		this.vendingMenu = menu;           // Make the Menu the user object passed, our Menu
	
		//this.aVendingMachine = new VendingMachine(vendingItem.readFile()); // Create a Vending Machine Object that reads the item file
		this.theInventory = new Inventory();
	}
	
	private DecimalFormat formatter = new DecimalFormat("0.00");
	private double totalBalance = 0;				     //declare a totalbalance variable

	/**************************************************************************************************************************
	*  VendingMachineCLI main processing loop
	*  
	*  Display the main menu and process option chosen
	*
	*  It is invoked from the VendingMachineApp program
	*
	*  THIS is where most, if not all, of your Vending Machine objects and interactions 
	*  should be coded
	*
	*  Methods should be defined following run() method and invoked from it
	 * @throws IOException 
	*
	***************************************************************************************************************************/
	
	public void run() throws IOException {
		
		theInventory.createLog(); //create log to rewrite
		
		boolean shouldProcess = true;         // Loop control variable
		
		while(shouldProcess) {                // Loop until user indicates they want to exit
			
			String choice = (String)vendingMenu.getChoiceFromOptions(MAIN_MENU_OPTIONS);  // Display menu and get choice
			
			switch(choice) {                  // Process based on user menu choice
			
				case MAIN_MENU_OPTION_DISPLAY_ITEMS:
					displayItems();           // invoke method to display items in Vending Machine
					break;                    // Exit switch statement
			
				case MAIN_MENU_OPTION_PURCHASE:
					purchaseItems();          // invoke method to purchase items from Vending Machine
					break;                    // Exit switch statement
			
				case MAIN_MENU_OPTION_EXIT:
					endMethodProcessing();    // Invoke method to perform end of method processing
					shouldProcess = false;    // Set variable to end loop
					break;                    // Exit switch statement
					
				case MAIN_MENU_OPTION_REPORT:
					salesReport();
					break;
			}	
		}
		return;                               // End method and return to caller
	}
/********************************************************************************************************
 * Methods used to perform processing
 ********************************************************************************************************/
	public void displayItems() {      
		for (Map.Entry<String, Item> item : theInventory.getSlots().entrySet()) {
		
		System.out.println(item.getValue().getLocation() + "|" +
				           item.getValue().getName()     + "|" +
				           item.getValue().getPrice()    + "|" +
				           item.getValue().getType()     + "|" +
			"Quantity: " + item.getValue().getQuantity() + "|");
		
		
		}
		
		
		// static attribute used as method is not associated with specific object instance
		// Code to display items in Vending Machine
		
	}
	
	public void purchaseItems() throws IOException {	 
		//initialize finish transaction = false;
		boolean finishTransaction = true;
		//show menu with feed money, purchase, finish and current money provided
		while (finishTransaction) { //WHILE finish transaction is True keep running, when finish transaction is False exit loop
		System.out.println("Current Money Provided: $" + formatter.format(theInventory.getBalance()));
		System.out.println("(1) Feed Money");
		System.out.println("(2) Select Product");		
		System.out.println("(3) Finish Transaction");
		
		
		//use scanner for userinput to insert money if correct bills 1 , 5 , 10
		Scanner userInput = new Scanner(System.in);				 //Instantiate an object for the keyboard
		
		String menuChoice = userInput.nextLine();				// Getting the users menu choice
		
		 //**********THIS IS THE FEED MONEY MENU**************************************************************
		if(menuChoice.equals("1")) {
			System.out.println("Current Money Provided: $" + formatter.format(theInventory.getBalance()));
			System.out.println("--------------------------------------------------------");
			System.out.println("Please enter only $1, $5 and $10 bills... NO CHANGE");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.print("Enter Money: $");
			
			userInput = new Scanner(System.in); //New scanner object
			
			double userDeposit = userInput.nextDouble(); //This lets user deposit an amount
				if(userDeposit == 1 || userDeposit == 5 || userDeposit == 10) { //Correct bill
					double depositForLog = userDeposit; //holding value for the log
					
					totalBalance = theInventory.getBalance() + userDeposit;   //Sum up every dollar entered
					
					theInventory.logDeposit(depositForLog); // This is logging the balance if fed monety
					
					theInventory.setBalance(totalBalance);   //UPDATE the balance
				} else {
					System.out.println("That is not a valid amount..."); //This is if user enters wrong value
				}
		}//END OF MENU CHOICE 1**
		//*****************************************************************************************************
				
				
		//***********THIS IS THE PURCHASE MENU*****************************************************************
		else if(menuChoice.equals("2")) { //IF press 2 , display and prompt user to enter item code
			this.displayItems();
			System.out.print("Please enter an item: "); //prompt user for item
			String itemChoice = userInput.next().toUpperCase();		//Store itemChoice from user input
			
			
			
			try { theInventory.dispenseItem(itemChoice);
			} catch (Exception e) {
				System.out.println("That is not a valid item!");
			}
			
		} //END OF MENU CHOICE 2**
					
		//****************************************************************************************************
				
		else if (menuChoice.equals("3")) {
			
			
			
			
			theInventory.dispenseChange();
		    theInventory.logChange();
			finishTransaction = false;
		

		}//END OF MENU CHOICE 3
			
		
		
		
		
		
		
		
		
		
		
		
		}//END OF WHILE LOOP
		
		
		
		

	
		// if user presses 3 then finish transaction == false;
		
		}//END OF PURCHASE METHOD*********************************************************************************
		
		// static attribute used as method is not associated with specific object instance
		// Code to purchase items from Vending Machine
	public void salesReport() throws IOException {
		Timestamp timestampNow = Timestamp.valueOf(LocalDateTime.now());  
		String thisFileName = "Todays_Report_" + timestampNow.getSeconds() + "-" + timestampNow.getDay() + "-" + timestampNow.getYear();
		File report = new File(thisFileName);
		report.createNewFile(); 
		FileWriter aFileWriter = new FileWriter(report);
		BufferedWriter aBufferedWriter= new BufferedWriter(aFileWriter);
		PrintWriter diskFileWriter = new PrintWriter(aBufferedWriter);
		
	
		for (Map.Entry<String, Item> item : theInventory.getSlots().entrySet()) {
			
			
			
			
			diskFileWriter.println(
					           item.getValue().getName()     + "|" +
					          
							   item.getValue().getQuantity());
			

			}
		diskFileWriter.println("");
		diskFileWriter.println("Total Sales: $" + formatter.format(theInventory.returnTotal()));
		diskFileWriter.close();
	}
	
	public void endMethodProcessing() { // static attribute used as method is not associated with specific object instance
		// Any processing that needs to be done before method ends
	}
}
