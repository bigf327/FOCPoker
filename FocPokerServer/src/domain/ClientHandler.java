package domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
	
	private Socket client;
	private BufferedReader in;
	@SuppressWarnings("unused")// I will unse soon
	private PrintStream out;
	
	public ClientHandler(final Socket aClient){
		this.client = aClient;
		this.initStreams(aClient);
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

			// Eingabe bearbeiten:
			if(input == null){
				input = "exit";
			}
			else if(input.equals("signIn")){
				System.out.println(input);
				try {
					System.out.println(this.in.ready());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					String name = this.in.readLine();
					String chips = this.in.readLine();
					String password = this.in.readLine();
					System.out.println(name + chips + password);
				} catch (IOException e) {
					System.err.println("ClientHandler: Cant get Sign in data");
					e.printStackTrace();
				}	
			}
			else if(input.equals("logIn")){
				System.out.println(input);
				
			}
		
		} while (!(input.equals("exit")));
		
		System.out.println("connection with: " + "\n" + this.client.getInetAddress() +
							"\n"+ this.client.getPort() + "\n" + "canceled by Client" );
		
		//Close Client because he dont want do play any more
		try {
			this.client.close();
		} catch (IOException e) {
			System.err.println("ERROR was not able to close conection after a succes commonication");
			e.printStackTrace();
		}
	}



}
