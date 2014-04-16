package com.dennyac.blackjack;

public class Player extends User {
	private int chips;
	
	public Player(int chips){
		super();
		this.chips = chips;
	}
	
	public Player(){
		this(BlackJack.INITIAL_CHIPS);
	}
	
	public int getChips(){
		return chips;
	}
	
	public void addChips(int value){
		chips += value;
	}
	
	public void removeChips(int value){
		chips -= value;
	}
	
	
}
