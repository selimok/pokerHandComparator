package software.sundc.flaregames.poker.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import software.sundc.flaregames.poker.model.PokerHand;
import software.sundc.flaregames.poker.model.RankResult;
import software.sundc.flaregames.poker.model.exception.InvalidCardSetException;

public interface PokerDealer {

	/**
	 * Ranks given poker hands according to poker rules.
	 * 
	 * @param pokerHands Poker hands to compare
	 * @return A list of rank results ( rank result for each poker hand)
	 * @throws {@link InvalidCardSetException} if the uniqueness rule of the
	 *       cards is broken or the number of total cards exceeds 52.
	 */
	public List<RankResult> rankPokerHands(Set<PokerHand> pokerHands) throws InvalidCardSetException;

	/**
	 * Randomly deal out cards according to given number of players.
	 * 
	 * @param numberOfPlayers
	 * 
	 * @return A set of poker hands (with 5 cards) for each player.
	 */
	public Set<PokerHand> dealOutCardsRandomly(Integer numberOfPlayer);


}
