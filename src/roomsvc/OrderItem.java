package roomsvc;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import systemTime.*;
import hrps.*;

/**
* OrderItem class to hold the details of each room service MenuItem that is ordered,
* such as its name, description and price. Additionally, it also hold Date/Time,
* Remarks and Order Status.
* @author Lee Alessandro
* @version 1.0
* @since 2022-04-17
*/
public class OrderItem extends MenuItem {
	
	
	/**
	* Remarks of OrderItem as stated by guest
	*/
	private String remarks;
	
	/**
	* DateTime of OrderItem ordered
	*/
	private String dateTime;
	
	/**
	* enum to represent the 3 states of Order Status
	*/
	public static enum OrderStatus {Confirmed, Preparing, Delivered};
	
	/**
	* Status type of Order Status
	*/
	private OrderStatus statusType; 
    
	/**
	 * Constructor which facilitates creating of a new OrderItem.
	 * Automatically generates the current Date/Time as well as setting statusType to Confirmed by default.
	 * @param name			Name of new MenuItem entered by staff
	 * @param description	Description of new MenuItem entered by staff
	 * @param price			Price of new MenuItem entered by staff
	 * @param remarks		Remarks of OrderItem as stated by guest
	 */
	
	/**
	* Date to receive from SystemTime class
	*/
	private String date = "";
	
	/**
	 * Order constructor
	 * @param name name of product
	 * @param description description of product
	 * @param price price of product
	 * @param remarks additional remarks of product
	 */
	public OrderItem(String name, String description, double price, String remarks) 
	{
		super(name, description, price);
		
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); //original
		//LocalDateTime now = LocalDateTime.now(); //original
		//dateTime = dtf.format(now); //original
		
		LocalTime timeNow = LocalTime.now(); //test 
		
		//Try this after integrating w Wesley's SystemTime Class
		date = SystemTime.parseDate(HRPS.time.getDate());
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(date+" HH:mm:ss");
		dateTime = dtf.format(timeNow);
		
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("13/04/2022"+" HH:mm:ss"); //test (to remove after integrating w Wesley's SystemTime class)
//		dateTime = dtf.format(timeNow); //test (to remove after integrating w Wesley's SystemTime class)
		this.remarks = remarks;
		statusType = OrderStatus.Confirmed;
	}
	
	/**
	 * Set method to update room service OrderItem's remarks.
	 * @param remarks		Remarks of OrderItem as stated by guest
	 */
	public void setRemarks(String remarks) {this.remarks = remarks;}
	
	/**
	 * Set method to update room service OrderItem's dateTime.
	 * @param dateTime		dateTime of OrderItem ordered
	 */
	public void setDateTime(String dateTime) {this.dateTime = dateTime;}
	
	/**
	 * Set method to update room service OrderItem's OrderStatus.
	 * @param statusType	OrderStatus of OrderItem
	 */
	public void setOrderStatus(OrderStatus statusType) {this.statusType = statusType;} 
	
	/**
	 * Get method to retrieve room service OrderItem's remarks.
	 * @param remarks		Remarks of OrderItem as stated by guest
	 */
	public String getRemarks() {return this.remarks;}
	
	/**
	 * Get method to retrieve room service OrderItem's dateTime.
	 * @param dateTime		dateTime of OrderItem ordered
	 */
	public String getDateTime() {return this.dateTime;}
	
	/**
	 * Get method to retrieve room service OrderItem's OrderStatus.
	 * @param statusType	OrderStatus of OrderItem
	 */
	public String getOrderStatus() {return enumToString(this.statusType);}
	
	/**
	 * Method to return enum as a string.
	 * @param statusType	OrderStatus of OrderItem
	 */
	public String enumToString(OrderStatus statusType) {
		if(statusType == OrderStatus.Confirmed)
			return "Confirmed";
		else if(statusType == OrderStatus.Preparing)
			return "Preparing";
		else
			return "Delivered";
	}
	
	/**
	 * Method to return string as a enum.
	 * @param orderString	String of OrderStatus
	 */
	public OrderStatus stringToEnum(String orderString) {
		if(orderString.equals("Confirmed"))
			return OrderStatus.Confirmed;
		else if(orderString.equals("Preparing"))
			return OrderStatus.Preparing;
		else
			return OrderStatus.Delivered;	
	}
}
