package exception;

public class NoReservationFoundException extends Exception {
    public NoReservationFoundException(){
        super("No Reservation found for given id");
    }
}
