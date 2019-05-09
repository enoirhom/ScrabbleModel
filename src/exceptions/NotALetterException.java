package exceptions;

public class NotALetterException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotALetterException() {
		super();
	}

	public NotALetterException(String message) {
		super(message);
	}
	
}
