package com.dennyac.blackjack;

import java.util.ArrayList;

public class Hand {
	private ArrayList<Card> cards;
	private boolean bust;
	private boolean aceExists;
	
	//If faceDown is true the dealer's second cards will be placed face-down
	private boolean faceDown;
	private int score;
	public static final int MAX_VAL = 21;
	
	public Hand() {
		cards = new ArrayList<Card>();
	}
	
	public void setFaceDown(boolean faceDown){
		this.faceDown = faceDown;
	}
	
	public boolean isFaceDown(){
		return this.faceDown;
	}
	
	public ArrayList<Card> getCards(){
		return this.cards;
	}	

	//Returns true if the Hand is BlackJack
	public boolean isBlackJack(){
		return (cards.size()==2 && getScore()==MAX_VAL);
	}
	
	public boolean isBust(){
		return bust;
	}

	//Adds a card to the hand and updates the score
	public void addCard(Card c) {

		cards.add(c);
		if (c.getRank() == Rank.ACE)
			aceExists = true;
		updateScore(c);
		if (getScore() > MAX_VAL)
			bust = true;
	}

	// Updating the score of your hand.
	// Invoked when a new card is added.
	private void updateScore(Card c) {
		score += c.getValue();
	}

	public int getScore() {
		// By default the value of Ace is 1. 
		// If the hand contains an Ace we add 10 to it, if it does not exceed 21
		if (score <= 11 && aceExists)
			return score + 10;
		return score;

	}
	
	//Returns the value of the first card. Will return 11 for Ace
	public int firstCardValue(){
		int value = this.cards.get(0).getValue();
		return value==1? 11:value;
	}
	
	public String toString(){
		if(isFaceDown()){
			return this.cards.get(0) + " " + Card.CardRear();
		}
		else{
			StringBuilder hand = new StringBuilder();
			for (Card card : this.cards) {
				hand.append(card + " ");
			}
			return hand.toString();
		}		
	}

}
