package hrps;

import java.util.Date;

import exception.*;
//import reservation.

/**
Input Validator class which if valid returns input else throws Input-related Exceptions
@author Wesley Lim
@version 1.0
@since 2022-04-17
*/
public class InputValidator {
	/*
	 * Maximum guest ID 480 since 48 rooms and 
	 */
	public static final Integer MAX_ROOMS =  48;
	public static final Integer MAX_GUESTS_PER_ROOM = 10;
	public static final Integer MAX_GUEST_ID = MAX_ROOMS * MAX_GUESTS_PER_ROOM;
    /**
    * Checks a date and if valid return it else throw InvalidDateException
    * @param date date to check validity
    * @return date if the date is valid
    */
    public static String validateDate(String date) throws InvalidDateException, InvalidInputException{
        //dd-mm-yyyy
        //0123456789
    	if(date.length()!=10 || isNumeric(date) || isAlpha(date)) throw new InvalidInputException("Date", "date should be <dd-mm-yyyy>");
        Integer month = Integer.parseInt(date.substring(3,5));
        Integer day = Integer.parseInt(date.substring(0,2));
        if(0<day && day<32 && 0<month && month<13) return date;
        throw new InvalidDateException();
    }

    /**
    * Checks if CheckOut date >= checkOut date
    * @param checkIn check in date
    * @param checkOut check out date
    */
    public static void validateCICO(Date checkIn, Date checkOut) throws InvalidCICOException {
        if(checkIn.compareTo(checkOut)> 0) {
            throw new InvalidCICOException();
        }
    }
    
    /**
     * Checks if number of guests less than 1 or more than MAX_GUESTS_PER_ROOM
     * @param numGuests number of guest inputted
     */
    public static Integer validateNumGuests(Integer numGuests) throws InvalidInputException{
        if(numGuests < 1) throw new InvalidInputException("Number of guests","number should be more than 0");
        if(numGuests > MAX_GUESTS_PER_ROOM) throw new InvalidInputException("Number of guests","number should not be more than 10");
        return numGuests;
    }
    

    /**
     * Checks if address is purely numeric
     * @param address address input
     */
    public static String validateAddress(String address) throws InvalidInputException{
        if(isNumeric(address)) throw new InvalidInputException("Address","Address should not be purely numeric");
        return address;
    }
    
    /**
     * Checks if credit card is purely numeric, length 16
     * @param cc credit card input
     */
    public static String validateCCNumber(String cc) throws InvalidInputException {
    	if (!cc.matches("[0-9]+")) throw new InvalidInputException("Credit card","Card Number can only be digits!");
    	if(cc.length() != 16) throw new InvalidInputException("Credit card","Card Number must have 16 digits!");
    	return cc;
    }
    
    /**
     * Checks if credit card is purely numeric, length 3
     * @param cvv CVV input
     */
    public static String validateCVV(String cvv) throws InvalidInputException{
    	if (!cvv.matches("[0-9]+")) {
            throw new InvalidInputException("CVV","CVV can only be digits!");
        }
        if (cvv.length() != 3) {
            throw new InvalidInputException("CVV","CVV must be 3 digits long!");
        }
        return cvv;
    }
    
    /**
     * Checks if an option is outside of Start and End for a context
     * @param start start value of range
     * @param end end value of range
     * @param context context of the validation e.g. menu item option
     * @param option option input
     */
    public static Integer validateIntRange(Integer start, Integer end, String context, Integer option) throws InvalidInputException{
    	if(option < start || option > end ) throw new InvalidInputException(context,String.format("input should be between %d and %d\n", start, end));
    	return option;
    }
    
    /**
     * Checks if guest is LT 0 or GT MAX_GUEST_ID
     * @param id guestId input
     */
    public static Integer validateGuestId(Integer id) throws InvalidInputException {
    	if(id<0) throw new InvalidInputException("Guest ID","ID should not be negative");
    	if(id>MAX_GUEST_ID) throw new InvalidInputException("Guest ID","ID should not be more than 480");
    	return id;
    }
    
    /**
     * Checks if year is numeric, length>3
     * @param year year input
     */
    public static String validateYear(String year) throws InvalidInputException {
    	if(!isNumeric(year) || year.length()<4) throw new InvalidInputException("Year","year should be numeric and has at least 4 characters");
    	return year;
    }
    
    /**
     * Checks if month is numeric, 0<month<13, length 2
     * @param month month input
     */
    public static String validateMonth(String month) throws InvalidInputException {
    	if(!isNumeric(month) || month.length()!=2 || Integer.parseInt(month)<1 || Integer.parseInt(month)>12) throw new InvalidInputException("Month","1<=month<=12");
    	return month;
    }
    
    /**
     * Checks if reservation Id is non=negative
     * @param id reservationId input
     */
    public static String validateReservationId(String id) throws InvalidInputException{
    	if(Integer.parseInt(id) < 0) throw new InvalidInputException("Reservation ID", "id should be >= 0");
    	return id;
    }
    
    /**
     * Checks if menu price is negative
     * @param price menuPrice input
     */
    public static Double validateMenuPrice(Double price) throws InvalidInputException{
    	if(price<0) throw new InvalidInputException("Dish Price","Price should not be negative\n");
    	return price;
    }
    
    /**
     * Checks if menu item name is purely numeric
     * @param name menuItemName input
     */
    public static String validateMenuName(String name) throws InvalidInputException{
    	if(isNumeric(name)) throw new InvalidInputException("Dish Name","Name should not be purely numeric\n");
    	return name;
    }
    
    /**
     * Checks if menu item description is purely numeric
     * @param description menuItemDescription input
     */
    public static String validateMenuDescription(String description) throws InvalidInputException{
    	if(isNumeric(description)) throw new InvalidInputException("Dish Description","Description should not be purely numeric\n");
    	return description;
    }
    
    /**
     * Checks if a string only has alphabets for a given context
     * @param context context for input validation
     * @param str string input
     */
    public static String validateString(String context, String str) throws InvalidInputException{
    	if(!isAlpha(str)) throw new InvalidInputException(context,"input should contain letters only\n");
    	return str;
    }
    
    /**
     * Checks if a string only has numbers for a given context
     * @param context context for input validation
     * @param str string input
     */
    public static String validateNumber(String context, String str) throws InvalidInputException{
    	if(!isNumeric(str)) throw new InvalidInputException(context,"input should contain numbers only\n");
    	return str;
    }
    
    public static String validateRoomNumber(String roomNumber) throws InvalidInputException {
    	if(!isNumeric(roomNumber)) throw new InvalidInputException("room number","input should contain numbers only\n");
    	if(Integer.parseInt(roomNumber)<0) throw new InvalidInputException("room number","input should be non-negative\n");
    	return roomNumber;
    }
    
    /**
     * Checks if a string only has numbers
     * @param str string input
     */
    private static boolean isNumeric(String str) { 
    	  try {  
    	    Double.parseDouble(str);  
    	    return true;
    	  } catch(NumberFormatException e){  
    	    return false;  
    	  }  
    	}
    
    /**
     * Checks if a string only has alphabets
     * @param str string input
     */
    private static boolean isAlpha(String str) {
    	return str.matches("[a-zA-Z]+");
    }
    
    public static Integer validateFloorNumber(Integer i) throws InvalidInputException {
    	if(i<2 || i>7) throw new InvalidInputException("Floor number","Number should be between 2 & 7!");
    	return i;
    }
    
    public static Integer validateUnitNumber(Integer i) throws InvalidInputException{
    	if(i<0 || i>99) throw new InvalidInputException("Unit number","Number should be between 0 & 99!");
    	return i;
    }
    
    
}


