package com.dennyac.blackjack;

import static org.junit.Assert.*;

import org.junit.Test;

public class RankTest {

	@Test
	public void testQueen() {
		Rank r = Rank.QUEEN;
		assertEquals(10,r.getValue());
	}
	@Test
	public void testAce() {
		Rank r = Rank.ACE;
		assertEquals(1,r.getValue());
	}

}
