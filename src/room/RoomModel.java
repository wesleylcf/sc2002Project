package room;

import java.util.Scanner;

import exception.InvalidInputException;
import room.RoomView;
import hrps.HRPS;
import hrps.InputValidator;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


public class RoomModel{
    // SHOULDNT NEED TO TOUCH Room AT ALL
    Scanner sc = HRPS.sc;
    private RoomData roomData;
    
    final Comparator<Room> comparator = new Comparator<Room>() {
	    @Override
	    public int compare(Room left, Room right) {
	        return Integer.parseInt(left.getRoomNumber()) - Integer.parseInt(right.getRoomNumber());
	    }
	};

    public RoomModel(RoomData roomData){
        this.roomData = roomData;
    	
    }

    public void createRoom() throws InvalidInputException{
        // Calls createRoom in RoomData (Maybe can consider naming both methods differently)
        String roomNumber;
        String roomType;
        int wifiEnabled;
        int smoking;
        RoomStatus status;
        int selection;

        // Take in room Number
        int floor, unit;
        System.out.println("Enter floor number from 2 to 7: ");
        floor = InputValidator.validateIntRange(2, 7, "Floor number", sc.nextInt());
        sc.nextLine();
        System.out.println("Enter unit number from 0 to 99: ");
        unit = InputValidator.validateIntRange(0, 99, "Unit number", sc.nextInt());
        sc.nextLine();

        if(unit<10)
            roomNumber = "0" + String.valueOf(floor) + "0" + String.valueOf(unit);
        else
            roomNumber = "0" + String.valueOf(floor)  + String.valueOf(unit);

        // Make sure room number not using
        if (roomData.exist(roomNumber)){
            System.out.println("This room number is already in use!");
            return;
        }

        // Take in Room type
        RoomView.printRoomTypeMenu();
        selection = InputValidator.validateIntRange(1,5,"room type option",sc.nextInt());
        sc.nextLine();
        switch(selection){
            case 1:{
                roomType = "SINGLE";
                break;
            }
            case 2:{
                roomType = "DOUBLE";
                break;
            }
            case 3:{
                roomType = "DELUXE";
                break;
            }
            case 4:{
                roomType = "JUNIOR_SUITE";
                break;
            }
            case 5:{
                roomType = "VIP_SUITE";
                break;
            }
            default:
                roomType = "SINGLE";
        }
        
        // Take in wifiEnabled
        RoomView.printWifiMenu();
        wifiEnabled = InputValidator.validateIntRange(0,1,"wifi enabled option",sc.nextInt());
        sc.nextLine();

        // Take in smoking
        RoomView.printSmokingMenu();
        smoking = InputValidator.validateIntRange(0,1,"smoking allowed option",sc.nextInt());
        sc.nextLine();

        // Select status
        // Shd I allow then to set all possible status at this point?
        RoomView.printRoomStatusMenu();
        selection = InputValidator.validateIntRange(1, 4, "Room status option", sc.nextInt());
        sc.nextLine();
        switch(selection){
            case 1:{
                status = RoomStatus.VACANT;
                break;
            }
            case 2:{
                status = RoomStatus.OCCUPIED;
                break;
            }
            case 3:{
                status = RoomStatus.RESERVED;
                break;
            }
            case 4:{
                status = RoomStatus.MAINTENANCE;
                break;
            }
            default:
                status = RoomStatus.VACANT;
        }
        int er = roomData.createRoom(roomNumber, roomType, wifiEnabled, smoking, status);
        if(er==1)
            System.out.println("Room " + roomNumber + " successfully created");
        else
            System.out.println("ERROR: Room not created");
    }

    public void updateDetails() throws InvalidInputException  {
        int selection;
        // Get room number we're updating
//        sc.nextLine();
        System.out.println("Enter room number you're updating");
        String room = InputValidator.validateRoomNumber(sc.nextLine());

        // Exit if room number does not exist
        if(!roomData.exist(room)){
            // Can we not use roomData.exist since its same package
            System.out.println("Room number " + (room) + " does not exist!");
            return;
        } 

        // Get detail to update & update it
        RoomView.printUpdateRoomMenu();
        selection = InputValidator.validateIntRange(1, 3, "update menu option", sc.nextInt());
        sc.nextLine();
        switch(selection){
            // (1):Status
            case 1:{
                RoomView.printRoomStatusMenu();
                updateStatus(room);
                break;
            }
            // (2):wifi
            case 2:{
                RoomView.printWifiMenu();
                selection = InputValidator.validateIntRange(0, 1, "wifi option", sc.nextInt());
                sc.nextLine();
                roomData.updatewifi(room, -(selection-1));
                break;
            }
            // (3):smoking
            case 3:{
                RoomView.printSmokingMenu();
                selection = InputValidator.validateIntRange(0, 1, "smoking option", sc.nextInt());
                sc.nextLine();
                roomData.updatewifi(room, -(selection-1));
                break;
            }
        }
            
    }
    
    private void updateStatus(String room) throws InvalidInputException {
    	int selection = InputValidator.validateIntRange(1, 4, "update status option", sc.nextInt());
    	sc.nextLine();
    	switch(selection){
        case 1:{
            roomData.updateStatus(room, RoomStatus.VACANT);
            System.out.println("Room number " + room + " updated to vacant!");
            break;
        }
        case 2:{
            roomData.updateStatus(room, RoomStatus.OCCUPIED);
            System.out.println("Room number " + room + " updated to occupied!");
            break;
        }
        case 3:{
            roomData.updateStatus(room, RoomStatus.RESERVED);
            System.out.println("Room number " + room + " updated to reserved!");
            break;
        }
        case 4:{
            roomData.updateStatus(room, RoomStatus.MAINTENANCE);
            System.out.println("Room number " + room + " updated to maintainence!");
            break;
        }
        default:{
            System.out.println("Invalid choice, status NOT updated!!");
        }
    }
   }

    public void checkAvailability() throws InvalidInputException{

        // For convenience sake
        System.out.println("Enter room number to check avaiability: ");
        String roomNumber = InputValidator.validateRoomNumber(sc.nextLine());
        if (!roomData.exist(roomNumber)){
            System.out.println("This room number does not exist");
            return;
        }
        System.out.println("Status: " + roomData.getStatus(roomNumber));
    }

    public void checkDetails() throws InvalidInputException{
        // Check details 
        // STILL FIGURING OUT IF THIS SHD PRINT OR RETURN THO
        System.out.println("Enter room number to check: ");
        String roomNumber = InputValidator.validateRoomNumber(sc.nextLine());

        // Ceck if room number EXIST
        if (!roomData.exist(roomNumber)){
            System.out.println("This room number does not exist!");
            return;
        }
        System.out.println("=====Room Numer "+ roomNumber + "=====");
        // Status
        System.out.println("Status: " + roomData.getStatus(roomNumber));
        // Room Type
        System.out.println("Room Type: " + roomData.getRoomType(roomNumber));
        // Bed
        System.out.println("Bed Type: " + roomData.getBed(roomNumber));
        // Wifi
        if(roomData.getWifi(roomNumber) == 1)
            System.out.println("Wifi: YES");
        else
            System.out.println("Wifi: NO");
        // Smoking
        if(roomData.getSmoking(roomNumber) == 1)
            System.out.println("Smoking: YES");
        else
            System.out.println("Smoking: NO");



    }
    
    public void roomStatusReport(){

        HashMap<String, HashMap<String, Room>> storage = roomData.roomStatusReport();
        
        System.out.println("====ROOM STATUS REPORT====");
        //System.out.println(storage.keySet()); //RETURNS A SET OF ALL THE KEYS
        //System.out.println(storage);
        int max_len = "Junior_Suite".length();
        for(String i : storage.keySet()){
           System.out.printf(i + " ".repeat(max_len - i.length())+ ":" + " Number: " + roomData.getNumberAvailable(i) + " out of " + storage.get(i).size());
           System.out.println(); 
           System.out.printf(" ".repeat(max_len + 2)+ "Rooms: ");
           
           List<? extends Room> rooms = new ArrayList<Room>(storage.get(i).values());
           Collections.sort(rooms, comparator);
           for(Room room : rooms){
               if(room.getStatus() == RoomStatus.VACANT){
                    System.out.printf(room.getRoomNumber() + ", ");
               }
           }
           System.out.println();
           System.out.println();
        }
    }

    public void roomTypeRSR(){
        HashMap<String, HashMap<String, Room>> storage = roomData.roomTypeRSR();
        System.out.println("====ROOM STATUS REPORT====");
        int max_len = "Maintainence".length();
        //System.out.println("MAXIMUM LENGTH IS: "+ storage.keySet());
        for(String i : storage.keySet()){
            System.out.println(i+ " ".repeat(max_len - i.length())+":");
            System.out.printf(" ".repeat(max_len) + "Rooms: ");
            List<? extends Room> rooms = new ArrayList<Room>(storage.get(i).values());
            Collections.sort(rooms, comparator);
            for(Room j : rooms){
                System.out.printf(j.getRoomNumber() + ", ");
            }
            System.out.println();
        }
    }

    public ArrayList<Room> occupied(){
        //** FOR ALEX */
        return(roomData.getAllOccupied());
    }
    
    public String getVacantRoom(String roomType) {
    	Room room = roomData.getAvailable(roomType);
    	if(room == null) return null;
    	return room.getRoomNumber();
    }
    
    public ArrayList<Room> available(){
        //** FOR WESLEY */
        return(roomData.getAllAvailable());
    }

    public void printAvailable(){
        //** FOR WESLEY */
        ArrayList<Room> avail = roomData.getAllAvailable();
        Collections.sort(avail,comparator);
        for(Room i : avail){
            System.out.printf(i.getRoomNumber() + " ");
        }
        System.out.println();
    }

    public Room getRoom(String id){
        // For Wesley, returns the room
        return(roomData.getRoom(id));
    }

    public void changeRoomStatus() throws InvalidInputException{
            // Get room number we're updating
            System.out.println("Enter room number you're updating");
            String room = InputValidator.validateRoomNumber(sc.nextLine());
    
            // Exit if room number does not exist
            if(!roomData.exist(room)){
                System.out.println("Room number " + (room) + " does not exist!");
                return;
            } 

            // Else proceed with changing status
            RoomView.printRoomStatusMenu();
            int selection = InputValidator.validateIntRange(1, 4, "room status option", sc.nextInt());
            sc.nextLine();
            switch(selection){
                    case 1:{
                        roomData.updateStatus(room, RoomStatus.VACANT);
                        System.out.println("Room number " + room + " updated to vacant!");
                        break;
                    }
                    case 2:{
                        roomData.updateStatus(room, RoomStatus.OCCUPIED);
                        System.out.println("Room number " + room + " updated to occupied!");
                        break;
                    }
                    case 3:{
                        roomData.updateStatus(room, RoomStatus.RESERVED);
                        System.out.println("Room number " + room + " updated to reserved!");
                        break;
                    }
                    case 4:{
                        roomData.updateStatus(room, RoomStatus.MAINTENANCE);
                        System.out.println("Room number " + room + " updated to maintainence!");
                        break;
                    }
                    default:{
                        System.out.println("Invalid choice, status NOT updated!!");
                        return;
                    }
                }
    }

}
