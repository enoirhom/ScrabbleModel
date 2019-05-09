package exceptions;

public class FaillureException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FaillureException() {
		super();
	}
	
	public FaillureException(String message) {
		super(message);
	}
 	
}
