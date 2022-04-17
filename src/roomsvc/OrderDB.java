package roomsvc;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.ArrayList;

import database.DataBase;
import database.HashMapDataBase;

/**
* Order DB control the reading and writing of the order list
* @author Lee Alessandro
* @version 1.0
* @since 2022-04-17
*/
public class OrderDB extends DataBase implements HashMapDataBase<String, OrderList>{
	public static final String SEPARATOR = "|";
	private final String fileName = "orderList.txt";
	
	private static OrderDB singleton = null;
	
	/**
	 * Order constructor
	 * This object can only have one instance
	 * @return orderDB object
	 */
	public static OrderDB getInstance() {
		if(singleton == null) {
			singleton = new OrderDB();
		}
		return singleton;
	}
	
    /**
     * reads from the text file and converts into an hash map
     * @return a hash map of Orders
     */
	public HashMap<String, OrderList> populate() throws IOException {
		// read String from text file
		ArrayList<String> stringArray = (ArrayList<String>)read(fileName);
		HashMap<String, OrderList> orders = new HashMap<String, OrderList>();
        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
				String room_id = star.nextToken().trim(); //second token
				int number = Integer.parseInt(star.nextToken().trim()); //first token
				//Settle orderList
				int j=0;
				OrderList orderList = new OrderList(room_id);
				while(j<number) {
					String name = star.nextToken().trim();	// third token
					String remarks = star.nextToken().trim(); // fourth token
					String dateTime = star.nextToken().trim(); // fifth token
					String orderStatus = star.nextToken().trim(); // sixth token
					double price = Double.parseDouble(star.nextToken().trim()); // seventh token
			        orderList.create(name, "", price, remarks);
			        orderList.getOrderList().get(j).setDateTime(dateTime);	
			        orderList.getOrderList().get(j).setOrderStatus(orderList.getOrderList().get(j).stringToEnum(orderStatus));
			        j++;
				}
				orders.put(room_id, orderList); 
			}
			return orders ;
	}

	/**
	 * writes the hash map into a text file for storage
	 */
	public void save(HashMap<String, OrderList> al) throws IOException {
		List<String> alw = new ArrayList<String>() ;// to store OrderItem data
		
		for (Map.Entry<String, OrderList> entry : al.entrySet()) {
		    String key = entry.getKey();
		    OrderList orderList = entry.getValue();
		    ArrayList<OrderItem> orderItem = orderList.getOrderList();
		    StringBuilder st = new StringBuilder();
		    st.append(key.trim());
			st.append(SEPARATOR);
	    	st.append(orderItem.size());
	    	st.append(SEPARATOR);
		    for(int i=0; i<orderItem.size(); i++) {
		    	st.append(orderItem.get(i).getName().trim());
				st.append(SEPARATOR);
				st.append(orderItem.get(i).getRemarks().trim());
				st.append(SEPARATOR);
				st.append(orderItem.get(i).getDateTime().trim());
				st.append(SEPARATOR);
				st.append(orderItem.get(i).getOrderStatus().trim());
				st.append(SEPARATOR);
				st.append(Double.toString(orderItem.get(i).getPrice()));
				st.append(SEPARATOR);
		    }
			alw.add(st.toString()) ; 
		    write(fileName,alw);
		}
	}

}