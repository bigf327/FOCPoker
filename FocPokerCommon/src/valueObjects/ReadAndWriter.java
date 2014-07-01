package valueObjects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ReadAndWriter {
	
	//Atributs for a conection
	private Socket client;
	private final String DEFAULT_HOST = "localHost";
	private final int PORT = 4342;
	
	//Atributs for write and Read
	private BufferedReader in;
	private PrintStream out;
	
	public ReadAndWriter(String host, int port){
		//by unsignt host use localhost
		if(host.equals("")){
			host = this.DEFAULT_HOST;
		}
		if(port == 0){
			port = this.PORT;
		}
		//conect to server
		try {
			this.client = new Socket(host,port);
		} catch (UnknownHostException e) {
			System.err.println("ReadAndWriter ERROR: unknow Host");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("ReadAndWriter ERROR: IO _ ERROR");
			e.printStackTrace();
		}
		//init Streams
		this.initStreams();
	}
	
	public void initStreams(){
		
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
			try {
				client.close();
			} catch (IOException e1) {
				System.err.println("ReadAndWrite: ERROR while closing client");
				e1.printStackTrace();
			}
			System.err.println("ReadAndWriter: ERROR while init IN / OUT put Streams");
			e.printStackTrace();
			return;
		}	
	}
	
	public void sendAText(String text){
		this.out.println(text);
	}
	
	public String getAText() throws IOException{
		String textFromServer = null;
		textFromServer = this.in.readLine();
		return textFromServer;
	
	}
	
	public void close() throws IOException{
		client.close();
	}

}
