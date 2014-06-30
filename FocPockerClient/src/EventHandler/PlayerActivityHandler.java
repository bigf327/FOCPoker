package EventHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerActivityHandler implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand() == "checkButton"){
			System.out.println("check");
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
