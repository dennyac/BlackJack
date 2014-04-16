package com.dennyac.blackjack;

public abstract class User {
	protected Hand hand;
	
	public User(){
		this.hand = new Hand();
	}

	public Hand getHand(){
		return this.hand;
	}

	public void setHand(Hand hand){
		this.hand = hand;
	}
	

	public void addCard(Card card){
		hand.addCard(card);
	}
	
	
	//Displays the User's cards and current score.
	public String toString(){

		//Checks whether the object is an instance of Dealer or Player
		String objectType = this instanceof Dealer? "Dealer":"Player";
		
		StringBuilder output = new StringBuilder();
		
		output.append(objectType + "'s Cards - ");
		output.append(System.lineSeparator());
		
		//if faceDown is set it will display the first card, otherwise it will display the entire hand.
		output.append(this.hand);
		output.append(System.lineSeparator());
		
		output.append(objectType + "'s Score - ");
		
		//if faceDown is set display score of first card, otherwise display the entire score.
		if(this.hand.isFaceDown()){
			output.append(getHand().firstCardValue());
		}
		else{
			output.append(getHand().getScore());
		}
		
		//Append the status of the hand
		if (getHand().isBlackJack()) {
			output.append(" (BlackJack!!)");
		} else if (getHand().isBust()) {
			output.append(" (Bust)");
		}
		output.append(System.lineSeparator());
		return output.toString();
		
	}
	
}
