package valueObjects;

/**
 * 
 * @author Oke Schwien, Christoph Schuette, Fabian Redecker
 *
 */
public class Player implements Comparable<Player>{
	
	// Attribute
	private int id;
	private String name;
	private String password; // sobald genutzt als Parameter in Constructor einfuegen
	private int chipNumber;
	// TODO: Player bekommt "eigenes" lastBet (actualPlayer) und ChipManager bekommt Liste mit Bets aller Spieler?
	private int actualBet;
	private Card[] hand = new Card[2];
	
	
	//Attribute for WinRank
	private int result = 0;
	private int handRank = 0;
	private int highCard = 0;
	
	// Constructor Player
	public Player(int id, String name, int chipNumber, String password) {
		this.id = id;
		this.name = name;
		this.chipNumber = chipNumber;
		this.actualBet = 0;
		this.password = password;
	}
	
	// Getter name
	public String getName() {
		return this.name;
	}
	
	// Getter chipNumber
	public int getChipNumber() {
		return this.chipNumber;
	}
	
	// Setter chipNumber
	public void setChipNumber(int chipNumberChange) {
		this.chipNumber = chipNumberChange;
	}
	
	// Getter hand
	public Card[] getHand() {
		return this.hand;
	}

	// Setter hand
	public void setHand(int i, Card c) {
		this.hand[i] = c;
	}

	// Getter id
	public int getId() {
		return id;
	}

	// Setter id
	public void setId(int id) {
		this.id = id;
	}

	// Getter password
	public String getPassword() {
		return password;
	}

	// Setter password
	public void setPassword(String password) {
		this.password = password;
	}

	// Getter actualBet
	public int getActualBet() {
		return actualBet;
	}

	// Setter actualBet
	public void setActualBet(int actualBet) {
		this.actualBet = actualBet;
	}
	
	// Player anzeigen
	@Override
	public String toString() {
		return "\t(" + this.id + ")" + "\t" + this.name + ", " + this.chipNumber + ", " + this.password;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	//Getter HighCard
	public int getHighCard() {
		return highCard;
	}

	//Setter HighCard
	public void setHighCard(int hK) {
		this.highCard = hK;
	}
	
	public int getHandRank() {
		return handRank;
	}

	public void setHandRank(int handRank) {
		this.handRank = handRank;
	}

	@Override
	public int compareTo(Player p) {
		return ((Integer)this.getHandRank()).compareTo(p.getHandRank());
	}
	
	
}
