package roomsvc;

import java.util.ArrayList;

/**
* Formatting class which aids in the dynamic printing of Menu and Order tables depending on the max  
* length of each columns.
* @author Lee Alessandro
* @version 1.0
* @since 2022-04-17
*/
public class Formatting {

	/**
	 * Method to format printing of Menu table (both header and content).
	 * Dynamically creates the format of Menu table and minimizing excess space,
	 * allocating appropriate column sizes for Dish Name, Dish Description and Dish Price.
	 * Column size is measured by the number of characters of the respective inputs.
	 * @param list List of Menu Items
	 * @return the printing format of Menu table content
	 */
	static public String typeMenu(ArrayList<MenuItem> list) {
		
	    int snChar=0, longestNameChar=4, longestDescChar=11, longestPriceChar=5, pad=3, totalChar=0, leftRepeat=0, rightRepeat=0;
	  	snChar = 5; 
	  	longestNameChar = getLongestNameCharMenu(longestNameChar, list) + pad; 
	  	longestDescChar = getLongestDescCharMenu(longestDescChar, list) + pad; 
	  	longestPriceChar = getLongestPriceCharMenu(longestPriceChar, list); 
	  	totalChar = snChar + longestNameChar + longestDescChar + longestPriceChar;
	  	if((totalChar-17)%2==1) {
	  		leftRepeat = (totalChar-17)/2;
	  		rightRepeat = leftRepeat+1;
	  	}
	  	else {
	  		leftRepeat = (totalChar-17)/2;
	  		rightRepeat = leftRepeat;
	  	}
	  	MenuModel.topLeftRepeatMenu = leftRepeat;
	  	MenuModel.topRightRepeatMenu = rightRepeat;
	  	MenuModel.bottomRepeatMenu = totalChar;
	  	MenuModel.formatHeaderMenu = "%-"+snChar+"s%-"+longestNameChar+"s%-"+longestDescChar+"s%-"+longestPriceChar+"s%n";
	  	return "%-"+snChar+"d%-"+longestNameChar+"s%-"+longestDescChar+"s%-"+longestPriceChar+".2f%n";
	}
	
	/**
	 * Method to format printing of Order table (both header and content).
	 * Dynamically creates the format of Menu table and minimizing excess space,
	 * allocating appropriate column sizes for Dish Name, Dish Remarks, Order DateTime, Order Status and Dish Price.
	 * Column size is measured by the number of characters of the respective inputs.
	 * @param list List of Order Items
	 * @return the printing format of Order table content
	 */
	static public String typeOrder(ArrayList<OrderItem> list) {
		int snChar=0, longestNameChar=4, longestRemarksChar=7, longestDateTimeChar=11, longestStatusChar=6, longestPriceChar=5, pad=3, 
      		totalChar=0, leftRepeat=0, rightRepeat=0;
		snChar = 5;
	  	longestNameChar = getLongestNameCharOrder(longestNameChar, list) + pad; 
	  	longestRemarksChar = getLongestRemarksCharOrder(longestRemarksChar, list) + pad; 
	  	longestDateTimeChar = getLongestDateTimeCharOrder(longestDateTimeChar, list) + pad;
	  	longestStatusChar = getLongestStatusCharOrder(longestStatusChar, list) + pad;
	  	longestPriceChar = getLongestPriceCharOrder(longestPriceChar, list);
	  	totalChar = snChar + longestNameChar + longestRemarksChar + longestDateTimeChar + longestStatusChar + longestPriceChar;
	  	if((totalChar-19)%2==1) {
	  		leftRepeat = (totalChar-19)/2;
	  		rightRepeat = leftRepeat+1;
	  	}
	  	else {
	  		leftRepeat = (totalChar-19)/2;
	  		rightRepeat = leftRepeat;
	  	}
	  	OrderModel.topLeftRepeatOrder = leftRepeat;
	  	OrderModel.topRightRepeatOrder = rightRepeat;
	  	OrderModel.bottomRepeatOrder = totalChar;
	  	OrderModel.formatHeaderOrder = "%-"+snChar+"s%-"+longestNameChar+"s%-"+longestRemarksChar+"s%-"+longestDateTimeChar+"s%-"+longestStatusChar+"s%-"+longestPriceChar+"s%n";
	  	return "%-"+snChar+"d%-"+longestNameChar+"s%-"+longestRemarksChar+"s%-"+longestDateTimeChar+"s%-"+longestStatusChar+"s%-"+longestPriceChar+".2f%n";
	}
	
	
	/**
	 * Method to aid the printing of Menu Table by getting maximum column size for Name.
	 * Column size is measured by the number of characters of the respective inputs.
	 * @param longestNameChar	Default size of Name column
	 * @param menuList			List of Menu Items to iterate through
	 * @return the maximum size of Name column
	 */
    static public int getLongestNameCharMenu(int longestNameChar, ArrayList<MenuItem> menuList){
    	for(MenuItem menuItem : menuList) {
    		if(menuItem.getName().length()>longestNameChar)
    			longestNameChar = menuItem.getName().length();
    	}
    	return longestNameChar;
    }
    
	/**
	 * Method to aid the printing of Menu Table by getting maximum column size for Description.
	 * Column size is measured by the number of characters of the respective inputs.
	 * @param longestDescChar	Default size of Description column
	 * @param menuList			List of Menu Items to iterate through
	 * @return the maximum size of Description column
	 */
    static public int getLongestDescCharMenu(int longestDescChar, ArrayList<MenuItem> menuList){
    	for(MenuItem menuItem : menuList) {
    		if(menuItem.getDescription().length()>longestDescChar)
    			longestDescChar = menuItem.getDescription().length();
    	}
    	return longestDescChar;
    }
    
	/**
	 * Method to aid the printing of Menu Table by getting maximum column size for Price.
	 * Column size is measured by the number of characters of the respective inputs.
	 * @param priceChar		Default size of Price column
	 * @param menuList		List of Menu Items to iterate through
	 * @return the maximum size of Price column
	 */
    static public int getLongestPriceCharMenu(int priceChar, ArrayList<MenuItem> menuList){
    	String doubleToString;
    	int longestPriceChar=priceChar; //original: int longestPriceChar=0;
    	for(MenuItem menuItem : menuList) {
    		doubleToString = String.format("%.2f", menuItem.getPrice());
    		priceChar = doubleToString.length();
    		if(priceChar>longestPriceChar)
    			longestPriceChar = priceChar;
    	}
    	return longestPriceChar;
    }
	
    
	/**
	 * Method to aid the printing of Order Table by getting maximum column size for Name.
	 * Column size is measured by the number of characters of the respective inputs.
	 * @param longestNameChar	Default size of Name column
	 * @param orderList			List of Order Items to iterate through
	 * @return the maximum size of Name column
	 */
    static public int getLongestNameCharOrder(int longestNameChar, ArrayList<OrderItem> orderList){
    	for(OrderItem orderItem : orderList) {
    		if(orderItem.getName().length()>longestNameChar)
    			longestNameChar = orderItem.getName().length();
    	}
    	return longestNameChar;
    }
    
	/**
	 * Method to aid the printing of Order Table by getting maximum column size for Price.
	 * Column size is measured by the number of characters of the respective inputs.
	 * @param priceChar		Default size of Price column
	 * @param orderList		List of Order Items to iterate through
	 * @return the maximum size of Price column
	 */
    static public int getLongestPriceCharOrder(int priceChar, ArrayList<OrderItem> orderList){
    	String doubleToString;
    	int longestPriceChar=priceChar; 
    	for(OrderItem orderItem : orderList) {
    		doubleToString = String.format("%.2f", orderItem.getPrice());
    		priceChar = doubleToString.length();
    		if(priceChar>longestPriceChar)
    			longestPriceChar = priceChar;
    	}
    	return longestPriceChar;
    }
    
	/**
	 * Method to aid the printing of Order Table by getting maximum column size for Remarks.
	 * Column size is measured by the number of characters of the respective inputs.
	 * @param longestRemarksChar	Default size of Remarks column
	 * @param orderList				List of Order Items to iterate through
	 * @return the maximum size of Remarks column
	 */
    static public int getLongestRemarksCharOrder(int longestRemarksChar, ArrayList<OrderItem> orderList){
    	for(OrderItem orderItem : orderList) {
    		if(orderItem.getRemarks().length()>longestRemarksChar)
    			longestRemarksChar = orderItem.getRemarks().length();
    	}
    	return longestRemarksChar;
    }
    
	/**
	 * Method to aid the printing of Order Table by getting maximum column size for DateTime.
	 * Column size is measured by the number of characters of the respective inputs
	 * @param longestDateTimeChar	Default size of DateTime column
	 * @param orderList				List of Order Items to iterate through
	 * @return the maximum size of DateTime column
	 */
    static public int getLongestDateTimeCharOrder(int longestDateTimeChar, ArrayList<OrderItem> orderList){
    	for(OrderItem orderItem : orderList) {
    		if(orderItem.getDateTime().length()>longestDateTimeChar)
    			longestDateTimeChar = orderItem.getDateTime().length();
    	}
    	return longestDateTimeChar;
    }
    
	/**
	 * Method to aid the printing of Order Table by getting maximum column size for Order Status	.
	 * Column size is measured by the number of characters of the respective inputs.
	 * @param longestStatusChar		Default size of Order Status column
	 * @param orderList				List of Order Items to iterate through
	 * @return the maximum size of Order Status column
	 */
    static public int getLongestStatusCharOrder(int longestStatusChar, ArrayList<OrderItem> orderList){
    	for(OrderItem orderItem : orderList) {
    		if(orderItem.getOrderStatus().length()>longestStatusChar)
    			longestStatusChar = orderItem.getOrderStatus().length();
    	}
    	return longestStatusChar;
    }
    
}
