package software.sundc.flaregames.poker.model;

import java.security.InvalidParameterException;

public enum Value {
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	JACK(10),
	QUEEN(11),
	KING(12),
	ACE(13);
	
	private Integer numericValue;
	
	private Value(Integer numericValue){
		this.numericValue = numericValue;
	}
	
	public Integer toNumericValue(){
		return numericValue;
	}
	
	public Value valueOf(Integer numericValue){
		for (Value value : Value.values()){
			if(value.toNumericValue().equals(numericValue)){
				return value;
			}
		}
		throw new InvalidParameterException("There is no card value for given numeric value");
	}
}
