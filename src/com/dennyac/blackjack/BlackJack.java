package com.dennyac.blackjack;

public class BlackJack {

	GUI gui = new GUI();
	private Player player;
	private Dealer dealer;
	private int betAmount;
	public static final int INITIAL_CHIPS = 100;

	public void initialize() {

		dealer = new Dealer();

		// Initializing Player with 100 chips.
		player = new Player(INITIAL_CHIPS);
	}

	public void startGame() {
		gui.initialize();
		int menuOption;

		// Display the Main Menu
		do {
			menuOption = gui.getMainMenuOption();
			switch (menuOption) {
			case 1:
				newGame();
				break;
			case 2:
				player.addChips(gui.getChipAmount());
				gui.renderChips(player);
				break;
			case 3:
				gui.exit();
				break;
			default:
				gui.renderValidation("Invalid Option. Please select a valid option.");
			}
		} while (menuOption != 3);

	}

	public void newGame() {

		if (player.getChips() == 0) {
			gui.renderValidation("You ran out of chips. Please get more chips and come back!");
			return;
		}

		// Get the initial betAmount from user and deal the initial cards.
		do {
			gui.renderChips(player);
			betAmount = gui.getBetAmount();
			if (player.getChips() < betAmount) {
				gui.renderValidation("You don't have enough chips.");
			} else if (betAmount < 1) {
				gui.renderValidation("Minimum bid amount is $1.");
			} else {
				player.removeChips(betAmount);

				// Dealer deals initial cards to player and self
				dealer.dealInitial(player);
				break;
			}
		} while (player.getChips() < betAmount || betAmount < 1);
		

		// Display in-game options till the current game is not over.
		while (!isGameOver()) {
			int inGameOption = gui.getInGameOptions(player);
			switch (inGameOption) {
			case 1:
				// Dealer deals an additional card to the player
				if (player.isSplit() && player.isFirstSplitComplete())
					dealer.dealSplit(player);
				else
					dealer.deal(player);
				break;
			case 2:
				// Dealer hits till his hand value is 17 or greater
				if (player.isSplit() && !player.isFirstSplitComplete()) {
					player.setFirstSplitComplete(true);
					dealer.dealSplit(player);
				} else {
					dealer.autoPlay();
				}
				break;
			case 3:
				if (!player.isEligibleSplit()) {
					gui.renderValidation("Invalid Option. Please select a valid option.");
				} else {
					player.splitHand();
					player.removeChips(betAmount);
					dealer.deal(player);

				}
				break;
			default:
				gui.renderValidation("Invalid Option. Please select a valid option.");
			}
		}
	}

	// Checks whether the game is over, and decides the winner.
	private boolean isGameOver() {
		String winner;
		boolean gameOver = false;
		Hand playerHand;
		
		
		if (player.isSplit() && player.isFirstSplitComplete()) {
			//Considering the second hand of the split
			playerHand = player.getSplitHand();
		} else {
			playerHand = player.getHand();
		}

		// Conditions for the game to be completed
		// 1. If the player or dealer goes bust
		// 2. If the player or dealer gets a blackjack
		// 3. If the player's hand value is 21
		// 4. If the dealer has played his hand (After the dealer plays his hand
		// the faceDown flag is set to false)
		if (playerHand.isBlackJack() || playerHand.isBust()
				|| dealer.getHand().isBlackJack() || dealer.getHand().isBust()
				|| playerHand.getScore() == Hand.MAX_VAL
				|| !dealer.getHand().isFaceDown()) {

			if (!player.isSplit()) {
				// Set the faceDown flag if the game ends before the dealer plays
				// his hand.
				dealer.getHand().setFaceDown(false);
				gameOver = true;
			}

			
			if (player.isSplit()) {
				if (player.isFirstSplitComplete()) {
					
					//Both hands of split have been completed
					dealer.getHand().setFaceDown(false);
					gameOver = true;
					
					if ((!player.getHand().isBlackJack() && !player.getHand()
							.isBust())
							|| (!player.getSplitHand().isBlackJack() && !player
									.getHand().isBust())) {
						dealer.autoPlay();
					}
				} else {
					//Setting this flag to denote that the first hand of the split is complete
					player.setFirstSplitComplete(true);
				}
			}

		}
		
		//Display the scores of the player and dealer
		gui.render(dealer.toString());
		if (player.isSplit()) {
			gui.render(player.display(player.getHand()));
			gui.render(player.display(player.getSplitHand()));
		} else {
			gui.render(player.display(player.getHand()));	
		}

		if (gameOver) {
			// Decide and display the winner of this round.
			if (player.isSplit()) {
				winner = decideWinner(player.getHand(), dealer.getHand());
				gui.renderWinner(winner);
				winner = decideWinner(player.getSplitHand(), dealer.getHand());
				gui.renderWinner(winner);
			} else {
				winner = decideWinner(player.getHand(), dealer.getHand());
				gui.renderWinner(winner);
			}
			

		}
		gui.renderChips(player);
		return gameOver;
	}

	// Decides who wins this round of BlackJack
	private String decideWinner(Hand playerHand, Hand dealerHand) {
		if (playerHand.isBust()
				|| ((dealerHand.getScore() > playerHand.getScore()) && !dealerHand
						.isBust())) {
			return "Dealer";
		} else if (dealerHand.isBust()
				|| (dealerHand.getScore() < playerHand.getScore())) {
			player.addChips(betAmount * 2);
			return "Player";
		} else {
			// When both have equal scores
			player.addChips(betAmount);
			return "Push";
		}
	}

	public static void main(String[] args) {
		BlackJack b = new BlackJack();
		b.initialize();
		b.startGame();
	}

}
