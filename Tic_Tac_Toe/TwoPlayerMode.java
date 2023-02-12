package com.project1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TwoPlayerMode {
	public static Scanner sc = new Scanner (System.in);
	private Board board;
	private Players [] player;
	private ArrayList<String> availabeIdx;
	private ArrayList<String> decisionList; //for making decision win or not
	private Random rand; 
	
	public void setBoard (Board board) {
		this.board = board;
	}
	
	//Has-A
	void setPlayer (Players [] player) {
		this.player = player;
	}
	
	public ArrayList<String> startGame () {
		//Injecting value of available cells that are not occupied
		availabeIdx = board.availabeIdx();
		return run();
	}
	
	private ArrayList<String> run() {
		decisionList = new ArrayList<>();
		rand = new Random();
		int playerIndex = rand.nextInt(2);
		String playerName;
		String playerElement;
		board.displayBoard();
		System.out.println();
		while (true) {
			playerIndex = playerIndex == 1 ? 0 : 1;
			playerName = player[playerIndex].getName();
			playerElement = player[playerIndex].getElement();
			System.out.println(playerName + " its your turn  " + playerElement + " is your tool");
			System.out.print("\nSelect a number where you wanna hit :\t");
			String idx;
			while (true) {
				idx = sc.next().toUpperCase();
				if (idx.equals("RESET")) {
					decisionList.add("RESET"); 
					return decisionList;
				}
				if(!availabeIdx.contains(idx)) {
					System.out.print("Enter the values which are not accquired :\t");
				}
				else {
					board.removeElement(idx);
					break;
				}
			}
			int iIdx = (idx.charAt(0) - '0');
			int jIdx = (idx.charAt(1) - '0');
			board.setBoardElement(iIdx, jIdx, playerElement);
			board.displayBoard();
			System.out.println();
			if(board.checkWin(iIdx, jIdx, playerElement)) {
				decisionList.add(playerName);
				decisionList.add(playerElement);
				return decisionList;
			}
			else if (board.checkDraw ()) {
				decisionList.add("DRAW");
				return decisionList;
			}
		}
	}
}
