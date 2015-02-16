package software.sundc.games.poker.impl;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import software.sundc.games.poker.api.PokerDealer;
import software.sundc.games.poker.model.Card;
import software.sundc.games.poker.model.PokerHand;
import software.sundc.games.poker.model.Rank;
import software.sundc.games.poker.model.RankResult;
import software.sundc.games.poker.model.exception.InvalidCardSetException;
import software.sundc.games.poker.util.CardUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Component("pokerDealer")
public class PokerDealerImpl implements PokerDealer {

	@Autowired
	private RankCalculator rankCalculator;
	
	private PokerHandComparator pokerHandComparator = new PokerHandComparator();

	@Override
	public List<RankResult> rankPokerHands(Set<PokerHand> pokerHands) throws InvalidCardSetException {
		
		if (pokerHands == null){
			throw new InvalidCardSetException("There is no cards to deal.");
		}else if (pokerHands.size() == 0 ){
			throw new InvalidCardSetException("No poker hand is provided. Please add at least two poker hand to compare.");
		}else if (pokerHands.size() == 1 ){
			throw new InvalidCardSetException("Only one poker hand is provided (or hands are totally equals). Please add at least two poker hand to compare.");
		}else if (pokerHands.size() > 10 ){
			throw new InvalidCardSetException("It's not allowed to deal more than 52 pieces cards.");
		}else if(!arePokerHandsFull(pokerHands)){
			throw new InvalidCardSetException("Poker hands contains less than 5 cards or some of the cards are duplicated!");
		}else if (!areEachCardUnique(pokerHands)){
			throw new InvalidCardSetException("There are non-uniqe cards in poker hands. Trying to cheat?");
		}
		
		List<RankResult> rankings = Lists.newArrayList();
		List<PokerHand> pokerHandList = Lists.newArrayList(pokerHands);
		Collections.sort(pokerHandList, pokerHandComparator);
		Collections.reverse(pokerHandList);

		for (int i = 0; i < pokerHandList.size(); i++) {
			Rank rank = rankCalculator.calculateRank(pokerHandList.get(i));
			RankResult rankResult = new RankResult(pokerHandList.get(i).getHandId(), i + 1, rank);
			rankings.add(rankResult);
		}

		return rankings;
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

	private boolean arePokerHandsFull(Set<PokerHand> pokerHands) {
		
		for(PokerHand pokerHand : pokerHands){
			if(pokerHand.getCards().size() != 5){
				return false;
			}
		}
		return true;
	}

	/**
	 * Check if each card in given poker hands is unique.
	 * 
	 * @param pokerHands Poker hands to check
	 * @return <code>true</code> if each card is unique, <code>false</code> otherwise.
	 */
	private boolean areEachCardUnique(Set<PokerHand> pokerHands) {

		Set<Card> uniqueCardSet = Sets.newHashSet();
		
		for(PokerHand pokerHand : pokerHands){
			for (Card card : pokerHand.getCards()){
				if (uniqueCardSet.contains(card)){
					return false;
				}else {
					uniqueCardSet.add(card);
				}
			}
		}
		
		return true;
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
