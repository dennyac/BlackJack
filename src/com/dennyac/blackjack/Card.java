package com.dennyac.blackjack;

public class Card {
	private Suit s;
	private Rank r;
	
	public Card(Suit s, Rank r){
		this.s = s;
		this.r = r;
	}
	
	@Override
	public String toString() {
		return "[ " + r + " of " + s + " ]";
	}

	//To render face down cards
	public static String CardRear() {
		return "[ ##### ## ##### ]";
	}
	
	public int getValue(){
			return r.getValue();
	}
	
	public Rank getRank(){
		return r;
	}
}
