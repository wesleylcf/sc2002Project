package cico;

/**
CheckIn View class which modularizes all UI related functions like printing menus 
@author Wesley Lim
@version 1.0
@since 2022-04-17
*/
public class CheckInView {
	/**
     * Prints Main Menu(check-in/print checked-in bookings)
     */
    static public void printMainMenu(){
        System.out.printf("------Check-in/Check-out menu------\n");
        System.out.printf("Choose an option:\n");
        System.out.printf("1) Check-in\n");
        System.out.printf("2) print Checked-in bookings\n");
        System.out.printf("Or enter '0' to exit\n");
    }

    /**
     * Prints CheckIn Menu(walk-in/reservation)
     */
    static public void printCheckInMenu() {
        System.out.printf("Choose an option:\n");
        System.out.printf("1) Walk-in\n");
        System.out.printf("2) Reservation\n");
        System.out.printf("Or enter '0' to exit\n");
    }

}
