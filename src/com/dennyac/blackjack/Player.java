package com.dennyac.blackjack;

public class Player extends User {
	private int chips;
	private Hand splitHand;
	private boolean firstSplitComplete;

	public boolean isSplit() {
		return splitHand != null;
	}
	
	//Returns true if the first hand of the split is complete
	public boolean isFirstSplitComplete(){
		return firstSplitComplete;
	}
	
	public void setFirstSplitComplete(boolean value){
		 firstSplitComplete = value;
	}
	
	public void setSplitHand(Hand splitHand){
		this.splitHand=splitHand;
	}
	
	public Hand getSplitHand(){
		return splitHand;
	}

	public void splitHand() {
		Card card = this.getHand().getCards().get(1);
		splitHand = new Hand();
		splitHand.addCard(card);
		this.getHand().removeCard();
	}

	public boolean isEligibleSplit() {
		return (this.hand.getCards().size() == 2 && (this.hand.getCards()
				.get(0).getRank() == this.hand.getCards().get(1).getRank()));
	}

	public Player(int chips) {
		super();
		this.chips = chips;
	}

	public Player() {
		this(BlackJack.INITIAL_CHIPS);
	}

	public int getChips() {
		return chips;
	}

	public void addChips(int value) {
		chips += value;
	}

	public void removeChips(int value) {
		chips -= value;
	}

}
