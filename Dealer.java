public class Dealer extends Person {
	public void dealerShow() {
		System.out.println("Dealer's Hand: ");
		for (Card c : super.getHand()) {
			System.out.println("\t" + c.toString());
		}
		System.out.println("Total Points: " + super.getTotal());
	}

	public String dealerChoice() {

		if (getTotal() < 17) {
			return "hit";
		}

		else {
			return "stay";
		}
	}
}