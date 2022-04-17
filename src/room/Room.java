package room;

import java.util.Comparator;
import java.util.Date;

import reservation.Booking;


/**
* Represents a room inside a hotel room
* An abstract class, will be extended by specific room types to implement additional features
* @author Lee Bo Hua
* @version 1.0
* @since 2022-04-17
*/
public abstract class Room{
	/**
	 * The room's room number
	 */
	protected String roomNumber;

	/**
	 * The Wifi setting of the room on/off (1/0)
	 */
	protected int wifiEnabled; 

	/**
	 * The room's smoking Rule (1/0)
	 */
    protected int smoking;
    
	/**
	 * The Status of the room
	 */
    protected RoomStatus status;
	
	/**
	 * The bed type in this room
	 */
    protected BedType bedType;


    /** 
	 * Creates a new Room with the given room number, wifi setting, smoking rule and room status
	 * The constructor method
	 * @param roomNumber  The room's room number
	 * @param wifiEnabled The Wifi setting of the room on/off (1/0)
	 * @param smoking The room's smoking Rule
	 * @param status The Status of the room 
	 */
	public Room(String roomNumber, int wifiEnabled, int smoking, RoomStatus status){
		this.roomNumber = roomNumber;
		this.wifiEnabled = wifiEnabled; 
		this.smoking = smoking; 
		this.status = status;
	}

	//**UPDATE Operations**// 
	
	/** 
	 * Set method to update the room's wifi setting
	 * @param newWifiSetting New Wifi setting of the room on/off (1/0)
	 */
	public void setWifi(int newWifiSetting) {
		this.wifiEnabled = newWifiSetting;
	}

	
	/** 
	 * Set method to update the room's smoking rule
	 * @param newSmokingSetting New Smoking rule(1/0) for the room
	 */
	public void setSmoking(int newSmokingSetting) {
		this.smoking = newSmokingSetting;
	}

	
	/** 
	 * Set method to change the status of the room
	 * @param status Status of the room
	 */
	public void setStatus(RoomStatus status) {
		this.status = status;
	}

	//**READ Operations**// 

	/** 
	 * Get method to retrieve the room's room number 
	 * @return int The room's room number
	 */
	public String getRoomNumber(){
		return roomNumber;
	}
	

	
	/** 
	 * Get method to retrieve the room's wifi status (1/0)
	 * @return int This room's wifi status
	 */
	public int getWifi(){
		return wifiEnabled;
	}

	
	/** 
	 * Get method to retrieve the room's smoking rule (1/0)
	 * @return int This room's smoking status
	 */
	public int getSmoking(){
		return smoking;
	}

	/** 
	 * Get method to retrieve the room current status 
	 * @return RoomStatus This room's status
	 */
	public RoomStatus getStatus() { 
		return this.status;
	}

	
	/** 
	 * Converts the status of the room from Enum RoomStatus to it's equivalent in String
	 * @param status This Room's status in Enum RoomStatus
	 * @return String This Room's status in String type
	 */
	public static String parseStatus(RoomStatus status) {
		if(status == RoomStatus.VACANT) return("VACANT");
		else if(status == RoomStatus.OCCUPIED) return("OCCUPIED");
		else if(status == RoomStatus.RESERVED) return("RESERVED");
		else if(status == RoomStatus.MAINTENANCE) return("MAINTENANCE");
		else return("ERROR");
	}

	
	/** 
	 * Converts the status of the room from String to it's equivalent in Enum RoomStatus
	 * @param statusString  This Room's status in String type
	 * @return RoomStatus This Room's status in enum RoomStatus
	 */
	public static RoomStatus parseStatus(String statusString) {
		if(statusString.equals("VACANT")) return RoomStatus.VACANT;
		if(statusString.equals("OCCUPIED")) return RoomStatus.OCCUPIED;
		if(statusString.equals("RESERVED")) return RoomStatus.RESERVED;
		if(statusString.equals("MAINTENANCE")) return RoomStatus.MAINTENANCE;
		return null;
	}
	
	
	public abstract String getRoomType();
	public abstract double getRoomPrice();
	public abstract String getBedType();

}