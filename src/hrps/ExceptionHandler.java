package hrps;

import java.io.IOException;
import java.text.*;
import java.util.InputMismatchException;

/**
Exception Handler class which outputs the appropriate Error message based on the type of Exception.
Since we want to keep the system running and try again when an error happens
@author Wesley Lim
@version 1.0
@since 2022-04-17
*/
public class ExceptionHandler {
    /**
    * Outputs the appropriate error message for Exceptions thrown by Reservation-related classes
    */
    static public void handleReservationException (Exception e ) {
        // if ( e instanceof InvalidCICOException) System.out.printf("\nERROR: %s\n", e.getMessage());
        // else if ( e instanceof NoVacantRoomException) System.out.printf("\nERROR: %s\n", e.getMessage());
        // else if ( e instanceof NoGuestFoundException) System.out.printf("\nERROR: %s\n", e.getMessage());
        // else if ( e instanceof ParseException || e instanceof InvalidDateException) System.out.printf("\nERROR: Invalid date format!\n");
        // else if ( e instanceof NoReservationFoundException) System.out.printf("\nERROR: %s\n", e.getMessage());
        if( e instanceof InputMismatchException) {
        	System.out.printf("\n ERROR: Input type mismatch.\n");
        	HRPS.sc.nextLine(); //Consumes the invalid token
        }
        else if (e instanceof ParseException) System.out.printf("\n ERROR: Invalid date");
        // else System.out.printf("\n ERROR: %s\n",e.getMessage());
        else System.out.printf("\nERROR: %s\n", e.getMessage());
//        e.printStackTrace();
        System.out.printf("Returning to menu...\n\n");
        
    }

    static public void handleFileIOException( Exception e) {
        if(e instanceof IOException) System.out.printf("\n ERROR: File could not be read/written");
    }
    
    static public void handleGuestException(Exception e) {
    	if( e instanceof InputMismatchException) {
    		System.out.printf("\n ERROR: Input type mismatch.\n");
    		HRPS.sc.next(); //Consumes the invalid token
    	}
        else if (e instanceof ParseException) System.out.printf("\n ERROR: Invalid date");
        // else System.out.printf("\n ERROR: %s\n",e.getMessage());
        else System.out.printf("\nERROR: %s\n", e.getMessage());
//        e.printStackTrace();
        System.out.printf("Returning to menu...\n\n");
    }
    
    static public void handleRoomServiceException(Exception e) {
    	if(e instanceof InputMismatchException) {
    		System.out.println("\n ERROR: Input type mismatch.\n");
    		HRPS.sc.nextLine(); //Consumes the invalid token
    	}
    	else System.out.printf("\n ERROR: %s\n", e.getMessage());
    	System.out.printf("Trying again...\n\n");
    }
    
    static public void handleRoomException(Exception e) {
       	if(e instanceof InputMismatchException) {
    		System.out.println("\n ERROR: Input type mismatch.\n");
    		HRPS.sc.nextLine(); //Consumes the invalid token
    	}
    	else System.out.printf("\n ERROR: %s\n", e.getMessage());
    	System.out.printf("Trying again...\n\n");
    }
    
    static public void handleMenuException(Exception e) {
    	if(e instanceof InputMismatchException) {
    		System.out.println("\n ERROR: Input type mismatch.\n");
    		HRPS.sc.nextLine(); //Consumes the invalid token
    	}
    	else System.out.printf("\n ERROR: %s\n", e.getMessage());
    	System.out.printf("Trying again...\n\n");
    }

}
