package GUI.EventHandler;

import domain.RoundManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerActivityHandler implements ActionListener {

    private final RoundManager roundManager;
    public PlayerActivityHandler(RoundManager roundManager){
        this.roundManager = roundManager;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand() == "checkButton"){
			System.out.println("check");
            roundManager.playerStatus('c',' ',0);
		}
		else if(e.getActionCommand() == "callButton"){
			System.out.println("call");
		}
		else if(e.getActionCommand() == "betButton"){
			System.out.println("bet");
		}
		else if(e.getActionCommand() == "raiseButton"){
			System.out.println("raise");
		}
		else if(e.getActionCommand() == "foldButton"){
			System.out.println("fold");
		}

	}

}
