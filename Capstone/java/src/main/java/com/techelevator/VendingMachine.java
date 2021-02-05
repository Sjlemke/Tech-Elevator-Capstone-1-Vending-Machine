package com.techelevator;

import java.util.ArrayList;

public class VendingMachine {
	
	private ArrayList<Item> list =  new ArrayList();			//Needs to get inventory slots 
	
		
	
	
	
	//**************************************************************************************************
	public ArrayList<Item> getList() {
		return list;
	}


	public void setList(ArrayList<Item> list) {
		this.list = list;
	}


	

    //*****************************************************************************************************
	public VendingMachine(ArrayList<Item> list) {
		this.list = list;
	}
	
	

}
