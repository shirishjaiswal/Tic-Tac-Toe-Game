package com.project1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SoloPlayerMode {
	public static Scanner sc = new Scanner (System.in);
	private Board board;
	private Players [] player;
	private ArrayList<String> availabeIdx;
	private ArrayList<String> decisionList;
	private ArrayList<String> cornorElement;
	
	public void setBoard (Board board) {
		this.board = board;
	}

	void setPlayer (Players [] player) {
		this.player = player;
	}
	
	private void setCornorMidElements() {
		int n = board.getBoardLength()-1;
        cornorElement = new ArrayList<>();
        cornorElement.add("00");
        String s = "0"+n;
        cornorElement.add(s);
        s = n + "0";
        cornorElement.add(s);
        s = n+""+n;
        cornorElement.add(s);
        int midIdx = board.getBoardLength()/2+1;
        String mid = midIdx +""+midIdx;
        cornorElement.add(mid);
	}
	
	public ArrayList<String> startGame () {
		//Injecting value of available cells that are not occupied
		availabeIdx = board.availabeIdx();
		setCornorMidElements();
		return run();
	}
	
	private ArrayList<String> run() {
		decisionList = new ArrayList<>();
		int playerIndex = (int)Math.floor(Math.random() * (0 - 1 + 1) + 0);
		String playerName;
		String playerElement;
		board.displayBoard();
		System.out.println();
		while (true) {
			playerIndex = playerIndex == 0 ? 1 : 0;
			playerName = player[playerIndex].getName();
			playerElement = player[playerIndex].getElement();
			if(!playerName.equals("COMPUTER")) {
				System.out.println(playerName + " its your turn  " + playerElement + " is your tool");
				System.out.print("\nSelect a number where you wanna hit :\t");
			}
			else System.out.print("COMPUTER Hits the cell with element " + playerElement + " :\t");
			
			String idx = "";
			while (true) {
				if(playerIndex == 1) {
					idx = computerElement(playerElement, player[0].getElement());
					System.out.println(idx);
				}
				if (playerIndex == 0){
					idx = sc.next().toUpperCase();
				}
				if (idx.equals("RESET")) {
					decisionList.add("RESET"); 
					return decisionList;
				}
				if(!availabeIdx.contains(idx)) {
					System.out.print("Enter the values which are not accquired :\t");
				}
				else {
					board.removeElement(idx);
					if(cornorElement.contains(idx)) cornorElement.remove(idx);
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
	
	public String computerElement(String computrElement, String userElement){
		Random computerHit = new Random();
		String [] ele = {computrElement, userElement};
        for (int i = 0; i < 2; i++) {
        	for (int j = 0; j < availabeIdx.size(); j++) {
        		String currEle = userElement;
        		String idx = availabeIdx.get(i);
        		int iIdx = (idx.charAt(0) - '0');
    			int jIdx = (idx.charAt(1) - '0');
        		
    			board.setBoardElement(iIdx, jIdx, currEle);
        		if(board.checkWin(iIdx, jIdx, currEle)) {
        			board.setBoardElement(iIdx, jIdx, idx);
        			board.displayBoard();
        			return idx;
        		}
        		else {
        			board.setBoardElement(iIdx, jIdx, idx);
        		}
        	}
        }
        
        for (int i = 0; i < cornorElement.size(); i++) {
        	int index = computerHit.nextInt(cornorElement.size());
        	if(availabeIdx.contains(cornorElement.get(index))) {
        		return cornorElement.get(index);
        	}
        }

    	String idx = "";
        for (int i = 0; i < availabeIdx.size(); i++) {
            int index = computerHit.nextInt(availabeIdx.size());
            idx = availabeIdx.get(index)+"";
        }
        return idx;
    }
}
