package reservation;
import guest.*;
import exception.*;
import hrps.*;
import systemTime.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.*;



/**
Reservation entity class  
@author Wesley Lim
@version 1.0
@since 2022-04-17
*/
public class Reservation extends Booking{
    /**
    * status of reservation
    */
    private ReservationStatus status;
    public Reservation(String id, int numGuests, ArrayList<String> guestIDs, String roomId, String billInfo, Date checkIn, Date checkOut, ReservationStatus status) {
        super(id,numGuests, guestIDs, roomId, billInfo,checkIn, checkOut);
        this.status = status;
    }
   
    /**
    * prints an acknowledgement/receipt after creating a reservation successfully
    * @param guestModel The model used to print Guests for a reservation
    */
    public void onCreateReservation(GuestModel guestModel) throws NoGuestFoundException {
        System.out.printf("Reservation created. Details are as follows:\n");
        System.out.printf("Reservation ID: %s\n", id);
        System.out.printf("Guests:\n");
        for( String guestId : guestIDs) {
            Guest g;
            g = guestModel.search(guestId);
            System.out.printf("----- Guest -----\n");
            g.print();
        }
        //print using reservation.room.getDetail
        System.out.printf("Check-in/Checkout date: %s/%s\n",SystemTime.sdformat.format(checkIn), SystemTime.sdformat.format(checkOut));
        // check how to print approriate string
        System.out.printf("Reservation status: %s\n",getStatus());
        System.out.printf("-----END OF DETAILS-----\n");
        System.out.println();
        
    }

   

    /**
    * Gets the Reservation Status(non-string)
    * @return this Reservation's Status
    */
    public ReservationStatus getStatus() { return this.status;}
    /**
    * Sets the status of this reservation
    * @param newStatus updated reservation status
    */
    public void setStatus(ReservationStatus newStatus) { this.status = newStatus;}

    /**
    * Increments the number of guests. Called when adding a guest
    */
    public void incrementNumGuests() {setNumGuests(getNumGuests()+1);}
    /**
    * Decrements the number of guests. Called when removing a guest
    */
    public void decrementNumGuests() { setNumGuests(getNumGuests()-1);}

    

    /**
    * Returns the correct RoomType for an option input
    * @param choice integer value passed from the user
    * @return the appropriate room type corresponding to choice
    */
    public static String getRoomType(Integer choice){
        String roomType = "SINGLE";
        if(choice == 2) roomType = "DOUBLE";
        if(choice == 3) roomType = "DELUXE";
        if(choice == 4) roomType = "JUNIOR_SUITE";
        if(choice == 5) roomType = "VIP";
        return roomType;
    }

    /**
     * Returns the correct Status for an option input
     * @param choice integer value passed from the user
     * @return the appropriate Status corresponding to choice
     */
    public static String getStatus(Integer choice) throws InvalidStatusInteger{
        if(choice <0 || choice > 4) throw new InvalidStatusInteger();
        String roomType = "CONFIRMED";
        if(choice == 2) roomType = "IN_WAITLIST";
        if(choice == 3) roomType = "CHECKED_IN";
        if(choice == 4) roomType = "EXPIRED";
        return roomType;
    }

    /**
     * Parse a string Status to return the Status as an Enum
     * @param statusString status string 
     * @return ReservationStatus Enum
     */
    public static ReservationStatus parseStatus(String statusString) throws InvalidStatusString{
        if(statusString.equals("CONFIRMED")) return ReservationStatus.CONFIRMED;
        else if(statusString.equals("IN_WAITLIST")) return ReservationStatus.IN_WAITLIST;
        else if(statusString.equals("CHECKED_IN")) return ReservationStatus.CHECKED_IN;
        else if (statusString.equals("EXPIRED")) return ReservationStatus.EXPIRED;
        else throw new InvalidStatusString();
    }

    /**
     * Parse an Enum Status to return the status as a String
     * @param status Enum status 
     * @return String Enum
     */
    static public String parseStatus(ReservationStatus s) {
        if(s == ReservationStatus.CONFIRMED) return "CONFIRMED";
        else if(s == ReservationStatus.IN_WAITLIST) return "IN_WAITLIST";
        else if(s == ReservationStatus.CHECKED_IN) return "CHECKED_IN";
        return "EXPIRED";
    }


//    public boolean equals(Object o) {
//		if (o instanceof Reservation) {
//			Reservation p = (Reservation)o;
//			return (this.getId().equals(p.getId()));
//		}
//		return false;
//	}
}