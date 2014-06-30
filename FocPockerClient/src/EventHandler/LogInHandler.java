package EventHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import forPockerFoc.LogInWindow;
import forPockerFoc.SuccessWindow;

public class LogInHandler implements ActionListener {
	
	private LogInWindow currentLogIn;
	//Attribute which was signed in from Costumer get by playerNameField & passwordField
	private String playerName;
	private String playerPassword;
	private String PLAYERLIST;
	private boolean playerListFound = false;
	private boolean playerInputOk = false;
	
	public LogInHandler(LogInWindow frame){
		this.currentLogIn = frame;
		
	}

	//get entered Playername & Password
	@SuppressWarnings("deprecation")
	public void getPlayerData(){
		//counter for playername & password has run
		int controlCounter = 0;
		
		//try get Playername
		try {
			if(! (currentLogIn.getPlayerNameField().getText().equals(""))){
				playerName = currentLogIn.getPlayerNameField().getText();
				controlCounter++;
			}	
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error in Field Playername", "Input ERROR",JOptionPane.WARNING_MESSAGE);
		}
		
		//try get Player.Password
		try {
			if(! (currentLogIn.getPasswordField().getText().equals(""))){
				playerPassword = currentLogIn.getPasswordField().getText();
				controlCounter++;
			}	
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error in Field Password", "Input ERROR",JOptionPane.WARNING_MESSAGE);
		}
		
		if(controlCounter ==2){
			playerInputOk = true;
		}
	}
	
	
	//get Playerlist.txt
	public void getPlayerList(){
		//init PLAYERLIST by get Playerlist.txt
		try {
			PLAYERLIST = ("/Users/fabianRedecker/Dropbox/Studium FR & CS/Prog2 Projekt/Projektdateien/workspace fabian/Grafics/TestArea/PlayerList.txt");
			playerListFound = true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "PlayerList Not Found", "List ERROR",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	
	// check if name and password is in the Playerlist
	public void checkPlayerData(){
		
		int check = 0;
		try{
			BufferedReader br = new BufferedReader(new FileReader(PLAYERLIST));

            String sZeile;
            while ((sZeile = br.readLine()) != null) {
                if(sZeile.contains(playerName) && sZeile.contains(playerPassword)){
					new SuccessWindow("Success", "Log in was successful", "OK");
					//TODO dispose LogInWIndow
                	check++;
                	//TODO close window start Game
                }
                
            }
            if(check == 0){
            	JOptionPane.showMessageDialog(null, "Log in was not successful please try again!", "Log In ERROR",JOptionPane.WARNING_MESSAGE);
            }    
            br.close();
        }
        catch(IOException e1){
        	JOptionPane.showMessageDialog(null, "Fail while checkPlayerData", "Read Data ERROR",JOptionPane.WARNING_MESSAGE);
            e1.printStackTrace();
        }
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		getPlayerData();
		getPlayerList();
		if(playerListFound && playerInputOk){
			checkPlayerData();
		}
	}


}
