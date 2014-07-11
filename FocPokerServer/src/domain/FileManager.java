package domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import valueObjects.*;

/**
 * 
 * @author Oke Schwien, Fabian Redecker, Christoph Schuette
 * FileManager organisiert Datei-Einlese
 */
public class FileManager{
    
	// Attribute
    private ArrayList<Card> cardDeck = new ArrayList<Card>();		// cardlist -> listToCardDeck, hier wird das noch ungemischte Kartendeck als ArrayList<Card> "zwischengespeichert"
    private ArrayList<Player> playerList = new ArrayList<Player>();	// cardlist -> listToPlayerList
    private Player[] savePlayerList;


    public  FileManager(){

    }
    /**
     * Einlesen der Textdatei
     * @param file Uebergibt auszulesende Datei in die Methode
     * @throws IOException
     */
	private List<String> readData(String file) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(file)));

        List<String> list = new ArrayList<String>();
        String row;
        String[] split = new String[1];

		/* Wird solange ausgelesen, wie etwas in der n�chsten Zeile steht und
		 * nicht "null" vom Stream zur�ckgegeben wird.
		 */
		while ((row = br.readLine()) != null) {
			split = row.split(";"); // Auslesen der Textdatei, Zeilenumbruch erfolgt nach jedem ";"
		    	for (int i = 0; i < split.length; i++) {
		    		list.add(split[i]);
		    	}
		    }
		    
		// Stream wird geschlossen
		br.close();
        return list;
    }
	
	// list wird in cardDeck geschrieben
    public void listToCardDeck() {
        List<String> list;
        try{
            list = this.readData("data/CardSet.txt");
        }catch (IOException e ){
            e.printStackTrace();
            return;
        }
    	for (int i = 0; i < (list.size()-2); i += 3) {
    		/* letzter String f�r jede Karte wird von String in int geparsed, 
    		 *weil Card(String value, String suit, int rank)
    		 */
    		this.cardDeck.add(new Card(list.get(i), list.get(i+1),
    				Integer.parseInt(list.get(i+2))));
    	}
    	// entfernt alle Objekte in ArrayList<String> list
    	list.removeAll(list);
    }
    
    // list wird in playerList geschrieben
    public void listToPlayerList() {
        List<String> list;
        try{
            list = this.readData("data/PlayerData.txt");
        }catch (IOException e ){
            e.printStackTrace();
            return;
        }
    	for (int i = 0; i < (list.size()-3); i += 4) {
    		/* erster und dritter String f�r jeden Player wird von String in int geparsed,
    		 * weil Player(int id, String name, int chips, String password)
    		 */
    		this.playerList.add(new Player(Integer.parseInt(list.get(i)), list.get(i+1),
    				Integer.parseInt(list.get(i+2)), list.get(i+3)));
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
        this.listToPlayerList();
        return this.playerList;
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