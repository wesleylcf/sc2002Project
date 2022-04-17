package roomsvc;

import java.util.ArrayList;

import room.Room;

/**
* RoomServiceView class which modularizes all UI related functions like printing menus 
* @author Lee Alessandro
* @version 1.0
* @since 2022-04-17
*/
public class RoomServiceView {

	 /**
     * Prints Room Service Application's Main Menu(Edit Room Service Orders/Edit Room Service Menu Items/Exit)
     */
	static public void printRoomServiceApplication() {
		System.out.println("===Room Service Application===");
		System.out.println("1. Edit Room Service Orders");
		System.out.println("2. Edit Room Service Menu Items");
		System.out.println("3. Exit");
	}
	
	/**
     * Prints Edit Room Service Order's Menu(Create Orders/Remove Orders/Update Order Status/Exit)
     */
	static public void printEditRoomServiceOrders() {
		System.out.println("==Edit Room Service Orders==");
		System.out.println("1. Create");
		System.out.println("2. Remove");
		System.out.println("3. Update Status");
		System.out.println("4. Exit");
	}
	
	/**
     * Prints Edit Room Service Menu Item's Menu(Create Menu/Remove Menu/Update Menu/Exit)
     */
	static public void printEditRoomServiceMenuItems() {
		System.out.println("==Edit Room Service Menu Items==");
		System.out.println("1. Create");
		System.out.println("2. Update");
		System.out.println("3. Remove");
		System.out.println("4. Exit");
	}
	
	/**
     * Prints Edit Room Service Menu Item's Update Menu(Modify Name/Modify Description/Modify Price/Exit)
     */
	static public void printUpdateRoomServiceMenuItems() {
		System.out.println("1. Modify Name");
		System.out.println("2. Modify Description");
		System.out.println("3. Modify Price");
		System.out.println("4. Exit");
	}
	
	static public void printRoomForService(ArrayList<Room> occupied) {
		//Printing header
		System.out.printf("-".repeat(4)); 
    	System.out.printf("Rooms"); 
    	System.out.printf("-".repeat(4)); 
    	System.out.println();
		System.out.printf("S/N   Room ID\n");
		//Printing content
		int i=1;
		for(Room room : occupied) {
			System.out.printf("%-6d%-7s\n", i, room.getRoomNumber());
			i++;
		}
		//Printing bottom header
		System.out.printf("-".repeat(13)); 
    	System.out.println();
	}
	
}
