package software.sundc.flaregames.poker.impl;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Sets;

import software.sundc.flaregames.poker.api.PokerDealer;
import software.sundc.flaregames.poker.model.Card;
import software.sundc.flaregames.poker.model.PokerHand;
import software.sundc.flaregames.poker.model.Suit;
import software.sundc.flaregames.poker.model.Value;
import software.sundc.flaregames.poker.model.exception.InvalidCardSetException;

@Controller
@RequestMapping("/poker")
public class PokerController implements PokerDealer {

	@Autowired
	@Qualifier("pokerDealer")
	private PokerDealer pokerDealer;
	
	@Override
	@RequestMapping(value = "/rankPokerHands")
	public @ResponseBody Map<PokerHand, Integer> rankPokerHands(@RequestBody Set<PokerHand> pokerHands) {
		return pokerDealer.rankPokerHands(pokerHands);
	}

	@Override
	@RequestMapping("/dealOutCardsRandomly")
	public @ResponseBody Set<PokerHand> dealOutCardsRandomly(@RequestBody Integer numberOfPlayer) {
		return pokerDealer.dealOutCardsRandomly(numberOfPlayer);
	}

	@Override
	@RequestMapping("/dealOutCardsManually")
	public Set<PokerHand> dealOutCardsManually(@RequestBody Map<String, Set<Card>> pokerHands) throws InvalidCardSetException {
		return pokerDealer.dealOutCardsManually(pokerHands);
	}

}
