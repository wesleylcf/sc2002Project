package cico;

import guest.*;
import hrps.*;
import payment.*;
import room.*;
import exception.*;
import reservation.*;
import systemTime.*;
import roomsvc.*;

import java.util.Scanner;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;

/**
Check in Model class which makes use of CheckIn Data class
@author Wesley Lim
@version 1.0
@since 2022-04-17
*/
public class CheckInModel {
    private Scanner sc = HRPS.sc;
    private GuestModel guestModel;
    private RoomModel roomModel;
    private OrderModel orderModel;
    
    /**
     * ReservationModel is aggregated with roomModel and guestModel, which it needs when creating a reservation
     * @param roomModel The Model used to deal with Rooms
     * @param guestModel The Model used to deal with Guests 
     * @param orderModel The Model used to deal with orders
     */
    public CheckInModel( GuestModel guestModel, RoomModel roomModel, OrderModel orderModel) {    
        this.guestModel = guestModel;
        this.roomModel = roomModel;
        this.orderModel = orderModel;
    }
    
    /**
     * Initializes checkin menu
     * @throws InvalidInputException 
     */
    public void checkIn() throws InvalidInputException {
        CheckInView.printCheckInMenu();
        int option = InputValidator.validateIntRange(0, 2, "Check in menu option", sc.nextInt());
        sc.nextLine();
        while(option!=0) {
            switch(option) {
                case 1:
                try{
                    walkInCheckIn();
                } catch(Exception e) {
                   ExceptionHandler.handleReservationException(e);
           
                }
                    break;
                case 2:
                	try {
                		reservationCheckIn();
                	} catch(Exception e) {
                		ExceptionHandler.handleReservationException(e);
                	}
                	break;
                default:
                	break;
                    
            }
            CheckInView.printCheckInMenu();
            option = InputValidator.validateIntRange(0, 2, "Check in menu option", sc.nextInt());
        }
    }

    /**
     * Simulates checking-in via walk-in
     */
    private void walkInCheckIn() throws InvalidRoomTypeInteger, NoVacantRoomException, NoGuestFoundException, InvalidDateException, InvalidDateException, InvalidCICOException, ParseException, InputMismatchException, InvalidInputException, InvalidRoomTypeInteger{
        
        System.out.printf("Book a room(walk-in) :\n");
        ReservationView.printRoomTypeMenu();
        int choice = InputValidator.validateIntRange(1, 5, "Room type option", sc.nextInt()); 
        sc.nextLine();
        String roomType = Reservation.getRoomType(choice);
        String roomId = roomModel.getVacantRoom(roomType);
        System.out.printf("Enter total number of guests:\n");
        Integer numGuests = InputValidator.validateNumGuests(sc.nextInt()); 
        sc.nextLine();

        // implementation is that first guest is the payee
        System.out.printf("Enter Payee's details:\n");
        String payeeId = String.valueOf(guestModel.createGuest());

        //let reservationId be name + roomId since there cannot be (non-distinct name,roomId) pair
        String id = payeeId + roomId; 

        ArrayList<String> guestIDs = new ArrayList<String>();
        guestIDs.add(payeeId);

        String billInfo = guestModel.search(payeeId).getCCNumber();
        for(int i=0; i<numGuests-1; i++) {
            System.out.printf("Enter guest %d details:\n",i+1);
            String guestId = String.valueOf(guestModel.createGuest());
            guestIDs.add(guestId);
        }

        System.out.printf("Enter check-in date <dd-MM-yyyy>:\n");
        String dateString = InputValidator.validateDate(sc.nextLine());
        Date checkIn = SystemTime.sdformat.parse(dateString);

        System.out.printf("Enter check-out date <dd-MM-yyyy>:\n");
        dateString = InputValidator.validateDate(sc.nextLine());
        Date checkOut = SystemTime.sdformat.parse(dateString);
        
        InputValidator.validateCICO(checkIn, checkOut);
       
        RoomStatus roomStatus = RoomStatus.OCCUPIED;
        roomModel.getRoom(roomId).setStatus(roomStatus);
        
        ReservationStatus status = ReservationStatus.CHECKED_IN;
        Reservation rsv = new Reservation(id, numGuests, guestIDs, roomId, billInfo,checkIn, checkOut, status);
        HRPS.checkInData.create(rsv);
    }

    /**
     * Simulates checking-in via reservation
     */
    public void reservationCheckIn() {
        try{
            System.out.printf("Enter reservation ID <payeeId+roomId>:\n");
            String id = sc.nextLine();
            Reservation rsv = HRPS.reservationData.read(id);
            if(SystemTime.compare(rsv.getCheckIn())!=0){
            	System.out.printf("Check-in date for reservation %s is not today! (%s VS %s)\n", rsv.getId(), SystemTime.parseDate(rsv.getCheckIn()), SystemTime.parseDate(HRPS.time.getDate()));
            }
            HRPS.checkInData.create(rsv);
            rsv.setStatus(ReservationStatus.CHECKED_IN);
            String roomId = rsv.getRoomId();
            int res = HRPS.roomData.updateStatus(roomId,RoomStatus.OCCUPIED);
            
        } catch(Exception e) {
            ExceptionHandler.handleReservationException(e);
        }
        
    }
    
    /**
     * Prints all checkIns
     */
    public void printCheckIns() {
    	ArrayList<Booking> checkIns = HRPS.checkInData.readAll();
    	if(checkIns.isEmpty()) System.out.printf("No checked-in bookings\n");
    	for(Booking bk : checkIns) {
    			System.out.printf("-----START OF BOOKING-----\n");
    	        System.out.printf("Booking ID: %s\n", bk.getId());
    	        System.out.printf("Guests:\n");
    	
    	        for( String guestId : bk.getGuestIDs()) {
    	            Guest g;
    	            g = guestModel.search(guestId);
    	            System.out.printf("----- Guest -----\n");
    	            g.print();
    	        }
    	        //print using reservation.room.getDetail
    	        System.out.printf("Check-in/Checkout date: %s/%s\n",SystemTime.sdformat.format(bk.getCheckIn()), SystemTime.sdformat.format(bk.getCheckOut()));
    	        System.out.printf("-----END OF BOOKING-----\n");
    	        System.out.println();
    	}
    }
    
    /**
     * Function to handle any checkouts&payment, and pull bookings from waiting list when SystemTime advances
     * Assumes that people on the waitlist always approve converting to a reservation
     */
    public void onUpdateTime() {
    	try {
    		updateCheckOuts();
    		updateCheckIns();
            
    	} catch(Exception e) {
    		ExceptionHandler.handleReservationException(e);
    	}
        
    }
    
    /**
     * Iterate trough array of checkout and check who is checking out today
     * For those that are checking out, perform payment for these bookings
     */
    private void updateCheckOuts() throws NoReservationFoundException {
    	System.out.printf("Updated system time to %s\n", SystemTime.sdformat.format(HRPS.time.getDate()));
        System.out.printf("Checking if any bookings are checking out today:\n");
        //On Date change check if any Checked-in guests need to check out
        ArrayList<Booking> checkOuts = HRPS.checkInData.getCheckOuts(HRPS.time.getDate());
        if(checkOuts.isEmpty()) {
        	System.out.println("No bookings to checkout!");
        	return;
        }
        for(Booking co : checkOuts) {
            System.out.printf("%s checkout is today, %s\n",co.getId(), SystemTime.sdformat.format(co.getCheckOut()));
            String roomId = co.getRoomId();
            String roomType = HRPS.roomData.getRoom(roomId).getRoomType();
            PaymentController paymentController = new PaymentController(orderModel);
            Payment p = new Payment(co,guestModel, roomModel);
            paymentController.printSummary(p);
            paymentController.makePayment(p);
            paymentController.printBillInvoice(p);
            //after pay check wait list before setting to occupied. 
            // Assumes waitlist guest automatically accepts and confirms reservation.
            String reseravtionId = HRPS.waitList.getFirst(roomType); 

            if(reseravtionId != null) {
                // if valid reservation exists in waitlist, update it to confirmed
                Reservation waitListBooking = HRPS.reservationData.read(reseravtionId);
                waitListBooking.setStatus(ReservationStatus.CONFIRMED);
                System.out.printf("reservation %s in waitList updated status to confirmed\n",waitListBooking.getId());
            } else {
                HRPS.roomData.getRoom(roomId).setStatus(RoomStatus.VACANT);
                System.out.printf("No reservation in waitlist for this room, freeing it...\n");
            }
            HRPS.checkInData.delete(co);
            HRPS.reservationData.delete(co.getId());
            for(String guestId : co.getGuestIDs()) {
            	HRPS.guestData.delete(Integer.valueOf(guestId));
            }
            
            HRPS.orderData.removeOrders(roomId);
        }
    }
    
    /**
     * Check through array of check ins and check for those checking in  today
     * Check if these check ins are expired and remove them
     * @throws NoReservationFoundException
     */
    private void updateCheckIns() throws NoReservationFoundException {
    	System.out.printf("Updating status of expired reservations\n");
        for(Reservation rsv : HRPS.reservationData.getCollection()){
            if(SystemTime.compare(rsv.getCheckIn())>=0 
            	&& rsv.getStatus() == ReservationStatus.CONFIRMED) {
            	
            	System.out.printf("Confirm that Reservation %s has passed the check-in time<Y/N> ?\n",rsv.getId());
            	if(HRPS.sc.nextLine().equals("N")) return;
                String roomId = rsv.getRoomId();
                String roomType = HRPS.roomData.getRoom(roomId).getRoomType();
                String reservationId = HRPS.waitList.getFirst(roomType);
                if(reservationId != null) {
                    // if valid reservation exists in waitlist, update it to confirmed
                    Reservation waitListBooking = HRPS.reservationData.read(reservationId);
                    waitListBooking.setStatus(ReservationStatus.CONFIRMED);
                    System.out.printf("reservation %s in waitList updated status to confirmed\n",waitListBooking.getId());
                } else {
                    HRPS.roomData.getRoom(roomId).setStatus(RoomStatus.VACANT);
                    System.out.printf("No reservation in waitlist for this room, freeing it...\n");
                    rsv.setStatus(ReservationStatus.EXPIRED);
                }
            }
        }
    }
}
