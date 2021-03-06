package forPockerFoc;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;

import valueObjects.ReadAndWriter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class SelectionWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3636720094424404829L;
	//Window Attribute
	private final JButton logInButton;
	private final JButton signInButton;
	private Image bild;
	
	// Image & Content Panels
	private final JPanel imgPanel;
	private final JPanel buttonPanel;
	
	//comonication with server
	private ReadAndWriter raw;
	
	public SelectionWindow(){
		super("Welcome to FOC Poker");
		this.raw = new ReadAndWriter("", 0);
		
		// logInButton & signInButton Layout and EventHandler
		logInButton = new JButton("Log in");
		logInButton.setBackground(Color.GRAY);
		logInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				raw.sendAText("logIn");
				new LogInWindow(raw);
				dispose();
				try {
					String a = raw.getAText();
					System.out.println(a);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		logInButton.setPreferredSize(new Dimension(250, 50));
		
		signInButton = new JButton("Sign in");
		signInButton.setBackground(Color.GRAY);
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				raw.sendAText("signIn");
				new SignInWindow(raw);
				dispose();
			}
		});
		signInButton.setPreferredSize(new Dimension(250,50));
		
		
		//Panel Layout & Panel input
		imgPanel = new JPanel();
		imgPanel.setBackground(Color.BLACK);
		imgPanel.repaint();
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2,1));
		buttonPanel.setBackground(Color.BLACK);
		buttonPanel.add(logInButton);
		buttonPanel.add(signInButton);
	
		
		this.init(true);
		this.validate();
	}
	
	// Style and WindowOption
	public void init(boolean windowVisible){
		getContentPane().setLayout(new GridLayout(2,1));
		this.setSize(300, 400);
		getContentPane().add(imgPanel);
		getContentPane().add(buttonPanel);
		this.setLocationRelativeTo(null);
		this.setVisible(windowVisible);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try{
			String message;
			message = raw.getAText();
			System.out.println(message);
		}catch(IOException e2){
			System.err.println("SelectionWindow: ERROR read server welcome");
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		final Toolkit tK = this.getToolkit();
		try {
			bild = tK.getImage(this.getClass().getResource("/Images/selection244x226.png"));
			g.drawImage(bild,25,10, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}	
	}
	
	public static void main(String[] args) {
		new SelectionWindow();
	}
	

}
