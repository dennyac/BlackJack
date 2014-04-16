package com.dennyac.blackjack;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardTest {

	@Test
	public void test() {
		Card c = new Card(Suit.HEARTS,Rank.FOUR);
		assertEquals("[ FOUR of HEARTS ]",c.toString());
	}

}
