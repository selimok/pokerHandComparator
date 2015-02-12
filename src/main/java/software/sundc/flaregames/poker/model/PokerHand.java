package software.sundc.flaregames.poker.model;

import java.util.Set;
import java.util.UUID;

import software.sundc.flaregames.poker.model.exception.CardOverflowException;

/**
 * Represents a poker hand with maximum of five poker cards.
 * 
 * @author selim
 *
 */
public class PokerHand {

	private String handId;
	private Set<Card> cards;
	
	public PokerHand(String handId, Set<Card> cards) throws CardOverflowException{
		if(cards.size() > 5){
			throw new CardOverflowException("It's not allowed to create a poker hand with more than 5 poker cards.");
		}
		this.handId = handId; 
		this.cards = cards;
	}
	
	public PokerHand(Set<Card> cards) throws CardOverflowException{
		new PokerHand(UUID.randomUUID().toString(), cards);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cards == null) ? 0 : cards.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PokerHand other = (PokerHand) obj;
		if (cards == null) {
			if (other.cards != null)
				return false;
		} else if (!cards.equals(other.cards))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PokerHand [handId=" + handId + ", cards=" + cards + "]";
	}


	
}
