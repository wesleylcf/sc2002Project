package reservation;

import guest.*;
import hrps.HRPS;
import hrps.*;
import room.*;
import exception.*;

import systemTime.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.text.*;
import java.util.Date;
import java.util.InputMismatchException;


/**
Reservation Model class which uses ReservationData according to specs of the assignment
@author Wesley Lim
@version 1.0
@since 2021-04-17
*/
public class ReservationModel{
    private RoomModel roomModel;
    private GuestModel guestModel;
    private Scanner sc = HRPS.sc;

    /**
    * ReservationModel is aggregated with roomModel and guestModel, which it needs when creating a reservation
    * @param roomModel The Model used to deal with Rooms
    * @param guestModel The Model used to deal with Guests 
    * @param data ReservationData used in ReservationModel
    */
    public ReservationModel(RoomModel rm, GuestModel gm){
        roomModel = rm;
        guestModel = gm;
        
    }
    /**
    * Creates a new reservation by calling Reservation Constructor
    * @param roomModel The Model used to check if there are vacant rooms, before assigning a reservation a room
    * @param guestModel The Model used to add a Guest 
    */
    public void createReservation() throws NoGuestFoundException, InvalidNumberException, ParseException, InvalidDateException, InvalidCICOException, InvalidInputException, NoVacantRoomException, InputMismatchException{
            ReservationStatus status = ReservationStatus.CONFIRMED;
            System.out.printf("Create a reservation:\n");
            ReservationView.printRoomTypeMenu();
            
            int choice = InputValidator.validateIntRange(1, 5, "Room type option", sc.nextInt()); 
            sc.nextLine();
            String roomType = Reservation.getRoomType(choice);
            
            System.out.printf("Enter check-in date <dd-MM-yyyy>:\n");
            String dateString = InputValidator.validateDate(sc.nextLine());
            Date checkIn = SystemTime.sdformat.parse(dateString);

            System.out.printf("Enter check-out date <dd-MM-yyyy>:\n");
            dateString = InputValidator.validateDate(sc.nextLine());
            Date checkOut = SystemTime.sdformat.parse(dateString);
            
            InputValidator.validateCICO(checkIn, checkOut);
            
            String roomId;
            String id;
            roomId = roomModel.getVacantRoom(roomType);
            
            System.out.printf("Enter total number of guests:\n");
            Integer numGuests = InputValidator.validateNumGuests(sc.nextInt()); 
            sc.nextLine();
            
         // implementation is that first guest is the payee
            System.out.printf("Enter Payee's details:\n");
            String payeeId = String.valueOf(guestModel.createGuest());

            ArrayList<String> guestIDs = new ArrayList<String>();
            guestIDs.add(payeeId);

            String billInfo = guestModel.search(payeeId).getCCNumber();
            for(int i=0; i<numGuests-1; i++) {
                System.out.printf("Enter guest %d details:\n",i+1);
                String guestId = String.valueOf(guestModel.createGuest());
                guestIDs.add(guestId);
            }
            
            
            if(roomId != null) {
                 HRPS.roomData.getRoom(roomId).setStatus(RoomStatus.RESERVED);
            }
            else roomId = ReservationModel.getVacantRoom(roomType, checkIn);
            //let reservationId be name + roomId since there cannot be (non-distinct name,roomId) pair
            id = payeeId + roomId; 
            if(roomId == null) {
                status = ReservationStatus.IN_WAITLIST;
                id = payeeId + "WAITLIST";
                // HRPS.waitList.add(roomType,id);
            }
            
            System.out.println();
            Reservation rsv = new Reservation(id, numGuests, guestIDs, roomId, billInfo,checkIn, checkOut, status);
            HRPS.reservationData.create(rsv);
            rsv.onCreateReservation(guestModel);
      
    }

    
    /**
    * Removes a reservation
     * @throws InvalidInputException 
    */
    public void removeReservation() throws InvalidInputException {
        System.out.printf("Enter reservationId to remove:\n");
        String reqId = InputValidator.validateReservationId(sc.nextLine());
        HRPS.reservationData.delete(reqId);
    }

    /**
    * Prints all reservations
    */
    public void printReservations() {
        ReservationView.printReservations(roomModel);
    }

    /**
    * Updates the id of a reservation
    * @param rsv Reservation to update
    * @param oldId oldId used to access the old Reservation
    * @param newId newId used to update the Reservation
    */
   public void updateId(Reservation rsv, String oldId, String newId) throws NoReservationFoundException{
        
        rsv.setId(newId);
        Reservation value = HRPS.reservationData.read(oldId);
        HRPS.reservationData.delete(oldId);
        HRPS.reservationData.create(value);
    }

    /**
    * Updates the guests of a reservation
    * @param rsv Reservation to update
    */
   public void updateGuests(Reservation rsv) throws NoGuestFoundException, InvalidInputException{
        // limit to deleting and adding guest
        ArrayList<String> guestIDs = rsv.getGuestIDs();
        System.out.printf("----- GUESTS -----\n");
        for(String id : guestIDs) {
            guestModel.printGuest(Integer.parseInt(id));
        }

        ReservationView.printUpdateGuestMenu();
        int option = InputValidator.validateIntRange(1, 2, "update guests option", sc.nextInt());
        sc.nextLine();
        if(option == 1){
            System.out.printf("Enter guest's details:\n");
            String newGuestId = String.valueOf(guestModel.createGuest());
            guestIDs.add(newGuestId);
            rsv.incrementNumGuests();
        }
        else if (option == 2) {
            ReservationView.printReservation(rsv, roomModel);
            System.out.printf("Choose guest to remove:\n");
            int index = InputValidator.validateIntRange(1,rsv.getNumGuests(),"Guest removal option",sc.nextInt());
            sc.nextLine();
            guestIDs.remove(index);
            rsv.decrementNumGuests();
        }
    } 
    
    /**
    * Updates the RoomType of a reservation
    * @param rsv Reservation to update
     * @throws InvalidNumberException 
     * @throws InvalidInputException 
    */
   public void updateRoomType(Reservation rsv) throws NoReservationFoundException, InvalidRoomTypeInteger, NoVacantRoomException, InvalidNumberException, InvalidInputException{
        ReservationView.printRoomTypeMenu();
        int choice = InputValidator.validateIntRange(1, 5, "Room type option", sc.nextInt()); 
        String roomType = Reservation.getRoomType(choice);
        String oldRoomId = rsv.getRoomId();
        String newRoomId;
        newRoomId = roomModel.getVacantRoom(roomType);
        if(newRoomId == null) {
            System.out.printf("No rooms available for given room type, returning to main menu\n");
            return;
        }
        
        rsv.setRoomId(newRoomId);
        //Since fix rsv_id = payeeId + roomId, also update rsv_id
        String oldId = rsv.getId();
        String newId = oldId.substring(0,oldId.length()-4)+newRoomId;
        updateId(rsv,oldId,newId);
        roomModel.getRoom(oldRoomId).setStatus(RoomStatus.VACANT);
        roomModel.getRoom(newRoomId).setStatus(RoomStatus.RESERVED);
        
   	}

     /**
    * Updates the BillInfo of a reservation
    * @param rsv Reservation to update
     * @throws InvalidInputException 
    */
   public void updateBillingInfo(Reservation rsv) throws InvalidInputException {
        System.out.printf("Enter updated billing info:\n");
        String newInfo = InputValidator.validateCCNumber(sc.nextLine());
        rsv.setBillInfo(newInfo);
    }

     /**
    * Updates the CheckIn Date of a reservation
    * @param rsv Reservation to update
     * @throws InvalidInputException 
     * @throws InvalidDateException 
    */
   public void updateCheckIn(Reservation rsv) throws InvalidDateException, InvalidInputException {
        System.out.printf("Enter new check-in date <dd-mm-yyyy>:\n");
        String input = InputValidator.validateDate(sc.nextLine());
        Date newDate;
        try{
            newDate = SystemTime.sdformat.parse(input);
        } catch( ParseException e) {
            System.out.printf("Invalid Date! returning to main menu...\n");
            return;
        }
        rsv.setCheckIn(newDate);
       
    }

    /**
    * Updates the CheckOut Date of a reservation
    * @param rsv Reservation to update
     * @throws InvalidInputException 
     * @throws InvalidDateException 
    */
   public void updateCheckOut(Reservation rsv) throws InvalidDateException, InvalidInputException {
        System.out.printf("Enter new check-out date <dd-mm-yyyy>:\n");
        String input = InputValidator.validateDate(sc.nextLine());
        Date newDate;
        try{
            newDate = SystemTime.sdformat.parse(input);
        } catch( ParseException e) {
            System.out.printf("Invalid Date! returning to main menu...\n");
            return;
        }
        rsv.setCheckOut(newDate);
       
    }
   
    /**
    * Updates the Status of a reservation
    * @param rsv Reservation to update
     * @throws InvalidInputException 
    */
    public void updateStatus(Reservation rsv) throws InvalidInputException {
        ReservationView.printUpdateStatusMenu();
        int option = InputValidator.validateIntRange(0, 4, "Update status option", sc.nextInt());
        sc.nextLine();
        if( option != 0 ) {
            if(option == ReservationStatus.CONFIRMED.ordinal()+1) rsv.setStatus(ReservationStatus.CONFIRMED);
            else if(option == ReservationStatus.IN_WAITLIST.ordinal()+1) rsv.setStatus(ReservationStatus.IN_WAITLIST);
            else if(option == ReservationStatus.CHECKED_IN.ordinal()+1) rsv.setStatus(ReservationStatus.CHECKED_IN);
            else rsv.setStatus(ReservationStatus.EXPIRED);
        }
    }
    
    /**
     * gets a room with status VACANT, or will be VACANT by asker's check in for a given roomType
     * @param roomType the RoomType for which to look for a VACANT room
     * @param checkIn the checkIn date of the asker
     */

    public static String getVacantRoom(String roomType, Date checkIn) throws NoVacantRoomException{
        for(Reservation rsv : HRPS.reservationData.getCollection()){
            String roomId = rsv.getRoomId();
            String otherRoomType = HRPS.roomData.getRoom(roomId).getRoomType();
            if(otherRoomType.equals(roomType) && checkIn.compareTo(rsv.getCheckOut())>=0) {
            	System.out.println("Vacant room exists for the given checkIn date, even though there are currently no vacant rooms");
                return rsv.getRoomId();
                
            }
        }
        throw new NoVacantRoomException();
    }
  

}