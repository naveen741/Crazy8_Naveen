package cardgame;

import java.util.*;
import java.util.logging.*;

import cardgame.Card.Rank;

public class GamePlay {
	Player1 play1=new Player1();
	Player2 play2=new Player2();
	List<Card> deck=new ArrayList<>();
	Card topCard;
	Logger logger=Logger.getLogger(GamePlay.class.getName());
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
		StringBuilder str1=new StringBuilder();
		for(i=0;i<player1.size();i++)
			str1.append((i+1)+" "+player1.get(i).getRank()+" "+player1.get(i).getSuit()+" ");
		String temp=str1.toString();
		logger.log(Level.INFO,"Player1 Cards : {0}",temp);
		StringBuilder str2=new StringBuilder();
		for(i=0;i<player2.size();i++)
			str2.append((i+1)+" "+player2.get(i).getRank()+" "+player2.get(i).getSuit()+" ");
		temp=str2.toString();
		logger.log(Level.INFO,"Player2 Cards : {0}",temp);
		play1.receiveInitialCards(player1);
		play2.receiveInitialCards(player2);
		topCard=deck.get(0);
		deck.remove(0);
		logger.log(Level.INFO,"TopCard : {0}",topCard.getRank()+" "+topCard.getSuit());
	}
	/**
	 * Play function for play the Crazy 8's and get points
	 * @param deck is passing for access the deck cards to play the game;
	 * @param play1 @param play2 for access their game Strategy and their Cards 
	 */
	void play() {
		while(point1<200 && point2<200) {	
			player2Move();
			if(play2.myCards.isEmpty()){
				logger.log(Level.INFO,"Player2 emptied");
				point2=play1.getScore(point1);
				if(point2 <200) {
					deck=Card.getDeck();
					Collections.shuffle(deck);
					start();
				}
			}
			player1Move();
			if(play1.myCards.isEmpty()){
				logger.log(Level.INFO,"Player1 emptied");
				point1=play2.getScore(point2);
				if(point1<200) {
					deck=Card.getDeck();
					Collections.shuffle(deck);
					start();
				}

			}
			if(deck.isEmpty() && point1<200 && point2 <200) {
				logger.log(Level.INFO,"Deck emptied and reshuffled");
				deck=Card.getDeck();
				Collections.shuffle(deck);
				start();
			}
		}
		results(point1,point2);
	}
	void player1Move() {
		for(i=0;i<4 && point2<200;i++) {
			if(play1.shouldDrawCard(topCard, decSuit)) {
				if(!deck.isEmpty() && i<3) {
					play1.receiveCard(deck.get(0));	
					deck.remove(0);
				}
			}
			else {
				topCard=play1.playCard();
				logger.log(Level.INFO,"TopCard : {0}",topCard.getRank()+" "+topCard.getSuit());
				if(topCard.getRank().equals(Rank.EIGHT) && !play1.myCards.isEmpty()) 
					decSuit=play1.declareSuit();
				break;
			}
		}
		
	}
	void player2Move() {
		for(i=0;i<4;i++) {
			if(play2.shouldDrawCard(topCard, decSuit)) {
				if(!deck.isEmpty() && i<3) {
					play2.receiveCard(deck.get(0));		
					deck.remove(0);			
				}
			}
			else {
				topCard=play2.playCard();
				logger.log(Level.INFO,"TopCard : {0}",topCard.getRank()+" "+topCard.getSuit());
				if(topCard.getRank()==Rank.EIGHT && !play2.myCards.isEmpty()) 
					decSuit=play2.declareSuit();
				break;
			}
		}
		
		
	}
	/**
	 * results function for show the results in console
	 * @param p1 @param p2 are the points of the player 1 and player 2;
	 */
	void results(int p1,int p2) {
		logger.log(Level.INFO,"Player1 : {0}",p1);
		logger.log(Level.INFO,"Player2 : {0}",p2);
		if(p1>=200)
			logger.log(Level.INFO,"Player1 wins");
		else if(p2>=200)
			logger.log(Level.INFO,"Player2 wins");
	}
}
