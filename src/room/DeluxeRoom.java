package room;

/**
* Represents a Deluxe Room inside a hotel 
* Inherits directly from Room
* @author Lee Bo Hua
* @version 1.0
* @since 2022-04-17
*/
public class DeluxeRoom extends Room{

    /**
	 * The price of staying in this room
	 */
    private double price;

    /** 
	 * Creates a new Deluxe Room with the given room number, wifi setting, smoking rule and room status
	 * The constructor method
	 * @param roomNumber  The room's room number
	 * @param wifiEnabled The Wifi setting of the room on/off (1/0)
	 * @param smoking The room's smoking Rule
	 * @param status The Status of the room 
	 */
    public DeluxeRoom(String roomNumber, int wifiEnabled, int smoking, RoomStatus status){
        super(roomNumber, wifiEnabled, smoking, status);
        this.price = 300;
        this.bedType = BedType.MASTER;
    }

    
    /** 
     * Get method to retrieve the room's price
     * @return double The price of staying in this room
     */
    public double getRoomPrice(){
        return this.price;
    }

    
    /** 
     * Get method to retrieve the room's bed type
     * @return String The bed type in this room
     */
    @Override
    public String getBedType(){
        if(bedType == BedType.SINGLE) return "SINGLE";
		if(bedType == BedType.DOUBLE) return "DOUBLE";
		if(bedType == BedType.MASTER) return "MASTER";
        return("Invalid");
    }
    
    
    /** 
     * Get method to retrieve the room's Room type
     * @return String The room type of the room
     */
    @Override
    public String getRoomType(){
        return("DELUXE");
    }
    
}
