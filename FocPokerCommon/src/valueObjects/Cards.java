package valueObjects;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;



public enum Cards {
	
	//herz
	ace_hearts(new Card("Ace", "hearts", 14),("/GUI.Images.cardImages/1.png")),
	Two_hearts(new Card("2", "hearts", 2), ("/GUI.Images.cardImages/2.png")),
	three_hearts(new Card("3", "hearts", 3), ("/GUI.Images.cardImages/3.png")),
	four_hearts(new Card("4", "hearts", 4), ("/GUI.Images.cardImages/4.png")),
	five_hearts(new Card("5", "hearts", 5), ("/GUI.Images.cardImages/5.png")),
	six_hearts(new Card("6", "hearts", 6), ("/GUI.Images.cardImages/6.png")),
	seven_hearts(new Card("7", "hearts", 7), ("/GUI.Images.cardImages/7.png")),
	eight_hearts(new Card("8", "hearts", 8), ("/GUI.Images.cardImages/8.png")),
	nine_hearts(new Card("9", "hearts", 9), ("/GUI.Images.cardImages/9.png")),
	ten_hearts(new Card("10", "hearts", 10), ("/GUI.Images.cardImages/10.png")),
	jack_hearts(new Card("Jack", "hearts", 11), ("/GUI.Images.cardImages/11.png")),
	queen_hearts(new Card("Queen", "hearts", 12), ("/GUI.Images.cardImages/12.png")),
	king_hearts(new Card("King", "hearts", 13), ("/GUI.Images.cardImages/13.png")),
	
	//Pik
	ace_spades(new Card("Ace", "spades", 14), ("/GUI.Images.cardImages/14.png")),
	two_spades(new Card("2", "spades", 2), ("/GUI.Images.cardImages/15.png")),
	three_spades(new Card("3", "spades", 3), ("/GUI.Images.cardImages/16.png")),
	four_spades(new Card("4", "spades", 4), ("/GUI.Images.cardImages/17.png")),
	five_spades(new Card("5", "spades", 5), ("/GUI.Images.cardImages/18.png")),
	six_spades(new Card("6", "spades", 6), ("/GUI.Images.cardImages/19.png")),
	seven_spades(new Card("7", "spades", 7), ("/GUI.Images.cardImages/20.png")),
	eight_spades(new Card("8", "spades", 8), ("/GUI.Images.cardImages/21.png")),
	nine_spades(new Card("9", "spades", 8), ("/GUI.Images.cardImages/22.png")),
	ten_spades(new Card("10", "spades", 10), ("/GUI.Images.cardImages/23.png")),
	jack_spades(new Card("Jack", "spades", 11), ("/GUI.Images.cardImages/24.png")),
	queen_spades(new Card("Queen", "spades", 12), ("/GUI.Images.cardImages/25.png")),
	king_spades(new Card("King", "spades", 13), ("/GUI.Images.cardImages/26.png")),
	
	//Karo
	ace_diamond(new Card("14", "diamonds", 14), ("/GUI.Images.cardImages/27.png")),
	two_diamond(new Card("2", "diamonds", 2), ("/GUI.Images.cardImages/28.png")),
	three_diamond(new Card("3", "diamonds", 3), ("/GUI.Images.cardImages/29.png")),
	four_diamond(new Card("4", "diamonds", 4), ("/GUI.Images.cardImages/30.png")),
	five_diamond(new Card("5", "diamonds", 5), ("/GUI.Images.cardImages/31.png")),
	six_diamond(new Card("6", "diamonds", 6), ("/GUI.Images.cardImages/32.png")),
	seven_diamond(new Card("7", "diamonds", 7), ("/GUI.Images.cardImages/33.png")),
	eight_diamond(new Card("8", "diamonds", 8), ("/GUI.Images.cardImages/34.png")),
	nine_diamond(new Card("9", "diamonds", 9), ("/GUI.Images.cardImages/35.png")),
	ten_diamond(new Card("10", "diamonds", 10), ("/GUI.Images.cardImages/36.png")),
	jack_diamond(new Card("Jack", "diamonds", 11), ("/GUI.Images.cardImages/37.png")),
	queen_diamond(new Card("Queen", "diamonds", 12), ("/GUI.Images.cardImages/38.png")),
	king_diamond(new Card("King", "diamonds", 13), ("/GUI.Images.cardImages/39.png")),
	
	//Kreuz
	ace_clubs(new Card("14", "clubs", 14), ("/GUI.Images.cardImages/40.png")),
	two_clubs(new Card("2", "clubs", 2), ("/GUI.Images.cardImages/41.png")),
	three_clubs(new Card("3", "clubs", 3), ("/GUI.Images.cardImages/42.png")),
	four_clubs(new Card("4", "clubs", 4), ("/GUI.Images.cardImages/43.png")),
	five_clubs(new Card("5", "clubs", 5), ("/GUI.Images.cardImages/44.png")),
	six_clubs(new Card("6", "clubs", 6), ("/GUI.Images.cardImages/45.png")),
	seven_clubs(new Card("7", "clubs", 7), ("/GUI.Images.cardImages/46.png")),
	eight_clubs(new Card("8", "clubs", 8), ("/GUI.Images.cardImages/47.png")),
	nine_clubs(new Card("9", "clubs", 9), ("/GUI.Images.cardImages/48.png")),
	ten_clubs(new Card("10", "clubs", 10), ("/GUI.Images.cardImages/49.png")),
	jack_clubs(new Card("Jack", "clubs", 11), ("/GUI.Images.cardImages/50.png")),
	queen_clubs(new Card("Queen", "clubs", 12), ("/GUI.Images.cardImages/51.png")),
	king_clubs(new Card("King", "clubs", 13), ("/GUI.Images.cardImages/52.png"));
	
	
	
	private Card card;
	private String image;
	
	private final static Stack<Cards> cardsDeck = new Stack<Cards>();
	private final static ArrayList<Cards> cardsArrayList = new ArrayList<Cards>();
	private final static Cards[] cardsArray = Cards.values();
	
	private Cards(Card card, String image){
		this.image = image;
		this.card = card;
	}
	
	public Card getCard() {
		return card;
	}

	public String getImage() {
		return image;
	}
	
	//Method to create a Card Deck (implements in a Stack)
	public static void createDeck(){
		//from Array to Arraylist
		for(int i =0; i< cardsArray.length; i++){
			cardsArrayList.add(cardsArray[i]);
		}
				
		//shuffle cards
		Collections.shuffle(cardsArrayList);
		
		// shuffeld cards push to Stack
		for(int i=0; i< cardsArrayList.size(); i++){
			cardsDeck.push(cardsArrayList.get(i));
		}
	}
	
	//Method to get a random Card from CardsDeck(Satck)
	public static Cards getACard(){
		Cards oneCard = null; 
		
		//check if cardsdeck is empty false => pop a Card from Stack
		if(! (cardsDeck.isEmpty())){
			oneCard = cardsDeck.pop();
			System.out.println("Cards in Deck: " + cardsDeck.size());	
		}
		//if cardsDeck is empty create a new one && if cardsdeck is empty false => pop a Card from Stack
		else if(cardsDeck.isEmpty()){
			System.out.println("Stack(cardsDeck) is empty try to create a new one");
			System.out.println("\n");
			Cards.createDeck();
			if(! (cardsDeck.isEmpty())){
				System.out.println("Stack(cardsDeck) is now available");
				System.out.println("\n");
				oneCard = cardsDeck.pop();
			//Error no Card in stack
			}else{
				System.err.println("ERROR in Cards.cardsDeck (Stack ist empty)");
				System.out.println("\n");
			}
		}
		return oneCard;
	}
	
	
}

	
