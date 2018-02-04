import java.util.ArrayList;
import java.util.Scanner;

/**
 * Simulates a game of Blackjack (21) with any number of players.
 */
public class BlackjackDriver {
	public static void main(String[] args) {
		final String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
		final String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		final int[] values = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };

		Deck blackJack = new Deck(ranks, suits, values);

		System.out.println("Welcome to Black Jack, a popular card game simulated on a computer!\n");
		System.out
				.println("Objective: The aim of the player is to achieve a hand whose points \n\t   total nearer to 21 "
						+ "than the dealer's hand, but without exceeding 21. \n");
		System.out.println("Specific Rules: No splitting or surrendering. Dealer hits on 16 stands on 17.\n"
				+ "                2 - 10 are face value. Royals are 10. Ace can be 1 or 11.\n");

		Scanner in = new Scanner(System.in);
		System.out.print("How many players? ");
		int numberOfPlayers = in.nextInt();

		in.nextLine();

		System.out.println();

		Player[] players = new Player[numberOfPlayers];
		String[] playerNames = new String[numberOfPlayers];

		for (int i = 0; i < numberOfPlayers; i++) {
			System.out.print("Player " + (i + 1) + ", enter your name: ");
			playerNames[i] = in.nextLine();
		}

		System.out.println();

		for (int i = 0; i < numberOfPlayers; i++) {
			players[i] = new Player(playerNames[i]);
		}

		Dealer dealer = new Dealer();
		BlackjackGame game = new BlackjackGame(players, dealer, blackJack);

		game.startPlaying();

		System.out.println("One of the dealer's cards is ...");
		System.out.println("\t" + dealer.getHand().get(0) + "\n");

		boolean playersGameOver = false;

		int count = 0;

		while (!playersGameOver) {
			for (int i = 0; i < numberOfPlayers; i++) {
				System.out.print(playerNames[i] + ", Hit <Enter> to check your hand! ");
				in.nextLine();

				System.out.println();

				players[i].showHand();
				System.out.println("Total Points: " + players[i].getTotal());

				if (!players[i].hasEndedTurn()) {
					System.out.print("\nEnter h to HIT or s to STAY: ");
					String response = in.next();

					while (!(response.equalsIgnoreCase("h") || response.equalsIgnoreCase("s"))) {
						System.out.print("Invalid Option. Please enter a valid option: ");
						String retry = in.next();
						response = retry;
					}

					if (response.equalsIgnoreCase("h")) {
						game.hitPlayer(i);
						System.out.println("You took a " + players[i].getLastCard().toString() + "\n");

						players[i].showHand();
						System.out.println("Total Points: " + players[i].getTotal());
					}

					else {
						players[i].endTurn();
						count++;
					}

					in.nextLine();
				}

				if (players[i].getTotal() > 21) {
					System.out.println("\n *** Sorry, You bust! *** ");
				}

				if (players[i].hasEndedTurn()) {
					System.out.println("\n *** You have ended you turn. *** ");
				}

				if (count == numberOfPlayers) {
					playersGameOver = true;
				}

				System.out.print("\nHit <Enter> to pass on to the next player! ");
				in.nextLine();

				System.out.println("\n\n\n\n\n\n\n\n");
			}
		}

		boolean dealerGameOver = false;

		System.out.println("Dealer is playing ...\n");

		while (!dealerGameOver) {
			if (dealer.dealerChoice().equals("hit")) {
				game.hitDealer();
			}

			else if (dealer.dealerChoice().equals("stay")) {
				dealer.endTurn();
			}

			if (dealer.hasEndedTurn() || !dealer.canHit()) {
				dealerGameOver = true;
			}
		}

		dealer.dealerShow();

		ArrayList<Player> winners = game.determineWinners(players, dealer);

		System.out.println("\nAnd the winner(s) is/are: \n");

		if (winners.size() > 0) {
			for (Player winner : winners) {
				System.out.println(winner.getName() + "!");
				System.out.println("Total Points: " + winner.getTotal() + "\n");
			}
		}

		else {
			System.out.println("*** The dealer wins! ***");
		}

		System.out.println("\nThanks for playing! Come again soon.");
		in.close();
	}
}