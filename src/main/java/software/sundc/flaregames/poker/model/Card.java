package software.sundc.flaregames.poker.model;

import java.util.UUID;

import software.sundc.flaregames.poker.model.exception.UnknownValueException;

/**
 * Poker card. A poker card has a value and suit.
 * 
 * @author selim
 * 
 */
public class Card implements Comparable<Card> {
	private Value value;
	private Suit suit;

	// Private constructor is used only for json deserialization
	@SuppressWarnings("unused")
	private Card() {
	}

	/**
	 * Create a card by suit sign and value denotion. Both parameters should be
	 * single char String object which represent card value and suit.
	 * 
	 * @param suitSign
	 *            Single character suit sign.
	 * @param denotion
	 *            Single character value denotion.
	 */
	public Card(String suitSign, String denotion) {
		this.suit = Suit.getValueOf(suitSign);
		this.value = Value.getValueOf(denotion);
	}

	/**
	 * Create a card by suit and value enumerations.
	 * 
	 * @param suit
	 *            Suit enumeration.
	 * @param value
	 *            Value enumeration.
	 */
	public Card(Suit suit, Value value) {
		if (suit == null || value == null) {
			throw new UnknownValueException("Null value is unknown. The card cannot be created");
		}
		this.suit = suit;
		this.value = value;
	}

	public Value getValue() {
		return value;
	}

	public Suit getSuit() {
		return suit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Card other = (Card) obj;
		if (suit != other.suit)
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return suit.toSign() + value.toDenotion();
	}

	@Override
	public int compareTo(Card otherCard) {
		Value thisValue = this.getValue();
		Value otherValue = otherCard.getValue();
		if (thisValue.equals(otherValue)) {
			return 0;
		} else {
			return thisValue.toNumericValue().compareTo(otherValue.toNumericValue());
		}
	}

}
