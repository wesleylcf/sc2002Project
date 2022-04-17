package roomsvc;

import java.util.InputMismatchException;

import hrps.ExceptionHandler;
import hrps.HRPS;
import hrps.InputValidator;

/**
* OrderModel class to take in staff inputs and call the respective methods in OrderData to
* support the creating, updating and removing of OrderItems.
* @author Lee Alessandro
* @version 1.0
* @since 2022-04-17
*/
public class OrderModel {

	/**
	* Local variable of type OrderData to assist in calling the methods to create, update and remove OrderItem.
	*/
	private OrderData orderData;
	
	/**
	* Defining the following variables to format Menu table.
      Variables declared as static protected are updated directly in Formatting class.
	*/
    static protected int topLeftRepeatOrder=0, topRightRepeatOrder=0, bottomRepeatOrder=0;
    static protected String formatHeaderOrder;
    
	/**
	* Defining the following variables to format Menu table.
      private String formatContentMenu is returned from Formatting class.
	*/
    private String formatContentOrder;
    
	/**
	* Defining the following private variables to be used in local methods.
	*/
	private int identifier;
	private double price; 
	private String name, description, remarks;
	
	/**
	 * Constructor.
	 * @param data passed in as parameter to assign to local orderData of type orderData.
	 */
	public OrderModel(OrderData orderData) {
        this.orderData = orderData;
	}
	

    /**
	 * Method to take in staff input to create new OrderItem.
	 * @param menuData	type MenuData to access information of MenuItems
	 * @param room_id	to identify which room is ordering
	 */
    public void createOrderItem(MenuData menuData, String room_id){
    	try {
			System.out.println("==Create==");
			System.out.println("Enter Dish S/N to add to order: ");
			identifier = InputValidator.validateIntRange(1, menuData.getMenuList().size(), "Enter Dish S/N to add to order",HRPS.sc.nextInt());
			identifier -= 1;
			HRPS.sc.nextLine();
			name = menuData.getMenuList().get(identifier).getName();
			description = menuData.getMenuList().get(identifier).getDescription();
			price = menuData.getMenuList().get(identifier).getPrice();
			System.out.println("Enter Dish Remarks: ");
			remarks = HRPS.sc.nextLine();
			orderData.setOrders(room_id);
			orderData.create(room_id, name, description, price, remarks);
    	}
    	catch(Exception e) {
    		ExceptionHandler.handleRoomServiceException(e);
    	}
    }
    
    /**
   	 * Method to take in room_id to remove OrderItem.
   	 * @param room_id	to identify which room is removing an order
   	 */
    public void removeOrderItem(String room_id) {
		try{
			System.out.println("==Remove==");
    		if(orderData.isEmptyOrder(room_id)==0) 
    			System.out.println("Order Records is already empty. Unable to remove.\n");
    		else {
        		System.out.println("Enter Order S/N  to remove: ");
        		identifier = InputValidator.validateIntRange(1, orderData.getOrderByRoomId(room_id).size(), "Enter Order S/N to remove", HRPS.sc.nextInt());
        		identifier -= 1;
            	orderData.remove(room_id, identifier);
    		}
		}
		catch(Exception e) {
			ExceptionHandler.handleRoomServiceException(e);
		}
    }
    
    /**
  	 * Method to take in staff input to update OrderItem OrderStatus.
  	 * @param room_id		to identify which room to update OrderStatus of OrderItem
  	 * @param statusType	takes in initial statusType to be updated
  	 */
    public void updateOrderItem(String room_id, OrderItem.OrderStatus statusType) {
		try{
			System.out.println("==Update Status==");
    		if(orderData.isEmptyOrder(room_id)==0)
    			System.out.println("Order Records is already empty. Unable to update status.\n");
    		else {
        		System.out.println("Enter Order S/N  to update status: ");
        		identifier = InputValidator.validateIntRange(1, orderData.getOrderByRoomId(room_id).size(), "Enter Order S/N to update status", HRPS.sc.nextInt());
        		identifier -= 1;
        		System.out.println("Select option:");
        		System.out.println("1. Update Status to Preparing");
        		System.out.println("2. Update Status to Delivered");
        		int statusSelect = InputValidator.validateIntRange(1, 2, "Select option", HRPS.sc.nextInt());
        		if(statusSelect==1) {
        			orderData.getOrderByRoomId(room_id).get(identifier).setOrderStatus(statusType.Preparing);
        		}
        		else if(statusSelect==2) {
        			orderData.getOrderByRoomId(room_id).get(identifier).setOrderStatus(statusType.Delivered);
        		}
    		}
		}
		catch(Exception e) {
			ExceptionHandler.handleRoomServiceException(e);
		}
    }
    
    /**
  	 * Method to print existing OrderItem of SPECIFIC ROOM.
  	 * @param room_id to identify which room to print OrderItem
  	 */
    public void printOrderItem(String room_id){
    	if(orderData.isEmptyOrder(room_id)==0) {
    		//Setting up fixed format to print top header, content and bottom header
	    	//Printing top header of order: 
	    	System.out.printf("-".repeat(16)); 
	    	System.out.printf("Room Service Orders"); 
	    	System.out.printf("-".repeat(16)); 
	    	System.out.println();
	    	//Printing contents of order:
    		System.out.println("Room ID: "+room_id);
	    	System.out.printf(" ".repeat(23)); 
    		System.out.printf("EMPTY"); 
    		System.out.printf(" ".repeat(23));
    		System.out.println();
	    	//Printing bottom header of order:
	    	System.out.printf("-".repeat(51)); 
	    	System.out.println();
    	}
    	else {
		 	//Getting dynamic format to print top header, content and bottom header
    		formatContentOrder = Formatting.typeOrder(orderData.getOrderByRoomId(room_id));
	    	//Printing top header of menu
	    	System.out.printf("-".repeat(topLeftRepeatOrder));
	    	System.out.printf("Room Service Orders");
	    	System.out.printf("-".repeat(topRightRepeatOrder));
	    	System.out.println();
	    	//Printing header of contents
	    	System.out.println("Room ID: "+room_id+"\n");
	    	System.out.printf(formatHeaderOrder, "S/N", "Dish", "Remarks", "Date & Time", "Status", "Price");
	    	//Printing contents of menu
	    	int i=1;
	    	for(OrderItem orderItem : orderData.getOrderByRoomId(room_id)) {
	    		System.out.printf(formatContentOrder, i, orderItem.getName(), orderItem.getRemarks(), orderItem.getDateTime(), orderItem.getOrderStatus(), orderItem.getPrice());
	    		i++;
	    	}
	    	//Printing bottom header of menu
	    	System.out.printf("-".repeat(bottomRepeatOrder));
	    	System.out.println();
    	}
    }
    
}
