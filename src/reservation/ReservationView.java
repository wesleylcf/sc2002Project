package reservation;

import systemTime.*;
import room.*;
import java.util.ArrayList;

import hrps.HRPS;

/**
Reservation View class which modularizes all UI related functions like printing menus 
@author Wesley Lim
@version 1.0
@since 2022-04-17
*/
public class ReservationView {
    /**
     * Prints Reservation's Main Menu(Create/Update/Remove/Print)
     */
    static public void printMainMenu() {
        System.out.printf("Choose an option:\n");
        System.out.printf("1) create a reservation\n");
        System.out.printf("2) update a reservation\n");
        System.out.printf("3) remove a reservation\n");
        System.out.printf("4) print Reservations\n");
        System.out.printf("Or '0' to exit current menu\n");
    }
    /**
     * Prints Reservation's Update Menu(ID/guests/roomType/...)
     */
    static public void printUpdateMenu() {
        System.out.printf("Choose a field to update:\n");
        System.out.printf("1) reservationID\n");
        System.out.printf("2) guests\n");
        System.out.printf("3) room type\n");
        System.out.printf("4) billing Information\n");
        System.out.printf("5) checkin date\n");
        System.out.printf("6) checkout date\n");
        System.out.printf("7) reservation status:\n");
        System.out.printf("Or press '0' to exit current menu\n");
    }
     /**
     * Prints Reservation's Update Guest Menu(Add/Remove)
     */
    static public void printUpdateGuestMenu() {
        System.out.printf("Choose an update option:\n");
        System.out.printf("1) Add guest\n");
        System.out.printf("2) Remove guest\n");
        System.out.printf("Or press 0 to quit\n");
    }
     /**
     * Prints Reservation's Update status Menu(CONFIRMED/IN_WAITLIST/CHECKED_IN/EXPIRED)
     */
    static public void printUpdateStatusMenu() {
        System.out.printf("Choose a new status:\n");
        System.out.printf("1) confirmed\n");
        System.out.printf("2) in wait-list\n");
        System.out.printf("3) checked-in\n");
        System.out.printf("4) expired\n");
        System.out.printf("Or press 0 to exit\n");
    }
     /**
     * Prints Reservation's Room Type Menu(SINGLE/DOUBLE/DELUXE/VIP)
     */
    static public void printRoomTypeMenu() {
        System.out.printf("Enter Room type:\n");
        System.out.printf("1) SINGLE\n");
        System.out.printf("2) DOUBLE\n");
        System.out.printf("3) DELUXE\n");
        System.out.printf("4) JUNIOR_SUITE\n");
        System.out.printf("5) VIP\n");
    }
     /**
      * Prints all reservations
      * @param roomModel Model used to print a reservation
     */
    static public void printReservations(RoomModel roomModel) {
        if(HRPS.reservationData.getCollection().isEmpty()) {
            System.out.printf("No reservations!\n");
        }
        System.out.println("Number of reservations: "+HRPS.reservationData.getCollection().size());
        for(Reservation rsv : HRPS.reservationData.getCollection()) {
            System.out.printf("-----START OF RESERVATION-----\n");
            printReservation(rsv, roomModel);
            System.out.printf("-----END OF RESERVATION-----\n");
        }
    }
    /**
     * Prints one reservation
     * @param rsv Reservation to print
     * @param rm Room Model to get Room details
     */
    static public void printReservation(Reservation rsv, RoomModel rm) {
        System.out.printf("ReservationId: %s\n", rsv.getId());
        System.out.printf("Guests: %d\n", rsv.getNumGuests());
        printGuests(rsv.getGuestIDs());
        System.out.printf("Room type: %s\n", rm.getRoom(rsv.getRoomId()).getRoomType());
        System.out.printf("Billing info: %s\n", rsv.getBillInfo());
        System.out.printf("Check in date: %s\n", SystemTime.sdformat.format(rsv.getCheckIn()));
        System.out.printf("Check out date: %s\n", SystemTime.sdformat.format(rsv.getCheckOut()));
        System.out.printf("Reservation status: %s\n", Reservation.parseStatus(rsv.getStatus()));
    }

    /**
     * Prints all reservations
     * @param guestIDs array of guestIDs to loop through and print Guests
     */
    static public void printGuests(ArrayList<String> guestIDs ) {
        for(int i=0; i<guestIDs.size(); i++) {
            System.out.printf("%d) %s\n",i+1,guestIDs.get(i));
        }
    }
}