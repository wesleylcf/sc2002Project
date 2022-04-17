package roomsvc;

import java.io.IOException;
import java.util.*;
import room.*;
import hrps.ExceptionHandler;
import hrps.HRPS;
import hrps.InputValidator;

/**
* RoomServiceController class in charge of routing to various menus or functions in MenuModel and OrderModel
* @author Lee Alessandro
* @version 1.0
* @since 2022-04-17
*/
public class RoomServiceController{
	
	MenuData menuData;
	MenuModel menu;
	OrderData orderData;
	OrderModel order;
	RoomModel roomModel;
	
	public RoomServiceController(MenuData menuData, MenuModel menu, OrderData orderData, OrderModel order, RoomModel model) {
		this.menuData = menuData;
		this.menu = menu;
		this.orderData = orderData;
		this.order = order;
		this.roomModel = model;
	}
	
	 /**
	 * User interface to interact with RoomService(Edit Room Service Order/Edit Room Service Menu)
	 */
	public void init(){
		int optMain=0;
		do{
			RoomServiceView.printRoomServiceApplication();
			try {
				optMain = InputValidator.validateIntRange(1, 3, "Room Service Application", HRPS.sc.nextInt());
				HRPS.sc.nextLine();
			}
			catch(Exception e) {
				ExceptionHandler.handleRoomServiceException(e);
			}
			switch(optMain){
				case 1:
					editRoomServiceOrdersMenu();
					break;
				case 2:
					editRoomServiceMenuItemsMenu();
					break;
				case 3: 
                    System.out.println("==Exited Room Service Application==");
                	break;
                default:
            		break;
			}
        }while(optMain!=3);
    }

	
	public void editRoomServiceOrdersMenu() {
	
		ArrayList<Room> occupied = new ArrayList<>();
		occupied = roomModel.occupied();
		
		int opt1=0, occupiedIdentifier;
		String room_id = "";
		OrderItem.OrderStatus statusType = null;

		if(occupied.size()==0)
			System.out.println("No rooms are occupied, room service orders not available.\n");
		else {
			RoomServiceView.printRoomForService(occupied);
			//Getting user input
			System.out.println("Enter Room ID S/N to edit: ");
			try {
				occupiedIdentifier = InputValidator.validateIntRange(1, occupied.size(), "Enter Room ID S/N to edit", HRPS.sc.nextInt());
				occupiedIdentifier -= 1;
				HRPS.sc.nextLine();
				room_id = occupied.get(occupiedIdentifier).getRoomNumber();	
			}
			catch(Exception e) {
				ExceptionHandler.handleRoomServiceException(e);
				return;
			}
			while(opt1!=4) {
				try {
					order.printOrderItem(room_id);
					RoomServiceView.printEditRoomServiceOrders();
					opt1 = InputValidator.validateIntRange(1, 4, "Edit Room Service Orders", HRPS.sc.nextInt());
					HRPS.sc.nextLine();
					switch(opt1) {
						case 1:
							menu.printMenuItem();
							order.createOrderItem(menuData, room_id);
							break;
						case 2:
							order.printOrderItem(room_id);
							order.removeOrderItem(room_id);
	                		break;
						case 3:
							order.printOrderItem(room_id);
							order.updateOrderItem(room_id, statusType);
							break;
						case 4:
							System.out.println("==Exited Room Service Orders==\n");
							break;
						default:
							break;
					}
				}
				catch(Exception e) {
					ExceptionHandler.handleRoomServiceException(e);
				}
			}
			opt1=0;
		}
	}
	
	public void editRoomServiceMenuItemsMenu() {
		int opt2=0;
		while(opt2!=4) {
			try {
				//int opt2=0;
				menu.printMenuItem();
				RoomServiceView.printEditRoomServiceMenuItems();
				opt2 =  InputValidator.validateIntRange(1, 4, "Edit Room Service Menu Items", HRPS.sc.nextInt());
				HRPS.sc.nextLine();
				switch(opt2) {
					case 1:
						menu.createMenuItem();
						break;
					case 2:
						menu.updateMenuItem();
						break;
	            	case 3: 
	            		menu.removeMenuItem();
	            		break;
	            	case 4:
	            		System.out.println("==Exited Edit Room Service Menu Items==\n");
	            		break;
	            	default:
	            		break;
	            }
			}
			catch(Exception e) {
				ExceptionHandler.handleRoomServiceException(e);
			}
		}
	}
	
}