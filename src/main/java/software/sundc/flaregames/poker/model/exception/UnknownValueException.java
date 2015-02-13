package software.sundc.flaregames.poker.model.exception;

public class UnknownValueException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Create a basic {@link UnknownValueException} instance.
	 * 
	 */
	public UnknownValueException() {
		super();
	}

	/**
	 * Create a {@link UnknownValueException} instance with a detail message.
	 * 
	 * @param message
	 */
	public UnknownValueException(String message) {
		super(message);
	}
}
