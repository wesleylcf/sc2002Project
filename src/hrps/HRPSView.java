package hrps;

/**
* Represents a Double Room inside a hotel 
* Inherits directly from Room
* @author Wesley Lim
* @version 1.0
* @since 2022-04-17
*/
public class HRPSView {
	/**
	 * print main menu
	 */
    static public void printMainMenu(){
            System.out.printf("-------------------------------------------------\n");
            System.out.printf("This is the hotel reservation and payment system\n");
            System.out.printf("-------------------------------------------------\n\n");
            System.out.printf("Choose a system to view or modify\n");
            System.out.printf("1) Reservations\n");
            System.out.printf("2) Check-in\n");
            System.out.printf("3) System Time(Check-outs on update)\n");
            System.out.printf("4) Room Service\n");
            System.out.printf("5) Guests \n");
            System.out.printf("6) Rooms \n");
            System.out.printf("Or '0' to exit current menu\n");
    }
}
