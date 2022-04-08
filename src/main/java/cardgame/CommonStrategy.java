package cardgame;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommonStrategy {
	int playerId;
	List<Integer> opponentIds;
	List<Card> myCards;
	Card topPileCard;
	Card.Suit changedSuit;
	Card outCard=null;
	Logger logger=Logger.getLogger(CommonStrategy.class.getName());
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
		logger.log(Level.INFO,"Player2 recieved : {0}",drawnCard.getRank()+" "+drawnCard.getSuit());
		myCards.add(drawnCard);
	}
}