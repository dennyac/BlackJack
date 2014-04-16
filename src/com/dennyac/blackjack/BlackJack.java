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
				gui.exit();
				break;
			default:
				gui.renderValidation("Invalid Option. Please select a valid option.");
			}
		} while (menuOption != 2);

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
			int inGameOption = gui.getInGameOptions();
			switch (inGameOption) {
			case 1:
				// Dealer deals an additional card to the player
				dealer.deal(player);
				break;
			case 2:
				// Dealer hits till his hand value is 17 or greater
				dealer.autoPlay();
				break;
			default:
				gui.renderValidation("Invalid Option. Please select a valid option.");
			}
		}
	}

	// Checks whether the game is over, and decides the winner.
	private boolean isGameOver() {
		boolean gameOver = false;

		// Conditions for the game to be completed
		// 1. If the player or dealer goes bust
		// 2. If the player or dealer gets a blackjack
		// 3. If the player's hand value is 21
		// 4. If the dealer has played his hand (After the dealer plays his hand the faceDown flag is set to false)
		if (player.getHand().isBlackJack() || player.getHand().isBust()
				|| dealer.getHand().isBlackJack() || dealer.getHand().isBust()
				|| player.getHand().getScore() == Hand.MAX_VAL
				|| !dealer.getHand().isFaceDown()) {
			gameOver = true;

			//Set the faceDown flag if the game ends before the dealer plays his hand.
			dealer.getHand().setFaceDown(false);
		}

		if (gameOver) {
			// Decide and display the winner of this round.
			decideWinner();
		} else {
			// Render intermediate scores
			gui.renderUser(dealer);
			gui.renderUser(player);
			gui.renderChips(player);
		}
		return gameOver;
	}

	// Decides who wins this round of BlackJack
	private void decideWinner() {
		String winner;
		
		if (player.getHand().isBust()
				|| ((dealer.getHand().getScore() > player.getHand().getScore()) && !dealer
						.getHand().isBust())) {
			winner = "Dealer";
		} else if (dealer.getHand().isBust()
				|| (dealer.getHand().getScore() < player.getHand().getScore())) {
			winner = "Player";
			player.addChips(betAmount * 2);
		} else {
			//When both have equal scores
			winner = "Push";
			player.addChips(betAmount);
		}
		gui.renderUser(dealer);
		gui.renderUser(player);
		gui.renderWinner(winner);
		gui.renderChips(player);
	}

	public static void main(String[] args) {
		BlackJack b = new BlackJack();
		b.initialize();
		b.startGame();
	}

}
