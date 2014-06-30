package domain;

import valueObjects.Card;

import java.util.Arrays;

import valueObjects.Player;


public class ResultManager {
	private Card[] resultDeck = new Card[7];
	private int finalRank = 0;
	private int handRank = 0;
	
	public void resultDeck(Player p) {
		Card[] hand = new Card[2];
		Card[] table = new Card[5];
		hand = p.getHand();
		table = CardManager.getTableDeck();
		this.resultDeck[0] = hand[0];
		this.resultDeck[1] = hand[1];
		p.setHighCard(highcard());
		int j = 0;
		for(int i = 2; i < this.resultDeck.length; i++){
			this.resultDeck[i] = table[j];
			j++;
		}
		sortDeck();
	}
		
	public void sortDeck() {
		Arrays.sort(this.resultDeck);
		/* Ausgabe auf Konsole
		for (int i = 0; i < this.resultDeck.length; i++) {
			System.out.println(resultDeck[i]);
		}
		*/
	}
	
	public int highcard() {
		if (this.resultDeck[0].getRank() > this.resultDeck[1].getRank()) {
			this.handRank = 10;
			return this.resultDeck[0].getRank();
		} else {
			this.handRank = 10;
			return this.resultDeck[1].getRank();
		}
	}
	
private boolean royalFlush(){
		
		Card[] heartsArray = new Card[7];
		Card[] diamondsArray = new Card[7];
		Card[] clubsArray = new Card[7];
		Card[] spadesArray = new Card[7];
		
		int counterHearts = 0;
		int counterClubs = 0;
		int counterDiamonds = 0;
		int counterSpades = 0;
		
		int royalFlushCounterHearts = 0;
		int royalFlushCounterDiamonds = 0;
		int royalFlushCounterClubs = 0;
		int royalFlushCounterSpades = 0;
		
		for(int i = 6; i >= 0; i--){
			if(this.resultDeck[i].getSuit().equals("hearts")){
				heartsArray[counterHearts] = this.resultDeck[i];
				counterHearts++;
				
			}
			if(this.resultDeck[i].getSuit().equals("diamonds")){
				diamondsArray[counterDiamonds] = this.resultDeck[i];
				counterDiamonds++;
			}
			if(this.resultDeck[i].getSuit().equals("clubs")){
				clubsArray[counterClubs] = this.resultDeck[i];
				counterClubs++;
			}
			if(this.resultDeck[i].getSuit().equals("spades")){
				spadesArray[counterSpades] = this.resultDeck[i];
				counterSpades++;
			}
		}
		
		for(int h = 0; h < 6 ; h++){
			if(heartsArray[h] == null || heartsArray[h+1] == null){
				break;
			}
			if(heartsArray[0].getRank() == 14 && heartsArray[h].getRank() >= 10){ 
				if(heartsArray[h] == null){
					break;
				}
				if(heartsArray[h+1] != null && heartsArray[h].getRank() == (heartsArray[h+1].getRank() +1)) {
					royalFlushCounterHearts++;
				}
			}
		}
		for(int d = 0; d < 6 ; d++){
			if(diamondsArray[d] == null || diamondsArray[d+1] == null){
				break;
			}
			if(diamondsArray[0].getRank() == 14 && diamondsArray[d].getRank() >= 10){ 
				if(diamondsArray[d] == null || diamondsArray[d+1] == null){
					break;
				}
				if(diamondsArray[d+1] != null && diamondsArray[d].getRank() == (diamondsArray[d + 1].getRank() +1)) {
					royalFlushCounterDiamonds++;
				}
			}
		}
		for(int c = 0; c < 6 ; c++){
			if(clubsArray[c] == null || clubsArray[c+1] == null){
				break;
			}
			if(clubsArray[0].getRank() == 14 && clubsArray[c].getRank() >= 10){ 
				if(clubsArray[c] == null || clubsArray[c+1] == null){
					break;
				}
				if(clubsArray[c+1] != null && clubsArray[c].getRank() == (clubsArray[c+1].getRank() +1)) {
					royalFlushCounterClubs++;
				}
				
			}
		}
		
		for(int s = 0; s < 6 ; s++){
			if(spadesArray[s] == null || spadesArray[s+1] == null){
				break;
			}
			if(spadesArray[0].getRank() == 14 && spadesArray[s].getRank() >= 10){ 
				if(spadesArray[s] == null || spadesArray[s+1] == null){
					break;
				}
				if(spadesArray[s+1] != null && spadesArray[s].getRank() == (spadesArray[s+1].getRank() +1)) {
					royalFlushCounterSpades++;
				}
			}
		}
		if((royalFlushCounterHearts >= 4) || (royalFlushCounterDiamonds >= 4) || (royalFlushCounterClubs >= 4) || (royalFlushCounterSpades >= 4)){
			this.handRank = 100;
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	/*public boolean royalFlush() {
		int royalFlushCounter = 0;
		int counter = 6;
		if (this.resultDeck[6].getRank() == 14) {
			royalFlushCounter++;
			for (int i = 5; i > 0; i--) {
				if (this.resultDeck[i].getRank() == this.resultDeck[counter].getRank() - 1 && this.resultDeck[i].getSuit().equals(this.resultDeck[counter].getSuit())) {
					royalFlushCounter++;
					counter--;
				}
				if (counter == 6) {
					if(this.resultDeck[i - 1].getRank() == this.resultDeck[counter].getRank() - 1 && this.resultDeck[i - 1].getSuit().equals(this.resultDeck[counter].getSuit())){
						royalFlushCounter++;
						counter--;
					}
				}else if (this.resultDeck[i].getRank() == this.resultDeck[counter + 1].getRank() - 1 
						&& this.resultDeck[i].getSuit().equals(this.resultDeck[counter + 1].getSuit())) {
					royalFlushCounter++;
					counter--;
				}	
			}
			if (royalFlushCounter >= 5) {
				this.handRank = 100;
				return true;
			}
		} else {
			return false;
		}
		return false;
	}*/
	
	public boolean straightFlush() {
		int straightFlushCounter = 0;
		for (int i = 0; i < this.resultDeck.length-1; i++) {
			if (this.resultDeck[i].getRank() == this.resultDeck[i+1].getRank()-1 
					&& this.resultDeck[i].getSuit().equals(this.resultDeck[i+1].getSuit())) {
				straightFlushCounter++;
				this.finalRank += this.resultDeck[i].getRank();
			}
		}
		if (straightFlushCounter >= 5) {
			this.handRank = 90;
			return true;
		}
		return false;
	}
	
	
	public boolean fourOfAKind() {
		int fourOfAKindCounter = 0;
		
		for(int i=0; i< this.resultDeck.length-3; i++){
			if(this.resultDeck[i].getRank() == this.resultDeck[i+1].getRank() && 
					this.resultDeck[i].getRank()== this.resultDeck[i+2].getRank() &&
						this.resultDeck[i].getRank() == this.resultDeck[i+3].getRank()){
				fourOfAKindCounter++;
				this.finalRank = this.resultDeck[i].getRank();
			}
		}
		if(fourOfAKindCounter == 1){
			this.handRank = 80;
			return true;
		}
		
		return false;
	}
	
	
	public boolean fullHouse() {
		int threeOfAKindCounter = 0;
		int pairCounter = 0;
		int threeOfRank = 0;
		int pairRank = 0;
		
		
		for(int i=0; i < this.resultDeck.length-2; i++) {
			if(this.resultDeck[i].getRank() == this.resultDeck[i+1].getRank() &&
					this.resultDeck[i].getRank() == this.resultDeck[i+2].getRank()){
				threeOfAKindCounter++;
				threeOfRank += this.resultDeck[i].getRank();
			}
		}
		
		for (int i = 0 ; i < this.resultDeck.length-1; i++){
			if(this.resultDeck[i].getRank() == this.resultDeck[i+1].getRank()){
				if(threeOfRank != this.resultDeck[i].getRank()){
					pairRank += resultDeck[i].getRank();
					pairCounter++;
				}	
			}
			
		}
		
		if(pairCounter > 0 && threeOfAKindCounter == 1){
			this.finalRank = pairRank + threeOfRank;
			this.handRank = 70;
			return true;
		}
		return false;		
	}

	
	public boolean flush() {
		int heartsCounter = 0;
		int diamondsCounter = 0;
		int spadesCounter = 0;
		int clubsCounter = 0;
		for(int i = 0; i < this.resultDeck.length; i++) {
			if(this.resultDeck[i].getSuit().equals("hearts")){
				this.finalRank = 10;
				heartsCounter++;
	  		}
			if(this.resultDeck[i].getSuit().equals("diamonds")){
				this.finalRank = 10;
				diamondsCounter++;
	  		}
			if(this.resultDeck[i].getSuit().equals("spades")){
				this.finalRank = 10;
				spadesCounter++;
	  		}
			if(this.resultDeck[i].getSuit().equals("clubs")){
				this.finalRank = 10;
				clubsCounter++;
	  		}
	  		if(heartsCounter >= 5 || diamondsCounter >= 5 || spadesCounter >= 5 || clubsCounter >= 5) {
	  			this.handRank = 60;
	  			return true;
	  		}
	  	}
		return false;
	}
	
	
	public boolean straight() {
		int straightCounter=0;
		
		for(int i=0; i< this.resultDeck.length-4; i++){
			if(this.resultDeck[i].getRank() == this.resultDeck[i+1].getRank()-1 && 
					this.resultDeck[i].getRank()== this.resultDeck[i+2].getRank()-2 &&
						this.resultDeck[i].getRank() == this.resultDeck[i+3].getRank()-3 &&
							this.resultDeck[i].getRank() == this.resultDeck[i+4].getRank()-4){
				straightCounter++;
				this.finalRank += this.resultDeck[i].getRank();
			}
		}
		if(straightCounter == 1){
			this.handRank = 50;
			return true;
		}
		return false;
	}
	
	//funktioniert auch nur wenn das resultDeck nach Ranks sortiert ist.(siehe pair() z169)
	public boolean threeOfAKind() {
		int threeOfAKindCounter = 0;
		
		for(int i=0; i < this.resultDeck.length-2; i++) {
			if(this.resultDeck[i].getRank() == this.resultDeck[i+1].getRank() &&
					this.resultDeck[i].getRank() == this.resultDeck[i+2].getRank()){
				threeOfAKindCounter++;
				this.finalRank = this.resultDeck[i].getRank();
			}
		}
		if(threeOfAKindCounter == 1){
			this.handRank = 40;
			return true;
		}
		return false;
	}
	
	public boolean twoPair() {
		int twoPairCounter = 0;

		for (int i = 0 ; i < this.resultDeck.length-1; i++){
			if(this.resultDeck[i].getRank() == this.resultDeck[i+1].getRank()){
				twoPairCounter++;
				this.finalRank = this.resultDeck[i].getRank();
			}			
		}
		if(twoPairCounter == 2) {
			this.handRank = 30;
			return true;
		}
		return false;
			
	}

	
	/*Diese Methode funtioniert nur wenn das resultDeck nach Ranks sortiert ist.
	 * Ich bin der Meinung das hatte wir so geplant z45(sort.array).
	 * Soll ein Paar unabhängig von der platzierung im Array gefunden werden, wird es komplexer.
	 * dann muss jeder Platz im Array mit jeden andren Platz verglichen werden. Sollte es eine 
	 * übereinstimmung gefunden werden muß sich diese gemerkt werden.
	 * */
	
	
	public boolean pair() {
		int pairCounter = 0;

		for (int i = 0 ; i < this.resultDeck.length - 1; i++){
			if(this.resultDeck[i].getRank() == this.resultDeck[i+1].getRank()){
				pairCounter++;
				this.finalRank = this.resultDeck[i].getRank();
			}
		}
		if(pairCounter == 1) {
			this.handRank = 20;
			return true;
		}
		return false;
	}
	
	
	public void identifyResult(Player p) {
		if (royalFlush()) {					// Pruefung auf Royal Flush
			p.setResult(this.finalRank);
			p.setHandRank(this.handRank);
		} else if (straightFlush()) {		// Pruefung auf Straight Flush
			p.setResult(this.finalRank);
			p.setHandRank(this.handRank);
		} else if (fourOfAKind()) {			// Pruefung auf Four of a Kind
			p.setResult(this.finalRank);
			p.setHandRank(this.handRank);
		} else if (fullHouse()) {				// Pruefung auf Full House
			p.setResult(this.finalRank);
			p.setHandRank(this.handRank);
		} else if (flush()) {					// Pruefung auf Flush
			p.setResult(this.finalRank);
			p.setHandRank(this.handRank);
		} else if (straight()) {				// Pruefung auf Straight
			p.setResult(this.finalRank);
			p.setHandRank(this.handRank);
		} else if (threeOfAKind()) {			// Pruefung auf Three of a Kind
			p.setResult(this.finalRank);
			p.setHandRank(this.handRank);
		} else if (twoPair()) {				// Pruefung auf Two Pair
			p.setResult(this.finalRank);
			p.setHandRank(this.handRank);
		} else if (pair()) {					// Pruefung auf Pair
			p.setResult(this.finalRank);
			p.setHandRank(this.handRank);
		} else {
			p.setResult(p.getHighCard());
			p.setHandRank(this.handRank);
		}
		/*System.out.println(p);
		System.out.println("\t\tHighcard: " + p.getHighCard());
		System.out.println("\t\tHand Rank: " + p.getHandRank());
		System.out.println("\t\tResult: " + p.getResult());
		*/
	}
	
	public void sortList(Player[] pL) {
		Arrays.sort(pL);
	}
}

