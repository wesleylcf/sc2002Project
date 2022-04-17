package exception;

public class InvalidStatusInteger extends Exception {
    public InvalidStatusInteger() {
        super("Reservation status choice should be >= 1 and <= 4");
    }
}
