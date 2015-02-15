package software.sundc.flaregames.poker.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import software.sundc.flaregames.poker.model.Card;
import software.sundc.flaregames.poker.model.PokerHand;
import software.sundc.flaregames.poker.model.Rank;
import software.sundc.flaregames.poker.util.CardUtils;

import com.google.common.collect.Lists;

public class PokerHandComparator implements Comparator<PokerHand> {

	private static RankComparator RANK_COMPARATOR = new RankComparator();

	/**
	 * Check if the firstHand has a higher rank to secondHand.
	 * 
	 * @param firstHand
	 *            First poker hand to compare
	 * @param secondHand
	 *            Second poker hand to compare
	 * @return A negative value if the first hand has a less rank than second
	 *         hand, 0 if both hands are equally ranked and a positive value if
	 *         first hand has a higher rank.
	 */
	@Override
	public int compare(PokerHand firstHand, PokerHand secondHand) {

		RankCalculator rankCalculator = new RankCalculator();

		Rank rankOfFirstHand = rankCalculator.calculateRank(firstHand);
		Rank rankOfSecondHand = rankCalculator.calculateRank(secondHand);

		int rankCompareResult = RANK_COMPARATOR.compare(rankOfFirstHand, rankOfSecondHand);

		if (rankCompareResult == 0) {

			// Compare hands by higher card or value of the cards
			if (Rank.TWO_PAIRS.equals(rankOfFirstHand)) {
				// Handle two pairs which requires special handling
				Integer highestPairResult = compareHighestPair(firstHand, secondHand);
				if (highestPairResult != 0) {
					return highestPairResult;
				}

				Integer lowestPairResult = compareLowestPair(firstHand, secondHand);
				if (lowestPairResult != 0) {
					return lowestPairResult;
				}

				return compareSingleCards(firstHand, secondHand);

			} else if (Rank.PAIR.equals(rankOfFirstHand)) {
				// Handle pairs which requires special handling
				Integer highestPairResult = compareHighestPair(firstHand, secondHand);
				if (highestPairResult != 0) {
					return highestPairResult;
				}
				
				return compareSingleCards(firstHand, secondHand);

			} else if (Rank.HIGH_CARD.equals(rankOfFirstHand)) {
				// Handle high cards which requires special handling
				return compareSingleCards(firstHand, secondHand);
			} else {
				Integer firstHandValue = getCardValueOfHighCard(firstHand, rankOfFirstHand);
				Integer secondHandValue = getCardValueOfHighCard(secondHand, rankOfSecondHand);

				return firstHandValue.compareTo(secondHandValue);
			}

		} else {
			return rankCompareResult;
		}

	}

	private int compareSingleCards(PokerHand firstHand, PokerHand secondHand) {
		
		List<Integer> valuesOfSingleCardsOfFirstHand = getValuesOfSingleCards(firstHand);
		List<Integer> valuesOfSingleCardsOfSecondHand = getValuesOfSingleCards(secondHand);
		
		Collections.sort(valuesOfSingleCardsOfFirstHand);
		Collections.reverse(valuesOfSingleCardsOfFirstHand);
		Collections.sort(valuesOfSingleCardsOfSecondHand);
		Collections.reverse(valuesOfSingleCardsOfSecondHand);
		
		for (int i = 0; i < valuesOfSingleCardsOfFirstHand.size(); i++) {
			Integer value1 = valuesOfSingleCardsOfFirstHand.get(i);
			Integer value2 = valuesOfSingleCardsOfSecondHand.get(i);
			Integer singleCardCompareResult = value1.compareTo(value2);
			if(singleCardCompareResult != 0){
				return singleCardCompareResult;
			}
		}
		
		return 0;
	}

	private Integer compareLowestPair(PokerHand firstHand, PokerHand secondHand) {
		Integer valueOfTheLowestPairOfFirstHand = getValueOfTheLowestPair(firstHand);
		Integer valueOfTheLowestPairOfSecondHand = getValueOfTheLowestPair(secondHand);

		return valueOfTheLowestPairOfFirstHand.compareTo(valueOfTheLowestPairOfSecondHand);
	}

	private Integer compareHighestPair(PokerHand firstHand, PokerHand secondHand) {

		Integer valueOfTheHighestPairOfFirstHand = getValueOfTheHighestPair(firstHand);
		Integer valueOfTheHighestPairOfSecondHand = getValueOfTheHighestPair(secondHand);

		return valueOfTheHighestPairOfFirstHand.compareTo(valueOfTheHighestPairOfSecondHand);
	}

	private Integer getCardValueOfHighCard(PokerHand pokerHand, Rank rank) {
	
		if (rank.equals(Rank.STRAIGHT_FLUSH)) {
			return getValueOfTheHighestCard(pokerHand);
		} else if (rank.equals(Rank.FOUR_OF_A_KIND)) {
			return getValueOfTheFourCards(pokerHand);
		} else if (rank.equals(Rank.FULL_HOUSE)) {
			return getValueOfTheThreeCards(pokerHand);
		} else if (rank.equals(Rank.FLUSH)) {
			return getValueOfTheHighestCard(pokerHand);
		} else if (rank.equals(Rank.STRAIGHT)) {
			return getValueOfTheHighestCard(pokerHand);
		} else if (rank.equals(Rank.THREE_OF_A_KIND)) {
			return getValueOfTheThreeCards(pokerHand);
		}
	
		return null;
	}

	private Integer getValueOfTheThreeCards(PokerHand pokerHand) {
	
		Map<Integer, Integer> valueMapOfCards = CardUtils.detectNumberOfSameCards(pokerHand);
	
		for (Entry<Integer, Integer> entry : valueMapOfCards.entrySet()) {
			if (entry.getValue().equals(3)) {
				return 3 * entry.getKey();
			}
		}
	
		return 0;
	}

	private Integer getValueOfTheFourCards(PokerHand pokerHand) {
	
		Map<Integer, Integer> valueMapOfCards = CardUtils.detectNumberOfSameCards(pokerHand);
	
		for (Entry<Integer, Integer> entry : valueMapOfCards.entrySet()) {
			if (entry.getValue().equals(4)) {
				return 4 * entry.getKey();
			}
		}
	
		return 0;
	}

	private Integer getValueOfTheHighestCard(PokerHand pokerHand) {
		List<Card> cards = Lists.newArrayList(pokerHand.getCards());
		Collections.sort(cards);
		Card card = cards.get(4);
		return card.getValue().toNumericValue();
	}

	private List<Integer> getValuesOfSingleCards(PokerHand pokerHand) {
	
		List<Integer> valuesOfSingleCards = Lists.newArrayList();
		Map<Integer, Integer> valueMapOfCards = CardUtils.detectNumberOfSameCards(pokerHand);
	
		for (Entry<Integer, Integer> entry : valueMapOfCards.entrySet()) {
			if (entry.getValue().equals(1)) {
				valuesOfSingleCards.add(entry.getKey());
			}
		}
	
		return valuesOfSingleCards;
	
	}

	private Integer getValueOfTheHighestPair(PokerHand pokerHand) {

		Map<Integer, Integer> valueMapOfCards = CardUtils.detectNumberOfSameCards(pokerHand);

		Integer valueOfTheHighestPair = 0;
		for (Entry<Integer, Integer> entry : valueMapOfCards.entrySet()) {
			if (entry.getValue().equals(2) && entry.getKey() > valueOfTheHighestPair) {
				valueOfTheHighestPair = entry.getKey();
			}
		}

		return valueOfTheHighestPair;

	}
	
	private Integer getValueOfTheLowestPair(PokerHand pokerHand) {

		Map<Integer, Integer> valueMapOfCards = CardUtils.detectNumberOfSameCards(pokerHand);

		Integer valueOfTheLowestPair = null;
		for (Entry<Integer, Integer> entry : valueMapOfCards.entrySet()) {
			if (entry.getValue().equals(2) && (valueOfTheLowestPair == null || entry.getKey() < valueOfTheLowestPair)) {
				valueOfTheLowestPair = entry.getKey();
			}
		}

		return valueOfTheLowestPair;

	}

}
