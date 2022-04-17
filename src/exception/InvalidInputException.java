package exception;

public class InvalidInputException extends Exception {
	/**
	 *  @param context context of which error occurs, e.g. Credit card
	 *  @param errorMessage error message to append to context
	*/
    public InvalidInputException(String context,String errorMessage){
        super("Invalid input for "+context+": "+errorMessage);
    }
}
