package domain;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import valueObjects.Card;

/**
 * @author Oke Schwien, Christoph Schuette, Fabian Redecker
 * Verwaltung CardDeck 
 * 		cardDeck shuffle, stack-Erzeugung (cardDeckStack)
 * 		Flop, Turn, River ziehen
 * 		(trashDeck für "weggeworfene Karten)
 */
public class CardManager {
	// Attribute
	public static Stack<Card> cardDeckStack = new Stack<Card>();
	public ArrayList<Card> trashDeck = new ArrayList<Card>();
	public static Card[] tableDeck = new Card[5];
	private FileManager fM;
	
	// Constructor
	public CardManager(FileManager fM) {
		this.fM = fM;
		//fM umgelagert
	}
	
	// Methode zum shuffle des cardDecks aus dem FileManager und bestueckung des Stacks mit den Karten
	public void shuffleCardDeckArray() {
		// Shuffle Cards
		Collections.shuffle(this.fM.getCardDeck());
		
		// Bestuecke Stack mit den gemischten Karten
		for(int i = 0; i < this.fM.getCardDeck().size(); i++) {
			cardDeckStack.push(this.fM.getCardDeck().get(i));
			//System.out.println(cardDeckStack.);
		}
	}
	
	// TableDeck mit karten bestücken
	public void drawTableCards() {
		// FLOP
		// 1 Karte weglegen
		trashDeck.add(cardDeckStack.pop());
		// Flop ziehen
		for (int i = 0; i < 3; i++) {
			tableDeck[i] = cardDeckStack.pop();
		}
		
		// TURN
		// 1 Karte weglegen
		trashDeck.add(cardDeckStack.pop());
		// Turn ziehen
		tableDeck[3] = cardDeckStack.pop();
		
		// RIVER
		// 1 Karte weglegen
		trashDeck.add(cardDeckStack.pop());		
		// ziehen
		tableDeck[4] = cardDeckStack.pop();	
	}
	
	// Getter tableDeck
	public static Card[] getTableDeck(){
		return tableDeck;
	}
	
	
	// TODO: Auslagern? Wohin?
	

	// trashDeck anzeigen
	public void showTrashDeck() {
		for (int i = 0; i < this.trashDeck.size(); i++) {
			System.out.println(this.trashDeck.get(i));
		}
	}
}
