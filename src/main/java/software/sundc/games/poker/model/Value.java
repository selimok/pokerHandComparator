package software.sundc.games.poker.model;

import software.sundc.games.poker.model.exception.UnknownValueException;

public enum Value {
	TWO(2,"2"),
	THREE(3,"3"),
	FOUR(4,"4"),
	FIVE(5,"5"),
	SIX(6,"6"),
	SEVEN(7,"7"),
	EIGHT(8,"8"),
	NINE(9,"9"),
	TEN(10,"10"),
	JACK(11,"J"),
	QUEEN(12,"Q"),
	KING(13,"K"),
	ACE(14,"A");
	
	private Integer numericValue;
	private String denotion;
	
	private Value(Integer numericValue, String denotion){
		this.numericValue = numericValue;
		this.denotion = denotion;
	}
	
	public Integer toNumericValue(){
		return numericValue;
	}
	
	public String toDenotion(){
		return denotion;
	}
	
	/**
	 * Get enumeration value of given card value.  
	 * 
	 * @param numericValue Numeric card value between 2 and 14 (both numbers included). 
	 * 
	 * @return Card value as enumeration.
	 * 
	 * @throws UnknownValueException when the given numeric value doesn't corresponds with real card value. 
	 */
	public static Value getValueOf(Integer numericValue) throws UnknownValueException{
		for (Value value : Value.values()){
			if(value.toNumericValue().equals(numericValue)){
				return value;
			}
		}
		throw new UnknownValueException("There is no card value for given numeric value");
	}
	
	/**
	 * Get enumeration value of given card denotion.
	 * 
	 * @param denotion
	 *            A card denotion is a one character String, represents Card
	 *            value.
	 * 
	 * @return Card value as enumeration.
	 * @throws UnknownValueException when the given denotion doesn't
	 *         corresponds with real card value.
	 */
	public static Value getValueOf(String denotion) throws UnknownValueException{
		for (Value value : Value.values()){
			if(value.toDenotion().equals(denotion)){
				return value;
			}
		}
		throw new UnknownValueException("There is no card value for given denotion");
	}
}
