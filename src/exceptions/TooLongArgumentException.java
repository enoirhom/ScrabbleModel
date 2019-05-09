package exceptions;

public class TooLongArgumentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TooLongArgumentException() {
		super();
	}

	public TooLongArgumentException(String message) {
		super(message);
	}

}
