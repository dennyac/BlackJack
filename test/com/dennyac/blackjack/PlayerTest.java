package com.dennyac.blackjack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	
	Player player;
	
	@Before
	public void setUpUsers() {
		player = new Player();
	}

	@Test
	public void testInitialChips() {
		assertEquals(100,player.getChips());
	}
	
	@Test
	public void testAddChips() {
		player.addChips(100);
		assertEquals(200,player.getChips());
	}
	
	@Test
	public void testRemoveChips() {
		player.removeChips(50);
		assertEquals(50,player.getChips());
	}

}
