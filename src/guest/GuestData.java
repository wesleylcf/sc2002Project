package guest;

import java.util.Scanner;
import hrps.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;

/**
* guest data manager
* @author Lee Bo Hua
* @version 1.0
* @since 2022-04-17
*/
public class GuestData {
    static public HashMap<Integer, Guest> guests = new HashMap<>();
    Scanner sc = HRPS.sc;

    /**
     * populate guest data
     */
    public GuestData(){
        // Load guestdata from txt.file
        try{
            guests = GuestDB.getInstance().populate();
        } catch(IOException e) {
            System.out.printf("Error reading file");
        }

    }

    /** 
    * CREATE OPERATIONS 
    */
    public void createGuest(int id, String firstName, String lastName, String country, String nationality, String contact, Gender gender, Identity identity, String address, CreditCard cc){
        guests.put( id, new Guest(id, firstName, lastName, country, nationality, contact, gender, identity, address, cc));
    }


    
    /**
     *READ OPERATIONS
     */
    public String fullName(int id){
        // Basically converts fn and ln to fullname
        return(guests.get(id).getFirstName() + " " + guests.get(id).getLastName());
    }

    /**
     * check if guest id exist
     * @param id
     * @return true for id exist
     */
    public boolean exist(int id){
        return(guests.containsKey(id));
    }

    /**
     * getter for name for a given id
     * @param id id of guest
     * @return
     */
    public String getName(int id){
       return(guests.get(id).getFirstName() + " "  + guests.get(id).getLastName());
    }

    /**
     * getter method for guest country for an id
     * @param id guest id
     * @return string for guest country
     */
    public String getCountry(int id) {
		return guests.get(id).getCountry();
	}
    
    /**
     * getter method for guest information
     * @param id guest id
     * @return
     */
	public String getNationality(int id) {
		return guests.get(id).getNationality();
	}
	
	/**
	 * getter method for guest contact for given ID
	 * @param id
	 * @return
	 */
	public String getContact(int id) {
		return guests.get(id).getContact();
	}

	/**
	 * getter method for guest gender for given id
	 * @param id geust id
	 * @return
	 */
	public String getGender(int id) {
		return guests.get(id).getGender().toString();

	}

	/**
	 * getter for getting guest identity
	 * @param id geust id
	 * @return
	 */
	public String getIdentity(int id){
		return guests.get(id).getIdentity().toString();
	}

	/**
	 * getter method for guest address
	 */
    public String getAddress(int id){
		return guests.get(id).getAddress();
	}
    /**
     * getter method for guest card number
     * @param id
     * @return
     */
    public String getCardNumber(int id) {
    	return guests.get(id).getCardNumber();
    }
    /**
     * getter method got card cvv
     * @param id
     * @return
     */
    public String getCardCVV(int id) {
    	return guests.get(id).getCVV();
    }
    /**
     * getter method for card expiry
     * @param id
     * @return
     */
    public String getCardExpiryMM(int id) {
    	return guests.get(id).getExpiryMM();
    }
    /**
     * getter method card exp
     * @param id
     * @return
     */
    public String getCardExpiryYYYY(int id) {
    	return guests.get(id).getExpiryYYYY();
    }
    
    /**
     * getter method for number of guest
     * @return
     */
    public int guestCount(){
        return(guests.size());
    }
    
        
    /**
     * setter for guest name for given id
     * @param id
     * @param firstName
     * @param lastName
     */
    public void updateName(int id, String firstName, String lastName){
        guests.get(id).setFirstName(firstName);
        guests.get(id).setLastName(lastName);
    }

    /**
     * setter method for guest country for given id
     * @param id
     * @param country
     */
    public void updateCountry(int id, String country){
        guests.get(id).setCountry(country);
    }

    /**
     * setter for guest nationality
     * @param id
     * @param nationality
     */
    public void updateNationality(int id, String nationality){
        guests.get(id).setNationality(nationality);
    }

    /**
     * setter method for contact
     * @param id
     * @param contact
     */
    public void updateContact(int id, String contact){
        guests.get(id).setContact(contact);
    }

    /**
     * setter method for guest gender
     * @param id
     * @param gender
     */
    public void updateGender(int id, String gender){
        Gender g = Gender.valueOf(gender);
        guests.get(id).setGender(g);
    }

    /**
     * setter method for guest identity
     * @param id
     * @param identity
     */
    public void updateIdentity(int id, String identity){
        Identity i = Identity.valueOf(identity);
        guests.get(id).setIdentity(i);
    }

    /**
     * setter method for address for given guest id
     * @param id
     * @param address
     */
    public void updateAddress(int id, String address){
		guests.get(id).setAddress(address);
	}

    /**
     * setter method to update credit card for given guest id
     */
    public void updateCC(int id, String cardNumber, String CVV, String mm, String yyyy){
        guests.get(id).updateCardNumber(cardNumber);
        guests.get(id).updateMM(mm);
        guests.get(id).updateYYYY(yyyy);
        guests.get(id).updateCVV(CVV);
        
    }
    
    /**
     * delete guest for a given guest id
     * @param guestId
     */
    public void delete(Integer guestId) {
    	guests.remove(guestId);
    }
    
    /**
     * return a hash map of a given guest
     * @return
     */
    public static HashMap getGuestHashMap(){
        // returns the hashmap
        return guests;
            
    }
    
    /**
     * getter method for guest for given id
     * @param id
     * @return
     */
    public Guest readOne(Integer id) {
    	return guests.get(id);
    }
}
