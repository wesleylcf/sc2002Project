package systemTime;
import java.util.Date;
import java.text.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Calendar;

import cico.*;
import hrps.HRPS;
import payment.*;
import reservation.*;
import room.*;


/**
* Holds the system time as a Date, which can be modified to set a reservation status to expired, or checkout
* bookings for guests which are already checked-in.
* @author Wesley Lim
* @version 1.0
* @since 2022-04-17
*/
public class SystemTime {
    public static Date date;
    public final static SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy");
    //private Scanner sc = HRPS.sc;
    CheckInModel checkInModel;

    /**
     * Constructor SystemTIme class which uses checkInModel
     * @param checkInModel Model used to handle checked-in bookings
     */
    public SystemTime(CheckInModel checkInModel) {
        date = new Date();
        this.checkInModel = checkInModel;
    }
    
    /**
     * Get the date 
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date
     * @param newDate new date(string) to set
     */
    public void setDate(String newDate) {
        try{
            Date date = sdformat.parse(newDate);
            SystemTime.date = date;
        } catch(ParseException e) {
            System.out.printf("Error setting date %s\n",newDate);
        }

        checkInModel.onUpdateTime();
    }
    
    /**
     * Sets the date
     * @param newDate new date(Date) to set
     */
    public void setDate(Date newDate) {
        date = newDate;
        checkInModel.onUpdateTime();
    }

    
    /**
     * parses a String date to a Date
     * @param dateString String date to parse to a Date
     * @return Date version of a dateString
     */
    static public Date parseDate(String dateString) {
        Date date;
        try{
            date = sdformat.parse(dateString);
            return date;
            
        } catch(ParseException e) {
            System.out.printf("Error parsing date %s\n",dateString);
            return null;
        }
    }

    /**
     * parses a Date date to a String
     * @param dateString Date date to parse to a String
     * @return String version of a Date
     */
    static public String parseDate(Date date) {
        return sdformat.format(date);
    }
    
    /**
     * compares a date with the system time
     * @param otherDate Date otherDate to compare to system time
     */
    public static int compare(Date otherDate) {
    	Calendar c1 = Calendar.getInstance();
    	c1.setTime(date);
    	
    	
    	Calendar c2 = Calendar.getInstance();
    	c2.setTime(otherDate);
    	
    	
        if (c1.get(Calendar.YEAR) != c2.get(Calendar.YEAR)) 
            return c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
        if (c1.get(Calendar.MONTH) != c2.get(Calendar.MONTH)) 
            return c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
        return c1.get(Calendar.DAY_OF_MONTH) - c2.get(Calendar.DAY_OF_MONTH);
      }
    
    /**
	 * calculate total number of days between 2 days
	 * @param startDate start date
	 * @param endDate end date
	 * @return number of days between 2 days
	 */
	public static int getDays(Date startDate, Date endDate) {
	    int daysdiff = 0;
	    long diff = endDate.getTime() - startDate.getTime();
	    long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
	    daysdiff = (int) diffDays;
	    return daysdiff;
	}
	
	/**
	 * calculate total number of days between 2 days
	 * @param startDate start date 
	 * @param endDate end date
	 * @return number of weekends between 2 days
	 */
	public static int getWeekdays(Date startDate, Date endDate) {
	    Calendar startCal = Calendar.getInstance();
	    startCal.setTime(startDate);        

	    Calendar endCal = Calendar.getInstance();
	    endCal.setTime(endDate);

	    int weekDays = 0;

	    //Return 0 if start and end are the same
	    if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
	        return 0;
	    }

	    if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
	        startCal.setTime(endDate);
	        endCal.setTime(startDate);
	    }

	    do {
	       //excluding start date
	        startCal.add(Calendar.DAY_OF_MONTH, 1);
	        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
	            ++weekDays;
	        }
	    } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date

	    return weekDays;
	}
}
