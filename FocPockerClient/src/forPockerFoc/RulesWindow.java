package forPockerFoc;


import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;




public class RulesWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2248538888699109440L;
	

	
	private String gameRulesText;
	private JTextArea textArea;
	private JPanel contentPanel;
	private JScrollPane scrollPanel;
	
	
	public RulesWindow(){
		super("Poker Rules");
		this.getRules();
		textArea = new JTextArea(gameRulesText);
		textArea.setEditable(false);
		//set max rows & culumns
		textArea.setRows(50);
		textArea.setColumns(50);
		//set automatic wordwrap && only whole words are alowed to wrap
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		contentPanel = new JPanel();
		contentPanel.add(textArea);
		scrollPanel = new JScrollPane(contentPanel, 
	            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
	            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPanel.setSize(650, 700);
		
		
		this.setSize(600, 700);
		this.setResizable(false);
		this.add(scrollPanel);
		this.setLocationRelativeTo(null);	
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void getRules(){
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("/bin/data/rules_pokerFOC.txt"));
			String aline;
			while ((aline = br.readLine()) != null) {
            	gameRulesText += aline + System.getProperty("line.separator");  
           }
            br.close();
			
		} catch (Exception e) {
			System.err.println("ERROR cant read gameRules");
			e.toString();
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println("afn");
	}
}
