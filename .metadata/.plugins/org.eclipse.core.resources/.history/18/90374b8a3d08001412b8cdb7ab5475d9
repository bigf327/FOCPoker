package domain;

import java.io.IOException;

import valueObjects.Player;


public class RoundManager {
	private FileManager fM;
	private CardManager cM;
	private ChipManager cH;
	private ResultManager rM;
	private PlayerManager pM;
	private int blind = 0;
	private boolean flopPlaced = false;
	private boolean turnPlaced = false;
	private boolean riverPlaced = false;
	private int endCounter = 0;
	private boolean roundEnd = false;
	
	public RoundManager (){
		
	}
	
	private void createPlayerList(){
		try {
			this.fM.readData("/Users/Ich/Dropbox/Studium FR & CS & Oke/Prog2 Projekt/Projektdateien/workspace christoph/PokerFOC/bin/data/PlayerData.txt"); // Mac
			//this.fM.readData("C:\\Users\\Oke Schwien\\Dropbox\\Studium\\Prog2 Projekt\\Projektdateien\\workspace oke\\PokerFOC\\src\\data\\PlayerData.txt"); // Windows
		} catch(IOException e) {
			System.err.println("Fehler beim Lesen der Datei");
		}
		this.fM.listToPlayerList();
		pM.setPlayerList(fM.getPlayerList().toArray(new Player[fM.getPlayerList().size()]));
	}
	
	private void createDeck(){
		try {
			this.fM.readData("/Users/Ich/Dropbox/Studium FR & CS & Oke/Prog2 Projekt/Projektdateien/workspace christoph/PokerFOC/bin/data/CardSet.txt"); // Mac
			//this.fM.readData("C:\\Users\\Oke Schwien\\Dropbox\\Studium\\Prog2 Projekt\\Projektdateien\\workspace oke\\PokerFOC\\src\\Data\\CardSet.txt"); // Windows
		} catch(IOException e) {
			System.err.println("Fehler beim Lesen der Datei");
		}
		this.fM.listToCardDeck();
		this.cM.shuffleCardDeckArray();
	}
	
	//TODO getActualPlayer in startRound darf nicht auf null zeigen | update oke: besprochen; kann nie auf null zeigen, ABER: spieler koennten zu wenige chips haben
	
	public void startRound(int startPlayer, int blind) {
		this.blind = blind;
		this.fM = new FileManager();
		this.cM = new CardManager(this.fM);
		this.cH = new ChipManager(this.blind);
		this.rM = new ResultManager();
		this.pM = new PlayerManager(this.cH);
		pM.setActualPlayer(startPlayer);
		createPlayerList();
		createDeck();
		this.pM.drawHandCards();
		this.cM.drawTableCards();
		if (startPlayer > this.pM.getPlayerList().length-1) {
			startPlayer = 0;
		}
		if (this.pM.getActualPlayer() <= startPlayer) {
			this.blind = this.pM.getPlayerList()[startPlayer].getChipNumber() - this.cH.getSmallBlind();
		}else{
			startPlayer = 0;
		}
		if(this.pM.getActualPlayer() > startPlayer){
			startPlayer = 0;
		}
		int bigBlind = this.pM.getPlayerList()[startPlayer].getChipNumber() - this.cH.getBigBlind();
		this.pM.getPlayerList()[startPlayer].setChipNumber(this.blind);
		this.pM.getPlayerList()[startPlayer].setActualBet(this.cH.getSmallBlind());
		if (startPlayer > this.pM.getPlayerList().length-2) {
			this.pM.getPlayerList()[0].setChipNumber(bigBlind);
			this.pM.getPlayerList()[0].setActualBet(this.cH.getBigBlind());
		} else {
			this.pM.getPlayerList()[startPlayer + 1].setChipNumber(bigBlind);
			this.pM.getPlayerList()[startPlayer + 1].setActualBet(this.cH.getBigBlind());
		}
		this.cH.setPot(this.cH.getBigBlind() + this.cH.getSmallBlind());
		if (startPlayer > this.pM.getPlayerList().length-3) {
			this.pM.setActualPlayer(0);
		} else {
			this.pM.setActualPlayer(startPlayer + 2);	
		}
		playerStatus();
	}
	
	public void endOfRound() {
		if(this.endCounter >= pM.getPlayerList().length) {
			if(!flopPlaced) {
				//showFlop();
				setFlopPlaced(true);
				this.pM.eraseBets();
				this.endCounter = 0;
			} else if (!turnPlaced) {
				//showTurn();
				setTurnPlaced(true);
				this.pM.eraseBets();
				this.endCounter = 0;
			} else if (!riverPlaced) {
				//showRiver();
				setRiverPlaced(true);
				this.pM.eraseBets();
				this.endCounter = 0;
			} else {
				int playerCounter = 0;
				for(int i = 0; i < this.pM.getPlayerList().length; i++) {
					if(pM.getPlayerList()[i] != null) {
						playerCounter++;
					}
				}
				// playerCounter anzeigen
				//System.out.println(playerCounter);
				Player[] tempPlayerList = new Player[playerCounter];
				int tempPlayerListCounter = 0;
				for(int i = 0; i < this.pM.getPlayerList().length; i++) {
					if(this.pM.getPlayerList()[i] != null) {
						tempPlayerList[tempPlayerListCounter] = this.pM.getPlayerList()[i];
						tempPlayerListCounter++;
					}
				}
				/* tempPlayerList anzeigen
				for(int i = 0; i < tempPlayerList.length; i++) {
					System.out.println(tempPlayerList[i]);
				}
				*/
				for(int i = 0; i < tempPlayerList.length; i++) {
					this.rM.resultDeck(tempPlayerList[i]);
					this.rM.identifyResult(tempPlayerList[i]);
				}
				this.rM.sortList(tempPlayerList);
				checkResult(tempPlayerList);
				this.roundEnd = true;
			}
		}
	}
	
	public void playerStatus() {
		endOfRound();
		if (lastPlayerCheck()) {
			roundEnd = true;
		}
		if (roundEnd == false) {
			System.out.println("____________________________________________");
			if(riverPlaced) {
				showRiver();
			} else if (turnPlaced) {
				showTurn();
			} else if (flopPlaced) {
				showFlop();
			}
			showActualBet();
			if(this.pM.allInNecessary()) {
				this.pM.nextPlayer();
			}
			if (this.pM.checkPossible()) {
				System.out.println("\n\t" + this.pM.getPlayerList()[this.pM.getActualPlayer()].getName() + ", "
						+ this.pM.getPlayerList()[this.pM.getActualPlayer()].getChipNumber() + " Chips");
				this.pM.showHand();
				System.out.println("\tMenu: "
						+ "\n\tc\t-\tCheck"
						+ "\n\tr\t-\tRaise"
						+ "\n\tf\t-\tFold"
						+ "\n\th\t-\tHelp|Rules");
				char selection = Eingabe.readChar();
				switch(selection) {
					case 'c':	check();
								playerStatus();
							break;
					case 'r':	raiseOrBet();
								playerStatus();
							break;
					case 'f':	fold();
								playerStatus();
							break;
					case 'h':	HelpAndRules.helpMenu();
								playerStatus();
							break;
					default:
							break;
				}
			}
			if (this.pM.callPossible()) {
				System.out.println("\n\t" + this.pM.getPlayerList()[this.pM.getActualPlayer()].getName() + ", "
						+ this.pM.getPlayerList()[this.pM.getActualPlayer()].getChipNumber() + " Chips");
				this.pM.showHand();
				System.out.println("\tMenu: "
						+ "\n\tc\t-\tCall"
						+ "\n\tr\t-\tRaise"
						+ "\n\tf\t-\tFold"
						+ "\n\th\t-\tHelp|Rules");
				char selection = Eingabe.readChar();
				switch(selection) {
					case 'c':	call();
								playerStatus();
							break;
					case 'r':	raiseOrBet();
								playerStatus();
							break;
					case 'f':	fold();
								playerStatus();
							break;
					case 'h':	HelpAndRules.helpMenu();
								playerStatus();
							break;
					default:
							break;
				}
			}
			
		}
	}
	
	public void showActualBet() { /*and Pot*/
		System.out.println("");
		for (int i = 0; i< this.pM.getPlayerList().length; i++) {
			if (this.pM.getPlayerList()[i] != null) {
				System.out.println("\tActual bet " + this.pM.getPlayerList()[i].getName() + ": \t"+ this.pM.getPlayerList()[i].getActualBet());
			}
		}
		System.out.println("\t------------------------------"
				+ "\n\tPot: \t\t\t" + this.cH.getPot());
	}
	
	public void fold() {
		this.pM.fold();
		this.endCounter++;
		this.pM.nextPlayer();
	}
	
	public void raiseOrBet() {
		int playerBefore = this.pM.playerBefore();
		if (this.pM.getPlayerList()[playerBefore].getActualBet() > 0) {
			System.out.println("\tWhat to do?"
					+ "\n\td\t-\tDouble actual call"
					+ "\n\tr\t-\tRaise"
					+ "\n\ta\t-\tAllIn");
			char selection = Eingabe.readChar();
			switch(selection) {
				case 'd':	raise();
						break;
				case 'r':	bet();
						break;
				case 'a':	allIn();
						break;
				default:
						break;
			}
		} else {
			bet();
		}
	}
	
	public void bet() {
		System.out.println("\tHow much do you want to raise?"
				+ "\n\tMinimum: " + (this.pM.calcDiff() + 1)
				+ "\n\tMaximum: " + (this.pM.getPlayerList()[this.pM.getActualPlayer()].getChipNumber()-1));
		int bet = Eingabe.readInt();
		if (bet > this.pM.getPlayerList()[this.pM.getActualPlayer()].getChipNumber()) {
			System.out.println("You don't have enough Chips: " + bet + " Please try again.");
			bet();
		}
		this.pM.bet(bet);
		this.endCounter = 0;
		this.pM.nextPlayer();
	}
	
	public void raise() {				
		this.pM.raise();
		this.endCounter = 0;
		this.pM.nextPlayer();
	}
	
	private void allIn() {
		System.out.println("\tSure?"
				+ "\n\ty\t-\tyes"
				+ "\n\tn\t-\tno");
		char choice = Eingabe.readChar();
		switch (choice){
		
		case 'y':	this.pM.allIn();
					if(this.pM.getPlayerList()[this.pM.getActualPlayer()].getChipNumber() > this.pM.getPlayerList()[this.pM.playerBefore()].getActualBet()) {
						this.endCounter = 0;
					} else {
						this.endCounter++;
					}					
				break;
		case'n':	playerStatus();
				break;
		default:	System.out.println("Unknown input!" + "\n Please try again.");
					allIn();
				break;
		}
		this.pM.nextPlayer();
	}
	
	private void call() {
		this.pM.call();
		this.endCounter++;
		this.pM.nextPlayer();
	}
	
	private void check() {
		this.endCounter++;
		this.pM.check();
	}
	
	// FLOP anzeigen
	public void showFlop() {
		System.out.println("********************************************");
		System.out.println("\t\tFLOP");
		for (int i = 0;i<3;i++) {
			System.out.println(CardManager.getTableDeck()[i]);
		}
		System.out.println("\n********************************************");
		setFlopPlaced(true);
	}
		
	// TURN anzeigen
	public void showTurn() {
		System.out.println("********************************************");
		System.out.println("\t\tTURN");
		for (int i = 0;i<4;i++) {
			System.out.println(CardManager.getTableDeck()[i]);
		}
		System.out.println("\n********************************************");
		setTurnPlaced(true);
	}
		
	// RIVER anzeigen
	public void showRiver() {
		System.out.println("********************************************");
		System.out.println("\t\tRIVER");
		for (int i = 0; i<5; i++) {
			System.out.println(CardManager.getTableDeck()[i]);
		}
		System.out.println("\n********************************************");
		setRiverPlaced(true);
	}
	
	public void setFlopPlaced(boolean flop) {
		this.flopPlaced = flop;
		
	}
	
	public void setTurnPlaced(boolean turn) {
		this.turnPlaced = turn;
	}
	
	public void setRiverPlaced(boolean river) {
		this.riverPlaced = river;
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
	
	private boolean lastPlayerCheck() {
		// Einer gewinnt, da der Rest gefolded hat
		int lastPlayerCounter = 0;
		for (int i = 0; i < this.pM.getPlayerList().length; i++) {
			if (this.pM.getPlayerList()[i] == null) {
				lastPlayerCounter++;
			}
		}
		if (lastPlayerCounter == this.pM.getPlayerList().length-1) {
			this.pM.getPlayerList()[this.pM.getActualPlayer()].setChipNumber(this.pM.getPlayerList()[this.pM.getActualPlayer()].getChipNumber() + this.cH.getPot());
			this.pM.eraseBets();
			System.out.println("\n\n\t" + this.pM.getPlayerList()[this.pM.getActualPlayer()].getName() + " wins " + this.cH.getPot());
			this.cH.setPot(0);
			return true;
		}
		return false;
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