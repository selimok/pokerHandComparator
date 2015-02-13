package software.sundc.flaregames.poker.model;

import java.security.InvalidParameterException;

import software.sundc.flaregames.poker.model.exception.UnknownValueException;

public enum Suit {
	CLUBS("C"), DIAMONDS("D"), HEARTS("H"), SPADES("S");

	private String sign;

	private Suit(String sign) {
		this.sign = sign;
	}

	public String toSign() {
		return sign;
	}

	/**
	 * Get enumeration value of given suit sign.
	 * 
	 * @param sign
	 *            A suit sign is a one character String, represents Card suit.
	 * @return Suit value as enumeration. {@link InvalidParameterException} when
	 *         the given sign doesn't corresponds with real card suit.
	 * @throws UnknownValueException when the given sign doesn't corresponds with real suit value. 
	 */
	public static Suit getValueOf(String sign) throws UnknownValueException {
		for (Suit suit : Suit.values()) {
			if (suit.toSign().equals(sign)) {
				return suit;
			}
		}
		throw new UnknownValueException(
				"There is no suit for the given sign.");
	}
}
