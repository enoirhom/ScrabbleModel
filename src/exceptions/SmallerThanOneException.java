package exceptions;

public class SmallerThanOneException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SmallerThanOneException() {
		super();
	}

	public SmallerThanOneException(String message) {
		super(message);
	}

}
