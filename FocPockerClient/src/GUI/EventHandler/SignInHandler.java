
package GUI.EventHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import valueObjects.ReadAndWriter;
import GUI.gameWindows.SelectionWindow;
import GUI.gameWindows.SignInWindow;
import GUI.gameWindows.SuccessWindow;

public class SignInHandler implements ActionListener {
	

	//TODO als Atribut von Player entfernen
	//private int id = 0;
	
	//Actual SignIn Window as Parame in Konstructor
	private SignInWindow currentSignIn;
	private String name;
	private final int chipNumber = 2500;
	private String valueOfChipnumber;
	private String password;
	private int controlCounter = 0;
	private ReadAndWriter raw;
	

	
	public SignInHandler(SignInWindow frame, ReadAndWriter raw){
		this.currentSignIn = frame;
		this.name = this.currentSignIn.getName();
		this.password = this.currentSignIn.getPassword();
		this.raw = raw;
		//get welcome from PokerServer
		
		try {
			String hallo = this.raw.getAText();
			System.out.println(hallo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@SuppressWarnings("deprecation")
	public void checkPlayerInput(){
		//check if a name was signed in playerNameField
		if(! (currentSignIn.getPlayerNameField().getText().equals(""))){
			name = currentSignIn.getPlayerNameField().getText();
			controlCounter++;
		}else{
			JOptionPane.showMessageDialog(null, "Input Error in Playername", "Input Error",JOptionPane.WARNING_MESSAGE);
		}
		
		//Check if password equal with passwordRepeat
		if (! (currentSignIn.getPasswordField().getText().equals(currentSignIn.getPasswordFieldRepeat().getText()))){
			JOptionPane.showMessageDialog(null, "The Passwords do not equals!", "Input Error",JOptionPane.WARNING_MESSAGE);
			controlCounter = -1;
		}
		
		//Check if a password was signed in passwordField
		if(! (currentSignIn.getPasswordField().getText().equals(""))){
			password = currentSignIn.getPasswordField().getText();
			controlCounter++;
		}else{
			JOptionPane.showMessageDialog(null, "Input Error in Password", "Input Error",JOptionPane.WARNING_MESSAGE);
			controlCounter = -1;	
		}
		
		this.valueOfChipnumber = String.valueOf(chipNumber);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String playerNamePossible = "";
		//conection to server
		checkPlayerInput();
		// if Name and (Password & passwordFieldRepeat) && PlayerName is available was correct signed in Write in a list
		if(controlCounter >= 2){
			String playerData = name + ";" + valueOfChipnumber + ";" + password;
			//System.out.println(playerData);
			//send PlayerData to Server 
			raw.sendAText(playerData);
			try {
				//get information from server above the sign in data
				playerNamePossible = raw.getAText();
			} catch (IOException e1) {
				System.err.println("SignInHandler: ERROR could not get playerNamePossible");
				e1.printStackTrace();
			}
			//if Playername is possible SuccessWindow else warning and chose a new one
			if(playerNamePossible.equals("ok")){
				this.currentSignIn.dispose();
				new SuccessWindow("Success", "You have been registered by FOC Pocker", "OK");
			}else if(playerNamePossible.equals("ERROR")){
				JOptionPane.showMessageDialog(null, "The selected Playername is not possible", "Playername EROOR",JOptionPane.WARNING_MESSAGE);
				currentSignIn.dispose();
				new SelectionWindow();
			}	
	
		}	
	}


}


