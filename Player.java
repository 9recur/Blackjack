public class Player extends Person {
	private String name;

	public Player(String playerName) {
		name = playerName;
	}

	public String getName() {
		return name;
	}

	public void showHand() {
		System.out.println(name + ", this is your hand: ");
		for (Card c : super.getHand()) {
			System.out.println("\t" + c.toString());
		}
	}
}