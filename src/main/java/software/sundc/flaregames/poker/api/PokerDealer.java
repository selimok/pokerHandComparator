package software.sundc.flaregames.poker.api;

import java.util.Map;
import java.util.Set;

import software.sundc.flaregames.poker.model.PokerHand;
import software.sundc.flaregames.poker.model.Card;
import software.sundc.flaregames.poker.model.exception.InvalidCardSetException;

public interface PokerDealer {

	/**
	 * Ranks given poker hands according to poker rules.
	 * 
	 * @param pokerHands
	 * @return
	 */
	public Map<PokerHand, Integer> rankPokerHands(Set<PokerHand> pokerHands);

	/**
	 * Randomly deal out cards according to given number of players.
	 * 
	 * @param numberOfPlayers
	 * 
	 * @return A set of poker hands (with 5 cards) for each player.
	 */
	public Set<PokerHand> dealOutCardsRandomly(Integer numberOfPlayer);

	/**
	 * Deal out cards according to given manually defined pokerHands.
	 * 
	 * @param pokerHands
	 *            Cheated pokerHands
	 * @return A set of comparable poker hands.
	 * @throws {@link InvalidCardSetException} if the uniqueness rule of the
	 *         cards is broken or the number of total cards exceeds 52.
	 */
	public Set<PokerHand> dealOutCardsManually(Map<String, Set<Card>> pokerHands)
			throws InvalidCardSetException;

}
