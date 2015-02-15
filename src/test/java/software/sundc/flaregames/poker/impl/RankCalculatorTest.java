package software.sundc.flaregames.poker.impl;

import org.junit.Assert;
import org.junit.Test;

import software.sundc.flaregames.poker.model.PokerHand;
import software.sundc.flaregames.poker.model.Rank;
import software.sundc.flaregames.poker.test.util.PokerTestUtil;

public class RankCalculatorTest {

	private RankCalculator rankCalculator = new RankCalculator();

	@Test
	public void shouldRankAsStraightFlush() {

		// Given		
		PokerHand pokerHand = PokerTestUtil.createPokerHand("CA","CK","CQ","CJ","C10");
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		
		// Then
		Assert.assertEquals(Rank.STRAIGHT_FLUSH, rank);
	}
	
	@Test
	public void shouldNotRankAsStraightFlush() {

		// Given
		PokerHand pokerHand = PokerTestUtil.createPokerHand("CA","CK","CQ","CJ","C9");
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		
		// Then
		Assert.assertNotEquals(Rank.STRAIGHT_FLUSH, rank);
	}
	
	@Test
	public void shouldRankAsFourOfAKind() {

		// Given
		PokerHand pokerHand = PokerTestUtil.createPokerHand("C5","H5","D5","S5","C10");
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		// Then
		Assert.assertEquals(Rank.FOUR_OF_A_KIND, rank);
	}

	@Test
	public void shouldNotRankAsFourOfAKind() {

		// Given
		PokerHand pokerHand = PokerTestUtil.createPokerHand("C5","H6","D5","S5","C10");
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		// Then
		Assert.assertNotEquals(Rank.FOUR_OF_A_KIND, rank);
	}
	
	
	@Test
	public void shouldRankAsFullHouse() {

		// Given
		PokerHand pokerHand = PokerTestUtil.createPokerHand("C5","H5","D5","S10","C10");
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		// Then
		Assert.assertEquals(Rank.FULL_HOUSE, rank);
	}
	
	@Test
	public void shouldNotRankAsFullHouse() {

		// Given
		PokerHand pokerHand = PokerTestUtil.createPokerHand("C5","H5","D6","S10","C10");
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		// Then
		Assert.assertNotEquals(Rank.FULL_HOUSE, rank);
	}
	
	
	@Test
	public void shouldRankAsFlush() {

		// Given
		PokerHand pokerHand = PokerTestUtil.createPokerHand("CA","C3","CQ","C5","C10");
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		// Then
		Assert.assertEquals(Rank.FLUSH, rank);
	}
	
	@Test
	public void shouldNotRankAsFlush() {

		// Given
		PokerHand pokerHand = PokerTestUtil.createPokerHand("CA","SK","CQ","CJ","C10");		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		// Then
		Assert.assertNotEquals(Rank.FLUSH, rank);
	}
	
	@Test
	public void shouldNotRankStraightFlushAsFlush() {

		// Given
		PokerHand pokerHand = PokerTestUtil.createPokerHand("CA","CK","CQ","CJ","C10");
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		// Then
		Assert.assertNotEquals(Rank.FLUSH, rank);
	}
	
	@Test
	public void shouldRankAsStraight() {

		// Given
		PokerHand pokerHand = PokerTestUtil.createPokerHand("CJ","S9","HQ","D8","C10");
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		// Then
		Assert.assertEquals(Rank.STRAIGHT, rank);
	}
	
	@Test
	public void shouldNotRankAsStraight() {

		// Given
		PokerHand pokerHand = PokerTestUtil.createPokerHand("CA","SK","C5","CJ","C10");		

		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		// Then
		Assert.assertNotEquals(Rank.STRAIGHT, rank);
	}
	
	@Test
	public void shouldNotRankStraightFlushAsStraight() {

		// Given
		PokerHand pokerHand = PokerTestUtil.createPokerHand("CA","CK","CQ","CJ","C10");
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		// Then
		Assert.assertNotEquals(Rank.STRAIGHT, rank);
	}
	
	@Test
	public void shouldRankAsThreeOfAKind() {

		// Given
		PokerHand pokerHand = PokerTestUtil.createPokerHand("CJ","SJ","HJ","D8","C10");

		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		// Then
		Assert.assertEquals(Rank.THREE_OF_A_KIND, rank);
	}
	
	@Test
	public void shouldNotRankAsThreeOfAKind() {
		// Given
		PokerHand pokerHand = PokerTestUtil.createPokerHand("CA","SK","C5","CJ","C10");
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		// Then
		Assert.assertNotEquals(Rank.THREE_OF_A_KIND, rank);
	}
	
	@Test
	public void shouldRankAsTwoPair() {

		// Given
		PokerHand pokerHand = PokerTestUtil.createPokerHand("CJ","SJ","HJ","D8","C10");

		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		// Then
		Assert.assertEquals(Rank.THREE_OF_A_KIND, rank);
	}
	
	@Test
	public void shouldNotRankAsTwoPairs() {

		// Given
		PokerHand pokerHand = PokerTestUtil.createPokerHand("CA","SJ","C5","S6","C10");
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		// Then
		Assert.assertNotEquals(Rank.TWO_PAIRS, rank);
	}	
	
	@Test
	public void shouldRankPair() {

		// Given
		PokerHand pokerHand = PokerTestUtil.createPokerHand("CJ","SJ","H6","D8","C10");
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		// Then
		Assert.assertEquals(Rank.PAIR, rank);
	}
	
	@Test
	public void shouldNotRankAsPair() {
		// Given
		PokerHand pokerHand = PokerTestUtil.createPokerHand("CK","SJ","C5","S6","C10");
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		// Then
		Assert.assertNotEquals(Rank.PAIR, rank);
	}	
	
	@Test
	public void shouldRankAsHighCard() {

		// Given
		PokerHand pokerHand = PokerTestUtil.createPokerHand("CJ","SK","H6","D8","C10");
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		// Then
		Assert.assertEquals(Rank.HIGH_CARD, rank);
	}

	@Test
	public void shouldNotRankAsHighCard() {
		// Given
		PokerHand pokerHand = PokerTestUtil.createPokerHand("CK","SK","C5","S6","C10");
		
		// When
		Rank rank = rankCalculator.calculateRank(pokerHand);
		
		// Then
		Assert.assertNotEquals(Rank.HIGH_CARD, rank);
	}	
	
}
