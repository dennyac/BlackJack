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
	
	
	//Passing the hand as parameter so that we can display both hands in the event of a "split"
	public String display(Hand hand){
		//Checks whether the object is an instance of Dealer or Player
				String objectType = this instanceof Dealer? "Dealer":"Player";
				
				StringBuilder output = new StringBuilder();
				
				output.append(objectType + "'s Cards - ");
				output.append(System.lineSeparator());
				
				//if faceDown is set it will display the first card, otherwise it will display the entire hand.
				output.append(hand);
				output.append(System.lineSeparator());
				
				output.append(objectType + "'s Score - ");
				
				//if faceDown is set display score of first card, otherwise display the entire score.
				if(hand.isFaceDown()){
					output.append(hand.firstCardValue());
				}
				else{
					output.append(hand.getScore());
				}
				
				//Append the status of the hand
				if (hand.isBlackJack()) {
					output.append(" (BlackJack!!)");
				} else if (hand.isBust()) {
					output.append(" (Bust)");
				}
				output.append(System.lineSeparator());
				return output.toString();
	}
	
	
	//Displays the User's cards and current score.
	public String toString(){
		return this.display(this.hand);
	}
	
}
