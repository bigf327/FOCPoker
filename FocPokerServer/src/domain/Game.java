package domain;


import valueObjects.Player;

import java.util.List;
import java.util.Stack;

/**
 * 
 * @author Oke Schwien, Christoph Schuette, Fabian Redecker
 *
 */
public class Game {


    /**
     * Die anzahl der Runden die gespielt wurde
     */
    private int roundNumber = 0;

    private Round currentRound;

    private final List<Player> players;

    /**
     * Erstellt ein Spiel
     */
    public Game(){

        FileManager fileManager = new FileManager();
        this.players = fileManager.getPlayerList();
    }

    /**
     * Läuft solange bis Spiel gewonnen
     */
    public void startNewRound(){

        /**
         * Statische Spieler hinzugefügt
         */

        int smallBlind = (roundNumber+1) * 50;
        Stack<Player> playerStack = new Stack<Player>();
        playerStack.addAll(this.players);
        this.currentRound =  new Round(playerStack,smallBlind);
        this.currentRound.startRound();
        roundNumber++;

    }

    public List<Player> getPalyers(){
        return this.players;
    }
    public boolean isNextRound(){
        return false;
    }
    public Round getCurrentRound(){
        return this.currentRound;
    }
}