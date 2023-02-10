package com.project1;

import java.util.Scanner;

public class Players {
	public static Scanner sc = new Scanner (System.in);
	private String name;
	private String element;
	
	Players () {	
	}
	Players (String name, String element){
		this.name = name;
		this.element = element;
	}
	//setting name
	public void setName(int playerIdx) {
		this.name = "";
		System.out.print("Enter player - " + (playerIdx) + " name \t\t:\t");
		while (name.length() < 1) {
			this.name = sc.nextLine().toUpperCase();
		}
	}
	
	//setting Element (X or O)
	public void setElement() {
		String element = "";
		while (true) {
			if (element.equals("X") || element.equals("O")) break;
			System.out.print("Select Proper Tool\tX OR O\t:\t");
			element = sc.nextLine().toUpperCase();
		}
		this.element = element;
	}
	public void setElement(String element) {
		this.element = element;
	}
	
	//getting name
	public String getName() {
		return this.name;
	}
	
	//getting Element (X or O)
	public String getElement() {
		return this.element;
	}
}
