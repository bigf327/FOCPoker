package forPockerFoc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;


import javax.swing.*;

import EventHandler.LogInHandler;

public class LogInWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3289588213035025693L;
	
	// Content
	private JLabel playerNameLabel;
	private JTextField playerNameField;
	private JLabel placeHolder;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JButton logInButton;
	
	//Label
	private JPanel picPanel;
	private JPanel contentPanel;
	
	// Font
	private Font myFont;
	
	public LogInWindow(){
		super("FOC Poker Log In");
		//init Content
		myFont = new Font("Arial", Font.BOLD, 12);
		playerNameLabel = new JLabel("Playername :");
		playerNameLabel.setFont(myFont);
		playerNameLabel.setForeground(Color.WHITE);
		playerNameField = new JTextField();
		placeHolder = new JLabel();
		placeHolder.setBackground(Color.BLACK);
		placeHolder.setPreferredSize(new Dimension(300,20));
		placeHolder.setOpaque(false);
		passwordLabel = new JLabel("Password :");
		passwordLabel.setFont(myFont);
		passwordLabel.setForeground(Color.WHITE);
		passwordField = new JPasswordField();
		logInButton = new JButton("Log In");
		logInButton.addActionListener(new LogInHandler(this));
		
		//init Panels
		picPanel = new JPanel();
		picPanel.setBackground(Color.BLACK);
		picPanel.repaint();
		contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(6,1));
		contentPanel.setBackground(Color.BLACK);
		contentPanel.add(playerNameLabel);
		contentPanel.add(playerNameField);
		contentPanel.add(placeHolder);
		contentPanel.add(passwordLabel);
		contentPanel.add(passwordField);
		contentPanel.add(logInButton);
		
		init(true);
	}
	
	public void init(boolean windowVisible){
		this.setLayout(new GridLayout(2,1));
		//this.setBackground(Color.BLACK);
		this.setSize(300, 400);
		this.add(picPanel);
		this.add(contentPanel);
		this.setLocationRelativeTo(null);
		this.setVisible(windowVisible);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		final Toolkit tK = this.getToolkit();
		try {
			//Image bild = tK.getImage("/Users/fabianRedecker/Dropbox/Studium FR & CS/Prog2 Projekt/Projektdateien/workspace fabian/Grafics/Image/selectionPic.jpg");
			Image bild = tK.getImage(this.getClass().getResource("/Images/login135x147White.png"));
			g.drawImage(bild,80,60, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}	
	}

	public JTextField getPlayerNameField() {
		return playerNameField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}


	
}
