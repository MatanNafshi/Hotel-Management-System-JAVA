package Exceptions;

public class RoomAlreadyExistException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoomAlreadyExistException() {
        super("Room with the same number already exists!");
    }

    public RoomAlreadyExistException(String message) {
        super(message);
    }
}
