package software.sundc.games.poker.model.exception;

/**
 * A {@link InvalidNumberOfCardsException} is thrown if a poker hand filled with more
 * than 5 poker cards.
 * 
 * @author selim
 * 
 */
public class InvalidNumberOfCardsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Create a basic {@link InvalidNumberOfCardsException} instance.
	 * 
	 */
	public InvalidNumberOfCardsException() {
		super();
	}

	/**
	 * Create a {@link InvalidNumberOfCardsException} instance with a detail message.
	 * 
	 * @param message
	 */
	public InvalidNumberOfCardsException(String message) {
		super(message);
	}

}
