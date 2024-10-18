package Exceptions;

public class BookingAlreadyExistException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Default constructor
    public BookingAlreadyExistException() {
        super("Booking with the same number already exists!");
    }

    // Constructor with a custom error message
    public BookingAlreadyExistException(String message) {
        super(message);
    }
}
