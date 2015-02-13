package software.sundc.flaregames.poker.impl;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import software.sundc.flaregames.poker.model.Card;
import software.sundc.flaregames.poker.model.PokerHand;
import software.sundc.flaregames.poker.test.util.PokerTestUtil;

import com.google.common.collect.Sets;

public class PokerHandComparatorTest {

	private PokerHandComparator pokerHandComparator = new PokerHandComparator();

	@Test
	public void shouldRankHigherStraightFlushWithHigherCardAgainstOtherStraightFlush() {

		// Given
		// Card Set 1
		Card cA = new Card("C", "A");
		Card cK = new Card("C", "K");
		Card cQ = new Card("C", "Q");
		Card cJ = new Card("C", "J");
		Card c10 = new Card("C", "10");
		Set<Card> firstHandCards = PokerTestUtil.createCardSet(c10,cK,cA,cJ,cQ);
		// Card Set 2
		Card dK = new Card("D", "K");
		Card dQ = new Card("D", "Q");	
		Card dJ = new Card("D", "J");
		Card d10 = new Card("D", "10");
		Card d9 = new Card("D", "9");

		Set<Card> secondHandCards = PokerTestUtil.createCardSet(d9,dK,dQ,dJ,d10);
		
		PokerHand firstHand = new PokerHand(firstHandCards);
		PokerHand secondHand = new PokerHand(secondHandCards);
		
		// When
		int compareResult = pokerHandComparator.compare(firstHand, secondHand);
		
		
		// Then
		Assert.assertTrue(compareResult > 0);
	}

	

}
