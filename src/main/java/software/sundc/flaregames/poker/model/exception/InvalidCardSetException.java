package software.sundc.flaregames.poker.model.exception;

public class InvalidCardSetException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Create a basic {@link InvalidCardSetException} instance.
	 * 
	 */
	public InvalidCardSetException() {
		super();
	}

	/**
	 * Create a {@link InvalidCardSetException} instance with a detail message.
	 * 
	 * @param message
	 */
	public InvalidCardSetException(String message) {
		super(message);
	}
}
