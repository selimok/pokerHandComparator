package software.sundc.flaregames.poker.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Component;

import software.sundc.flaregames.poker.api.PokerDealer;
import software.sundc.flaregames.poker.model.Card;
import software.sundc.flaregames.poker.model.PokerHand;
import software.sundc.flaregames.poker.model.exception.InvalidCardSetException;
import software.sundc.flaregames.poker.util.CardUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

@Component("pokerDealer")
public class PokerDealerImpl implements PokerDealer {

	private PokerHandComparator pokerHandComparator = new PokerHandComparator();

	@Override
	public Map<PokerHand, Integer> rankPokerHands(Set<PokerHand> pokerHands) {
		Map<PokerHand, Integer> rankingMap = Maps.newHashMap();
		List<PokerHand> pokerHandList = Lists.newArrayList(pokerHands);
		Collections.sort(pokerHandList, pokerHandComparator);
		Collections.reverse(pokerHandList);

		for (int i = 0; i < pokerHandList.size(); i++) {
			rankingMap.put(pokerHandList.get(i), i + 1);
		}

		return rankingMap;
	}

	@Override
	public Set<PokerHand> dealOutCardsRandomly(Integer numberOfPlayer) {

		List<Card> pokerDeck = CardUtils.createFreshPokerDeck();
		Set<PokerHand> pokerHands = Sets.newHashSet();

		for (int i = 0; i < numberOfPlayer; i++) {
			PokerHand pokerHand = pickFiveRandomCardsFromDeck(pokerDeck);
			pokerHands.add(pokerHand);
		}

		return pokerHands;
	}

	@Override
	public Set<PokerHand> dealOutCardsManually(Map<String, Set<Card>> pokerHands) throws InvalidCardSetException {
		// TODO Auto-generated method stub
		return null;
	}

	private PokerHand pickFiveRandomCardsFromDeck(List<Card> pokerDeck) {
		Set<Card> cards = Sets.newHashSet();
		for (int i = 0; i < 5; i++) {
			Random random = new Random();
			int pickIndex = random.nextInt(pokerDeck.size());
			cards.add(pokerDeck.get(pickIndex));
			pokerDeck.remove(pickIndex);
		}
		return new PokerHand(cards);
	}

}
