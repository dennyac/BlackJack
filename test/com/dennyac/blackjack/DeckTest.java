package com.dennyac.blackjack;

import static org.junit.Assert.*;

import org.junit.Test;

public class DeckTest {

	@Test
	public void testDeckSize() {
		Deck d = new Deck();
		d.initialize();
		d.shuffle();
		assertEquals(52,d.size());		
	}


	// Checking whether a new deck is added after the existing deck is over. 
	@Test
	public void testNewDeck() {
		Deck d = new Deck();
		d.initialize();
		d.shuffle();
		for(int i =0;i<100;i++){
			d.deal();
		}
		
	}

}
