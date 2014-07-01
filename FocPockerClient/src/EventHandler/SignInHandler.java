	//server mu�� das machen
//	public boolean playerNamePossible(String playername){
//		//TODO wie komm ich an die Liste vom Server (client kennt nur common)!
//		//String file = ("/Users/fabianRedecker/Dropbox/Studium FR & CS/Prog2 Projekt/Projektdateien/workspace fabian/Grafics/TestArea/PlayerList.txt");
//		String file = "";
//		try {
//			file = ("/data/PlayerList.txt");
//			
//		} catch (Exception e) {
//			System.err.println("FAIL while reading Playerlist.txt" + e.getMessage());
//		}
//		String row;
//		String [] split = new String[1];
//	    ArrayList<String> playerNameList = new ArrayList<String>();
//	    int playerFoundCounter = 0;
//		
//	    //read in Playerdata an split after Playername
//	    try {
//			BufferedReader br = new BufferedReader(new FileReader(file));
//
//			while ((row = br.readLine()) != null) {
//				split = row.split(";"); // Auslesen der Textdatei, Zeilenumbruch erfolgt nach jedem ";"
//			    	for (int i = 0; i < split.length; i++) {
//			    		playerNameList.add(split[i]);
//			    	}
//			    }
//			    
//			// Stream wird geschlossen
//			br.close();
//		} catch (Exception e) {
//			System.err.println("ERROR while reading txtFile");
//		}
//	    
//	    //check if playername(param) ist allready in the list if yes playerFoundCounter++
//	    for (int i=0; i< playerNameList.size(); i++){
//	    	if(playerNameList.get(i).equals(playername)){
//	    		playerFoundCounter++;
//	    	}
//	    }
//		
//	    if(playerFoundCounter == 0){
//	    	return true;
//	    }else{
//	    	JOptionPane.showMessageDialog(null, "The Playername is not available", "Playername ERROR",JOptionPane.WARNING_MESSAGE);
//	    
//	    	return false;
//	    }	    
//	}
	
	
	
//	public void writeIn(){
//		@SuppressWarnings("unused")
//		String stringId = null;
//		String stringChipNumber = null;
//		
//		try {
//			stringId = String.valueOf(id);
//			stringChipNumber = String.valueOf(chipNumber);
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, "StingId or StringChipNumber was not able to parse", "Parse Error",JOptionPane.WARNING_MESSAGE);
//		}
//		
//		try {
//			//BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/fabianRedecker/Dropbox/Studium FR & CS/Prog2 Projekt/Projektdateien/workspace fabian/Grafics/TestArea/PlayerList.txt", true));
//			BufferedWriter bw = new BufferedWriter(new FileWriter("/data/PlayerList.txt", true));
//			bw.write(name + ";" + stringChipNumber + ";" + password);
//			bw.newLine();
//			bw.close();
//		} catch (IOException e2) {
//			JOptionPane.showMessageDialog(null, "Error while Writing to .txt", "Writing Error",JOptionPane.WARNING_MESSAGE);
//			System.exit(0);
//			new SelectionWindow();
//			e2.printStackTrace();
//		}
//	
//	}
package EventHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import forPockerFoc.SignInWindow;
import forPockerFoc.SuccessWindow;

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
	
	//CLient
	private Socket client;
	//Streams
	private PrintStream out; 
	private BufferedReader in;
	
	public SignInHandler(SignInWindow frame){
		this.currentSignIn = frame;
		this.name = this.currentSignIn.getName();
		this.password = this.currentSignIn.getPassword();	
		
	}
	
	private void initClient(String host, int port){
		@SuppressWarnings("unused")//wait for server welcome
		String textFromserver;
		try {
			this.client = new Socket(host, port);
			System.out.println("client auf server");
		} catch (IOException e) {
			System.err.println("ERROR while conection with server" + "\n" +  e.getMessage());
			e.printStackTrace();
		}
		
	
	}
	
	private void initStreams(){
		String serverMassage = null;
	
		//output Stream
		try {
			this.out = new PrintStream(this.client.getOutputStream());
			System.out.println("out Ready");
		} catch (IOException e) {
			System.err.println("ERROR while init OUTPUTSTREAM");
			e.printStackTrace();
		}
		//input Stream
		
		try {
			this.in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
			System.out.println("in Ready");
		} catch (IOException e) {
			System.err.println("ERROR while init INPUTSTRAM");			
			e.printStackTrace();
		}
		try {
			serverMassage = this.in.readLine();
			System.out.println(serverMassage);
		} catch (IOException e) {
			System.err.println("ERROR while reading serverMesssage");
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
		//conection to server
		this.initClient("localHost", 4342);
		checkPlayerInput();
		System.out.println(name + ";" + chipNumber + ";" + password);
		System.out.println(controlCounter);
		// if Name and (Password & passwordFieldRepeat) && PlayerName is available was correct signed in Write in a list
		if(controlCounter >= 2){
			System.out.println("test1");
			
			//initialize Streams
			this.initStreams();
			

			
			//send SignIn player data so server
			
			System.out.println(name + ";" + valueOfChipnumber + ";" + password);
			this.out.println(name + ";" + valueOfChipnumber + ";" + password);
			this.currentSignIn.dispose();
			new SuccessWindow("Success", "You have been registered by FOC Pocker", "OK");
	
		}	
	}
}


