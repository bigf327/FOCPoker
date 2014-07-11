package GUI.gameWindows;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SuccessWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2405712245975045919L;
	
	//Window Attribute
	private final JLabel windowText;
	private final JButton okConfirm;
	private final JPanel picPanel;
	private final JPanel textPanel;
	
	//Constructor
	public SuccessWindow(String windowTitle, String SuccesText, String buttonText){
		super(windowTitle);
		
		// init Window Attribute
		windowText = new JLabel(SuccesText);
		okConfirm = new JButton(buttonText);
		okConfirm.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				//dispose();
				init(false);
			}
		});
		
		//init Panels
		picPanel = new JPanel();
		picPanel.setMinimumSize(new Dimension(150,100));
		picPanel.setLayout(new FlowLayout());
		picPanel.repaint();
		textPanel = new JPanel();
		textPanel.setMaximumSize(new Dimension(250,100));
		textPanel.setLayout(new GridLayout(2,1));
		textPanel.add(windowText);
		textPanel.add(okConfirm);
		
		init(true);
	}
	
	public void init(boolean windowVisible){
		this.setLayout(new FlowLayout());
		this.setSize(400, 100);
		this.add(picPanel);
		this.add(textPanel);
		this.setLocationRelativeTo(null);
		this.setVisible(windowVisible);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		final Toolkit tK = this.getToolkit();
		try {
			Image bild = tK.getImage("/Users/fabianRedecker/Dropbox/Studium FR & CS/Prog2 Projekt/Projektdateien/workspace fabian/Grafics/Image/success1.png");
			g.drawImage(bild,10,30, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}	
	}	
}
