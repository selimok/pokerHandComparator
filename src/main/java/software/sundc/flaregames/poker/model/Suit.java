package software.sundc.flaregames.poker.model;

import java.security.InvalidParameterException;

public enum Suit {
	CLUBS("C"),
	DIAMONDS("D"),
	HEARTS("H"),
	SPADES("S");

	
	private String sign;
	
	private Suit(String sign){
		this.sign = sign;
	}
	
	public String toSign(){
		return sign;
	}
	
	public Suit valueOfSign(String sign){
		for (Suit suit : Suit.values()){
			if(suit.toSign().equals(sign)){
				return suit;
			}
		}
		throw new InvalidParameterException("There is no suit for the given sign.");
	}
}
