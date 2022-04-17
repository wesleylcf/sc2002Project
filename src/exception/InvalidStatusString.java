package exception;

public class InvalidStatusString extends Exception {
    public InvalidStatusString() {
        super("Reservation String status invalid");
    }
}