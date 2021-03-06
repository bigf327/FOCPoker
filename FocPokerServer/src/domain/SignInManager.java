	//server mu�� das machen

//	public boolean playerNamePossible(String playername){
//		
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
package domain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SignInManager {
	private String playername;
	private String chipsString;
	//private int chips;
	private String playerPassword;
	private String PLAYERLIST;
	public boolean playerNamePossible = false;
	
	
	public SignInManager(String playerData){
		
		//write playerData in a Array for splitting 
		
		String[] playerDataUnsort = playerData.split(";");
		//if there are more than 3 Arguments they is a wrong input
		if(playerDataUnsort.length != 3){
			throw new IllegalArgumentException("SignInManager: ERROR playerData has to be three Arguments(playername, chips, password)");
		}
		//set ClassAtributes from playerData
		this.playername = playerDataUnsort[0];
		this.chipsString = playerDataUnsort[1];
		this.playerPassword = playerDataUnsort[2];	
		this.checkPlayername(playername);
		if(checkPlayername(playername) == true){
			this.writeIn();
			playerNamePossible = true;
		}
	}
	
	public void writeIn(){
		
	try {
		//BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/fabianRedecker/Dropbox/Studium FR & CS/Prog2 Projekt/Projektdateien/workspace fabian/Grafics/TestArea/PlayerList.txt", true));
		BufferedWriter bw = new BufferedWriter(new FileWriter("bin/data/PlayerList.txt", true));
		bw.write(this.playername + ";" + this.chipsString + ";" + this.playerPassword);
		bw.newLine();
		bw.close();
	} catch (IOException e) {
		System.err.println("SignInManager: ERROR while writing Playerdata to playerList.txt");
	}

}
	
	
	public boolean checkPlayername(String playerName){
		String line = "";
		int playerFoundCounter = 0;

		try {
			BufferedReader br = new BufferedReader(new FileReader("bin/data/PlayerList.txt"));
			//read playerlist.txt
			do {
				try {
					line = br.readLine();
					this.PLAYERLIST += line;
					System.out.println(PLAYERLIST);
					if(PLAYERLIST.contains(playerName)){
						playerFoundCounter++;
					}	
					System.out.println(PLAYERLIST);
				} catch (IOException e) {
					System.err
							.println("SignInManager: ERROR while reading PLAYERLIST");
					e.printStackTrace();
				}

			} while (line != null);
			try {
				br.close();
			} catch (IOException e) {
				System.err.println("SignInManager: ERROR br was not able to close");
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.err.println("SignInmanager: ERROR file do not exist");
			e.printStackTrace();
		}
		//check if chosen Playername already booked if yes return false
	    if(playerFoundCounter == 0){
	    	return true;
	    }
	    return false;		
	}

	public boolean isPlayerNamePossible() {
		return playerNamePossible;
	}


	
	

}
