package cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cardgame.Card.Rank;

public class GamePlay {
	Player1 play1=new Player1();
	Player2 play2=new Player2();
	List<Card> deck=new ArrayList<>();
	Card topCard;
	/**
	 * start function for start and restart the game
	 * @param deck passing the deck for pass the Cards to the player 
	 * @param play1 @param play2 for access the recieveIntialCard function
	 * @return deck because we need the updated deck;
	 */
	void start() {
		int i;
		deck=Card.getDeck();
		Collections.shuffle(deck);
		List<Card> player1=new ArrayList<>();
		List<Card> player2=new ArrayList<>();
		for(i=0;i<14;i++) {
			if(i%2==0)
				player2.add(deck.get(0));
			else 
				player1.add(deck.get(0));
			deck.remove(0);			
		}
		System.out.println("================================================");
		System.out.println("Player1 Cards : ");
		for(i=0;i<player1.size();i++) 
			System.out.print((i+1)+" "+player1.get(i).getRank()+" "+player1.get(i).getSuit()+" ");
		System.out.println("\n================================================");
		System.out.println("Player2 Cards : ");
		for(i=0;i<player2.size();i++) 
			System.out.print((i+1)+" "+player2.get(i).getRank()+" "+player2.get(i).getSuit()+" ");
		System.out.println("\n================================================");
		play1.receiveInitialCards(player1);
		play2.receiveInitialCards(player2);
		topCard=deck.get(0);
		deck.remove(0);
		System.out.println("TopCard : "+topCard.getRank()+" "+topCard.getSuit());
	}
	/**
	 * Play function for play the Crazy 8's and get points
	 * @param deck is passing for access the deck cards to play the game;
	 * @param play1 @param play2 for access their game Strategy and their Cards 
	 */
	void play() {
		GamePlay ob=new GamePlay();
		int point1=0,i,point2=0;
		Card.Suit decSuit=null;
		while(point1<200 && point2<200) {	
			for(i=0;i<4;i++) {
				if(play2.shouldDrawCard(topCard, decSuit)) {
					if(deck.size()!=0 && i<3) {
						System.out.println("================================================");
						play2.receiveCard(deck.get(0));		
						deck.remove(0);			
					}
				}
				else {
					topCard=play2.playCard();
					System.out.println("================================================");
					System.out.println("TopCard : "+topCard.getRank()+" "+topCard.getSuit());
					if(topCard.getRank()==Rank.EIGHT && play2.myCards.size()!=0) 
						decSuit=play2.declareSuit();
					break;
				}
			}
			if(play2.myCards.size()==0){
				System.out.println("================================================");
				System.out.println("Player2 emptied");
				point2=play1.getScore(point1);
				deck=Card.getDeck();
				Collections.shuffle(deck);
				ob.start();
			}
			for(i=0;i<4 && point2<200;i++) {
				if(play1.shouldDrawCard(topCard, decSuit)) {
					if(deck.size()!=0 && i<3) {
						System.out.println("================================================");
						play1.receiveCard(deck.get(0));	
						deck.remove(0);
					}
				}
				else {
					topCard=play1.playCard();
					System.out.println("================================================");
					System.out.println("TopCard : "+topCard.getRank()+" "+topCard.getSuit());
					if(topCard.getRank().equals(Rank.EIGHT) && play1.myCards.size()!=0) 
						decSuit=play1.declareSuit();
					break;
				}
			}
			if(play1.myCards.size()==0){
				System.out.println("================================================");
				System.out.println("Player1 emptied");
				point1=play2.getScore(point2);
				deck=Card.getDeck();
				Collections.shuffle(deck);
				ob.start();
				
			}
			if(deck.size()==0 && point1<200 && point2 <200) {
				System.out.println("================================================");
				System.out.println("Deck emptied and reshuffled");
				deck=Card.getDeck();
				Collections.shuffle(deck);
				ob.start();
			}
		}
		ob.results(point1,point2);
	}
	/**
	 * results function for show the results in console
	 * @param p1 @param p2 are the points of the player 1 and player 2;
	 */
	void results(int p1,int p2) {
		System.out.println("================================================");
		System.out.println("player1 :"+p1);
		System.out.println("player2 :"+p2);
		System.out.println("================================================");
		if(p1>=200) 
			System.out.println("player1 wins");
		else if(p2>=200) 
			System.out.println("player2 wins");
		System.out.println("================================================");
	}
}
