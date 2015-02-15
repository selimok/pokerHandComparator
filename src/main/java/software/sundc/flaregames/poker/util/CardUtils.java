package software.sundc.flaregames.poker.util;

import java.util.List;
import java.util.Map;

import software.sundc.flaregames.poker.model.Card;
import software.sundc.flaregames.poker.model.PokerHand;
import software.sundc.flaregames.poker.model.Suit;
import software.sundc.flaregames.poker.model.Value;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class CardUtils {
	/**
	 * Detect number of same cars and return them as a map. Each entry composed
	 * of numeric card value (as entry key) and number of cards with this
	 * numeric value (as value)
	 * 
	 * @param pokerHand
	 *            Poker hand to check
	 * @return A map with numeric card value and number of theirs occurrences
	 */
	public static Map<Integer, Integer> detectNumberOfSameCards(PokerHand pokerHand) {
		Map<Integer, Integer> valueMapOfCards = Maps.newHashMap();

		for (Card card : pokerHand.getCards()) {
			Integer numericCardValue = card.getValue().toNumericValue();
			Integer numberOfSameCards = valueMapOfCards.get(numericCardValue);
			if (numberOfSameCards == null) {
				numberOfSameCards = 0;
			}
			valueMapOfCards.put(numericCardValue, ++numberOfSameCards);
		}
		return valueMapOfCards;
	}

	/**
	 * Create a fresh poker deck.
	 * 
	 * @return A fresh poker deck with 52 card.s
	 */
	public static List<Card> createFreshPokerDeck() {
		List<Card> pokerDeck = Lists.newArrayList();

		for (Suit suit : Suit.values()) {
			for (Value value : Value.values()) {
				pokerDeck.add(new Card(suit, value));
			}
		}

		return pokerDeck;
	}
}
