package systemTime;

/**
* View class for system time which routes to print menus
* @author Wesley Lim
* @version 1.0
* @since 2022-04-17
*/
public class SystemTimeView {
   
	/**
	 * Prints main menu for system time
	 */
    static public void printMainMenu() {
        System.out.printf("Choose an option:\n");
        System.out.printf("1) check System Time\n");
        System.out.printf("2) update System Time\n");
        System.out.printf("Or '0' to exit current menu\n");
    }

}
