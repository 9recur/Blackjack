import java.util.ArrayList;
import java.util.List;

public class Person {
	private List<Card> hand;
	private boolean turnEnded;
	private boolean inGame;
	private int handValue;

	public Person() {
		hand = new ArrayList<Card>();
		inGame = true;
		turnEnded = false;
	}

	public List<Card> getHand() {
		return hand;
	}

	public Card getLastCard() {
		return hand.get(hand.size() - 1);
	}

	public int getTotal() {
		int total = 0;

		for (Card c : hand) {
			total += c.pointValue();
		}

		handValue = total;

		checkAce();

		return handValue;
	}

	public boolean canHit() {
		if (getTotal() > 21) {
			inGame = false;
		}

		return inGame;
	}

	public void endTurn() {
		turnEnded = true;
	}

	public boolean hasEndedTurn() {
		return turnEnded;
	}

	public void addToHand(Card newCard) {
		hand.add(newCard);
	}

	public void checkAce() {
		for (Card c : hand) {
			if (c.rank().equals("Ace")) {
				if (handValue > 21) {
					handValue = handValue - 10;
				}
			}
		}
	}
}