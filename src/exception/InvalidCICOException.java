package exception;

public class InvalidCICOException extends Exception {
    public InvalidCICOException (){
        super("Check-out date was earlier than Check-in date");
    }
}