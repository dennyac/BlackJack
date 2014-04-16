package com.dennyac.blackjack;

import java.util.Collections;
import java.util.Stack;

public class Deck {

	private Stack<Card> cards;

	//Initialize deck of cards
	public void initialize() {
		cards = new Stack<Card>();
		for (Suit s : Suit.values()) {
			for (Rank r : Rank.values()) {
				cards.push(new Card(s,r));
			}
		}
	}
	
	public int size(){
		return cards.size();
	}

	//Shuffle deck of cards (Fisher–Yates shuffle)
	public void shuffle() {
		Collections.shuffle(this.cards);
	}
	
	public Card deal(){
		
		//If the current deck is empty, initialize a new deck and then shuffle it.
		if(this.isEmpty()){
			initialize();
			shuffle();
		}
		return this.cards.pop();
	}
	
	public boolean isEmpty(){
		return this.cards.empty();
	}

}
