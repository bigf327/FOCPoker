package domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import valueObjects.Card;
import valueObjects.Player;


public class Round {
	private final FileManager fM;
	private final CardManager cM;
	private final ChipManager cH;
	private final ResultManager rM;
	private final PlayerManager pM;
    private final int smallBlind;
    private final int bigBlind;
    private final List<Card> shownCards = new ArrayList<Card>();
    private final Stack<Player> beforePlayerStack;
    private final Stack<Player> nextPlayerStack = new Stack<Player>();
    private Player currentPlayer;
    private boolean raisedPotThisRound;
	private boolean flopShown = false;
	private boolean turnShown = false;
	private boolean riverShown = false;


	
	public Round(Stack<Player> nextPlayerStack, int blind){

        /**
         * Set Manager
         */
        this.fM = new FileManager();
        this.cM = new CardManager(this.fM);
        this.cH = new ChipManager(blind);
        this.rM = new ResultManager();
        this.pM = new PlayerManager(this.cH);


        this.beforePlayerStack = new Stack<Player>();
        this.nextPlayerStack.addAll(nextPlayerStack);
        this.smallBlind = this.cH.getSmallBlind();;
        this.bigBlind = this.cH.getBigBlind(); // @todo Bitte die Berechnung für BigBlind einfügen
        this.currentPlayer = this.nextPlayerStack.pop(); // Ersten Spieler
	}

	public void startRound() {

        this.fM.listToCardDeck();
        this.cM.shuffleCardDeckArray();
		this.drawHandCards();
		this.cM.drawTableCards();

        /**
         * Small Blind setzten
         */
        this.currentPlayer.setChipNumber(this.smallBlind);
        this.currentPlayer.setActualBet(this.smallBlind);

        /**
         * Big Blind setzten
         */
        Player nextPlayer = this.nextPlayerStack.peek(); // Peck gibt den nächsten Spieler im stack zurück
        nextPlayer.setChipNumber(this.bigBlind);
        nextPlayer.setActualBet(this.bigBlind);

		this.cH.setPot(this.cH.getBigBlind() + this.cH.getSmallBlind());
	}


    /* Hand: ziehen 2 Karten aus der ArrayList cardDeck aus dem Cardmanager
	 * und loesche die gezogene karte aus der List
	 */
    public void drawHandCards() {
        int j = 0;
        Iterator<Player> iter = this.nextPlayerStack.iterator();
       while (iter.hasNext()){
           Player player = iter.next();
           player.setHand(0, CardManager.cardDeckStack.pop());
           player.setHand(1, CardManager.cardDeckStack.pop());
        }
    }

    public void throwFlop(){
        if(!this.flopShown){
          // @todo code um den FLOP anzuzeigen bzw. zu setzten
            shownCards.add(new Card("2","hearts",2));
            shownCards.add(new Card("3","hearts",3));
            shownCards.add(new Card("3","hearts",3));
        }
    }
    public void  throwTurn(){
        if(!this.turnShown){
          // @todo Code um den turn anzuzeigen
        }
    }
    public void throwRiver(){
        if(!this.riverShown){
            // @todo Code um den River anzuzeigen
        }
    }

	public void fold() {
        this.currentPlayer.setInRound(false);
		this.nextPlayer();
	}

	public void bet(int bet) throws IllegalArgumentException{
		if (bet > this.currentPlayer.getChipNumber()) {
			throw new IllegalArgumentException("You don't have enough Chips: " + bet + " Please try again.");
		}
		this.pM.bet(bet,this.currentPlayer,this.getPlayerBefore());
        this.nextPlayer();
	}
	
	public void raise() {				
		this.pM.raise(this.currentPlayer,this.getPlayerBefore());
        this.nextPlayer();
	}
	
	public void allIn() {
		this.currentPlayer.setActualBet(this.currentPlayer.getChipNumber());
        this.currentPlayer.setChipNumber(0);
        this.nextPlayer();
	}
	
	public void call() {
		this.pM.call(this.currentPlayer,this.getPlayerBefore());
		this.nextPlayer();
	}
	
	public void check() {
        if(!this.beforePlayerStack.empty() ){
            Player playerBefore = this.getPlayerBefore();
            if(playerBefore.getActualBet() == this.currentPlayer.getActualBet()){
                this.nextPlayer();
            }
        }else {
            this.nextPlayer();
        }


	}

    public Player getPlayerBefore(){
        return this.beforePlayerStack.peek();
    }
    public boolean canCurrentPlayerBet(){
        return true; //@todo einbauen der Prüfung
    }
    public boolean canCurrentPlayerCheck(){
        if (this.raisedPotThisRound){
            return false;
        }
        return true; //@todo einbauen der Pürfung
    }
    public boolean canCurrentPlayerRaise(){
        return true;//@todo einbauen der Pürfung
    }
    public boolean canCurrentPlayerCall(){
        return true;//@todo einbauen der Pürfung
    }
    public boolean canCurrentPlayerAllIn(){
        return true;//@todo einbauen der Pürfung
    }
    public boolean canCurrentPlayerFold(){
        return true;//@todo einbauen der Pürfung
    }

    /**
     * Setzt den nächsten Spieler, wenn denn noch nötig
     */
    public void nextPlayer(){

        if(this.nextPlayerStack.size() > 0 ){
            //Player umstapeln
            if(this.nextPlayerStack.peek().isInRound()) {
                this.beforePlayerStack.add(this.currentPlayer);
                this.currentPlayer = this.nextPlayerStack.pop();
            }else {
                this.nextPlayerStack.pop();
                this.nextPlayer();
            }
        }
        else {
           if(this.allCardsShown()){
               //@todo Check Results ausführen bzw. erst anpassen und dann ausführen
           }else {
               //Wenn noch nicht alle Karten gezeigt wurden
               if(this.raisedPotThisRound){
                    //Wenn der aktuelle Spieler, also der letzte in der Runde, den Pot nochmakl erhöht hat darf jeder nochmal erhöhen
                   this.nextPlayerStack.addAll(this.beforePlayerStack);
               }
           }
        }

    }
    private boolean allCardsShown(){
        return this.flopShown && this.turnShown && this.riverShown;
    }
	public void checkResult(Player[] p) {
		int sameRankCounter = 0;
		int winnerSpotted = 0;
		int high = 0;
		System.out.println("############################################");
		for (int i = p.length-1;i > 0; i--) {
			if(p[i].getHandRank() > p[i-1].getHandRank()) {
				p[i].setChipNumber(p[i].getChipNumber() + this.cH.getPot());
				System.out.println("\n\t" + p[i].getName() + " wins " + this.cH.getPot() + " Chips with a " + handRankToString(p[i]));
				this.cH.setPot(0);
				winnerSpotted++;
				break;
			} else if(p[i].getHandRank() == p[i-1].getHandRank() && p[i].getResult() > p[i-1].getResult()) {
				p[i].setChipNumber(p[i].getChipNumber() + this.cH.getPot());
				System.out.println("\n\t" + p[i].getName() + " wins " + this.cH.getPot() + " Chips with a " + handRankToString(p[i]));
				this.cH.setPot(0);
				winnerSpotted++;
				break;
			} else if(p[i].getHandRank() == p[i-1].getHandRank() && p[i].getResult() == p[i-1].getResult()) {
				if(high < p[i].getHighCard()) {
					high = p[i].getHighCard();
				}
			}
		}
		for (int i = p.length-1; i > 0; i--) {
			if (p[i].getHandRank() == p[i-1].getHandRank() && p[i].getResult() == p[i-1].getResult() && high == p[i].getHighCard()) {
				if (p[i].getHighCard() == p[i-1].getHighCard()) {
					sameRankCounter++;
				}
				if (sameRankCounter == 0) {
					p[i].setChipNumber(p[i].getChipNumber() + this.cH.getPot());
					System.out.println("\n\t" + p[i].getName() + " wins " + this.cH.getPot() + " Chips with a " + handRankToString(p[i]));
					this.cH.setPot(0);
					winnerSpotted++;
					break;
				}
			}
		}
		if (sameRankCounter > 0 && winnerSpotted == 0) {
			int splitpot = this.cH.getPot() / sameRankCounter;
			System.out.println("\n\tSPLITPOT");
			for (int i = p.length-1; i > 0; i--) {
				if (p[i].getHandRank() == p[i-1].getHandRank() && p[i].getResult() == p[i-1].getResult()) {
					p[i].setChipNumber(p[i].getChipNumber() + splitpot);
					System.out.println("\n\t" + p[i].getName() + " wins " + splitpot + " Chips with a " + handRankToString(p[i]));
					this.cH.setPot(0);
				}
			}
		}
		System.out.println("\n############################################");
	}


    private int getBigBlind(){
        return this.bigBlind;
    }

	private boolean isLastPlayerInRound() {
		return this.nextPlayerStack.size()==0;
	}
	public int getPot(){
        return this.cH.getPot();
    }
    public Player getCurrentPlayer(){
        return currentPlayer;
    }
	public String handRankToString(Player p) {
		int hR = p.getHandRank();
		String hand = "";
		if(hR == 100) {
			hand = "Royal Flush";
		} else if (hR == 90) {
			hand = "Straight Flush";
		} else if (hR == 80) {
			hand = "Four Of A Kind";
		} else if (hR == 70) {
			hand = "Full House";
		} else if (hR == 60) {
			hand = "Flush";
		} else if (hR == 50) {
			hand = "Straight";
		} else if (hR == 40) {
			hand = "Three Of A Kind";
		} else if (hR == 30) {
			hand = "Two Pair";
		} else if (hR == 20) {
			hand = "Pair";
		} else if (hR == 10) {
			hand = "Highcard";
		}
		return hand;
	}

}