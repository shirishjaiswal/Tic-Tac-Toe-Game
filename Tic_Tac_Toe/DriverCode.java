package com.project1;

import java.util.Scanner;

public class DriverCode {
	public static Scanner sc = new Scanner (System.in);
	public static void main(String[] args) {
		//Start
		System.out.println("===\tTIC  -  TAC  -  TOE\t===");
		isPlay();
	}
	//play or exit
	public static void isPlay () {
		String play = isvalid();
		
		while (play.equals("1") || play.equals("PLAY")) {
			System.out.println();
			PreProcess start = new PreProcess();	//object that will initialize the game
			start.launch();
			play = isvalid();
		}
		System.out.println("---  Thanks Welcome Back  ---");
	}
	
	//take input for play or exit
	public static String isvalid() {
		System.out.println("\n===  WANNA PLAY??  ===\n1 -\tPLAY\n2 -\tEXIT");
		String play = "";
		boolean isValid = false;
		while (!isValid) {
			try {
				play = sc.nextLine().toUpperCase();
				if(!play.equals("1")  && !play.equals("PLAY") && !play.equals("2")  && !play.equals("EXIT")) System.out.print("Please select appropriate option :\t");
				else isValid = true;
			}
			catch (Exception e) {
				System.out.print("Please enter appropriate input :\t");
			}
		}
		return play;
	}
}
