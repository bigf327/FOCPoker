package GUI.gameWindows;
import GUI.EventHandler.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

import domain.Game;
import domain.Round;
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
	
	// GUI.Images
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


    public List<Label> playerLabels = new ArrayList<Label>();
	
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


	

    private final Game game;
    private Round currentRound;
	Cards playerCardOne;
	Cards playerCardTwo;
	
	public MainWindow(Game game){
		super("FOC Poker");
        this.game = game;
        this.currentRound = game.getCurrentRound();
		
		
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

        this.setPlayerLabels();
		
	}

    private void setPlayerLabels(){
        int i = 0;
        for(Player player : this.game.getPalyers()){
            String foldMsg = (player.isInRound() ? "":"Fold");
            String currentPlayerMsg = (this.currentRound.getCurrentPlayer() == player ? " Am Zug ":"");
            int chips = player.getChipNumber();
            int bet = player.getActualBet();
            this.playerLabels.get(i).setText(player.getName() + " (" + foldMsg + currentPlayerMsg +") AllChips: " + chips + "ActualBet: " + bet);
            i++;
        }
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
		//senderButton.addActionListener(new ChatHandler(this, this.currentPlayer));
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


        otherPlayersLabel.setFont(new Font("sansserif ", Font.BOLD, 18));
        //Add otherPlayerComponents to otherPlayerInfo(JPanel)
        otherPlayersAtTable.setBorder(blackline);
        otherPlayersAtTable.add(otherPlayersLabel);

        this.playerLabels.add(new Label());
        this.playerLabels.add(new Label());
        this.playerLabels.add(new Label());
        this.playerLabels.add(new Label());
        this.playerLabels.add(new Label());
        this.playerLabels.add(new Label());
        for(Label label : this.playerLabels){
            label.setForeground(Color.WHITE);
            otherPlayersAtTable.add(label);
        }
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
		//add ActionListener fpr Button(PlayerActivityHander=>GUI.EventHandler)
		checkButton.addActionListener(new PlayerActivityHandler(this.currentRound,this));
		callButton.addActionListener(new PlayerActivityHandler(this.currentRound,this));
		betButton.addActionListener(new PlayerActivityHandler(this.currentRound,this));
		raiseButton.addActionListener(new PlayerActivityHandler(this.currentRound,this));
		foldButton.addActionListener(new PlayerActivityHandler(this.currentRound,this));
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
			mainImage = tK.getImage(this.getClass().getResource("/GUI/Images/table900x600.png"));
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
			Image cardOne = tK.getImage(this.getClass().getResource("/GUI/Images/cardImages/cardHidden90.png"));
			g.drawImage(cardOne,50,300, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}
		
		//Playerplace Hiddem left two
		try {
			Image cardTwo = tK.getImage(this.getClass().getResource("/GUI/Images/cardImages/cardHidden90.png"));
			g.drawImage(cardTwo,50,370, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}
		
		//Playerplace Hidden right one
		try {
			Image cardOne = tK.getImage(this.getClass().getResource("/GUI/Images/cardImages/cardHidden90.png"));
			g.drawImage(cardOne,792,300, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}
		
		//Playerplace Hiddem right two
		try {
			Image cardTwo = tK.getImage(this.getClass().getResource("/GUI/Images/cardImages/cardHidden90.png"));
			g.drawImage(cardTwo,792,370, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}
		
		//Playerplace Hidden opposite one
		try {
			Image cardOne = tK.getImage(this.getClass().getResource("/GUI/Images/cardImages/cardHidden.png"));
			g.drawImage(cardOne,400,85, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}
		
		//Playerplace Hiddem opposite two
		try {
			Image cardTwo = tK.getImage(this.getClass().getResource("/GUI/Images/cardImages/cardHidden.png"));
			g.drawImage(cardTwo,470,85, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}
		
		//Playerplace Hidden opposite left one
		try {
			Image cardOne = tK.getImage(this.getClass().getResource("/GUI/Images/cardImages/cardHidden.png"));
			g.drawImage(cardOne,180,150, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}
		
		//Playerplace Hiddem opposite right one
		try {
			Image cardTwo = tK.getImage(this.getClass().getResource("/GUI/Images/cardImages/cardHidden.png"));
			g.drawImage(cardTwo,250,150, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}
		
		//Playerplace Hidden opposite right one
		try {
			Image cardOne = tK.getImage(this.getClass().getResource("/GUI/Images/cardImages/cardHidden.png"));
			g.drawImage(cardOne,620,150, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}
		
		//Playerplace Hiddem opposite right two
		try {
			Image cardTwo = tK.getImage(this.getClass().getResource("/GUI/Images/cardImages/cardHidden.png"));
			g.drawImage(cardTwo,690,150, this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found", "Picture ERROR",JOptionPane.WARNING_MESSAGE);
		}
        this.checkButton.setEnabled(this.currentRound.canCurrentPlayerCheck());
        this.raiseButton.setEnabled(this.currentRound.canCurrentPlayerRaise());
        this.betButton.setEnabled(this.currentRound.canCurrentPlayerBet());
        this.foldButton.setEnabled(this.currentRound.canCurrentPlayerFold());
        this.callButton.setEnabled(this.currentRound.canCurrentPlayerCall());
        serverInfo.setText("Pot:" +this.currentRound.getPot() + "Player: "+ this.currentRound.getCurrentPlayer().getName());
        this.setPlayerLabels();
	}
	
	//getter + setter fpr own Player information (Jlabel)
	public String getOwnPlayerInfo(){
		String ownPlayer = ownPlayerInfo.getText();
		return ownPlayer;
	}
	
	public void setOwnPlayerInfo(String text){
		ownPlayerInfo.setText(text);
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
	


}
