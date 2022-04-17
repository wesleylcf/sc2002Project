package exception;

public class NoGuestFoundException extends Exception{
	public NoGuestFoundException() {
		super("No guest found!");
	}
}
