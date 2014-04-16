package com.dennyac.blackjack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DealerTest {
	
	Dealer dealer;
	Player player;
	
	@Before
	public void setUpUsers() {
		dealer = new Dealer();
		player = new Player();
	}

	@Test
	public void testDealInitial() {
		dealer.dealInitial(player);
		assertEquals(2,dealer.getHand().getCards().size());
		assertEquals(2,player.getHand().getCards().size());
		assertEquals(true,dealer.getHand().isFaceDown());
	}
	
	@Test
	public void testAutoPlay() {
		dealer.dealInitial(player);
		dealer.autoPlay();
		assertTrue("Hand value is under 17",dealer.getHand().getScore()>=Dealer.MIN_HAND_VALUE);
	}

}
