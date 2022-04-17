package roomsvc;

public class RoomView {
    public static void printRoomMenu() {
        System.out.println("==HRPS Room Main Menu==");
        System.out.println("(1) Create room");
        System.out.println("(2) Update room details ");
        System.out.println("(3) Check room availability");
        System.out.println("(4) Check room details");
        System.out.println("(5) Room statistic report by type");
        System.out.println("(6) Room statistic report by status");
        System.out.println("(7) Get all available room");
        System.out.println("(8) QUIT");
	}


    public static void printRoomTypeMenu(){
        System.out.println("Select Room Type: (1):Single (2):Double (3):Deluxe (4): Junior Suite (5): VIP Suite");
    }


    public static void printSmokingMenu(){
        System.out.println("Smoking allowed?: (1):Yes (0):No");
    }

    public static void printWifiMenu(){
        System.out.println("Enable wifi?: (1):Yes (0):No");    
    }

    public static void printRoomStatusMenu(){
        System.out.println("Select Room status: (1):Vacant (2):Occupied (3):Reserved (4):Maintainence");
    }

    public static void printUpdateRoomMenu(){
        System.out.println("Select detail to update: (1):Status (2):wifi (3):smoking");    
    }


}
