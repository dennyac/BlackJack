package com.dennyac.blackjack;

public class Dealer extends User {
	private Deck deck;
	public static final int MIN_HAND_VALUE=17;
	
	public Dealer(){
		super();
		deck = new Deck();
		deck.initialize();
		deck.shuffle();
	}
	
	//Reseting data of player and dealer. 
	private void resetData(Player player){
		player.setHand(new Hand());
		this.setHand(new Hand());
		player.setSplitHand(null);
		player.setFirstSplitComplete(false);
		
		//Initially only the first card of the dealer should be shown
		this.getHand().setFaceDown(true);
	}

	public void dealInitial(Player player){
		
		this.resetData(player);
		
		//Dealing two cards to the Player and Dealer
		player.addCard(deck.deal());
		this.addCard(deck.deal());
		player.addCard(deck.deal());
		this.addCard(deck.deal());
	}

	public void deal(Player player){
		player.addCard(deck.deal());
	}
	
	public void dealSplit(Player player){
		player.getSplitHand().addCard(deck.deal());
	}
	
	//Dealer draws cards till hand value is at least 17
	public void autoPlay(){
		
		while(hand.getScore() < MIN_HAND_VALUE ){
			hand.addCard(deck.deal());
		}
		
		//Dealer's complete hand will be displayed when it is rendered next 
		getHand().setFaceDown(false);
	}
}
