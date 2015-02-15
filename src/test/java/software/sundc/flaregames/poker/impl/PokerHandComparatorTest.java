package software.sundc.flaregames.poker.impl;

import org.junit.Assert;
import org.junit.Test;

import software.sundc.flaregames.poker.model.PokerHand;
import software.sundc.flaregames.poker.test.util.PokerTestUtil;

public class PokerHandComparatorTest {

	private PokerHandComparator pokerHandComparator = new PokerHandComparator();

	@Test
	public void shouldRankHigherStraightFlushWithHigherCardAgainstOtherStraightFlush() {

		// Given
		PokerHand firstHand = PokerTestUtil.createPokerHand("C10","CK","CA","CJ","CQ");
		PokerHand secondHand = PokerTestUtil.createPokerHand("D9","DK","DQ","DJ","D10");
		
		// When
		int compareResult = pokerHandComparator.compare(firstHand, secondHand);
		
		// Then
		Assert.assertTrue(compareResult > 0);
	}

	@Test
	public void shouldRankHigherStraightFlushAgainstFourOfAKind() {
		// Given
		PokerHand firstHand = PokerTestUtil.createPokerHand("CA","CK","CQ","CJ","C10");
		PokerHand secondHand = PokerTestUtil.createPokerHand("D8","S8","H8","C8","D9");
		
		// When
		int compareResult = pokerHandComparator.compare(firstHand, secondHand);
		
		// Then
		Assert.assertTrue(compareResult > 0);
	}

	@Test
	public void shouldRankLowerFourOfAKindAgainstStraightFlush() {

		// Given
		PokerHand firstHand = PokerTestUtil.createPokerHand("C10","CK","CA","CJ","CQ");
		PokerHand secondHand = PokerTestUtil.createPokerHand("D9","D8","S8","H8","C9");

		// When
		int compareResult = pokerHandComparator.compare(secondHand, firstHand);
		
		// Then
		Assert.assertTrue(compareResult < 0);
	}

	@Test
	public void shouldRankHigherFourOfAKindWithHigherValueAgainstOtherFourOfAKind() {

		// Given
		PokerHand firstHand = PokerTestUtil.createPokerHand("HK","CK","SK","DK","C8");
		PokerHand secondHand = PokerTestUtil.createPokerHand("HQ","DQ","SQ","CQ","D10");
		
		// When
		int compareResult = pokerHandComparator.compare(firstHand, secondHand);
		
		// Then
		Assert.assertTrue(compareResult > 0);
	}
	
	@Test
	public void shouldRankHigherFourOfAKindAgainstFullHouse() {

		// Given
		PokerHand firstHand = PokerTestUtil.createPokerHand("HK","CK","SK","DK","C8");
		PokerHand secondHand = PokerTestUtil.createPokerHand("HQ","DQ","SQ","SJ","CJ");
		
		// When
		int compareResult = pokerHandComparator.compare(firstHand, secondHand);
		
		// Then
		Assert.assertTrue(compareResult > 0);
	}
	
	@Test
	public void shouldRankHigherFullHouseWithHigherValueAgainstOtherFullHouse() {

		// Given
		PokerHand firstHand = PokerTestUtil.createPokerHand("HK","CK","SK","D8","C8");
		PokerHand secondHand = PokerTestUtil.createPokerHand("HQ","DQ","SQ","CJ","DJ");
		
		// When
		int compareResult = pokerHandComparator.compare(firstHand, secondHand);
		
		// Then
		Assert.assertTrue(compareResult > 0);
	}	

	@Test
	public void shouldRankHigherFullHouseAgainstFlush() {

		// Given
		PokerHand firstHand = PokerTestUtil.createPokerHand("HK","CK","SK","DK","C8");
		PokerHand secondHand = PokerTestUtil.createPokerHand("CQ","CJ","C10","C9","C7");
		
		// When
		int compareResult = pokerHandComparator.compare(firstHand, secondHand);
		
		// Then
		Assert.assertTrue(compareResult > 0);
	}
	
	
	@Test
	public void shouldRankHigherFlushWithHigherCardAgainstOtherFlush() {

		// Given
		PokerHand firstHand = PokerTestUtil.createPokerHand("HK","HQ","HJ","H10","H2");
		PokerHand secondHand = PokerTestUtil.createPokerHand("CQ","CJ","C10","C9","C3");
		
		// When
		int compareResult = pokerHandComparator.compare(firstHand, secondHand);
		
		// Then
		Assert.assertTrue(compareResult > 0);
	}

	@Test
	public void shouldRankHigherFlushAgainstStraight() {

		// Given
		PokerHand firstHand = PokerTestUtil.createPokerHand("HK","HQ","HJ","H3","H7");
		PokerHand secondHand = PokerTestUtil.createPokerHand("CQ","SJ","H10","C9","D8");
		
		// When
		int compareResult = pokerHandComparator.compare(firstHand, secondHand);
		
		// Then
		Assert.assertTrue(compareResult > 0);
	}

	@Test
	public void shouldRankHigherStraightHighestCardAgainstOtherStraight() {

		// Given
		PokerHand firstHand = PokerTestUtil.createPokerHand("HK","DQ","SJ","H10","H9");
		PokerHand secondHand = PokerTestUtil.createPokerHand("HQ","CJ","C10","C9","C8");
		
		// When
		int compareResult = pokerHandComparator.compare(firstHand, secondHand);
		
		// Then
		Assert.assertTrue(compareResult > 0);
	}

	@Test
	public void shouldRankHigherStraightAgainsThreeOfAKind() {

		// Given
		PokerHand firstHand = PokerTestUtil.createPokerHand("CQ","SJ","H10","C9","D8");
		PokerHand secondHand = PokerTestUtil.createPokerHand("HK","CK","DK","H3","H7");
		
		// When
		int compareResult = pokerHandComparator.compare(firstHand, secondHand);
		
		// Then
		Assert.assertTrue(compareResult > 0);
	}
	
	@Test
	public void shouldRankHigherThreeOfAKindWithHigherValueAgainstOtherThreeOfAKind() {

		// Given
		PokerHand firstHand = PokerTestUtil.createPokerHand("HA","DA","SA","H10","H9");
		PokerHand secondHand = PokerTestUtil.createPokerHand("CK","DK","SK","C9","C8");
		
		// When
		int compareResult = pokerHandComparator.compare(firstHand, secondHand);
		
		// Then
		Assert.assertTrue(compareResult > 0);
	}

	@Test
	public void shouldRankHigherThreeOfAKindAgainsTwoPairs() {

		// Given
		PokerHand firstHand = PokerTestUtil.createPokerHand("CA","SA","HA","C9","D8");
		PokerHand secondHand = PokerTestUtil.createPokerHand("HK","CK","DQ","HQ","H7");
		
		// When
		int compareResult = pokerHandComparator.compare(firstHand, secondHand);
		
		// Then
		Assert.assertTrue(compareResult > 0);
	}
	
	@Test
	public void shouldRankHigherTwoPairsWithHigherValueAgainstOtherTwoPairs() {

		// Given
		PokerHand firstHand = PokerTestUtil.createPokerHand("HA","DA","SQ","HQ","H9");
		PokerHand secondHand = PokerTestUtil.createPokerHand("CK","DK","SJ","CJ","C8");
		
		// When
		int compareResult = pokerHandComparator.compare(firstHand, secondHand);
		
		// Then
		Assert.assertTrue(compareResult > 0);
	}
	
	@Test
	public void shouldRankHigherTwoPairsWithHigherValueOfSecondPairAgainstOtherTwoPairs() {

		// Given
		PokerHand firstHand = PokerTestUtil.createPokerHand("HA","DA","SK","HK","H9");
		PokerHand secondHand = PokerTestUtil.createPokerHand("CA","DA","SQ","CQ","C8");
		
		// When
		int compareResult = pokerHandComparator.compare(firstHand, secondHand);
		
		// Then
		Assert.assertTrue(compareResult > 0);
	}
	
	@Test
	public void shouldRankHigherTwoPairsWithHigherValueOfLastCardAgainstOtherThreeOfAKind() {

		// Given
		PokerHand firstHand = PokerTestUtil.createPokerHand("HA","DA","SK","HK","H9");
		PokerHand secondHand = PokerTestUtil.createPokerHand("CA","DA","CK","DK","C8");
		
		// When
		int compareResult = pokerHandComparator.compare(firstHand, secondHand);
		
		// Then
		Assert.assertTrue(compareResult > 0);
	}

	@Test
	public void shouldRankHigherTwoPairsAgainsPair() {

		// Given
		PokerHand firstHand = PokerTestUtil.createPokerHand("CA","SA","D10","C10","D8");
		PokerHand secondHand = PokerTestUtil.createPokerHand("HK","CK","D7","HQ","H5");
		
		// When
		int compareResult = pokerHandComparator.compare(firstHand, secondHand);
		
		// Then
		Assert.assertTrue(compareResult > 0);
	}
	
	@Test
	public void shouldRankHigherPairWithHigherValueAgainstOtherPair() {

		// Given
		PokerHand firstHand = PokerTestUtil.createPokerHand("HA","DA","SQ","HJ","H9");
		PokerHand secondHand = PokerTestUtil.createPokerHand("CK","DK","SJ","C9","C8");
		
		// When
		int compareResult = pokerHandComparator.compare(firstHand, secondHand);
		
		// Then
		Assert.assertTrue(compareResult > 0);
	}
	
	@Test
	public void shouldRankHigherPairWithHigherSingleCardAgainstOtherPair() {

		// Given
		PokerHand firstHand = PokerTestUtil.createPokerHand("HA","DA","SQ","HJ","H9");
		PokerHand secondHand = PokerTestUtil.createPokerHand("CA","SA","SJ","C9","C8");
		
		// When
		int compareResult = pokerHandComparator.compare(firstHand, secondHand);
		
		// Then
		Assert.assertTrue(compareResult > 0);
	}
	
	@Test
	public void shouldRankHigherPairAgainstHighCard() {

		// Given
		PokerHand firstHand = PokerTestUtil.createPokerHand("C6","S9","D10","C10","D8");
		PokerHand secondHand = PokerTestUtil.createPokerHand("HK","CQ","D7","H4","H5");
		
		// When
		int compareResult = pokerHandComparator.compare(firstHand, secondHand);
		
		// Then
		Assert.assertTrue(compareResult > 0);
	}
	
	@Test
	public void shouldRankHigherHandWithHigherCardAgainstOtherHand() {

		// Given
		PokerHand firstHand = PokerTestUtil.createPokerHand("C6","S9","DJ","C2","D8");
		PokerHand secondHand = PokerTestUtil.createPokerHand("H10","C3","D6","H4","H5");
		
		// When
		int compareResult = pokerHandComparator.compare(firstHand, secondHand);
		
		// Then
		Assert.assertTrue(compareResult > 0);
	}
	
	@Test
	public void shouldRankHigherHandWithSecondHigherCardAgainstOtherHand() {

		// Given
		PokerHand firstHand = PokerTestUtil.createPokerHand("C6","S9","DJ","C2","D8");
		PokerHand secondHand = PokerTestUtil.createPokerHand("HJ","C3","D6","H4","H8");
		
		// When
		int compareResult = pokerHandComparator.compare(firstHand, secondHand);
		
		// Then
		Assert.assertTrue(compareResult > 0);
	}
}
