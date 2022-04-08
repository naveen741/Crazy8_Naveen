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
		int i;
		if(changedSuit==null) {
			for( i=myCards.size()-1;i>=0;i--) {
				if(myCards.get(i).getSuit().equals(topPileCard.getSuit()) || myCards.get(i).getRank().equals(topPileCard.getRank())) {
					break;
				}
			}
		}
		else {
			
			for(i=myCards.size()-1;i>=0;i--) {
				if(myCards.get(i).getSuit().equals(changedSuit)) {
					break;
				}
			}
			changedSuit=null;
		}
		outCard=myCards.get(i);
		printPlayed(outCard);
		myCards.remove(outCard);
		return outCard;
	}
	
	void printPlayed(Card outCard) {
		logger1.log(Level.INFO,"Player1 played: {0}",outCard.getRank()+" "+outCard.getSuit());
	}			
}
