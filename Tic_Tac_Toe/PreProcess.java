package com.project1;

import java.util.ArrayList;
import java.util.Scanner;

public class PreProcess {
	public static Scanner sc = new Scanner (System.in);
	
	private int mode = 0;
	private Players [] playerList;
	private Board board;
	
	public void launch () {
		startGame();
		return;
	}
//	selecting option to play in which mode
	public void startGame () {
		System.out.println("Select Playing Mode");
		System.out.println("1\t-\tSolo Playing");
		System.out.println("2\t-\tTwo-Player");
		System.out.println("3\t-\tExit\n");
		
		
		String inMode;
		boolean isValid = false;
		
		while (!isValid) {
			System.out.print("In which mode you want to play :\t");
			inMode = sc.next().toUpperCase();
			if(inMode.equals("3") || inMode.equals("EXIT")) return;
			try {
				mode = Integer.parseInt(inMode);
				if (mode == 1 || mode == 2) {
					isValid = true;
				}
				else {
					System.out.println("Please select from given option");
					isValid = false;
				}
			}
			catch (Exception e) {
				System.out.println("Please enter correct option");
			}
		}
		
		if(mode == 2) twoplayer();
		else soloPlayer();
		
	}
	
	private void setBoardAndGameRules () {
		board = new Board();
		board.setBoardSize();
		System.out.println("Lets Start The Game");
		System.out.println("\nRule 1 : Each two digit number means a cell \n"
				+ "Rule 2 : Enter that number where you want to place your tool\n"
				+ "Rule 3 : At any point of game enter RESET to reset the game\n" 
				+ "\nPress any Key to Continue  -->");
		sc.nextLine();sc.nextLine();
	}
	
	private void soloPlayer() {
		System.out.println("\nYou have slelected Solo-Player Mode\n");
		
		playerList = new Players[2];
		
		for (int i = 0; i < mode; i++) {
			Players p1 = new Players();
			p1.setName(i+1);
			if(p1.getName().equals("EXIT")) return;
			p1.setElement();
			playerList[i] = p1;
			System.out.println(playerList[i].getName() + "'s tool\t:\t" + playerList[i].getElement());
			Players p2 = new Players("COMPUTER", p1.getElement().equals("X") ? "O" : "X");
			playerList[i+1] = p2;
			System.out.println("\n"+playerList[i+1].getName() + "'s tool\t:\t" + playerList[i+1].getElement());
			System.out.println();
		}
		setBoardAndGameRules();
		
		SoloPlayerMode gameInitialize = new SoloPlayerMode();
		gameInitialize.setBoard(board);
		gameInitialize.setPlayer(playerList);
		
		ArrayList<String> winDrawReset = new ArrayList<>();
		winDrawReset = gameInitialize.startGame();
		
		decision (winDrawReset);
		return;
	}
	
	private void twoplayer () {
		System.out.println("\nYou have slelected Two-Player Mode\n");
		
		playerList = new Players[2];
		
		for (int i = 0; i < mode; i++) {
			Players p1 = new Players();
			p1.setName(i+1);
			if(p1.getName().equals("EXIT")) return;
			if(i == 1) {
				while (p1.getName().equals(playerList[0].getName())) {
					System.out.print("Both players cant have same name\nPlease Enter correctly :\t");
					p1.setName(i+1);
				}
				if(playerList[0].getElement().equals("X")) p1.setElement("O");
				else p1.setElement("X");
				System.out.println(p1.getName() + "'s tool\t:\t" + p1.getElement());
			}
			else {
				p1.setElement();
				System.out.println(p1.getName() + "'s tool\t:\t" + p1.getElement());
			}
			playerList[i] = p1;
			System.out.println();
		}
		setBoardAndGameRules();
		
		TwoPlayerMode gameInitialize = new TwoPlayerMode();
		gameInitialize.setBoard(board);
		gameInitialize.setPlayer(playerList);
		
		ArrayList<String> winDrawReset = new ArrayList<>();
		winDrawReset = gameInitialize.startGame();
		
		decision (winDrawReset);
		return;
	}
	private void decision (ArrayList<String> list) {
		if(list.contains("RESET")) {
			startGame();
		}
		if (list.contains("DRAW")) {
			System.out.println("X O X   DRAW!!... Better Luck Next Time...!!   X O X");
		}
		if(list.size() > 1) {
			Object ele = list.get(1);
			System.out.println(ele+" "+ele+" "+ele+"   WwOwW "+list.get(0)+" is the WINNER of the Match   "+ele+" "+ele+" "+ele);
		}
	}

}
