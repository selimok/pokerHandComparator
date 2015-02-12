package software.sundc.flaregames.poker.impl;

import java.util.Comparator;

import software.sundc.flaregames.poker.model.PokerHand;

public class PokerHandComparator implements Comparator<PokerHand> {

	/**
	 * 	
	 */

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
		// TODO Auto-generated method stub
		return 0;
	}

}
