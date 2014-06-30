package domain;






/**
 * 
 * @author Oke Schwien, Christoph Schuette, Fabian Redecker
 *
 */
public class PokerMain {
	
	

	public static void main(String[] args) {
		// CUI
		char selection;
		int roundNumber = 1;
		int smallblind = roundNumber * 50;
		do {
			RoundManager roundManager = new RoundManager();
			roundManager.startRound(roundNumber - 1, smallblind);
			roundNumber++;
			System.out.println("\n\tNew game? y/n");
			selection = Eingabe.readChar();
		} while (selection == 'y');
		
	/*	
		// GUI
		final Frame mainFrame = new MainFrame("PokerFOC", 
				"C:\\Users\\Oke Schwien\\Dropbox\\Studium\\Prog2 Projekt\\Projektdateien\\workspace oke\\PokerFOC\\src\\data\\testIcon.jpg", 
				800, 600);
		
		((MainFrame)mainFrame).initialize();
		
		//mainFrame.setVisible(false);
		//mainFrame.dispose();*/
	}
}