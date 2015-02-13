package software.sundc.flaregames.poker.impl;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import software.sundc.flaregames.poker.model.Card;
import software.sundc.flaregames.poker.model.PokerHand;
import software.sundc.flaregames.poker.model.Rank;
import software.sundc.flaregames.poker.test.util.PokerTestUtil;

import com.google.common.collect.Sets;

public class RankCalculatorTest {

	private RankCalculator rankCalculator = new RankCalculator();

	@Test
	public void shouldRankAsStraightFlush() {

		// Given
		Card cA = new Card("C", "A");
		Card cK = new Card("C", "K");
		Card cQ = new Card("C", "Q");
		Card cJ = new Card("C", "J");
		Card c10 = new Card("C", "10");
		Set<Card> cards = PokerTestUtil.createCardSet(c10,cK,cA,cJ,cQ);
		
		PokerHand pokerHand = new PokerHand(cards);
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		
		// Then
		Assert.assertEquals(Rank.STRAIGHT_FLUSH, rank);
	}
	
	@Test
	public void shouldNotRankAsStraightFlush() {

		// Given
		Card cA = new Card("C", "A");
		Card cK = new Card("C", "K");
		Card cQ = new Card("C", "Q");
		Card cJ = new Card("C", "J");
		Card c9 = new Card("C", "9");
		Set<Card> cards = PokerTestUtil.createCardSet(c9,cK,cA,cJ,cQ);
		
		PokerHand pokerHand = new PokerHand(cards);
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		
		// Then
		Assert.assertNotEquals(Rank.STRAIGHT_FLUSH, rank);
	}
	
	@Test
	public void shouldRankAsFourOfAKind() {

		// Given
		Card c5 = new Card("C", "5");
		Card h5 = new Card("H", "5");
		Card d5 = new Card("D", "5");
		Card s5 = new Card("S", "5");
		Card c10 = new Card("C", "10");
		Set<Card> cards = PokerTestUtil.createCardSet(c5,h5,d5,c10,s5);
		
		PokerHand pokerHand = new PokerHand(cards);
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		// Then
		Assert.assertEquals(Rank.FOUR_OF_A_KIND, rank);
	}

	@Test
	public void shouldNotRankAsFourOfAKind() {

		// Given
		Card c5 = new Card("C", "5");
		Card h6 = new Card("H", "6");
		Card d5 = new Card("D", "5");
		Card s5 = new Card("S", "5");
		Card c10 = new Card("C", "10");
		Set<Card> cards = PokerTestUtil.createCardSet(c5,h6,d5,c10,s5);
		
		PokerHand pokerHand = new PokerHand(cards);
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		// Then
		Assert.assertNotEquals(Rank.FOUR_OF_A_KIND, rank);
	}
	
	
	@Test
	public void shouldRankAsFullHouse() {

		// Given
		Card c5 = new Card("C", "5");
		Card h5 = new Card("H", "5");
		Card d5 = new Card("D", "5");
		Card s10 = new Card("S", "10");
		Card c10 = new Card("C", "10");
		Set<Card> cards = PokerTestUtil.createCardSet(c5,h5,d5,c10,s10);
		
		PokerHand pokerHand = new PokerHand(cards);
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		// Then
		Assert.assertEquals(Rank.FULL_HOUSE, rank);
	}
	
	@Test
	public void shouldNotRankAsFullHouse() {

		// Given
		Card c5 = new Card("C", "5");
		Card h5 = new Card("H", "5");
		Card d6 = new Card("D", "6");
		Card s10 = new Card("S", "10");
		Card c10 = new Card("C", "10");
		Set<Card> cards = PokerTestUtil.createCardSet(c5,h5,d6,c10,s10);
		
		PokerHand pokerHand = new PokerHand(cards);
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		// Then
		Assert.assertNotEquals(Rank.FULL_HOUSE, rank);
	}
	
	
	@Test
	public void shouldRankAsFlush() {

		// Given
		Card cA = new Card("C", "A");
		Card c3 = new Card("C", "3");
		Card cQ = new Card("C", "Q");
		Card c5 = new Card("C", "5");
		Card c10 = new Card("C", "10");
		Set<Card> cards = PokerTestUtil.createCardSet(c10,c3,cA,c5,cQ);
		
		PokerHand pokerHand = new PokerHand(cards);
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		
		// Then
		Assert.assertEquals(Rank.FLUSH, rank);
	}
	
	@Test
	public void shouldNotRankAsFlush() {

		// Given
		Card cA = new Card("C", "A");
		Card cK = new Card("S", "K");
		Card cQ = new Card("C", "Q");
		Card cJ = new Card("C", "J");
		Card c10 = new Card("C", "10");
		Set<Card> cards = PokerTestUtil.createCardSet(c10,cK,cA,cJ,cQ);
		
		PokerHand pokerHand = new PokerHand(cards);
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		
		// Then
		Assert.assertNotEquals(Rank.FLUSH, rank);
	}
	
	@Test
	public void shouldNotRankStraightFlushAsFlush() {

		// Given
		Card cA = new Card("C", "A");
		Card cK = new Card("C", "K");
		Card cQ = new Card("C", "Q");
		Card cJ = new Card("C", "J");
		Card c10 = new Card("C", "10");
		Set<Card> cards = PokerTestUtil.createCardSet(c10,cK,cA,cJ,cQ);
		
		PokerHand pokerHand = new PokerHand(cards);
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		
		// Then
		Assert.assertNotEquals(Rank.FLUSH, rank);
	}
	
	@Test
	public void shouldRankAsStraight() {

		// Given
		Card cJ = new Card("C", "J");
		Card s9 = new Card("S", "9");
		Card hQ = new Card("H", "Q");
		Card d8 = new Card("D", "8");
		Card c10 = new Card("C", "10");
		Set<Card> cards = PokerTestUtil.createCardSet(c10,s9,cJ,d8,hQ);
		
		PokerHand pokerHand = new PokerHand(cards);
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		
		// Then
		Assert.assertEquals(Rank.STRAIGHT, rank);
	}
	
	@Test
	public void shouldNotRankAsStraight() {

		// Given
		Card cA = new Card("C", "A");
		Card sK = new Card("S", "K");
		Card c5 = new Card("C", "5");
		Card cJ = new Card("C", "J");
		Card c10 = new Card("C", "10");
		Set<Card> cards = PokerTestUtil.createCardSet(c10,c5,cA,cJ,sK);
		
		PokerHand pokerHand = new PokerHand(cards);
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		
		// Then
		Assert.assertNotEquals(Rank.STRAIGHT, rank);
	}
	
	@Test
	public void shouldNotRankStraightFlushAsStraight() {

		// Given
		Card cA = new Card("C", "A");
		Card cK = new Card("C", "K");
		Card cQ = new Card("C", "Q");
		Card cJ = new Card("C", "J");
		Card c10 = new Card("C", "10");
		Set<Card> cards = PokerTestUtil.createCardSet(c10,cK,cA,cJ,cQ);
		
		PokerHand pokerHand = new PokerHand(cards);
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		
		// Then
		Assert.assertNotEquals(Rank.STRAIGHT, rank);
	}
	
}
