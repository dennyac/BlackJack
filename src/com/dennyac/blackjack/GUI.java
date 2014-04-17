package com.dennyac.blackjack;

import java.io.PrintStream;
import java.util.Scanner;

public class GUI {
	private static Scanner sc;
	private static PrintStream out;
	
	public GUI(){
		out = System.out;
		sc = new Scanner(System.in);
	}

	public void initialize() {
		out.println("$$$$ BlackJack $$$$");
		out.println("Congratulations!! You have won $100 chips.");
	}

	public int getMainMenuOption() {
		out.println("1. New Game");
		out.println("2. Get More Chips");
		out.println("3. Exit");
		return getInput();
	}
	
	public int getInGameOptions(Player player) {
		out.println("1. Hit");
		out.println("2. Stand");
		if(player.isEligibleSplit()){
			out.println("3. Split");
		}
		
		return getInput();
	}


	public int getBetAmount() {
		out.println("Enter Bet Amount.");
		return getInput();
	}
	
	public int getChipAmount(){
		out.println("Enter Chip Amount.");
		return getInput();
	}

	public int getInput() {

		String input = "";
		out.print(">> ");
		try {
			input = sc.nextLine();
			int n = Integer.parseInt(input);
			return n;
		} catch (NumberFormatException e) {
			renderValidation("Invalid Input. Please enter valid input.");
			return getInput();
		}
	}
	
	public void render(String user){
		out.println(user);
	}
	
	public void renderWinner(String winner){
		if (winner.equals("Push")) {
			out.println("Its a tie (Push)");
		}
		else {
			out.println("Winner - " + winner);
		}
	}

	public void renderChips(Player p) {
		out.println("Available Chips - $" + p.getChips());
	}

	public void renderValidation(String msg) {
		out.println("Error - " + msg);
	}

	public void exit() {
		sc.close();
		out.println("Exiting Game.");
	}

}
