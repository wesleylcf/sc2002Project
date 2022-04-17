package guest;
import java.util.Scanner;

import hrps.ExceptionHandler;
import hrps.HRPS;
import hrps.InputValidator;

/**
* Guest controller class
* Control the flow of the guest controller
* @author Lee Bo Hua
* @version 1.0
* @since 2022-04-17
*/
public class GuestController {
	
	private GuestModel guests;
	
	/**
	 * constructor method for guest 
	 * @param guestModel
	 */
	public GuestController(GuestModel guestModel) {
		guests = guestModel;
	}
	
	/**
	 * main entry proint to control guest infomation
	 */
    public void init(){
        int choice = 0;
		Scanner sc = HRPS.sc;
			do {
				
				try {
					GuestView.printMainMenu();
					choice = InputValidator.validateIntRange(1, 5, "Guest menu", sc.nextInt());
					switch (choice) {
						 case 1:{
		                        guests.createGuest();
		                        break;
						 }
						 case 2:{
		                        guests.updateGuestDetails();
							break;
						 }
							
						 case 3:{
							 	System.out.println("Enter ID of guest to check:");
		                        guests.printGuest(InputValidator.validateGuestId(sc.nextInt()));
							break;
						 }
						 	
						 case 4:{
							guests.getAllGuest();
							break;
						 }
						 
						 case 5:{
		                    System.out.println("Program terminating!!!");
							 break;
						 }
						 default:
							 System.out.printf("default\n");
							 break;
					}
				} catch(Exception e) {
					ExceptionHandler.handleGuestException(e);
				}	
				
			} while (choice < 5);
		} 
		
 }

