public class Card {
	/**
	 * String value that holds the suit of the card
	 */
	private String suit;

	/**
	 * String value that holds the rank of the card
	 */
	private String rank;

	/**
	 * int value that holds the point value.
	 */
	private int pointValue;

	/**
	 * Creates a new Card instance.
	 */
	public Card(String cardRank, String cardSuit, int cardPointValue) {
		rank = cardRank;
		suit = cardSuit;
		pointValue = cardPointValue;
	}

	/**
	 * Accesses this Card's suit.
	 */
	public String suit() {
		return suit;
	}

	/**
	 * Accesses this Card's rank.
	 */
	public String rank() {
		return rank;
	}

	/**
	 * Accesses this Card's point value.
	 */
	public int pointValue() {
		return pointValue;
	}

	/**
	 * Compare this card with the argument.
	 */
	public boolean matches(Card otherCard) {
		return (this.rank.equals(otherCard.rank) && this.suit.equals(otherCard.suit)
				&& this.pointValue == otherCard.pointValue);
	}

	/**
	 * Converts the rank, suit, and point value into a string in the format
	 */
	@Override
	public String toString() {
		return this.rank + " of " + this.suit + " (point value = " + this.pointValue + ")";
	}
}

