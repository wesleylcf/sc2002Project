package exception;

public class NoVacantRoomException extends Exception{
	public NoVacantRoomException () {
		super("No vacant room for the given type");
	}
	
}
