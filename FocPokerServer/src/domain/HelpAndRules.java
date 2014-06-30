package domain;

public class HelpAndRules {
	public static void helpMenu() {
		System.out.println("\tDESCRIPTION"
				+ "\n\tTexas hold 'em (also known as hold 'em or holdem) is a variation of the standard card game of poker. "
				+ "\n\tTexas hold 'em consists of two cards (hole cards) being dealt face down to each player and then five community "
				+ "\n\tcards being placed face-up by the dealer — a series of three ('Flop') then an additional single card "
				+ "\n\t('Turn') and another additional card ('River') - with players having the option to check, bet, raise or fold "
				+ "\n\tafter each deal."); //Source: en.wikipedia.org/wiki/Texas_hold_'em
		rulesMenu();
	}
	
	public static void rulesMenu() {
		System.out.println("\n\tRULES");
		System.out.println("\tb\t-\tBetting"); // b
		System.out.println("\tp\t-\tPlay of the hand"); // p
		System.out.println("\ts\t-\tThe Showdown"); // s
		System.out.println("\th\t-\tList of hands"); // h
		System.out.println("\te\t-\tExit"); // e
		
		System.out.println("\n\tSelection:\t");
		char helpSelection = Eingabe.readChar();
		switch (helpSelection) {
		case 'b':
			System.out.println("\n\tBETTING"
					+ "\n\tHold 'em is normally played using small and big blind bets – forced bets by two players. Antes "
					+ "\n\t(forced contributions by all players) may be used in addition to blinds, particularly in later stages of "
					+ "\n\ttournament play. A dealer button is used to represent the player in the dealer position; the dealer button "
					+ "\n\trotates clockwise after each hand, changing the position of the dealer and blinds. The small blind is posted by "
					+ "\n\tthe player to the left of the dealer and is usually equal to half of the big blind. The big blind, posted by "
					+ "\n\tthe player to the left of the small blind, is equal to the minimum bet. In tournament poker, the blind/ante "
					+ "\n\tstructure periodically increases as the tournament progresses. After one round of betting is done, the next "
					+ "\n\tbetting round will start by the person after the big blind and small blind.");
			rulesMenu();
			break;
		case 'p':
			System.out.println("\n\tPLAY OF THE HAND"
					+ "\n\tPlay begins with each player being dealt two cards face down, with the player in the small blind receiving "
					+ "\n\tthe first card and the player in the button seat receiving the last card dealt. (As in most poker games, "
					+ "\n\tthe deck is a standard 52-card deck containing no jokers.) These cards are the players' hole or pocket cards. "
					+ "\n\tThese are the only cards each player will receive individually, and they will only (possibly) be revealed at the "
					+ "\n\tshowdown, making Texas hold 'em a closed poker game."
					+ "\n\tThe hand begins with a 'pre-flop' betting round, beginning with the player to the left of the big blind "
					+ "\n\t(or the player to the left of the dealer, if no blinds are used) "
					+ "\n\tand continuing clockwise. A round of betting continues until every player has folded, put in all of their chips, "
					+ "\n\tor matched the amount put in by all other active players. See betting for a detailed account. Note that the "
					+ "\n\tblinds are considered 'live' in the pre-flop betting round, meaning that they are counted toward the amount "
					+ "\n\tthat the blind player must contribute. If all players call around to the player in the big blind position, "
					+ "\n\tthat player may either check or raise."
					+ "\n\tAfter the pre-flop betting round, assuming there remain at least two players taking part in the hand, the "
					+ "\n\tdealer deals a flop, three face-up community cards. The flop is followed by a second betting round. "
					+ "\n\tThis and all subsequent betting rounds begin with the player to the dealer's left and continue clockwise. "
					+ "\n\tAfter the flop betting round ends, a single community card (called the turn or fourth street) is dealt, followed "
					+ "\n\tby a third betting round. A final single community card (called the river or fifth street) is then dealt, "
					+ "\n\tfollowed by a fourth betting round and the showdown, if necessary.");
			rulesMenu();
			break;
		case 's':
			System.out.println("\n\tTHE SHOWDOWN"
					+ "\n\tIf a player bets and all other players fold, then the remaining player is awarded the pot and is not required "
					+ "\n\tto show his hole cards. If two or more players remain after the final betting round, a showdown occurs. On the "
					+ "\n\tshowdown, each player plays the best poker hand they can make from the seven cards comprising his two hole cards "
					+ "\n\tand the five community cards. A player may use both of his own two hole cards, only one, or none at all, to form "
					+ "\n\this final five-card hand. If the five community cards form the player's best hand, then the player is said to be "
					+ "\n\tplaying the board and can only hope to split the pot, because each other player can also use the same five cards "
					+ "\n\tto construct the same hand. "
					+ "\n\tIf the best hand is shared by more than one player, then the pot is split equally among them, with any extra chips "
					+ "\n\tgoing to the first players after the button in clockwise order. It is common for players to have closely valued, but "
					+ "\n\tnot identically ranked hands. Nevertheless, one must be careful in determining the best hand; if the hand involves "
					+ "\n\tfewer than five cards, (such as two pair or three of a kind), then kickers are used to settle ties (see the second "
					+ "\n\texample below). Note that the card's numerical rank is of sole importance; suit values are irrelevant in Hold'em. "
					+ "\n\tThe last player to bet is the first player to show his hand.");
			rulesMenu();
			break;
		case 'h':
			System.out.println("\n\tLIST OF HANDS"
					+ "\n\tRoyal Flush"
					+ "\n\tAn ace-high straight flush such as [Ace+King+Queen+Jack+10 of diamonds] is known as a royal flush, and is the "
					+ "\n\thighest-ranking standard poker hand. It is usually treated as a distinct hand in video poker. There are 4 different "
					+ "\n\tpossibilities for a royal flush: one for each suit."
					+ "\n\n\tStraight Flush"
					+ "\n\tA straight flush is a hand that contains five cards in sequence, all of the same suit, such as [Queen+Jack+10+9+8 of clubs] "
					+ "\n\t(a hand that meets the requirements of both a straight, and a flush). Two such hands are compared by their card that "
					+ "\n\tis ranked highest. Aces can play high or low in straights and straight flushes: [5+4+3+2+A of diamonds] is a 5-high straight "
					+ "\n\tflush, also known as a 'steel wheel'."
					+ "\n\n\tFour of a Kind"
					+ "\n\tFour of a kind, also known as quads, is a poker hand such as [9 of clubs+9 of spades+9 of diamonds+9+J of hearts], that "
					+ "\n\tcontains all four cards of one trank and any other (unmatched) card. Quads with higher-ranking cards defeat lower-ranking ones. "
					+ "\n\tIn community-card games (such as Texas Hold 'em) or games with wildcards or multiple decks it is possible for two or more "
					+ "\n\tplayers to obtain the same quad; in this instance, the unmatched card acts as a kicker, so "
					+ "\n\t[7 of clubs+7 of spades+7 of diamonds+7+J of hearts] defeats [7 of clubs+7 of spades+7 of diamonds+7 of hearts+10 of clubs]. "
					+ "\n\tIf two hands have the same kicker, they tie and the pot is split."
					+ "\n\n\tFull House"
					+ "\n\tA full house, also known as a full boat, is a hand such as [3 of clubs+3 of spades+3 of diamonds+6 of clubs+6 of hearts], "
					+ "\n\tthat contains three matching cards of one rank and two matching cards of another rank. Between two full houses, the one with "
					+ "\n\tthe higher-ranking three cards wins, so [7 of spades+7 of hearts+7 of diamonds+4 of spades+4 of clubs] defeats "
					+ "\n\t[6 of spades+6 of hearts+6 of diamonds+A of spades+A of clubs]. " 
					+ "\n\tIf two hands have the same three cards (possible in wild card and community card games), the hand with the higher pair "
					+ "\n\twins, so [5 of hearts+5 of diamonds+5 of spades+Q of hearts+Q of clubs] defeats [5 of clubs+5 of diamonds+5+J of spades+J of diamonds]."
					+ "\n\n\tFlush"
					+ "\n\tA flush is a poker hand such as [Q+10+7+6+4 of clubs], where all five cards are of the same suit, but not in sequence. "
					+ "\n\tTwo flushes are compared as if they were high card hands; the highest-ranking card of each is compared to determine the "
					+ "\n\twinner. If both hands have the same highest card, then the second highest-ranking card is compared, and so on until a "
					+ "\n\tdifference is found. If the two flushes contain the same five ranks of cards, they are tied and split the pot, that is, "
					+ "\n\tsuits are not used to rank them."
					+ "\n\n\tStraight"
					+ "\n\tA straight is a poker hand such as [Q of clubs+J+10 of spades+9+8 of hearts], that contains five cards of sequential rank in at least two "
					+ "\n\tdifferent suits. Two straights are ranked by comparing the highest card of each. Two straights with the same high card "
					+ "\n\tare of equal value, suits are not used to separate them. Straights are described by their highest card, as in 'ten-high "
					+ "\n\tstraight' or 'straight to the ten' for [10 of clubs+9 of diamonds+8 of hearts+7 of clubs+6 of spades]."
					+ "\n\n\tThree of a Kind"
					+ "\n\tThree of a kind, also called trips or a set, is a poker hand such as [2 of diamonds+2 of spades+2 of clubs+K of spades+6 of hearts] "
					+ "\n\tthat contains three cards of the same rank, plus two cards which are not of this rank nor the same as each other."
					+ "\n\tA higher-valued three-of-a-kind defeats a lower-valued three-of-kind, so [Q of spades+Q of hearts+Q of diamonds+7 "
					+ "\n\tof spades+4 of clubs+] defeats [J of spades+J of clubs+J+A of diamonds+K of clubs]. "
					+ "\n\tIf two hands contain three of a kind of the same value, which is possible in games with wild cards or community cards, "
					+ "\n\tthe kickers are compared to break the tie, so [4 of diamonds+4 of clubs+4 of spades+9 of diamonds+2 of clubs] defeats "
					+ "\n\t[4 of diamonds+4 of clubs+4 of spades+8 of clubs+7 of diamonds]."
					+ "\n\n\tTwo Pair"
					+ "\n\tA poker hand such as [J of hearts+J+4 of clubs+4 of spades+9 of hearts], that contains two cards of the same rank, plus two cards of another rank "
					+ "\n\t(that match each other but not the first pair), plus any card not of either rank, is called two pair. To rank two hands "
					+ "\n\tboth containing two pair, the higher-ranking pair of each is first compared, and the higher pair wins "
					+ "\n\t(so [10 of spades+10 of clubs+8 of hearts+8 of clubs+4 of spades] defeats [8 of hearts+8 of clubs+4 of spades+4 of clubs+10 "
					+ "\n\tof spades]). If both hands have the same top pair, then the second pair of each is compared, such that [10 of spades+10 of clubs+8 of "
					+ "\n\thearts+8 of clubs+4 of spades] defeats [10 of spades+10 of clubs+4 of spades+4+8 of hearts]. If both hands have the same two pairs, the kicker "
					+ "\n\tdetermines the winner, so [10 of spades+10 of clubs+8 of hearts+8 of clubs+A of diamonds] beats [10 of spades+10 of clubs+8 of hearts+8 of clubs+4 of spades]."
					+ "\n\n\tPair"
					+ "\n\tOne pair is a poker hand such as [4 of hearts+4 of spades+K of spades+10 of diamonds+5 of spades], "
					+ "\n\tthat contains two cards of one rank, plus three cards which are not of this rank nor the same as each other. Higher-ranking "
					+ "\n\tpairs defeat lower-ranking pairs; if two hands have the same pair, the non-paired cards (the kickers) are compared in descending "
					+ "\n\torder to determine the winner."
					+ "\n\n\tHighcard"
					+ "\n\tA high-card or no-pair hand is a poker hand such as [K+J of hearts+8 of clubs+7 of diamonds+4 of spades], made of any five cards not meeting any of "
					+ "\n\tthe above requirements. Essentially, no hand is made, and the only thing of any potential meaning in the hand is the "
					+ "\n\thighest card. Nevertheless, they sometimes win a pot if the other players fold or even at a showdown. Two high-card "
					+ "\n\thands are ranked by comparing the highest-ranking card. If those are equal, then the next highest-ranking card from "
					+ "\n\teach hand is compared, and so on until a difference is found. "
					+ "\n\tHigh card hands are described by the one or two highest cards in the hand, such as 'king high', 'ace-queen high', "
					+ "\n\tor by as many cards as are necessary to break a tie. They are also referred to as 'nothing', 'garbage', "
					+ "\n\tand other derogatory terms. "
					+ "\n\tThe lowest possible high card is seven-high (such as [7 of spades+5 of clubs+4+3 of diamonds+2 of clubs]), "
					+ "\n\tbecause a hand such as [6 of diamonds+5 of clubs+4 of spades+3 of diamonds+2 of hearts] would "
					+ "\n\tbe a straight, and in [6 of diamonds+4 of spades+3 of diamonds+2 of clubs+A of hearts] the ace would serve as the high card.\n");
			break;
		case 'e':
			System.out.println("-Exit help-");
			break;
		}
	}
}
