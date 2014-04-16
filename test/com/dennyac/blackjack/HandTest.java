package com.dennyac.blackjack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HandTest {
	
	Hand hand;
	
	@Before
	public void setUpHand() {
		hand = new Hand();
	}

	@Test
	public void testBlackJack() {
		hand.addCard(new Card(Suit.SPADES,Rank.ACE));
		hand.addCard(new Card(Suit.HEARTS,Rank.KING));
		assertTrue(hand.isBlackJack());
	}
	
	@Test
	public void testBust() {
		hand.addCard(new Card(Suit.SPADES,Rank.JACK));
		hand.addCard(new Card(Suit.HEARTS,Rank.KING));
		hand.addCard(new Card(Suit.DIAMONDS,Rank.SEVEN));
		assertTrue(hand.isBust());
	}
	
	@Test
	public void testScoreHighAce() {
		hand.addCard(new Card(Suit.SPADES,Rank.ACE));
		hand.addCard(new Card(Suit.HEARTS,Rank.KING));
		assertEquals(21,hand.getScore());
	}
	
	@Test
	public void testScoreLowAce() {
		hand.addCard(new Card(Suit.SPADES,Rank.ACE));
		hand.addCard(new Card(Suit.HEARTS,Rank.NINE));
		hand.addCard(new Card(Suit.HEARTS,Rank.FIVE));
		assertEquals(15,hand.getScore());
	}
	
	@Test
	public void testFirstCard() {
		hand.addCard(new Card(Suit.SPADES,Rank.ACE));
		hand.addCard(new Card(Suit.HEARTS,Rank.NINE));
		hand.addCard(new Card(Suit.HEARTS,Rank.FIVE));
		assertEquals(11,hand.firstCardValue());
	}
	

}
