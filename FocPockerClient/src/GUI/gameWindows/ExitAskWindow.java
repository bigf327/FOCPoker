package GUI.gameWindows;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ExitAskWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1687758552316622804L;
	
	//JComponents
	private JLabel windowText = new JLabel("Do you really want to exit?");
	private JLabel placeHolder = new JLabel("");
	private JButton ok = new JButton("YES");
	private JButton no = new JButton("NO");
	
	//JPanels
	private JPanel imgPanel = new JPanel();
	private JPanel contentPanel = new JPanel();
	
	//static boolean
	private static boolean close = false;
	
	public ExitAskWindow(){
		
		//ActionHandler for ok & no Buttons
		ok.setActionCommand("ok");
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand() == "ok"){
					System.out.println(ExitAskWindow.close);
					ExitAskWindow.setClose(true);
					System.exit(0);
				}	
			}
		});
		
		no.setActionCommand("no");
		no.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand() == "no"){
					ExitAskWindow.setClose(false);
					dispose();
				}	
			}
		});
		
		
		
		//init ContentPanel
		GridLayout contentLayout = new GridLayout(2,2);
		//set vertical gap between components
		contentLayout.setVgap(55);
		contentPanel.setLayout(contentLayout);
		contentPanel.add(windowText);
		contentPanel.add(placeHolder);
		contentPanel.add(ok);
		contentPanel.add(no);
		
		//Window Attributes
		this.setLayout(new FlowLayout());
		this.add(imgPanel);
		this.add(contentPanel);
		imgPanel.repaint();
		this.setSize(600, 150);
		this.setResizable(false);
		this.setLocationRelativeTo(null);	
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		final Toolkit tK = this.getToolkit();
		try {
			Image mainImage = tK.getImage(this.getClass().getResource("/GUI/Images/exit128x128.png"));
			g.drawImage(mainImage,5,22, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}	
	}
	
	
	public static boolean isClose() {
		return close;
	}

	public static void setClose(boolean close) {
		ExitAskWindow.close = close;
	}
}
