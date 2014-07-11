package domain;

import valueObjects.*;


public class PlayerManager {

    private ChipManager cH;

    // Constructor
    public PlayerManager(ChipManager cH) {
        this.cH = cH;

    }

    public int calcDiff(Player currentPlayer, Player playerBefore) {
        return playerBefore.getActualBet() - currentPlayer.getActualBet();

    }


    // Call:	Mit dem aktuellen Call-Betrag mitgehen
    public void call(Player currentPlayer, Player playerBefore) {

        int diff = calcDiff(currentPlayer, playerBefore);
        currentPlayer.setChipNumber(currentPlayer.getChipNumber() - diff);
        currentPlayer.setActualBet(playerBefore.getActualBet());
        this.cH.setPot(this.cH.getPot() + diff);
    }


    // Raise: 	Mindesteinsatz verdoppeln
    public void raise(Player currentPlayer, Player playerBefore) {

        int diff = calcDiff(currentPlayer, playerBefore) * 2;
        currentPlayer.setChipNumber(currentPlayer.getChipNumber() - diff);
        currentPlayer.setActualBet(playerBefore.getActualBet() * 2);
        int newPot = this.cH.getPot() + diff;
        this.cH.setPot(newPot);
    }

    // Bet: 	Auf eigenes Blatt "wetten"; akt. Call + mind. 1 Chip
    public void bet(int bet, Player currentPlayer, Player beforePlayer) {

        if (bet < currentPlayer.getChipNumber() && bet > calcDiff(currentPlayer, beforePlayer)) {
            currentPlayer.setActualBet(currentPlayer.getActualBet() + bet);
            currentPlayer.setChipNumber(currentPlayer.getChipNumber() - bet);
            this.cH.setPot((this.cH.getPot() + bet));
        }

    }

}
