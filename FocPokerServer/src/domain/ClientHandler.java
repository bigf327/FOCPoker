package domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
	
	private Socket client;
	private BufferedReader in;
	private PrintStream out;
	
	public ClientHandler(final Socket aClient){
		this.client = aClient;
		this.initStreams(aClient);
		this.out.println("Server: Welcome to Poker FOC");
	}
	

	
	public void initStreams(Socket clientSocket){
		client = clientSocket;
		//input/output Stream
		try {
			this.out = new PrintStream(this.client.getOutputStream());
			this.in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
		} catch (IOException e) {
			try{
				client.close();
			}catch (IOException e2) {
				//ignore Handle with e
			}
			System.err.println("ERROR while init IN / OUT put Streams");
			e.printStackTrace();
			return;
		}	
	}
	
	
	@Override
	public void run() {
		String input= "";
		
		do {
			
			try {
				input = this.in.readLine();
			} catch (final IOException e) {
				System.err.println("ERROR while reading lines from Client");
				System.err.println(e.getMessage());
				continue;
			}
			
			// work with input
			if(input == null){
				input = "exit";
			}
			else if(input.equals("signIn")){
				String playerData;
				System.out.println(input);
				this.out.println("Server: You choose Sign in");	
				try {
					playerData = this.in.readLine();
					SignInManager sim = new SignInManager(playerData);
					if(sim.playerNamePossible == true){
						this.out.println("ok");
					}else{
						this.out.println("ERROR");
					}
				} catch (IOException e) {
					System.err.println("clientHandler: ERROR in signIn");
					e.printStackTrace();
				}
				
			}
			else if(input.equals("logIn")){
				System.out.println(input);
				this.out.println("Server: You choose LogIn");
				String playerLogInData;
				try {
					playerLogInData = this.in.readLine();
					System.out.println(playerLogInData);
					LogInManager lim = new LogInManager(playerLogInData);
					System.out.println("ClientHandler: " + lim.isLogInDataFound());
					if(lim.isLogInDataFound() == true){
						this.out.println("ok");
					}else{
						this.out.println("ERROR");
					}
				} catch (IOException e) {
					System.err.println("clientHandler: ERROR in LogIn");
					e.printStackTrace();
				}
				
			}
		
		} while (!(input.equals("exit")));
		
		System.out.println("connection with: " + "\n" + this.client.getInetAddress() +
							"\n"+ this.client.getPort() + "\n" + "canceled by Client" );
		
		//Close IClient because he dont want do play any more
		try {
			this.client.close();
		} catch (IOException e) {
			System.err.println("ClientHandler: ERROR was not able to close conection after a succes commonication");
			e.printStackTrace();
		}
	}



}
