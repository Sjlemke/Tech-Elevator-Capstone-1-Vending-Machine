package com.techelevator;

import java.util.ArrayList;

public class VendingMachine {
	
	private ArrayList<Item> list =  new ArrayList();			//Needs to get inventory slots 
	
	private double balance;
	
	//**************************************************************************************************
	public ArrayList<Item> getList() {
		return list;
	}


	public void setList(ArrayList<Item> list) {
		this.list = list;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}

    //*****************************************************************************************************
	public VendingMachine(ArrayList<Item> list) {
		this.list = list;
	}
	
	

}
