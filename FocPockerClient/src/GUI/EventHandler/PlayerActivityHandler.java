package GUI.EventHandler;

import domain.Round;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerActivityHandler implements ActionListener {

    private final Round round;
    private final Component component;
    public PlayerActivityHandler(Round round, Component component){
        this.round = round;
        this.component = component;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand() == "checkButton"){
			System.out.println("check");
            round.check();
            component.repaint();
		}
		else if(e.getActionCommand() == "callButton"){
			System.out.println("call");
            round.call();
            component.repaint();
		}
		else if(e.getActionCommand() == "betButton"){
			System.out.println("bet");
            String input = JOptionPane.showInputDialog(this.component, "Bitte gebe deine Bet ein", "Bet Abfrage");
            int result;
            try{
                result = Integer.parseInt(input);
            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(this.component,"Bitte gebe eine gültige Zahl ein dein Bet wurde nicht durchgeführt ");
                return;
            }
            round.bet(result);
            component.repaint();
		}
		else if(e.getActionCommand() == "raiseButton"){
			System.out.println("raise");
            round.raise();
            component.repaint();
		}
		else if(e.getActionCommand() == "foldButton"){
			System.out.println("fold");
            round.fold();
            component.repaint();
		}

	}

}
