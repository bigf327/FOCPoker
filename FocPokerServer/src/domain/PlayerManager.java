package domain;

import valueObjects.*;


public class PlayerManager {

	// Attibute
	private int actualPlayer = 0;
	private Player[] playerList;
	private int diff = 0;
	private ChipManager cH;

	
	
	// Constructor
	public PlayerManager(ChipManager cH) {
		this.cH = cH;
	}
	
	/* Hand: ziehen 2 Karten aus der ArrayList cardDeck aus dem Cardmanager 
	 * und loesche die gezogene karte aus der List
	 */
	public void drawHandCards() {
		int j = 0;
		for(int i = 0; i < this.playerList.length; i++){
			this.playerList[j].setHand(0, CardManager.cardDeckStack.pop());
			this.playerList[j].setHand(1, CardManager.cardDeckStack.pop());
			j++;
		}
	}
	
	public void eraseBets() {
		for(int i = 0; i < this.playerList.length; i++) {
			if (this.playerList[i] != null) {
				this.playerList[i].setActualBet(0);
			}
		}
	}

	public int calcDiff(){
		int playerBefore = playerBefore();
		this.diff = playerList[playerBefore].getActualBet() - playerList[this.actualPlayer].getActualBet();
		return this.diff;
	}
	
	public boolean checkPossible() {
		int playerBefore = playerBefore();
		if (this.playerList[this.actualPlayer].getActualBet() == this.playerList[playerBefore].getActualBet()) {
			return true;
		}
		return false;
	}
	
	public boolean callPossible() {
		int playerBefore = playerBefore();
		if (this.playerList[this.actualPlayer].getChipNumber() > this.diff/* geht hier nicht auch allIn?*/ 
				&& this.playerList[this.actualPlayer].getActualBet() != this.playerList[playerBefore].getActualBet()) {
			return true;
		}
		return false;
	}
	
	public boolean allInNecessary() {
		if(this.playerList[this.actualPlayer].getChipNumber() < calcDiff()) {
			return true;
		}
		return false;
	}

	//Fold:		Aussetzen
	public void fold() {
		this.playerList[this.actualPlayer] = null;
	}
	
	// Call:	Mit dem aktuellen Call-Betrag mitgehen 
	public void call() {
		int playerBefore = playerBefore();
		int diff = calcDiff();
		playerList[this.actualPlayer].setChipNumber(this.playerList[this.actualPlayer].getChipNumber() - diff);
		playerList[this.actualPlayer].setActualBet(this.playerList[playerBefore].getActualBet());
		this.cH.setPot(this.cH.getPot() + diff);
	}
	
	public void allIn() {
		this.cH.setPot(this.cH.getPot() + this.playerList[actualPlayer].getChipNumber());
		this.playerList[actualPlayer].setChipNumber(0);
	}

	// Raise: 	Mindesteinsatz verdoppeln
	public void raise() {	
		int playerBefore = playerBefore();
		int diff = calcDiff() * 2;
		playerList[this.actualPlayer].setChipNumber(playerList[this.actualPlayer].getChipNumber() - diff);
		playerList[this.actualPlayer].setActualBet(this.playerList[playerBefore].getActualBet() * 2);
		int newPot = this.cH.getPot() + diff;
		this.cH.setPot(newPot);
	}
		
	// Bet: 	Auf eigenes Blatt "wetten"; akt. Call + mind. 1 Chip
	public void bet(int bet){
		
		if (bet < playerList[this.actualPlayer].getChipNumber() && bet > calcDiff()){
			playerList[this.actualPlayer].setActualBet(this.playerList[actualPlayer].getActualBet() + bet);
			playerList[this.actualPlayer].setChipNumber(playerList[this.actualPlayer].getChipNumber() - bet);
			this.cH.setPot((this.cH.getPot() + bet));
		}
		
	}
		
	/* Check:	Kann nur derjenige Spieler, welcher den BigBlind gesetzt hat, wenn sein BigBlind
	 *			nicht ueberboten wurde.
	 */
	public void check(){
		nextPlayer();
	}

	/**
	 * Zeigt die Hand eines Spielers an
	 * @param actualPlayer Spieler, der an der Reihe ist
	 */
	public void showHand() {
		// Hole Hand von actualPlayer
		Card[] actualHand = playerList[this.actualPlayer].getHand();

		// Zeige Karten in Console
		System.out.println("\tHand: ");
		for (int i = 0; i < 2; i++) {
			System.out.println(actualHand[i]);
		}					
	}	
	
	public void nextPlayer() {
		do {
			if (this.actualPlayer+1 > this.playerList.length-1) {
				this.actualPlayer = 0;
			} else {
			actualPlayer++;
			}
		} while (this.playerList[this.actualPlayer] == null);
	}
	
	public int playerBefore() {
		int playerBefore = this.actualPlayer-1;
		if (playerBefore < 0) {
			playerBefore = this.playerList.length-1;
		}
		while (this.playerList[playerBefore] == null) {
			if (playerList[playerBefore] == playerList[actualPlayer]) {
				// TODO spiel zu ende, weil nur noch 1 spieler da -> spieler gewinnt -> pot an spieler ausschuetten
				break;
			}
			playerBefore--;
			if (playerBefore < 0) {
				playerBefore = this.playerList.length-1;
			}
		}
		return playerBefore;
	}

	// Chips anzeigen
	public void showChips(){
		System.out.println("\n\tChips: " + this.playerList[this.actualPlayer].getName()
				+ "\n\t" + playerList[this.actualPlayer].getChipNumber() + " Chips");
	}
	
	public void setPlayerList(Player[] playerList) {
		this.playerList = playerList;
	}
	
	public Player[] getPlayerList() {
		return this.playerList;
	}
	
	// Getter actualPlayer
	public int getActualPlayer() {
		return this.actualPlayer;
	}

	public void setActualPlayer(int actualPlayer) {
		this.actualPlayer = actualPlayer;
	}
}
