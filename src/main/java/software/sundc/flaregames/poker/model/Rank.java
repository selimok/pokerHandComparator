package software.sundc.flaregames.poker.model;

import software.sundc.flaregames.poker.model.exception.UnknownValueException;

public enum Rank {
	STRAIGHT_FLUSH(9),
	FOUR_OF_A_KIND(8),
	FULL_HOUSE(7),
	FLUSH(6),
	STRAIGHT(5),
	THREE_OF_A_KIND(4),
	TWO_PAIRS(3),
	PAIR(2),
	HIGH_CARD(1);
	
	private Integer numericValue;
	
	private Rank(Integer numericValue){
		this.numericValue = numericValue;
	}
	
	public Integer toNumericValue(){
		return numericValue;
	}
	
	
	/**
	 * Get enumeration value of given numeric score.  
	 * 
	 * @param numericValue Numeric score. 
	 * 
	 * @return Score value as enumeration.
	 * 
	 * @throws UnknownValueException when the given numeric score doesn't corresponds with a real score value. 
	 */
	public static Rank getValueOf(Integer numericValue) throws UnknownValueException{
		for (Rank rank : Rank.values()){
			if(rank.toNumericValue().equals(numericValue)){
				return rank;
			}
		}
		throw new UnknownValueException("There is no rank for given numeric rank value");
	}
	
}
