package room;

import java.io.IOException;
import java.util.Scanner;

import hrps.*;
import roomsvc.RoomView;

/**
* Room controller class to control flow of room
* @author Lee Bo Hua
* @version 1.0
* @since 2021-04-17
*/
public class RoomController{
	private RoomModel rooms;
	
	/**
	 * constructor of room controller
	 * @param model
	 */
	public RoomController(RoomModel model) {
		rooms = model;
	}
	
	/**
	 * main menu of room controller
	 */
    public void init(){
        int choice=1;
		Scanner sc = HRPS.sc;
		
		do {
			try {
				RoomView.printRoomMenu();
				choice = InputValidator.validateIntRange(1, 8, "Room Menu", sc.nextInt());
				sc.nextLine();
				switch (choice) {
					 case 1:{
							rooms.createRoom();
						break;
					 }
					 case 2:{
						rooms.updateDetails();
						break;
					 }
						
					 case 3:{
						rooms.checkAvailability();
						break;
					 }
					 	
					 case 4:{
						rooms.checkDetails();
						break;
					 }

					 
					 case 5:{
						 rooms.roomStatusReport();
						 break;
					 }
					 case 6:{
						rooms.roomTypeRSR();
						 break;
					 }
					 case 7:{
						rooms.printAvailable();
						 break;
					 }
					 case 8: 
					 	System.out.println("Program terminating!!!!!!!.");
				}
			} catch(Exception e) {
				ExceptionHandler.handleRoomException(e);
			}
			
		} while (choice < 8);

		try{
			RoomDB.getInstance().save(HRPS.roomData.getRooms());
		} catch(IOException e) {
			System.out.printf("Error writing to file\n ");
		}
    }
}