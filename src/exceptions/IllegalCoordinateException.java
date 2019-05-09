package exceptions;

public class IllegalCoordinateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalCoordinateException() {
		super();
	}

	public IllegalCoordinateException(String message) {
		super(message);
	}

}
