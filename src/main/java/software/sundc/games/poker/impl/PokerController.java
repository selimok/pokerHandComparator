package software.sundc.games.poker.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import software.sundc.games.poker.api.PokerDealer;
import software.sundc.games.poker.model.PokerHand;
import software.sundc.games.poker.model.RankResult;
import software.sundc.games.poker.model.exception.InvalidCardSetException;

@RestController
@RequestMapping("/poker")
public class PokerController implements PokerDealer {

	@Autowired
	@Qualifier("pokerDealer")
	private PokerDealer pokerDealer;
	
	@Override
	@RequestMapping(value = "/rankPokerHands")
	public @ResponseBody List<RankResult> rankPokerHands(@RequestBody Set<PokerHand> pokerHands) throws InvalidCardSetException {
		return pokerDealer.rankPokerHands(pokerHands);
	}

	@Override
	@RequestMapping("/dealOutCardsRandomly")
	public @ResponseBody Set<PokerHand> dealOutCardsRandomly(@RequestBody Integer numberOfPlayer) {
		return pokerDealer.dealOutCardsRandomly(numberOfPlayer);
	}
}
