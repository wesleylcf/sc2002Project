package cico;

import reservation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
Check in Data class which holds checked-in bookings
@author Wesley Lim
@version 1.0
@since 2022-04-17
*/
public class CheckInData {
    private ArrayList<Booking> bookings = new ArrayList<Booking>();
    
	/**
	 * Initializes CheckInData with checked-in reservations
	 * @param data ReservationData
	 */
    public CheckInData(ReservationData data) {
    	for(Reservation rsv : data.getCollection()) {
    		if(rsv.getStatus() == ReservationStatus.CHECKED_IN ) {
    			bookings.add(rsv);
    		}
    	}
    	Collections.sort(bookings);
    }
    
    /**
	 * Adds a booking to checked-in data and sorts it
	 * @param booking booking to add
	 */
    public void create(Booking booking) {
        bookings.add(booking);
        Collections.sort(bookings);
    }
    
    /**
	 * deletes a booking from checked-in data
	 * @param booking booking to delete
	 */
    public void delete(Booking booking) {
        boolean res = bookings.remove(booking);
        if(!res) System.out.printf("No such booking to delete!\n");
    }
    
    /**
	 * Gets all checked-in bookings for a given check-out date
	 * @param date checkOut date
	 * @return checkedInBookings 
	 */
    public ArrayList<Booking> getCheckOuts(Date date) {
        ArrayList<Booking> checkOuts = new ArrayList<Booking>();
        for(Booking b : bookings) {
            if(b.getCheckOut().compareTo(date)==0) {
                checkOuts.add(b);
            }
        }
        return checkOuts;
    }
    
    /**
	 * Gets all checked-in data
	 * @return arrayList
	 */
    public ArrayList<Booking> readAll() {
    	return bookings;
    }

    
}
