package Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import domain.ClientHandler;


public class PokerServer {
	
	 private final int port;
	 private ServerSocket serverSocket;
	 
	 public PokerServer(){
		 
		 this.port = 4342;
		 try {
	            // Server-Socket anlegen
	            this.serverSocket = new ServerSocket(this.port);

	            // Serverdaten ausgeben
	            final InetAddress ia = InetAddress.getLocalHost();
	            System.out.println("Host: " + ia.getHostName());
	            System.out.println("Server :" + ia.getHostAddress() + " is conectable on Port:  "
	                    + this.port);
	        } catch (final IOException e) {
	            System.err.println("Eine Ausnahme trat beim Anlegen des Server-Sockets auf: "
	                    + e);
	            System.exit(1);
	        }
		 
	}
	 
	 public void workWithClients(){
		 try {
	            while (true) {
	                // wqit for incomming Clients
	                final Socket clientSocket = serverSocket.accept();
	                // if a Clienents was accepted Start a new ClientHandler with current accepted IClient
	                ClientHandler cH = new ClientHandler(clientSocket);
	                final Thread t = new Thread(cH);
	                t.start();
	            }
	        } catch (final IOException e) {
	            System.err.println("ERROR while waiting for a connection " + e);
	            System.exit(1);
	        }
	 }
	 
//		public void acceptClients(){
//		
//		while(true){
//			
//			Socket clientSocket = null;
//			
//			try {
//				clientSocket = serverSocket.accept();
//				this.initStreams(clientSocket);
//			} catch (IOException e) {
//				System.err.println("ERROR while accepting clients" + "\n" + e.getMessage());
//				e.printStackTrace();
//			}
//			finally{
//				if(clientSocket != null){
//					try {
//						clientSocket.close();
//					} catch (IOException e) {
//						System.err.println("ERROR Clinetsocket cant be closed" + "\n" + e.getMessage());
//						e.printStackTrace();
//					}
//				}
//			}
//			
//		}
//	}
	 

	public static void main(String[] args) {
		final PokerServer server = new PokerServer();
		server.workWithClients();
	

	}

}
