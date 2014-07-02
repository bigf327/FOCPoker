package forPockerFoc;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import valueObjects.ReadAndWriter;
import EventHandler.SignInHandler;

public class SignInWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 32391333288742727L;
	
	//Color for JComponents
	private Color transparent = new Color(0,0,0,0);
	private Font myFont = new Font("Arial", Font.BOLD, 12);
	
	
	//Player Attribute
	private int id = 0;
	private String name;
	private final int chipNumber = 2500;
	private String password;
	
	//Window Attribute
	private JLabel playerNameLabel = new JLabel("Player Name:");
	private JTextField playerNameField = new JTextField();
	private JLabel placeHolder = new JLabel();
	private JLabel passwordLabel = new JLabel("Password:");
	private JPasswordField passwordField = new JPasswordField();
	private JLabel passwordLabelRepeat = new JLabel("Please Repeat Password");
	private JPasswordField passwordFieldRepeat = new JPasswordField();
	private JLabel placeHolder2 = new JLabel();
	private JButton createButton = new JButton("Create new Account");
	
	//Content Panel
	private JPanel content = new JPanel();
	
	private ReadAndWriter raw;
	
	//Constructor
	public SignInWindow(ReadAndWriter raw){
		super("Sign In, enjoy FOC Poker");
		this.raw = raw;
		this.id++;
		this.setJComponentTransparent();
		this.fillPanel();
		createButton.addActionListener(new SignInHandler(this, this.raw));
		this.init(true);
	}
	
	// Style and WindowOption
		public void init(boolean windowVisible){
			
			this.add(content);
			this.setSize(300, 400);
			this.setLocationRelativeTo(null);
			this.setVisible(windowVisible);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		//Add JComponents to JPanel(GridLayout)
		private void fillPanel(){
			content.setLayout(new GridLayout(9,1));
			content.setBackground(Color.BLACK);
			content.add(playerNameLabel);
			content.add(playerNameField);
			content.add(placeHolder);
			content.add(passwordLabel);
			content.add(passwordField);
			content.add(passwordLabelRepeat);
			content.add(passwordFieldRepeat);
			content.add(placeHolder2);
			content.add(createButton);	
		}
		
		//Design JComponents
		private void setJComponentTransparent(){
		this.playerNameLabel.setBackground(transparent);
		this.playerNameLabel.setFont(myFont);
		this.playerNameLabel.setForeground(Color.WHITE);
		this.playerNameField.setBackground(Color.WHITE);
		this.placeHolder.setBackground(transparent);
		this.passwordLabel.setBackground(Color.WHITE);
		this.passwordLabel.setFont(myFont);
		this.passwordLabel.setForeground(Color.WHITE);
		this.passwordField.setBackground(Color.WHITE);
		this.passwordLabelRepeat.setBackground(transparent);
		this.passwordLabelRepeat.setFont(myFont);
		this.passwordLabelRepeat.setForeground(Color.WHITE);
		this.passwordFieldRepeat.setBackground(Color.WHITE);
		this.placeHolder2.setBackground(transparent);
		}
		
		// write in a .txt
	

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getPassword() {
			return password;
		}

		public int getChipNumber() {
			return chipNumber;
		}

		public JTextField getPlayerNameField() {
			return playerNameField;
		}

		public void setPlayerNameField(JTextField playerNameField) {
			this.playerNameField = playerNameField;
		}

		public JPasswordField getPasswordField() {
			return passwordField;
		}


		public JPasswordField getPasswordFieldRepeat() {
			return passwordFieldRepeat;
		}

		
	
		
		
		
}
