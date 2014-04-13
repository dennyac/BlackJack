package com.dennyac.blackjack;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardTest {

	@Test
	public void test() {
		Card c = new Card(Suit.HEARTS,Rank.FOUR);
		assertEquals(c.toString(),"[ FOUR of HEARTS ]");
	}

}
