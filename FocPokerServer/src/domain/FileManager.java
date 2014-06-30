package domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import valueObjects.*;

/**
 * 
 * @author Oke Schwien, Fabian Redecker, Christoph Schuette
 * FileManager organisiert Datei-Einlese
 */
public class FileManager{
    
	// Attribute
	private String row;												// readData TODO: kann auch in readData rein, oder?
    private ArrayList<String> list = new ArrayList<String>();		// readData
    private String[] split = new String[1];							// readData TODO: kann auch in readData rein, oder?
    private ArrayList<Card> cardDeck = new ArrayList<Card>();		// cardlist -> listToCardDeck, hier wird das noch ungemischte Kartendeck als ArrayList<Card> "zwischengespeichert"
    private ArrayList<Player> playerList = new ArrayList<Player>();	// cardlist -> listToPlayerList
    private Player[] savePlayerList;

    /**
     * Einlesen der Textdatei
     * @param file Uebergibt auszulesende Datei in die Methode
     * @throws IOException
     */
	public void readData(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		    
		/* Wird solange ausgelesen, wie etwas in der nächsten Zeile steht und
		 * nicht "null" vom Stream zurückgegeben wird.
		 */
		while ((row = br.readLine()) != null) {
			split = row.split(";"); // Auslesen der Textdatei, Zeilenumbruch erfolgt nach jedem ";"
		    	for (int i = 0; i < split.length; i++) {
		    		list.add(split[i]);
		    	}
		    }
		    
		// Stream wird geschlossen
		br.close();
    }
	
	// list wird in cardDeck geschrieben
    public void listToCardDeck() {
    	for (int i = 0; i < (this.list.size()-2); i += 3) {
    		/* letzter String für jede Karte wird von String in int geparsed, 
    		 *weil Card(String value, String suit, int rank)
    		 */
    		this.cardDeck.add(new Card(this.list.get(i), this.list.get(i+1), 
    				Integer.parseInt(this.list.get(i+2))));
    	}
    	// entfernt alle Objekte in ArrayList<String> list
    	list.removeAll(list);
    }
    
    // list wird in playerList geschrieben
    public void listToPlayerList() {
    	for (int i = 0; i < (this.list.size()-3); i += 4) {
    		/* erster und dritter String für jeden Player wird von String in int geparsed,
    		 * weil Player(int id, String name, int chips, String password)
    		 */
    		this.playerList.add(new Player(Integer.parseInt(this.list.get(i)), this.list.get(i+1), 
    				Integer.parseInt(this.list.get(i+2)), this.list.get(i+3)));
    	}
    	// entfernt alle Objekte in ArrayList<String> list
    	list.removeAll(list);
    }
    
    // Getter cardDeck
    public ArrayList<Card> getCardDeck() {
		return this.cardDeck;
	}
    
 // Getter playerList
    public ArrayList<Player> getPlayerList() {
		return playerList;
	}
    
    public Player[] getSavePlayerList() {
		return savePlayerList;
	}

	public void setSavePlayerList(Player[] savePlayerList) {
		this.savePlayerList = savePlayerList;
	}

	// ausgabefunktion zum testen
    public void ausgabe() {
    	// cardDeck ausgeben
    	for (int i = 0; i < this.cardDeck.size(); i++) {
			System.out.println(this.cardDeck.get(i));
		}
    	// playerList ausgeben
    	for (int i = 0; i < this.playerList.size(); i++) {
			System.out.println(this.playerList.get(i));
		}
    }
}