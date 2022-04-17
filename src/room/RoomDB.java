package room;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

import database.DataBase;
import database.HashMapDataBase;
import roomsvc.OrderDB;
import roomsvc.OrderList;

import java.text.*;
import java.util.Date;
import java.util.HashMap;

/**
* Room DB class to handle reading and writing to the txt file
* @author Lee Bo Hua
* @version 1.0
* @since 2021-04-17
*/
public class RoomDB extends DataBase implements HashMapDataBase<String, Room> {
	public static final String SEPARATOR = "|";
	private final String fileName = "rooms.txt";
	private static RoomDB singleton = null;
	
	/**
	 * singleton constructor method
	 * @return
	 */
	public static RoomDB getInstance() {
		if(singleton == null) {
			singleton = new RoomDB();
		}
		return singleton;
	}
	
    /**
     * reading form file
     */
	public HashMap<String,Room> populate() throws IOException{
		// read String from text file
		ArrayList<String> stringArray = (ArrayList<String>) read(fileName);
        // ArrayList<String> stringArray = new ArrayList<String>();
        // while(sc.hasNextLine()) stringArray.add(sc.nextLine());
		HashMap<String,Room> alr = new HashMap<String,Room>() ;// to store new data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
                String id =  star.nextToken().trim();
                String roomType = star.nextToken().trim();
                Integer isWifiEnabled = Integer.parseInt(star.nextToken().trim());
                Integer isSmokingAllowed = Integer.parseInt(star.nextToken().trim());
                RoomStatus status = Room.parseStatus(star.nextToken().trim());     
				// add to Rooms list
				alr.put(id, RoomData.MakeRoom(id, roomType, isWifiEnabled, isSmokingAllowed, status));
			}
			return alr ;
	}

	/**
	 * method to convert hashmap to string to save
	 */
	public void save(HashMap<String,Room> rooms) throws IOException {
			ArrayList<String> alw = new ArrayList<String>() ;// to store Professors data
	        for(Room room : rooms.values()) {
	            StringBuilder st =  new StringBuilder() ;
	            st.append(room.getRoomNumber().trim());
	            st.append(SEPARATOR);
	            st.append(room.getRoomType().trim());
	            st.append(SEPARATOR);
	            st.append(room.getWifi());
	            st.append(SEPARATOR);
	            st.append(room.getSmoking());
	            st.append(SEPARATOR);
	            st.append(Room.parseStatus(room.getStatus()));
	            st.append(SEPARATOR);
	            alw.add(st.toString()) ;
	        }
			write(fileName,alw);
	}

}