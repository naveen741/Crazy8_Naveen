package cardgame;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player2 extends CommonStrategy implements PlayerStrategy{
	Logger logger2=Logger.getLogger(Player2.class.getName());
	public void receiveCard(Card drawnCard) {
		logger.log(Level.INFO,"Player2 recieved : {0}",drawnCard.getRank()+" "+drawnCard.getSuit());
		myCards.add(drawnCard);
	}
	
	public Card playCard() {
		outCard=isEight();
		return playTwo();
		
	}
	public Card playTwo() {
		if(changedSuit==null && outCard==null) {
			for(int i=myCards.size()-1;i>=0;i--) {
				if(myCards.get(i).getSuit().equals(topPileCard.getSuit()) || myCards.get(i).getRank().equals(topPileCard.getRank())) {
					outCard=myCards.get(i);
					break;
				}
			}
		}
		else if(outCard== null){
			
			for(int i=myCards.size()-1;i>=0;i--) {
				if(myCards.get(i).getSuit().equals(changedSuit)) {
					outCard=myCards.get(i);
					break;
				}
			}
			changedSuit=null;
		}
		printPlayed(outCard);
		myCards.remove(outCard);
		return outCard;
	}
	public Card.Suit declareSuit(){
		Card.Suit declareSuit=myCards.get(0).getSuit();
		int max=52;
		int count;
		for(int i=0;i<myCards.size();i++) {
			count=0;
			for(int j=0;j<myCards.size();j++) {
				if(myCards.get(i)==myCards.get(j))
					count++;
			}
			if(count<max && declareSuit != topPileCard.getSuit()) {
				max=count;
				declareSuit=myCards.get(i).getSuit();
			}
		}
		logger2.log(Level.INFO,"Delcare suit: {0}",declareSuit);
		return declareSuit;
		
	}
	void printPlayed(Card outCard) {
		logger2.log(Level.INFO,"Player2 played: {0}",outCard.getRank()+" "+outCard.getSuit());
	}
	
}
