package software.sundc.flaregames.poker.test.util;

import java.util.List;
import java.util.Set;

import software.sundc.flaregames.poker.model.Card;
import software.sundc.flaregames.poker.model.PokerHand;

import com.google.common.collect.Sets;

public class PokerTestUtil {

	public static Set<Card> createCardSet(Card one, Card two, Card three, Card four, Card five) {
		
		Set<Card> cards = Sets.newHashSet();
		cards.add(one);
		cards.add(two);
		cards.add(three);
		cards.add(four);
		cards.add(five);

		return cards;
	}

	public static PokerHand createPokerHand(String... cardList) {
		
		Set<Card> cards = Sets.newHashSet();
		
		for (String card : cardList){
			cards.add(new Card(card.substring(0, 1),card.substring(1)));
		}
		
		return new PokerHand(cards);
	}
}
