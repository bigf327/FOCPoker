package forPockerFoc;
import EventHandler.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.border.Border;

import valueObjects.Cards;
import valueObjects.Player;


public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8131808332147849819L;
	
	//chatWindow lightweight components
	private JTextArea showArea;
	private JTextArea sendArea;
	private JButton senderButton;
	
	//MenuBar
	private JMenuBar menuList;
	//Options
	private JMenu options;
	private JMenuItem logOut;
	private JMenuItem exit;
	//Help
	private JMenu help;
	private JMenuItem rules;
	private JMenuItem hands;
	
	//BorderColors
	private Border blackline  = BorderFactory.createLineBorder(Color.black);
	
	// Images
	private Image mainImage;
	
	//Timer
	private Countdown countdown = new Countdown(120);
	
	//Buttons
	private JTextField timer;
	public static JTextField serverInfo;
	private final JButton checkButton = new JButton("Check");
	private final JButton callButton = new JButton("Call");
	private final JButton betButton = new JButton("Bet");
	private final JButton raiseButton = new JButton("Raise");
	private final JButton foldButton = new JButton("Fold");
	
	//Other Players at Table max 5
	private final JLabel otherPlayersLabel = new JLabel("Players at Table");
	private JLabel ownPlayerInfo;
	public static JLabel otherPlayer01 = new JLabel();
	public static JLabel otherPlayer02 = new JLabel();
	public static JLabel otherPlayer03 = new JLabel();
	public static JLabel otherPlayer04 = new JLabel();
	public static JLabel otherPlayer05 = new JLabel();
	
	//Panels for BorderLayout
	private JPanel buttonPanel;
	private JPanel mainField;
	private JPanel otherPlayersAtTable;
	private JSplitPane eastSection;
	//Pannels for Chatwindow
	private JPanel chatPanel;
	private JPanel showPanel;
	private JSplitPane chatWindowPanel;
	private JScrollPane chatShowScrollPane;
	
	//Player far a MainWindow
	private final Player currentPlayer;
	Cards playerCardOne;
	Cards playerCardTwo;
	
	public MainWindow(Player p){
		super("FOC Poker");
		this.currentPlayer = p;
		
		
		//get Timer from Class Countdown
		timer = countdown.getTimerIcon();
		this.initChat();
		this.initOtherPlayersAtTable();
		this.initMenuBar();
		this.initButtonPanel();
		this.initMainfield();
		this.init(true);
		
		//TODO handle cards
		playerCardOne= Cards.getACard();
		playerCardTwo= Cards.getACard();
		
		//only for testing.....
		this.setOwnPlayerInfo(currentPlayer.getName() + "\n" + currentPlayer.getChipNumber());
		MainWindow.setOtherPlayer01("Player01" + " " + 1000);
		MainWindow.setOtherPlayer02("Player02" + " " + 1000);
		MainWindow.setOtherPlayer03("Player03" + " " + 1000);
		MainWindow.setOtherPlayer04("Player04" + " " + 1000);
		MainWindow.setOtherPlayer05("Player05" + " " + 1000);
		
	}
	
	
	//Window Attribut
	public void init(boolean windowVisible){
		this.setLayout(new BorderLayout());
		this.add(menuList, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.add(mainField, BorderLayout.CENTER);
		this.add(eastSection, BorderLayout.EAST);
		this.setSize(1150, 720);
		//MainWindow is not resizable
		this.setResizable(false);
		this.setLocationRelativeTo(null);	
		this.setVisible(windowVisible);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//init MenuBar
	private void initMenuBar(){
		//MenuBar
		menuList = new JMenuBar();
		menuList.setBackground(Color.DARK_GRAY);
		//options
		options = new JMenu("Options");
		options.setBackground(Color.DARK_GRAY);
		logOut = new JMenuItem("Log out");
		logOut.setActionCommand("logOut");
		logOut.addActionListener(new MenuBarHandler(this));
		exit = new JMenuItem("Exit");
		exit.setActionCommand("exit");
		exit.addActionListener(new MenuBarHandler(this));
		options.add(logOut);
		options.add(exit);
		//Help
		help = new JMenu("Help");
		help.setBackground(Color.DARK_GRAY);
		rules = new JMenuItem("Game Rules");
		rules.setActionCommand("rules");
		rules.addActionListener(new MenuBarHandler(this));
		hands = new JMenuItem("Hands");
		hands.setActionCommand("hands");
		hands.addActionListener(new MenuBarHandler(this));
		help.add(rules);
		help.add(hands);
		//add to MenuBar
		menuList.add(options);
		menuList.add(help);
	}
	
	private void initChat(){
		//init showArea
		showArea = new JTextArea();
		showArea.setEditable(false);
		//showArea.setSize(500, 500);
		showArea.setColumns(15);
		showArea.setRows(40);
		//if a word is to long to write in the same line the word will be written in next line
		showArea.setWrapStyleWord(true);
		showArea.setLineWrap(true);
		//init sendArea
		sendArea = new JTextArea();
		sendArea.setWrapStyleWord(true);
		sendArea.setLineWrap(true);
		//init send Button + actionListener
		senderButton = new JButton("send");
		senderButton.setActionCommand("send");
		senderButton.addActionListener(new ChatHandler(this, this.currentPlayer));
		//init chatPanel
		chatPanel = new JPanel();
		chatPanel.add(showArea);
		//add sendAria and Sender Button in show Pannel(after init showPanel)
		showPanel = new JPanel(new GridLayout(2,1));
		showPanel.add(sendArea);
		showPanel.add(senderButton);
		//init ScrollPane and add Chatpanel
		chatShowScrollPane = new JScrollPane(chatPanel, 
	            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
	            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//init ChatWindowPanel (JSpltPane) Top: chatShowScrollPane Buttom: chatsendScrollPane
		chatWindowPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		chatWindowPanel.setSize(400,700);
		chatWindowPanel.setDividerLocation(500);
		chatWindowPanel.setTopComponent(chatShowScrollPane);
		chatWindowPanel.setBottomComponent(showPanel);
	}
	
	//init Other Players at Table
	private void initOtherPlayersAtTable(){
		
		ownPlayerInfo = new JLabel();
		otherPlayersAtTable = new JPanel();
		otherPlayersAtTable.setLayout(new GridLayout(7,1));
		otherPlayersAtTable.setBackground(Color.DARK_GRAY);
		otherPlayersAtTable.setSize(400,700);
		//Set Color for otherPlayers Label
		otherPlayersLabel.setForeground(Color.WHITE);
		ownPlayerInfo.setForeground(Color.WHITE);
		otherPlayer01.setForeground(Color.WHITE);
		otherPlayer02.setForeground(Color.WHITE);
		otherPlayer03.setForeground(Color.WHITE);
		otherPlayer04.setForeground(Color.WHITE);
		otherPlayer05.setForeground(Color.WHITE);
		otherPlayersLabel.setFont(new Font("sansserif ", Font.BOLD, 18));
		//Add otherPlayerComponents to otherPlayerInfo(JPanel)
		otherPlayersAtTable.setBorder(blackline);
		otherPlayersAtTable.add(otherPlayersLabel);
		otherPlayersAtTable.add(ownPlayerInfo);
		otherPlayersAtTable.add(otherPlayer01);
		otherPlayersAtTable.add(otherPlayer02);
		otherPlayersAtTable.add(otherPlayer03);
		otherPlayersAtTable.add(otherPlayer04);
		otherPlayersAtTable.add(otherPlayer05);
		//init eastSection (JSpitPane) top: otherPlayersAtTable Buttom: ChatWindowPannel
		eastSection = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		eastSection.setDividerLocation(300);
		eastSection.setSize(400, 700);
		eastSection.setBorder(blackline);
		eastSection.setTopComponent(otherPlayersAtTable);
		eastSection.setBottomComponent(chatWindowPanel);
		
	}
	
	//init Mainfield (Table pictue)
	private void initMainfield(){
		mainField = new JPanel();
		mainField.setLayout(new FlowLayout());
		mainField.setBackground(Color.DARK_GRAY);
		mainField.repaint();
	}
	
	private void initButtonPanel(){
		//init ButtonPanel
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(Color.DARK_GRAY);
		buttonPanel.setBorder(blackline);
		buttonPanel.setMinimumSize(new Dimension(50, 30));
		checkButton.setMinimumSize(new Dimension(50, 30));
		callButton.setMinimumSize(new Dimension(50, 30));
		betButton.setMinimumSize(new Dimension(50, 30));
		raiseButton.setMinimumSize(new Dimension(50, 30));
		foldButton.setMinimumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		//set ActionCommand for Buttons
		checkButton.setActionCommand("checkButton");
		callButton.setActionCommand("callButton");
		betButton.setActionCommand("betButton");
		raiseButton.setActionCommand("raiseButton");
		foldButton.setActionCommand("foldButton");
		//add ActionListener fpr Button(PlayerActivityHander=>EventHandler)
		checkButton.addActionListener(new PlayerActivityHandler());
		callButton.addActionListener(new PlayerActivityHandler());
		betButton.addActionListener(new PlayerActivityHandler());
		raiseButton.addActionListener(new PlayerActivityHandler());
		foldButton.addActionListener(new PlayerActivityHandler());
		//init static JTextField for Server information 
		serverInfo = new JTextField();
		serverInfo.setEditable(false);
		serverInfo.setColumns(25);
		buttonPanel.add(serverInfo);
		buttonPanel.add(timer);
		buttonPanel.add(checkButton);
		buttonPanel.add(callButton);
		buttonPanel.add(betButton);
		buttonPanel.add(raiseButton);
		buttonPanel.add(foldButton);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		final Toolkit tK = this.getToolkit();		
		//draw PlayerTable
		try {
			mainImage = tK.getImage(this.getClass().getResource("/Images/table900x600.png"));
			g.drawImage(mainImage,20,65, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}	
		
		//draw first PlayerCard (left Card)
		try {
			Image cardOne = tK.getImage(this.getClass().getResource(playerCardOne.getImage()));
			g.drawImage(cardOne,400,545, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}
		
		//draw second PlayerCard (rightCard)
		try {
			Image cardTwo = tK.getImage(this.getClass().getResource(playerCardTwo.getImage()));
			g.drawImage(cardTwo,470,545, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}
		
		//Playerplace Hidden left one
		try {
			Image cardOne = tK.getImage(this.getClass().getResource("/cardImages/cardHidden90.png"));
			g.drawImage(cardOne,50,300, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}
		
		//Playerplace Hiddem left two
		try {
			Image cardTwo = tK.getImage(this.getClass().getResource("/cardImages/cardHidden90.png"));
			g.drawImage(cardTwo,50,370, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}
		
		//Playerplace Hidden right one
		try {
			Image cardOne = tK.getImage(this.getClass().getResource("/cardImages/cardHidden90.png"));
			g.drawImage(cardOne,792,300, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}
		
		//Playerplace Hiddem right two
		try {
			Image cardTwo = tK.getImage(this.getClass().getResource("/cardImages/cardHidden90.png"));
			g.drawImage(cardTwo,792,370, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}
		
		//Playerplace Hidden opposite one
		try {
			Image cardOne = tK.getImage(this.getClass().getResource("/cardImages/cardHidden.png"));
			g.drawImage(cardOne,400,85, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}
		
		//Playerplace Hiddem opposite two
		try {
			Image cardTwo = tK.getImage(this.getClass().getResource("/cardImages/cardHidden.png"));
			g.drawImage(cardTwo,470,85, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}
		
		//Playerplace Hidden opposite left one
		try {
			Image cardOne = tK.getImage(this.getClass().getResource("/cardImages/cardHidden.png"));
			g.drawImage(cardOne,180,150, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}
		
		//Playerplace Hiddem opposite right one
		try {
			Image cardTwo = tK.getImage(this.getClass().getResource("/cardImages/cardHidden.png"));
			g.drawImage(cardTwo,250,150, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}
		
		//Playerplace Hidden opposite right one
		try {
			Image cardOne = tK.getImage(this.getClass().getResource("/cardImages/cardHidden.png"));
			g.drawImage(cardOne,620,150, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}
		
		//Playerplace Hiddem opposite right two
		try {
			Image cardTwo = tK.getImage(this.getClass().getResource("/cardImages/cardHidden.png"));
			g.drawImage(cardTwo,690,150, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}
			
	}
	
	//getter + setter fpr own Player information (Jlabel)
	public String getOwnPlayerInfo(){
		String ownPlayer = ownPlayerInfo.getText();
		return ownPlayer;
	}
	
	public void setOwnPlayerInfo(String text){
		ownPlayerInfo.setText(text);
	}
	
	//getter + setter for public static JLabel otherPlayer 01 -05 
	public static String getOtherPlayer01(){
		String otherPlayer01text = otherPlayer01.getText();
		return otherPlayer01text;
	}
	
	public static void setOtherPlayer01(String text){
		otherPlayer01.setText(text);
	}
	
	public static String getOtherPlayer02(){
		String otherPlayer02text = otherPlayer02.getText();
		return otherPlayer02text;
	}
	
	public static void setOtherPlayer02(String text){
		otherPlayer02.setText(text);
	}
	
	public static String getOtherPlayer03(){
		String otherPlayer03text = otherPlayer03.getText();
		return otherPlayer03text;
	}
	
	public static void setOtherPlayer03(String text){
		otherPlayer03.setText(text);
	}
	
	public static String getOtherPlayer04(){
		String otherPlayer04text = otherPlayer04.getText();
		return otherPlayer04text;
	}
	
	public static void setOtherPlayer04(String text){
		otherPlayer04.setText(text);
	}
	
	public static String getOtherPlayer05(){
		String otherPlayer05text = otherPlayer05.getText();
		return otherPlayer05text;
	}
	
	public static void setOtherPlayer05(String text){
		otherPlayer05.setText(text);
	}
	
	//getter & setter for chat Window
	public void setChatText(String text){
		String oldText;
		oldText = showArea.getText();
		if(! oldText.equals("")){
			showArea.setText(oldText + "\n" +text);
		}else{
			showArea.setText(text);
		}	
	}
	
	public String getChatText(){
		String giveBack;
		giveBack = sendArea.getText();
		return giveBack;
	}
	//setter for sendArea
	public void setSendAreaText(String text){
		sendArea.setText(text);
	}
	
	//getter + setter for server info
	public static String getSeverInfo(){
		String serverInfoText;
		serverInfoText = serverInfo.getText();
		return serverInfoText;
	}
	public static void setServerInfo(String text){
		serverInfo.setText("Server: " +text);
	}
	
	public static void main(String[] args) {
		Cards.createDeck();
		/*MainWindow m =*/ new MainWindow(new Player(1,"BigF327", 2500, "password"));
		MainWindow.setServerInfo("Hello and welcome to FOCPoker");
		

	}

}
