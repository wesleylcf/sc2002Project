package roomsvc;

import java.util.ArrayList;

/**
* OrderList class to manage an ArrayList of OrderItem objects for a specific room_id.
* @author Lee Alessandro
* @version 1.0
* @since 2022-04-17
*/
public class OrderList{

	/**
	* Room identification
	*/
	private String room_id;
	
	/**
	* ArrayList of OrderItem with private visibility
	*/
	private ArrayList<OrderItem> orderList = new ArrayList<>();
	
	/**
	* Constructor
	* @param room_id which is assigned to local variable room_id
	*/
	public OrderList(String room_id){
		this.room_id = room_id;
	}

	/**
	 * Method to create a new OrderItem.
	 * @param name			Name of new MenuItem to order
	 * @param description	Description of MenuItem to order
	 * @param price			Price of MenuItem to order
	 * @param remarks		Remarks of OrderItem as stated by guest
	 */
    public void create(String name, String description, double price, String remarks){
        orderList.add(new OrderItem(name, description, price, remarks));
        System.out.println("Successfully added to Room "+room_id);
    }
    
	/**
	 * Method to remove an OrderItem.
	 * @param identifier	S/N of OrderItem to remove
	 */
    public void remove(int identifier) {
    	orderList.remove(identifier);
   		System.out.println("Successfully removed from Room "+room_id);
    }
    
	/**
	 * Method to retrieve ArrayList<OrderItem>.
	 * @return the ArrayList of OrderItem
	 */
	public ArrayList<OrderItem> getOrderList(){
		return orderList;
	}
	
}
