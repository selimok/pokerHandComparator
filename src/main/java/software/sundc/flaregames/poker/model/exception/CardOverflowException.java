package software.sundc.flaregames.poker.model.exception;

/**
 * A {@link CardOverflowException} is thrown if a poker hand filled with more
 * than 5 poker cards.
 * 
 * @author selim
 * 
 */
public class CardOverflowException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Create a basic {@link CardOverflowException} instance.
	 * 
	 */
	public CardOverflowException() {
		super();
	}

	/**
	 * Create a {@link CardOverflowException} instance with a detail message.
	 * 
	 * @param message
	 */
	public CardOverflowException(String message) {
		super(message);
	}

}
