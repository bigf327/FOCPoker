package domain;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import valueObjects.Card;
import valueObjects.Cards;

/**
 * @author Oke Schwien, Christoph Schuette, Fabian Redecker
 * Verwaltung CardDeck 
 * 		cardDeck shuffle, stack-Erzeugung (cardDeckStack)
 * 		Flop, Turn, River ziehen
 * 		(trashDeck f�r "weggeworfene Karten)
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
        Cards.createDeck();
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
	
	// TableDeck mit karten best�cken
	public void drawTableCards() {
		// FLOP
		// 1 Karte weglegen
		//trashDeck.add(cardDeckStack.pop());
        trashDeck.add(Cards.getACard().getCard());
		// Flop ziehen
		for (int i = 0; i < 3; i++) {
			//tableDeck[i] = cardDeckStack.pop();
            tableDeck[i] = Cards.getACard().getCard();
		}
		
		// TURN
		// 1 Karte weglegen
		//trashDeck.add(cardDeckStack.pop());
        trashDeck.add(Cards.getACard().getCard());
		// Turn ziehen
		//tableDeck[3] = cardDeckStack.pop();
        tableDeck[3] = Cards.getACard().getCard();
		
		// RIVER
		// 1 Karte weglegen
		//trashDeck.add(cardDeckStack.pop());
        trashDeck.add(Cards.getACard().getCard());
        // ziehen
		//tableDeck[4] = cardDeckStack.pop();
        tableDeck[4] = Cards.getACard().getCard();
    }
	
	// Getter tableDeck
	public static Card[] getTableDeck(){
		return tableDeck;
	}

	

	// trashDeck anzeigen
	public void showTrashDeck() {
		for (int i = 0; i < this.trashDeck.size(); i++) {
			System.out.println(this.trashDeck.get(i));
		}
	}
}
