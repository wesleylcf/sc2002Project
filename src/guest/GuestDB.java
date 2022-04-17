package guest;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.StringTokenizer;

import database.DataBase;
import database.HashMapDataBase;


import java.util.HashMap;

/**
* manage the reading and writing of guest data in guest data base
* @author Lee Bo Hua
* @version 1.0
* @since 2022-04-17
*/
public class GuestDB extends DataBase implements HashMapDataBase<Integer, Guest>{
	public static final String SEPARATOR = "|";
	private final String fileName = "guest.txt";
	
	
	private static GuestDB singleton = null;
	
	/**
	 * singleton constructor
	 * @return guestDB object
	 */
	public static GuestDB getInstance() {
		if(singleton == null) {
			singleton = new GuestDB();
		}
		return singleton;
	}
	
    /**
     * read all guest data from text file into guest 
     * return a hash map of guests
     * @return hash map of guest
     */
	public HashMap<Integer,Guest> populate() throws IOException{
    // read String from text file
    ArrayList<String> stringArray = (ArrayList<String>) read(fileName);
    // to store new data
    HashMap<Integer,Guest> loadGuests = new HashMap<>();

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
                int id =  Integer.parseInt(star.nextToken().trim());
                String firstName = star.nextToken().trim();
                String lastName = star.nextToken().trim();
                String country = star.nextToken().trim();
                String nationality = star.nextToken().trim();
                String contact = star.nextToken().trim();
                Gender gender = Guest.parseGenderType(star.nextToken().trim());
                Identity identity = Guest.parseIdentityType(star.nextToken().trim());
                String address  = star.nextToken().trim();
                String ccNumber =  star.nextToken().trim();
                String ccCVV =  star.nextToken().trim();
                String ccExpiryDateMM =  star.nextToken().trim();
                String ccExpiryDateYYYY =  star.nextToken().trim();
				// create guest object from file data
				//Guest guest = new Guest(id, firstName, lastName, country, nationality, contact, gender, identity, address, new CreditCard(ccNumber, ccCVV, ccExpiryDateMM, ccExpiryDateYYYY));
				// add to Guests list
				loadGuests.put(id,new Guest(id, firstName, lastName, country, nationality, contact, gender, identity, address, new CreditCard(ccNumber, ccCVV, ccExpiryDateMM, ccExpiryDateYYYY)));
			}
			return loadGuests ;
	}

	/**
	 * writes guest hash map into a text file for saving
	 * @param guests hashmap of guest
	 */
	public void save(HashMap<Integer,Guest> guests) throws IOException {
	    //System.out.println("STARTING saveGuest:........");
			ArrayList<String> stringArray = new ArrayList<String>() ;// to store Guest data to update guest.txt
	        for(Guest guest : guests.values()) {
	            //System.out.println("STARTING saveGuest:........");
	            StringBuilder st =  new StringBuilder() ;
	            st.append(String.valueOf(guest.getId())); // INT
	            st.append(SEPARATOR);
	            st.append(guest.getFirstName().trim()); // STR
	            st.append(SEPARATOR);
	            st.append(guest.getLastName().trim()); // STR
	            st.append(SEPARATOR);
	            st.append(guest.getCountry().trim()); // STR
	            st.append(SEPARATOR);
	            st.append(guest.getNationality().trim()); // STR
	            st.append(SEPARATOR);
	            st.append(guest.getContact().trim()); // STR
	            st.append(SEPARATOR);
	            st.append(Guest.parseGenderType(guest.getGender())); // ENUM Guest
	            st.append(SEPARATOR);
	            st.append(Guest.parseIdentityType(guest.getIdentity())); // ENUM Identity
	            st.append(SEPARATOR);
	            st.append(guest.getAddress().trim()); // STR for cc
	            st.append(SEPARATOR);
	            st.append(guest.getCardNumber().trim()); // STR for cc
	            st.append(SEPARATOR);
	            st.append(guest.getCVV().trim()); // STR for cc
	            st.append(SEPARATOR);
	            st.append(guest.getExpiryMM().trim()); // STR for cc
	            st.append(SEPARATOR);
	            st.append(guest.getExpiryYYYY().trim()); // STR for cc
	            stringArray.add(st.toString());
	        }
				write(fileName,stringArray);
		}
	
	  /**
	   * writes to file to save
	   * @param fileName
	   * @param data
	   * @throws IOException
	   */
	  public static void write(String fileName, ArrayList<String> data) throws IOException  {
	    PrintWriter out = new PrintWriter(new FileWriter(fileName));
	
	    try {
			for (int i =0; i < data.size() ; i++) {
	      		out.println((String)data.get(i));
			}
	    }
	    finally {
	      out.close();
	    }
  }

}