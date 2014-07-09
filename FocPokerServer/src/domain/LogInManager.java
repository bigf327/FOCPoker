package domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogInManager {
	private String playerName;
	private String playerPassword;
	private boolean logInDataFound = false;
	
	
	public LogInManager(String playerLogInData){
		String[] playerDataUnsort = playerLogInData.split(";");
		//if there are more than 3 Arguments they is a wrong input
		if(playerDataUnsort.length != 2){
			throw new IllegalArgumentException("SignInManager: ERROR playerData has to be three Arguments(playername, chips, password)");
		}
		playerName = playerDataUnsort[0];
		playerPassword = playerDataUnsort[1];
		System.out.println("LogInManager: " + playerName);
		System.out.println("LogInManager: " + playerPassword);
		if(checkPlayerData() == true){
			logInDataFound = true;
		}
	}
	
	public boolean checkPlayerData(){
		
		try{
			BufferedReader br = new BufferedReader(new FileReader("bin/data/PlayerList.txt"));

            String sZeile;
            while ((sZeile = br.readLine()) != null) {
            	System.out.println(sZeile);
                if(sZeile.contains(playerName) && sZeile.contains(playerPassword)){
                	
                	return true;
                }
               
            }
            br.close();
        }
        catch(IOException e1){
        	System.err.println("LogInManager: ERROR while reading Playerlist.txt");
            e1.printStackTrace();
        }
		return false;
	}

	public boolean isLogInDataFound() {
		return logInDataFound;
	}

}
