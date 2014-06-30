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
	private Socket clientSocket;
	private BufferedReader in;
	private PrintStream out;
	
	public SignInManager(){
//		//initialize Streams
//		this.initStreams();
//		
//		//initialize Server
//		this.initServer();
//		
//		//wait for Clients
//		this.acceptClients();
		
	}
	
	public void initStreams(){
		//output Stream
		try {
			this.out = new PrintStream(this.clientSocket.getOutputStream());
		} catch (IOException e) {
			System.err.println("ERROR while init OUTPUTSTREAM");
			e.printStackTrace();
		}
		//input Stream
		try {
			this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
		} catch (IOException e) {
			System.err.println("ERROR while init INPUTSTRAM");			
			e.printStackTrace();
		}
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
		
		try {
			while(true) {
			clientSocket = serverSocket.accept();
			}
		} catch (Exception e) {
			System.out.println("ERROR while accepting clients" + "\n" + e.getMessage());
		}
		this.out.println("SignInManager ready for data");
		
	
		
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
	


	@Override
	public void run() {
		initServer();
		acceptClients();
		initStreams();
		getAMessage();
		

	}

	public static void main(String[] args) {
		Runnable r = new SignInManager();
		Thread t = new Thread(r);
		t.start();

	}

}
