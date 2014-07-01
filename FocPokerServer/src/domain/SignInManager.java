package domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SignInManager implements Runnable {
	
	private final int PORT = 4342;
	private ServerSocket serverSocket;
	private Socket client;
	private BufferedReader in;
	private PrintStream out;
	
	public SignInManager(){
		
	}
	

	
	public void initServer(){
		
		try {
			
			serverSocket = new ServerSocket(PORT);
			final InetAddress iA = InetAddress.getLocalHost();
			System.out.println("Host: " + iA.getHostName());
			System.out.println("Serverport : " + PORT + "\n" + "Is ready for connection");
			
			
		} catch (IOException e) {
			System.err.println("Es gab einen Fehler bei init des Servers");
			e.printStackTrace();
		}
	}
	
	public void acceptClients(){
		
		while(true){
			
			Socket clientSocket = null;
			
			try {
				clientSocket = serverSocket.accept();
				this.initStreams(clientSocket);
			} catch (IOException e) {
				System.err.println("ERROR while accepting clients" + "\n" + e.getMessage());
				e.printStackTrace();
			}
			finally{
				if(clientSocket != null){
					try {
						clientSocket.close();
					} catch (IOException e) {
						System.err.println("ERROR Clinetsocket cant be closed" + "\n" + e.getMessage());
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	
	
	
	public void initStreams(Socket clientSocket){
		client = clientSocket;
		//output Stream
		try {
			this.out = new PrintStream(this.client.getOutputStream());
			System.out.println("server out ready");
		} catch (IOException e) {
			System.err.println("ERROR while init OUTPUTSTREAM");
			e.printStackTrace();
		}
		//input Stream
		try {
			this.in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
			System.out.println("server in ready");
		} catch (IOException e) {
			System.err.println("ERROR while init INPUTSTRAM");			
			e.printStackTrace();
		}
		
		
		sendAMessage();
		getAMessage();
	}
	
	public void getAMessage(){
		
		String test = "nich geklappt";
		try {
			test = this.in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(test);
	}
	
	public void sendAMessage(){
		this.out.println("SignInManager ready for data");
	}
	


	@Override
	public void run() {
		initServer();
		acceptClients();
	
		
	
	}

	public static void main(String[] args) {
		Runnable r = new SignInManager();
		Thread t = new Thread(r);
		t.start();
	
	}

}
