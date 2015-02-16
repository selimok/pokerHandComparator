package software.sundc.games.poker.model;

import org.junit.Assert;
import org.junit.Test;

import software.sundc.games.poker.model.Card;
import software.sundc.games.poker.model.Suit;
import software.sundc.games.poker.model.Value;
import software.sundc.games.poker.model.exception.UnknownValueException;

public class CardTest {

	@Test
	public void shouldCreateCardBySuitSignAndValueDenotion() {
		// Given
		String valueDenotion = "J";
		String suitSign = "C";
		
		Card expectedCard = new Card(Suit.CLUBS, Value.JACK);

		// When
		Card newCard = new Card(suitSign, valueDenotion);

		// Then
		Assert.assertEquals(expectedCard, newCard);
	}

	@Test
	public void shouldD2LessThanD3() {

		// Given
		Card d2 = new Card("D", "2");
		Card d3 = new Card("D", "3");

		// When
		int compareResult = d2.compareTo(d3);

		// Then
		Assert.assertTrue(compareResult < 0);
	}

	@Test
	public void shouldD2EqualToH2() {

		// Given
		Card d2 = new Card("D", "2");
		Card h2 = new Card("H", "2");

		// When
		int compareResult = d2.compareTo(h2);

		// Then
		Assert.assertTrue(compareResult == 0);
	}

	@Test
	public void shouldDAGreaterThanD2() {

		// Given
		Card d2 = new Card("D", "2");
		Card dA = new Card("D", "A");

		// When
		int compareResult = dA.compareTo(d2);

		// Then
		Assert.assertTrue(compareResult > 0);
	}

	@Test(expected = UnknownValueException.class)
	public void shouldCardInstantiationWithNullValueThrowException() {

		// Given
		Value value = null;
		Suit suit = Suit.DIAMONDS;

		// When
		new Card(suit, value);

		// Then
		// Expect exception
	}

	@Test(expected = UnknownValueException.class)
	public void shouldCardInstantiationWithNullSuitThrowException() {

		// Given
		Value value = Value.ACE;
		Suit suit = null;

		// When
		new Card(suit, value);

		// Then
		// Expect exception
	}

	@Test(expected = UnknownValueException.class)
	public void shouldCardInstantiationWithUnknownValueDenotionThrowException() {

		// Given
		String valueDenotion = "X";
		String suitSign = "D";

		// When
		new Card(suitSign, valueDenotion);

		// Then
		// Expect exception
	}

	@Test(expected = UnknownValueException.class)
	public void shouldCardInstantiationWithUnknownSuitSignThrowException() {

		// Given
		String valueDenotion = "K";
		String suitSign = "X";

		// When
		new Card(suitSign, valueDenotion);

		// Then
		// Expect exception
	}

}
