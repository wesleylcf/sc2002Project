package room;

import hrps.*;
import java.util.Scanner;
import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;


/**
* RoomData class which holds a HashMap Data Structure to support creating, updating and removing Room objects.
* @author Lee Bo Hua
* @version 1.0
* @since 2022-04-16
*/
public class RoomData {
    static public HashMap<String, Room> rooms = new HashMap<>();
    Scanner sc = HRPS.sc;

    
    /** 
     * @param roomNumber room number that we want to check if exist
     * @return boolean whether room number exist
     */
    public boolean exist(String roomNumber){
        return(rooms.containsKey(roomNumber));
    }

    public RoomData(){
        try{
            rooms = RoomDB.getInstance().populate();
        } catch(IOException e) {
            System.out.printf("Error reading file");
        }
       
    }


    
    /** 
     * @param roomNo
     * @param roomType
     * @param wifiEnabled
     * @param smoking
     * @param status
     * @return int 1 is returned if createRoom is successful. Else, 0
     */
    public int createRoom(String roomNo, String roomType, int wifiEnabled, int smoking, RoomStatus status){
        if (exist(roomNo)){
            // If that roomNumber alr exist, we cannot use. Creation failed
            return 0;
        }

        // TRY FIRST NOW I PUT ONLY SINGLEROOM
        if(roomType == "SINGLE") rooms.put(roomNo, new SingleRoom(roomNo, wifiEnabled, smoking, status));
        else if(roomType == "DOUBLE") rooms.put(roomNo, new DoubleRoom(roomNo, wifiEnabled, smoking, status));
        else if(roomType == "DELUXE") rooms.put(roomNo, new DeluxeRoom(roomNo, wifiEnabled, smoking, status));
        else if(roomType == "JUNIOR_SUITE") rooms.put(roomNo, new JuniorSuiteRoom(roomNo, wifiEnabled, smoking, status));
        else if(roomType == "VIP_SUITE") rooms.put(roomNo, new VipSuiteRoom(roomNo, wifiEnabled, smoking, status));
        return 1;
    }


    
    /** 
     * @param roomNo
     * @param roomType
     * @param wifiEnabled
     * @param smoking
     * @param status
     * @return the entire room object created
     */
    public static Room MakeRoom(String roomNo, String roomType, Integer wifiEnabled, Integer smoking, RoomStatus status){
        //System.out.println("Calling MakeRoom for room type" + roomType);
        if("SINGLE".equals(roomType)) return(new SingleRoom(roomNo, wifiEnabled, smoking, status));
        if("DOUBLE".equals(roomType)) return(new DoubleRoom(roomNo, wifiEnabled, smoking, status));
        if("DELUXE".equals(roomType)) return(new DeluxeRoom(roomNo, wifiEnabled, smoking, status));
        if("JUNIOR_SUITE".equals(roomType)) return(new JuniorSuiteRoom(roomNo, wifiEnabled, smoking, status));
        if("VIP_SUITE".equals(roomType)) return(new VipSuiteRoom(roomNo, wifiEnabled, smoking, status));
        System.out.println("ERROR: SKIPPED ALL IFS");
        return(new SingleRoom(roomNo, wifiEnabled, smoking, status));
    }
    
    
    /** 
     * @param oldRoomNo room number of room we want to update the room number of
     * @param newRoomNo room number to change the above room to
     * @return int 1 is returned if updateRoomNumber is successful. Else, 0
     */
//    public int updateRoomNumber(String oldRoomNo, String newRoomNo){
//        if (!exist(oldRoomNo) || exist(newRoomNo)){
//            // If the oldRoomNo does not exist, exit
//            // If newRoomNo alr exist, exit
//            return 0;
//        }
//
//        rooms.get(oldRoomNo).setroomNumber(newRoomNo);
//        return 1;
//        // Also, create sth that can detect if it fails? Maybe do it at roomsys instead?
//    }

    
    /** 
     * @param roomNo room number of room we want to update the status of
     * @param status the new status we want to update to 
     * @return int 1 is returned if updateStatus is successful. Else, 0
     */
    public int updateStatus(String roomNo, RoomStatus status){
        if (!exist(roomNo)){
            return 0;
        }
        rooms.get(roomNo).setStatus(status);
        return 1;
        // Also, create sth that can detect if it fails? Maybe do it at roomsys instead?
    }

    
    /** 
     * @param roomNo room number of room we want to update the wifi of
     * @param newWifiSetting new wifi setting we want to update to 
     * @return int 1 is returned if updateStatus is successful. Else, 0
     */
    public int updatewifi(String roomNo, int newWifiSetting){
        if (!exist(roomNo)){
            return 0;
        }
        rooms.get(roomNo).setWifi(newWifiSetting);
        return 1;
    }
    


    /**
    * Returns a nested HashMap for the generation of the room status report
    * @return the nested HashMap
    */
    public HashMap roomStatusReport(){
        // returns the top layer hashmap?
        // GENERATE HASHMAP
        // Hashmap to store each type
        HashMap<String, HashMap<String, Room>> storage = new HashMap<String, HashMap<String, Room>>(); // Top layer hashmap <roomType, eachTypeHashMap>

        // Initialise hashmaps
        RoomType[] roomType = RoomType.values();
        for(RoomType j : roomType){
            //System.out.println(j.name());
            //System.out.println("Yes working");
            storage.put(j.name(), new HashMap<String, Room>()); // Put a layer 2 hashmap into the main hashmap storage

        }

        // Loop through the initial HashMap
        for (Room room : rooms.values()){
            for(RoomType j : roomType){
                if(j.name() == room.getRoomType())
                    storage.get(j.name()).put(room.getRoomNumber(), room);
            }
        }
        return storage;


    }

    
    /** 
     * @return HashMap
     */
    public HashMap roomTypeRSR(){
        // 2 layered hashmaps
        HashMap<String, HashMap<String, Room>> storage = new HashMap<String, HashMap<String, Room>>();

        // Initialise hashmaps
        RoomStatus[] status = RoomStatus.values();
        for(RoomStatus s : status){
            storage.put(s.name(), new HashMap<String, Room>());
        }

        // Loop through and populate the hashmap accordingly
        for (Room room : rooms.values()){
            for(RoomStatus s : status){
                if(s == room.getStatus()){
                    storage.get(s.name()).put(room.getRoomNumber(), room);
                }
            }
        }
        return storage;


    }

    
    /** 
     * @return ArrayList<Room>
     */
    public ArrayList<Room> getAllOccupied(){
        // Return a arrayList of all Occupied rooms
        ArrayList<Room> occupied = new ArrayList<Room>();
        for(Room i : rooms.values()){
            if(i.getStatus() == RoomStatus.OCCUPIED) {
                occupied.add(i);
            }
        }
        return(occupied);
    }

    
    /** 
     * @return ArrayList<Room>
     */
    public ArrayList<Room> getAllAvailable(){
        // Return a arrayList of all available rooms
        ArrayList<Room> available = new ArrayList<Room>();
        for(Room i : rooms.values()){
            if(i.getStatus() == RoomStatus.VACANT) {
                available.add(i);
            }
        }
        return(available);
    }
    
    
    /** 
     * @param roomType
     * @return Room
     */
    public Room getAvailable(String roomType) {

        for(Room i : rooms.values()){
            if(i.getStatus() == RoomStatus.VACANT && i.getRoomType().equals(roomType)) {
                return i;
            }
        }
        return null;
    }

    
    /** 
     * @param roomType
     * @return int
     */
    public int getNumberAvailable(String roomType){
        // Return the number of available room per roomtype
        int n = 0;
        for(Room i : rooms.values()){
            if(i.getStatus() == RoomStatus.VACANT && i.getRoomType().equals(roomType)){
                n++;
            }
          
        }
        return n;
    }

    
    /** 
     * @param roomNo
     * @return String
     */
    public String getRoomNumber(String roomNo){
		return rooms.get(roomNo).getRoomNumber();
	}

	
    /** 
     * @param roomNo
     * @return String
     */
    public String getRoomType(String roomNo) {
		return rooms.get(roomNo).getRoomType();
	}	

	
    /** 
     * @param roomNo
     * @return String
     */
    public String getBed(String roomNo) {
		return rooms.get(roomNo).getBedType();
	}

	
    /** 
     * @param roomNo
     * @return int
     */
    public int getWifi(String roomNo){
		return rooms.get(roomNo).getWifi();
	}

	
    /** 
     * @param roomNo
     * @return int
     */
    public int getSmoking(String roomNo){
		return rooms.get(roomNo).getSmoking();
	}

	
    /** 
     * @param roomNo
     * @return String
     */
    public String getStatus(String roomNo) {
		return Room.parseStatus(rooms.get(roomNo).getStatus());
//        System.out.println("Chibai");
//        System.out.println(rooms);
//        RoomStatus status = rooms.get(roomNo).getStatus();
//        if(status == RoomStatus.VACANT) return("VACANT");
//		else if(status == RoomStatus.OCCUPIED) return("OCCUPIED");
//		else if(status == RoomStatus.RESERVED) return("RESERVED");
//		else if(status == RoomStatus.MAINTENANCE) return("MAINTENANCE");
//		else return("ERROR");
	}

    
    /** 
     * @param id
     * @return Room
     */
    public Room getRoom(String id){
        return(rooms.get(id));
    }

    //** DELETE OPERATIONS */
    public void deleteRoom(String roomNo) {
        rooms.remove(roomNo);
	}

    //** FOR SAVING DATA */
    public HashMap<String, Room> getRooms() {
        return rooms;
    }


}
