package cardgame;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player1 extends CommonStrategy implements PlayerStrategy{
	Logger logger1=Logger.getLogger(Player1.class.getName());
	public void receiveCard(Card drawnCard) {
		logger.log(Level.INFO,"Player1 recieved : {0}",drawnCard.getRank()+" "+drawnCard.getSuit());
		myCards.add(drawnCard);
	}
	public Card playCard() {
		outCard=null;
		outCard=isEight();
		if(outCard==null)
			return play();
		else {
			printPlayed(outCard);
			myCards.remove(outCard);
			return outCard;
		}
		
	}
	public Card play() {
		if(changedSuit==null) {
			for(int i=myCards.size()-1;i>=0;i--) {
				if(myCards.get(i).getSuit().equals(topPileCard.getSuit()) || myCards.get(i).getRank().equals(topPileCard.getRank())) {
					outCard=myCards.get(i);
					break;
				}
			}
		}
		else {
			
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
		int max=0;
		int count=0;
		for(int i=0;i<myCards.size();i++) {
			count=0;
			for(int j=0;j<myCards.size();j++) {
				if(myCards.get(i)==myCards.get(j))
					count++;
			}
			if(count>max && declareSuit != topPileCard.getSuit()) {
				max=count;
				declareSuit=myCards.get(i).getSuit();
			}
		}
		logger1.log(Level.INFO,"Delcare suit: {0}",declareSuit);
		return declareSuit;
		
	}
	void printPlayed(Card outCard) {
		logger1.log(Level.INFO,"Player1 played: {0}",outCard.getRank()+" "+outCard.getSuit());
	}			
}
