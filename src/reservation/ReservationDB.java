package reservation;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

import database.DataBase;
import database.HashMapDataBase;
import exception.InvalidStatusString;
import systemTime.SystemTime;


/**
 * ReservationDB is in charge of reading from .txt to and writing to local app state at the start,
 * and writing from local app state to .txt at the end.
 @author Wesley Lim
 @version 1.0
 @since 2022-04-17
 */
public class ReservationDB extends DataBase implements HashMapDataBase<String, Reservation>{
	public static final String SEPARATOR = "|";
	public static final String fileName = "reservations.txt";
	
	private static ReservationDB singleton = null;
	
	/**
	 * Creates an instance of ReservationDB once, and only returns this instance(Singleton design pattern 
	 */
	public static ReservationDB getInstance() {
		if(singleton == null) {
			singleton = new ReservationDB();
		}
		return singleton;
	}

    /**
     * Returns HashMap of reservation to populate local app state
     */
	public HashMap<String,Reservation> populate() throws IOException, ParseException, InvalidStatusString {
		// read String from text file
        Scanner sc = new Scanner(new FileInputStream(fileName));
		ArrayList<String> stringArray = (ArrayList<String>) read(fileName);
        // ArrayList<String> stringArray = new ArrayList<String>();
        // while(sc.hasNextLine()) stringArray.add(sc.nextLine());
        sc.close();
		HashMap<String,Reservation> alr = new HashMap<String,Reservation>() ;// to store new data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
                String id =  star.nextToken().trim();
                int numGuests = Integer.parseInt(star.nextToken().trim());
                ArrayList<String> guestIDs = new ArrayList<String>();
                for(int g=0; g<numGuests; g++) {
                    guestIDs.add(star.nextToken().trim());
                }
                String roomId =  star.nextToken().trim();
                String billInfo =  star.nextToken().trim();
                Date checkIn = SystemTime.sdformat.parse(star.nextToken().trim());
                Date checkOut = SystemTime.sdformat.parse(star.nextToken().trim());      
                ReservationStatus status = Reservation.parseStatus(star.nextToken().trim());
				// create Professor object from file data
				Reservation rsv = new Reservation(id,numGuests,guestIDs,roomId,billInfo,checkIn, checkOut,status);
				// add to Reservations list
				alr.put(id,rsv);
			}
			return alr ;
	}

	/**
     * Saves hashmap of reservations to .txt file
     */
	public void save(HashMap<String,Reservation> reservations) throws IOException {
			ArrayList<String> alw = new ArrayList<String>() ;
	        for(Reservation rsv : reservations.values()) {
	            StringBuilder st =  new StringBuilder() ;
	            st.append(rsv.getId().trim());
	            st.append(SEPARATOR);
	            st.append(rsv.getNumGuests());
	            st.append(SEPARATOR);
	            for(int g=0; g<rsv.getNumGuests(); g++) {
	                st.append(rsv.getGuestIDs().get(g));
	                st.append(SEPARATOR);
	            }
	            st.append(rsv.getRoomId().trim());
	            st.append(SEPARATOR);
	            st.append(rsv.getBillInfo().trim());
	            st.append(SEPARATOR);
	            st.append(SystemTime.sdformat.format(rsv.getCheckIn()));
	            st.append(SEPARATOR);
	            st.append(SystemTime.sdformat.format(rsv.getCheckOut()));
	            st.append(SEPARATOR);
	            st.append(Reservation.parseStatus(rsv.getStatus()));
	            alw.add(st.toString()) ;
	        }
				write(fileName,alw);
	}
}

