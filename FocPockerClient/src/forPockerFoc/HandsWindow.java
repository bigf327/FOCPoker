package forPockerFoc;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class HandsWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7752538228392116434L;
	private String hands;
	private JTextArea handsArea;
	private JPanel contentPanel;
	private JScrollPane scrollPanel;

	public HandsWindow(){
		super("List of Hands");
		this.getHandsTXT();
		
		handsArea = new JTextArea(hands);
		handsArea.setEditable(false);
		handsArea.setRows(80);
		handsArea.setColumns(80);
		handsArea.setWrapStyleWord(true);
		handsArea.setLineWrap(true);
		
		contentPanel = new JPanel();
		contentPanel.add(handsArea);
		scrollPanel = new JScrollPane(contentPanel, 
	            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
	            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPanel.setSize(650, 700);
	
		this.setSize(650, 700);
		this.setResizable(false);
		this.add(scrollPanel);
		this.setLocationRelativeTo(null);	
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
	}
	
	private void getHandsTXT(){
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("bin/data/listOfHands_pokerFOC.txt"));
			String aline;
			while ((aline = br.readLine()) != null) {
            	hands += aline + System.getProperty("line.separator");  
           }
            br.close();
			
		} catch (Exception e) {
			System.err.println("ERROR cant read gameRules");
			e.toString();
		}
	}
}
