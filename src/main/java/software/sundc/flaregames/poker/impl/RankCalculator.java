package software.sundc.flaregames.poker.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import software.sundc.flaregames.poker.model.Card;
import software.sundc.flaregames.poker.model.PokerHand;
import software.sundc.flaregames.poker.model.Rank;
import software.sundc.flaregames.poker.model.Suit;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class RankCalculator {

	public Rank calculateRank(PokerHand pokerHand) {

		if (isFlush(pokerHand) && isStraight(pokerHand)) {
			return Rank.STRAIGHT_FLUSH;
		} else if (isFourOfAKind(pokerHand)) {
			return Rank.FOUR_OF_A_KIND;
		} else if (isFullHouse(pokerHand)) {
			return Rank.FULL_HOUSE;
		} else if (isFlush(pokerHand)) {
			return Rank.FLUSH;
		} else if (isStraight(pokerHand)) {
			return Rank.STRAIGHT;
		}

		return null;
	}

	private boolean isStraight(PokerHand pokerHand) {
		List<Card> cards = Lists.newArrayList(pokerHand.getCards());
		Collections.sort(cards);

		Integer baseCardValue = null;
		for (Card card : cards) {
			if (baseCardValue == null) {
				baseCardValue = card.getValue().toNumericValue();
				continue;
			}

			Integer numericCardValue = card.getValue().toNumericValue();
			if (!numericCardValue.equals(baseCardValue + 1)) {
				return false;
			}

			baseCardValue++;
		}

		return true;
	}

	private boolean isFullHouse(PokerHand pokerHand) {
		Map<Integer, Integer> valueMapOfCards = Maps.newHashMap();

		for (Card card : pokerHand.getCards()) {
			Integer numericCardValue = card.getValue().toNumericValue();
			Integer numberOfSameCards = valueMapOfCards.get(numericCardValue);
			if (numberOfSameCards == null) {
				numberOfSameCards = 0;
			}
			valueMapOfCards.put(numericCardValue, ++numberOfSameCards);
		}

		List<Integer> numberOfSameCardList = Lists.newArrayList(valueMapOfCards
				.values());

		return numberOfSameCardList.size() == 2
				&& (numberOfSameCardList.get(0) == 2 || numberOfSameCardList
						.get(0) == 3);
	}

	private boolean isFourOfAKind(PokerHand pokerHand) {

		Map<Integer, Integer> valueMapOfCards = Maps.newHashMap();

		for (Card card : pokerHand.getCards()) {
			Integer numericCardValue = card.getValue().toNumericValue();
			Integer numberOfSameCards = valueMapOfCards.get(numericCardValue);
			if (numberOfSameCards == null) {
				numberOfSameCards = 0;
			}
			valueMapOfCards.put(numericCardValue, ++numberOfSameCards);
		}

		for (Integer numberOfSameCards : valueMapOfCards.values()) {
			if (numberOfSameCards.equals(4)) {
				return true;
			}
		}

		return false;

	}

	private boolean isFlush(PokerHand pokerHand) {

		Suit referenceSuit = null;
		for (Card card : pokerHand.getCards()) {
			if (referenceSuit == null) {
				referenceSuit = card.getSuit();
				continue;
			}

			if (!referenceSuit.equals(card.getSuit())) {
				return false;
			}
		}

		return true;
	}

}
