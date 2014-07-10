package domain;


import valueObjects.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Oke Schwien, Christoph Schuette, Fabian Redecker
 *
 */
public class Game {


    /**
     * Der Rounde Manager für alle Runden des Spiels
     */
    private final RoundManager roundManager = new RoundManager();
    /**
     * Die anzahl der Runden die gespielt wurde
     */
    private int roundNumber = 0;

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
    public void startGameLoop(){

        /**
         * Statische Spieler hinzugefügt
         */
        players.add(new Player(1,"Karl",2500,"karl"));
        players.add(new Player(2,"Karl",2500,"karl"));
        players.add(new Player(3,"Karl",2500,"karl"));
        players.add(new Player(4,"Karl",2500,"karl"));
        players.add(new Player(5,"Karl",2500,"karl"));
        players.add(new Player(6,"Karl",2500,"karl"));

        int smallBlind = (roundNumber+1) * 50;
        do {
            RoundManager roundManager = new RoundManager();
            roundManager.startRound(roundNumber, smallBlind);
            roundNumber++;
        } while (this.isNextRound());
    }

    public List<Player> getPalyers(){
        return this.players;
    }
    public boolean isNextRound(){
        return false;
    }
}