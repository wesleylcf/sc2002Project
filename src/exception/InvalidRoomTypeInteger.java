package exception;

public class InvalidRoomTypeInteger extends Exception {
    public InvalidRoomTypeInteger() {
        super("RoomType choice should be >=1 and <=4");
    }
}