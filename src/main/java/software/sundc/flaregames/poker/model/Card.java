package software.sundc.flaregames.poker.model;

/**
 * Poker card. A poker card has a value and suit.
 * 
 * @author selim
 * 
 */
public class Card {
	private Value value;
	private Suit suit;
	
	public Card(Suit suit, Value value){
		this.suit = suit;
		this.value = value;
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
		return suit.toSign() + value.toNumericValue();
	}

}
