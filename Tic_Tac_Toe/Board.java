package com.project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
//creating board and its behavior to perform actions
public class Board {
	Scanner sc = new Scanner (System.in);
	private int boardSize;
	private String [][] board;
	private ArrayList<String> availabeIdx;
	
	//setBoardSize
	public void setBoardSize () {
		System.out.print("Board size availabe : 3 (Recommended) and 4\nEnter boardSize \t:\t");
		setSizeTry();
		while (this.boardSize != 3 && this.boardSize != 4) {
			System.out.println("Sorry more the board size You get bored of playing\nSelect from 3 and 4");
			setSizeTry();
		}
		createBoard();
	}
	
	public void setSizeTry () {
		try {
			this.boardSize = sc.nextInt();
		}
		catch (Exception e) {
			System.out.println("Error : Please enter valid input");
			sc.next();
			setBoardSize();
		}
	}
	//creating board and adding values in the arrayList to make all the index available
	private void createBoard () {
		availabeIdx = new ArrayList<>();
		this.board = new String [this.boardSize][this.boardSize];
		for (int i = 0; i < this.boardSize; i++) {
			for (int j = 0; j < this.boardSize; j++) {
				String idx = i+""+j;
				board[i][j] = idx;
				availabeIdx.add(idx);
			}
		}
	}
	public int getBoardLength () {
		return board.length;
	}
	public void setBoardElement (int i, int j, String element) {
		this.board[i][j] = element;
	}
	public String getBoardElement (int i, int j) {
		return this.board[i][j];
	}
	public void removeElement (String element) {
		this.availabeIdx.remove(element);
	}
	public void displayBoard () {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				System.out.print(board[i][j] + "\t");
			}
			System.out.println();
		}
	}
	public ArrayList<String> availabeIdx() {
		return this.availabeIdx;
	}
	
	//To check does the player won the game
	public boolean checkWin(int row, int col, String element) {
		
		int n = getBoardLength();
		int count = 0;
		//row
		for (int i = 0; i < n; i++) {
			if (getBoardElement(row, i) != element) break;
			else count++;
		}
		if(count != n) count = 0;
		else return true;
		
		//column
		for (int i = 0; i < n; i++) {
			if (getBoardElement(i, col) != element) break;
			else count++;
		}
		if(count != n) count = 0;
		else return true;
		
		//diagonal 1
		for (int i = 0, j = col-row; (i >= 0 && i < n) && (j >= 0 && j < n); i++, j++) {
			if (getBoardElement(i, j) != element) break;
			else count++;
		}
		if(count != n) count = 0;
		else return true;
		
		//diagonal 2
		for (int i = 0, j = col+row; (i >= 0 && i < n) && (j >= 0 && j < n); i++, j-- ) {
			if (getBoardElement(i, j) != element) break;
			else count++;
		}
		if(count != n) count = 0;
		else return true;
		
		return false;
	}
	//If arrayList is empty that means there is no location empty so its draw
	public boolean checkDraw () {
		if (this.availabeIdx.size() == 0 ) {
			return true;
		}
		return false;
	}
}
