package EventHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import valueObjects.Player;
import forPockerFoc.MainWindow;

public class ChatHandler implements ActionListener {
	
	private final MainWindow handleChat;
	private final Player handlePlayer;
	
		
	public ChatHandler(MainWindow mW, Player p){
		this.handleChat = mW;
		this.handlePlayer =p;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "send"){
			handleChat.setChatText(handlePlayer.getName() + ":" + "\n" +handleChat.getChatText());
		}
		handleChat.setSendAreaText("");

	}

}
