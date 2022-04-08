package cardgame;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player1 implements PlayerStrategy{
	int playerId;
	List<Integer> opponentIds;
	List<Card> myCards;
	Card topPileCard;
	Card.Suit changedSuit;
	Logger logger=Logger.getLogger(Player1.class.getName());
	public void init(int playerId, List<Integer> opponentIds) {
		this.playerId=playerId;
		this.opponentIds=opponentIds;
	}
	public void receiveInitialCards(List<Card> cards) {
		this.myCards=cards;
	}
	public boolean shouldDrawCard(Card topPileCard, Card.Suit changedSuit) {
		this.topPileCard=topPileCard;
		this.changedSuit=changedSuit;
		for(int i=0;i<myCards.size();i++) {
			if(myCards.get(i).getRank().equals(Card.Rank.EIGHT)) {
				return false;
			}
		}
		return check();
		
	}
	public boolean check() {
		if(changedSuit==null) {
			for(int i=0;i<myCards.size();i++) {
				if(myCards.get(i).getSuit().equals(topPileCard.getSuit()) || myCards.get(i).getRank().equals(topPileCard.getRank())) {
					return false;
				}
			}
		}
		else {
			for(int i=0;i<myCards.size();i++) {
				if(myCards.get(i).getSuit().equals(changedSuit)) {
					return false;
				}
			}
			
		}
		return true;
	}
	public void receiveCard(Card drawnCard) {
		logger.log(Level.INFO,"Player1 recieved :"+drawnCard.getRank()+" "+drawnCard.getSuit());
		myCards.add(drawnCard);
	}
	public Card playCard() {
		Card outCard=null;
		for(int i=0;i<myCards.size();i++) {
			if(myCards.get(i).getRank().equals(Card.Rank.EIGHT)) {
				outCard=myCards.get(i);
				logger.log(Level.INFO,"Player1 played: "+myCards.get(i).getRank()+" "+myCards.get(i).getSuit());
				myCards.remove(i);
				
				return outCard;
			}
		}
		if(changedSuit==null) {
			
			for(int i=0;i<myCards.size();i++) {
				if(myCards.get(i).getSuit().equals(topPileCard.getSuit()) || myCards.get(i).getRank().equals(topPileCard.getRank())) {
					logger.log(Level.INFO,"Player1 played: "+myCards.get(i).getRank()+" "+myCards.get(i).getSuit());
					outCard=myCards.get(i);
					myCards.remove(i);
					break;
				}
			}
		}
		else {
			for(int i=0;i<myCards.size();i++) {
				if(myCards.get(i).getSuit().equals(changedSuit)) {
					logger.log(Level.INFO,"Player1 played: "+myCards.get(i).getRank()+" "+myCards.get(i).getSuit());
					outCard=myCards.get(i);
					myCards.remove(i);
					changedSuit=null;
					break;
				}
			}
		}
		return outCard;
		
	}
	public Card.Suit declareSuit(){
		Card.Suit Dsuit=myCards.get(0).getSuit();
		int max=0,count=0;
		for(int i=0;i<myCards.size();i++) {
			count=0;
			for(int j=0;j<myCards.size();j++) {
				if(myCards.get(i)==myCards.get(j))
					count++;
			}
			if(count>max && Dsuit != topPileCard.getSuit()) {
				max=count;
				Dsuit=myCards.get(i).getSuit();
			}
		}
		logger.log(Level.INFO,"Delcare suit: "+Dsuit);
		return Dsuit;
		
	}
	public void processOpponentActions(List<PlayerTurn> opponentActions) {
		
	}
	 public void reset() {
		 
	 }
	@Override
	 public int getScore(int point) {
		for(int i=0;i<myCards.size();i++) {
			if(point<200)
				point+=myCards.get(i).getPointValue();
		}
		return point;
	}			
}
