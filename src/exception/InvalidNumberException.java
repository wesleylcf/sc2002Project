package exception;

public class InvalidNumberException extends Exception {
	public InvalidNumberException(String context) {
		super("Invalid number input for "+context);
	}
	
}
