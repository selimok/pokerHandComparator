package software.sundc.flaregames.poker.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import software.sundc.flaregames.poker.model.Card;
import software.sundc.flaregames.poker.model.PokerHand;
import software.sundc.flaregames.poker.model.Rank;
import software.sundc.flaregames.poker.model.Suit;
import software.sundc.flaregames.poker.util.CardUtils;

import com.google.common.collect.Lists;

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
		} else if (isThreeOfAKind(pokerHand)){
			return Rank.THREE_OF_A_KIND;
		} else if (isTwoPairs(pokerHand)){
			return Rank.TWO_PAIRS;
		} else if (isPair(pokerHand)){
			return Rank.PAIR;
		} else {
			return Rank.HIGH_CARD;
		}
		
	}

	private boolean isPair(PokerHand pokerHand) {
		Map<Integer, Integer> valueMapOfCards = CardUtils.detectNumberOfSameCards(pokerHand);

		for (Integer numberOfSameCards : valueMapOfCards.values()) {
			if (numberOfSameCards.equals(2)) {
				return true;
			}
		}
		
		return false;
	}

	private boolean isTwoPairs(PokerHand pokerHand) {
		Map<Integer, Integer> valueMapOfCards = CardUtils.detectNumberOfSameCards(pokerHand);

		Integer numberOfPairs = 0;
		for (Integer numberOfSameCards : valueMapOfCards.values()) {
			if (numberOfSameCards.equals(2)) {
				numberOfPairs++;
			}
		}
		
		if(numberOfPairs == 2){
			return true;
		}
		
		return false;
	}

	private boolean isThreeOfAKind(PokerHand pokerHand) {
		Map<Integer, Integer> valueMapOfCards = CardUtils.detectNumberOfSameCards(pokerHand);

		for (Integer numberOfSameCards : valueMapOfCards.values()) {
			if (numberOfSameCards.equals(3)) {
				return true;
			}
		}

		return false;
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
		return isPair(pokerHand) && isThreeOfAKind(pokerHand);
	}

	private boolean isFourOfAKind(PokerHand pokerHand) {

		Map<Integer, Integer> valueMapOfCards = CardUtils.detectNumberOfSameCards(pokerHand);

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
