import java.util.ArrayList;

public class BlackjackGame {
	private Deck deck;
	private Player[] players;
	private Dealer dealer;

	public BlackjackGame(Player[] allPlayers, Dealer theDealer, Deck inputDeck) {
		players = allPlayers;
		deck = inputDeck;
		dealer = theDealer;

		deck.shuffle();
	}

	public void hitPlayer(int playerToHit) {
		if (!players[playerToHit].hasEndedTurn()) {
			players[playerToHit].addToHand(deck.deal());
			players[playerToHit].checkAce();
		}
	}

	public void hitDealer() {
		if (!dealer.hasEndedTurn() && dealer.dealerChoice().equals("hit")) {
			dealer.addToHand(deck.deal());
			dealer.checkAce();
		}
	}

	public void startPlaying() {
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < players.length; i++) {
				hitPlayer(i);
			}

			hitDealer();
		}
	}

	public ArrayList<Player> determineWinners(Player[] players, Dealer dealer) {
		ArrayList<Player> winners = new ArrayList<Player>();

		if (!dealer.canHit()) {
			for (Player player : players) {
				if (player.canHit()) {
					winners.add(player);
				}
			}
		}

		else {
			for (Player player : players) {
				if (player.canHit() && (player.getTotal() > dealer.getTotal()
						|| (player.getTotal() == 21 && player.getHand().size() == 2))) {
					winners.add(player);
				}
			}
		}

		return winners;
	}
}
