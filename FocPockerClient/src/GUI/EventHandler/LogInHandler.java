	//Server mu�� das machen
//	//get Playerlist.txt
//	public void getPlayerList(){
//		//init PLAYERLIST by get Playerlist.txt
//		try {
//			PLAYERLIST = ("/Users/fabianRedecker/Dropbox/Studium FR & CS/Prog2 Projekt/Projektdateien/workspace fabian/Grafics/TestArea/PlayerList.txt");
//			playerListFound = true;
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, "PlayerList Not Found", "List ERROR",JOptionPane.WARNING_MESSAGE);
//		}
//	}
//	

//	// check if name and password is in the Playerlist
//	public void checkPlayerData(){
//		
//		int check = 0;
//		try{
//			BufferedReader br = new BufferedReader(new FileReader(PLAYERLIST));
//
//            String sZeile;
//            while ((sZeile = br.readLine()) != null) {
//                if(sZeile.contains(playerName) && sZeile.contains(playerPassword)){
//					new SuccessWindow("Success", "Log in was successful", "OK");
//
//                	check++;
//
//                }
//                
//            }
//            if(check == 0){
//            	JOptionPane.showMessageDialog(null, "Log in was not successful please try again!", "Log In ERROR",JOptionPane.WARNING_MESSAGE);
//            }    
//            br.close();
//        }
//        catch(IOException e1){
//        	JOptionPane.showMessageDialog(null, "Fail while checkPlayerData", "Read Data ERROR",JOptionPane.WARNING_MESSAGE);
//            e1.printStackTrace();
//        }
//	}
//	

package GUI.EventHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import valueObjects.ReadAndWriter;
import GUI.gameWindows.LogInWindow;
import GUI.gameWindows.SelectionWindow;
import GUI.gameWindows.SuccessWindow;


public class LogInHandler implements ActionListener {
	
	private LogInWindow currentLogIn;
	//Attribute which was signed in from Costumer get by playerNameField & passwordField
	private String playerName;
	private String playerPassword;
	private int controlCounter = 0;
	private ReadAndWriter raw;
	
	public LogInHandler(LogInWindow frame, ReadAndWriter raw){
		this.currentLogIn = frame;
		this.raw = raw;
		
	}

	//get entered Playername & Password
	@SuppressWarnings("deprecation")
	public boolean getPlayerData(){
		//counter for playername & password has run
	
		//try get Playername
		try {
			if(! (currentLogIn.getPlayerNameField().getText().equals(""))){
				playerName = currentLogIn.getPlayerNameField().getText();
				controlCounter++;
			}	
		} catch (Exception e) {
			controlCounter = -1;
			JOptionPane.showMessageDialog(null, "Error in Field Playername", "Input ERROR",JOptionPane.WARNING_MESSAGE);
		}
		
		//try get Player.Password
		try {
			if(! (currentLogIn.getPasswordField().getText().equals(""))){
				playerPassword = currentLogIn.getPasswordField().getText();
				controlCounter++;
			}	
		} catch (Exception e) {
			controlCounter = -1;
			JOptionPane.showMessageDialog(null, "Error in Field Password", "Input ERROR",JOptionPane.WARNING_MESSAGE);
		}
		
		if (controlCounter >=2){
			return true;
		}
		return false;
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String answerFromServer = "";
        /**

		if(this.getPlayerData() == true){
//			System.out.println(playerName + ";" + playerPassword);
			raw.sendAText(playerName + ";" + playerPassword);
			try {
				answerFromServer = raw.getAText();
			} catch (IOException e1) {
				System.err.println("LogInHandler: ERROR get answerFromServer");
				e1.printStackTrace();
			}
			switch (answerFromServer){
			
			case "ok":		currentLogIn.dispose();
							new SuccessWindow("Success", "You have been loged in", "OK");
							break;
			case "ERROR":	currentLogIn.dispose();	
							JOptionPane.showMessageDialog(null, "Error was not able to find your sighned in Player Data", "Input ERROR",JOptionPane.WARNING_MESSAGE);
							new SelectionWindow();
			}
			
		}
         */
	}
}



