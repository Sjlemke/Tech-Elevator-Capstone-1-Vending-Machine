package com.techelevator;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													    MAIN_MENU_OPTION_PURCHASE,
													    MAIN_MENU_OPTION_EXIT
													    };
	VendingMachine aVendingMachine;

	
	private Menu vendingMenu;              // Menu object to be used by an instance of this class
	private Item vendingItem;
	public VendingMachineCLI(Menu menu) throws FileNotFoundException {  // Constructor - user will pas a menu for this class to use
		this.vendingMenu = menu;           // Make the Menu the user object passed, our Menu
		this.vendingItem = new Item();	   // creating vending Item object
		this.aVendingMachine = new VendingMachine(vendingItem.readFile()); // Create a Vending Machine Object that reads the item file
	}
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
	 * @throws FileNotFoundException 
	*
	***************************************************************************************************************************/

	public void run() throws FileNotFoundException {
		
	
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
			}	
		}
		return;                               // End method and return to caller
	}
/********************************************************************************************************
 * Methods used to perform processing
 ********************************************************************************************************/
	public void displayItems() {      
		for (int i =0; i < aVendingMachine.getList().size(); i++) {
			
		System.out.println(aVendingMachine.getList().get(i).getLocation() + "|" +
				           aVendingMachine.getList().get(i).getName() + "|" +
				           aVendingMachine.getList().get(i).getPrice() + "|" +
				           aVendingMachine.getList().get(i).getType() + "|" );
		}
		
		
		// static attribute used as method is not associated with specific object instance
		// Code to display items in Vending Machine
		
	}
	
	public void purchaseItems() {	 
		//initialize finish transaction = false;
		boolean finishTransaction = true;
		//show menu with feed money, purchase, finish and current money provided
		while (finishTransaction) { //WHILE finish transaction is FALSE keep running, when finish transaction is TRUE exit loop
		System.out.println("Current Money Provided: $" + aVendingMachine.getBalance());
		System.out.println("(1) Feed Money");
		System.out.println("(2) Select Product");		
		System.out.println("(3) Finish Transaction");
		
		
		//use scanner for userinput to insert money if correct bills 1 , 5 , 10
		Scanner userInput = new Scanner(System.in);				 //Instantiate an object for the keyboard
		
		String menuChoice = userInput.nextLine();				// Getting the users menu choice
		
		 //**********THIS IS THE FEED MONEY MENU**************************************************************
		if(menuChoice.equals("1")) {
			System.out.println("Current Money Provided: $" + aVendingMachine.getBalance());
			System.out.println("--------------------------------------------------------");
			System.out.println("Please enter only $1, $5 and $10 bills... NO CHANGE");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("Enter Money: ");
			
			userInput = new Scanner(System.in); //New scanner object
			
			double userDeposit = userInput.nextDouble(); //This lets user deposit an amount
				if(userDeposit == 1 || userDeposit == 5 || userDeposit == 10) { //Correct bill
		
					double totalBalance = 0;				     //declare a totalbalance variable
					totalBalance = totalBalance + userDeposit;   //Sum up every dollar entered

					aVendingMachine.setBalance(userDeposit);   //UPDATE the balance
				} else {
					System.out.println("That is not a valid amount..."); //This is if user enters wrong value
				}
		}
		//*****************************************************************************************************
				
				
		//***********THIS IS THE PURCHASE MENU*****************************************************************
		else if(menuChoice.equals("2")) { //IF press 2 , display and prompt user to enter item code
			this.displayItems();
			System.out.println("Please enter an item: "); //prompt user for item
			String itemChoice = userInput.next();		//Store itemChoice from user input
			itemChoice.toUpperCase();					//change to upper
			
			if(itemChoice.equals("A1")  ||
			   itemChoice.equals("A2")  ||
			   itemChoice.equals("A3")  ||
			   itemChoice.equals("A4")  || 
			   itemChoice.equals("B1")  ||
			   itemChoice.equals("B2")  ||
			   itemChoice.equals("B3")  ||
			   itemChoice.equals("B4")  ||
			   itemChoice.equals("C1")  ||
			   itemChoice.equals("C2")  ||
			   itemChoice.equals("C3")  ||
			   itemChoice.equals("C4")  ||
			   itemChoice.equals("D1")  ||
			   itemChoice.equals("D2")  ||
			   itemChoice.equals("D3")  ||
			   itemChoice.equals("D4")    ) {
			//	aVendingMachine.getList().get(1).getLocation(itemChoice);
				for (int i =0; i < aVendingMachine.getList().size(); i++) {	//Loop through the items list
					
					if(itemChoice.equals(aVendingMachine.getList().get(i).getLocation())) { //IF user item and loop matches then....
						//System.out.println(aVendingMachine.getList().get(i).getLocation());
						if(aVendingMachine.getList().get(i).getPrice() <= aVendingMachine.getBalance()) { //IF** balance is >= items price then...
							System.out.println("HI");
						}
						//we want subtract from slow inventory
						//we want to get the price from the object and subtract from the balance 
						
						
						
						
						
					}
				}
			
				} else {
				System.out.println("That is not a valid item!");
				}
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
					
		//****************************************************************************************************
				
			
			
			
			
			
			
			
			
			
			
			
		}

		
		
		
		//aVendingMachine.getList().get().getLocation(userinput); loop through until location equals
		//update balance 
		//update sales
		//update inventory
		// if user presses 3 then finish transaction == true;
		
		}
		
		// static attribute used as method is not associated with specific object instance
		// Code to purchase items from Vending Machine
	
	
	public void endMethodProcessing() { // static attribute used as method is not associated with specific object instance
		// Any processing that needs to be done before method ends
	}
}
