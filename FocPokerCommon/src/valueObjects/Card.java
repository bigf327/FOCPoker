package valueObjects;

/**
 * 
 * @author Oke Schwien, Christoph Schuette, Fabian Redecker
 *
 */
public class Card implements Comparable<Card>{

	// Attribute
	private String suit;
	private String value;
	private int rank;
	
	// Constructor Card
	public Card(String value, String suit, int rank) {
		this.suit = suit;
		this.value = value;
		this.rank = rank;
	}

	// Getter suit
	public String getSuit() {
		return this.suit;
	}

	// Getter value
	public String getValue() {
		return this.value;
	}
	
	// Getter rank
	public int getRank() {
		return this.rank;
	}
	
	// Karte anzeigen
	@Override
	public String toString() {
		return "\t(" + this.rank + ")" + "\t[" + this.value + " of " + this.suit + "]";
	}

	@Override
	public int compareTo(Card c) {
		return ((Integer)this.getRank()).compareTo(c.getRank()); // WARUM TYPECAST? -> Sebastian Offermann fragen!
	}
	

	
}
