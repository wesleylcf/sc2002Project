package exception;

public class InvalidDateException extends Exception {
    public InvalidDateException(){
        super("Check-out date was earlier than Check-in date");
    }
}

