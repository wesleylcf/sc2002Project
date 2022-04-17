package reservation;

import java.util.ArrayList;
import java.util.Date;
import java.lang.Comparable;

/**
Booking entity class  
@author Wesley Lim
@version 1.0
@since 2022-04-17
*/
public class Booking implements Comparable<Booking>{
    /**
    * id of a reservation = guestId + roomId. guestId used is the first guest's id.
    */
    protected String id;
    /**
    * Number of guests
    */
    public Integer numGuests;
    /**
    * Array of guestIDs
    */
    public ArrayList<String> guestIDs = new ArrayList<String>();
    /**
    * id of room associated with a reservation
    */
    public String roomId;
    /**
    * billInfo of a reservation, initial value is first Guest's billInfo
    */
    public String billInfo;
    /**
    * checkIn date <YYYY-MM-DD>
    */
    public Date checkIn;
    /**
    * checkOut date <YYYY-MM-DD>
    */
    public Date checkOut;
    
    /*
     * Constructor for a booking
     * @param id id for this booking
     * @param numGuests number of guests for this booking
     * @param guestIDs Array of guestIDS for this booking
     * @param roomId roomId for this booking
     * @param billInfo credit card details for this booking, defaults to first guest entered
     * @param checkIn check-in date for this reservation
     * @param checkOut check-out date for this reservation
     */
    public Booking(String id, int numGuests, ArrayList<String> guestIDs, String roomId, String billInfo, Date checkIn, Date checkOut) {
        this.id = id;
        this.numGuests = numGuests;
        this.guestIDs = guestIDs;
        this.roomId = roomId;
        this.billInfo = billInfo;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    /**
    * Gets the Reservation ID
    * @return this Reservation's ID
    */
    public String getId() {return this.id;}
    public void setId(String newId) { this.id = newId;}

    /**
     * Gets the Reservation's number of guests
     * @return this Reservation's number of guests
     */
    public Integer getNumGuests() { return this.numGuests;}
    /**
    * Sets the Number of guests of this reservation
    * @param newDate upated number of guests
    */
    public void setNumGuests(Integer newNumGuests) { this.numGuests = newNumGuests;}
     /**
    * Gets the Reservation's Guest IDs
    * @return The array of Guest IDs in this reservation
    */
    public ArrayList<String> getGuestIDs() {return this.guestIDs;}
    
    
    /**
    * Sets the guestIDs of this reservation
    * @param newGuestIDs Array of new GuestIDs
    */
    public void setGuestIDs(ArrayList<String> newGuestIDs) { 
        this.guestIDs = newGuestIDs;
        this.numGuests = newGuestIDs.size();
    }

    /**
    * Gets the room ID of this reservation
    * @return this Reservation's room ID
    */
    public String getRoomId() {return this.roomId;}
    /**
    * Sets the roomId of this reservation
    * @param newId the updated roomId
    */
    public void setRoomId(String newId) {this.roomId = newId;}

    /**
    * Gets the billInfo of this reservation
    * @return this Reservation's billInfo
    */
    public String getBillInfo() {return this.billInfo;}
    /**
    * Sets the billInfo of this reservation
    * @param newInfo the updated BillInfo
    */
    public void setBillInfo(String newInfo) { this.billInfo = newInfo;}

    /**
    * Gets this Reservation's CheckIn Date
    * @return this Reservation's CheckIn Date
    */
    public Date getCheckIn() { return this.checkIn; }
    /**
    * Sets the CheckIn Date of this reservation
    * @param newDate updated CheckIn Date
    */
    public void setCheckIn(Date newDate) { this.checkIn = newDate; }

    /**
    * Gets the Reservation's CheckOut Date
    * @return this Reservation's CheckOut Date
    */
    public Date getCheckOut() { return this.checkOut; }
    /**
    * Sets the CheckOut Date of this reservation
    * @param newDate updated CheckOut Date
    */
    public void setCheckOut(Date newDate) { this.checkOut = newDate; }
    
    /*
     * @param o Booking to compare to
     * @returns 1 | 0 | -1 depending on this.checkOut > | = | < o.checkOut
     */
    @Override
    public int compareTo(Booking o) {
        // TODO Auto-generated method stub
        Date d1 = this.getCheckOut();
        Date d2 = ((Booking) o).getCheckOut();
        if(d1.compareTo(d2)>0) return 1;
        else if (d1.compareTo(d2)<0) return -1;
        else return 0;
    }
}
