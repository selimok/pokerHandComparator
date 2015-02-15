package software.sundc.flaregames.poker.model;

import java.util.Set;
import java.util.UUID;

import software.sundc.flaregames.poker.model.exception.InvalidNumberOfCardsException;

/**
 * Represents a poker hand with maximum of five poker cards.
 * 
 * @author selim
 * 
 */
public class PokerHand {

	private String handId;
	private Set<Card> cards;

	// Private constructor is used only for json deserialization
	@SuppressWarnings("unused")
	private PokerHand(){
		handId = UUID.randomUUID().toString();
	}
	
	/**
	 * Create a poker hand with five cards and given hand Id.
	 * 
	 * @param cards
	 *            A set of 5 poker cards.
	 * @param handId
	 *            Id of the poker hand.
	 * @throws InvalidNumberOfCardsException
	 *             when the numbers of the cards not equals to 5.
	 */
	public PokerHand(String handId, Set<Card> cards)
			throws InvalidNumberOfCardsException {
		if (cards.size() > 5) {
			throw new InvalidNumberOfCardsException(
					"It's not allowed to create a poker hand with more than 5 poker cards.");
		}
		this.handId = handId;
		this.cards = cards;
	}

	/**
	 * Create a poker hand with five cards and a random hand Id.
	 * 
	 * @param cards
	 *            A set of 5 poker cards.
	 * @throws InvalidNumberOfCardsException
	 *             when the numbers of the cards not equals to 5.
	 */
	public PokerHand(Set<Card> cards) throws InvalidNumberOfCardsException {
		this(UUID.randomUUID().toString(), cards);
	}
	
	public String getHandId() {
		return handId;
	}

	public Set<Card> getCards() {
		return cards;
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
