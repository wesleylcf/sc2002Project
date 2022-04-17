package roomsvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import database.DataBase;

/**
* OrderData class which holds a HashMap of OrderList objects Data Structure, where each OrderList(value)
* corresponds to a specific room_id(key), and each OrderList contains an ArrayList of OrderItem.
* @author Lee Alessandro
* @version 1.0
* @since 2021-04-17
*/
public class OrderData extends DataBase{
	
	/**
	 * HashMap with protected visibility.
	 */
	protected HashMap<String, OrderList> orders = new HashMap<String, OrderList>();
	
	/**
	 * Constructor which reads from orderList.txt database to initialise base order items.
	 * For instance, if a guest pre-orders dishes during reservation.
	 */
	public OrderData(){
		try {
			orders = OrderDB.getInstance().populate();
		}catch(IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}
	
	/**
	 * Method to create a new OrderList object in the HashMap.
	 * @param room_id	used to locate for existence of such key in HashMap
	 */
	public void setOrders(String room_id) {
		//Need to first check whether there is existing object of room_id already created
		if(orders.get(room_id)==null)
			orders.put(room_id, new OrderList(room_id));
		//Else, do nothing
	}
	
	/**
	 * Method to remove an OrderList object in the HashMap (call this method after payment is done).
	 * @param room_id	used locate the key and aid in removing the entire key-value pair in Hashmap
	 */
	public void removeOrders(String room_id) {
		orders.remove(room_id);
	}
	
	/**
	 * Method to check if OrderList object's ArrayList is empty.
	 * @param room_id	used to locate the key in HashMap
	 */
	public int isEmptyOrder(String room_id) {
		if(getOrderByRoomId(room_id)==null || getOrderByRoomId(room_id).size()==0)
			return 0;
		else
			return 1;
	}

	/**
	 * Method to call another method in OrderList class to create new order items in SPECIFIC ROOM.
	 * @param room_id		Room ID of room using the room service
	 * @param name			Name of MenuItem to order
	 * @param description	Description of MenuItem to order
	 * @param price			Price of MenuItem to order
	 */
    public void create(String room_id, String name, String description, double price, String remarks){
        orders.get(room_id).create(name,  description,  price,  remarks);
    }
	
	/**
	 * Method to call another method in OrderList class to remove existing order items in SPECIFIC ROOM
	 * @param room_id		Room ID of room using the room service
	 * @param identifier	S/N of OrderItem to remove
	 */
    public void remove(String room_id, int identifier) {
    	orders.get(room_id).remove(identifier);
    }
     
	/**
	 * Method to call another method in OrderList class to return ArrayList<OrderItem> in SPECIFIC ROOM
	 * @param room_id		Room ID of room using the room service
	 */
	public ArrayList<OrderItem> getOrderByRoomId(String room_id){
		if(orders.get(room_id)!=null)
			return orders.get(room_id).getOrderList();
		else
			return null;
	}
	
	/**
	 * Retrieves a hash map of orderList
	 * @return a hash map of orders
	 */
	public HashMap<String, OrderList> getOrdersHashMap(){
		return orders;
	}
	
}

