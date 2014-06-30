package domain;

public class ChipManager {
	private int pot;
	private int smallBlind;
	private int bigBlind;
	
	/**
	 * Constructor ChipManager
	 * @param smallBlind Uebergibt den aktuellen SmallBlind
	 */
	public ChipManager(int smallBlind){
		this.pot = 0;
		this.smallBlind = smallBlind;
		this.bigBlind = 2*smallBlind;
	}
	
	

	
	/**
	 * Setter pot
	 * @param newPot
	 */
	public void setPot(int newPot){
		pot = newPot;
	}
	
	//Getter pot
	public int getPot(){
		return pot;
	}
	
	/**
	 * Blinds setzen
	 * @param oldSmallBlind
	 */
	public void setSmallBlind(int oldSmallBlind){
		this.smallBlind = oldSmallBlind*2;
	}
	
	//Getter smallBlind
	public int getSmallBlind(){
		return this.smallBlind;
	}
	
	//Getter bigBlind
	public int getBigBlind(){
		return this.bigBlind;
	}
}
