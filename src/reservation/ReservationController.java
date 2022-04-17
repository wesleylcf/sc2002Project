package reservation;

import java.util.Scanner;

import hrps.*;
import exception.*;

import java.util.InputMismatchException;

/**
Reservation Controller class in charge of routing to various menus or functions in ReservationModel
@author Wesley Lim
@version 1.0
@since 2022-04-17
*/
public class ReservationController {
    private Scanner sc = HRPS.sc;
    private ReservationModel model;

    /*
     * Assigns a reservationModel to this reservationController
     * @param reservationModel to aggregate with reservationModel
     */
    public ReservationController(ReservationModel model){
        this.model = model;
    }
    /**
    * User interface to interact with reservations(Create/update/remove/print)
    */
    public void init() {
        int option=1;
        System.out.printf("Currently modifying Reservations:\n");
        do {
        	try {
        		ReservationView.printMainMenu();
        		option = InputValidator.validateIntRange(0,4,"Reservation menu option",sc.nextInt()) ;           
        		sc.nextLine(); // since potentially read a string next;
          
                    switch(option) {
                        case 1:
                            model.createReservation();
                            break;
                        case 2:
                            updateReservationRoute();
                            break;
                        case 3:
                            model.removeReservation();
                            break;
                        case 4:
                            model.printReservations();
                            break;
                        default:
                            break;
                 
//                    ReservationView.printMainMenu();
//                    option = InputValidator.validateIntRange(1,4,"Reservation menu option",sc.nextInt()) ;   
//                    sc.nextLine(); // since potentially read a string next;
        	}
        	
            } catch(Exception e) {
            	ExceptionHandler.handleReservationException(e);
            }
        } while(option!=0);
        
    }

    /**
    * Update reservation UI which is one of the Routes in ReservationController.init()
    */
    private void updateReservationRoute() {
        
            try {
            	System.out.printf("Enter reservation id to update<payeeId+roomId> or '0' to exit:\n");
                String reservationId = InputValidator.validateReservationId(sc.nextLine());
                if(reservationId.equals("0")) return;
                System.out.printf("-----Update reservation-----\n");
                Reservation rsv = HRPS.reservationData.read(reservationId);
                ReservationView.printUpdateMenu();
                int option = InputValidator.validateIntRange(0, 7, "update reservation option", sc.nextInt()); 
                sc.nextLine(); // since we potentially read strings
                while(option != 0) {
                    switch(option) {
                        case 1:
                            System.out.printf("Enter new id <payeeId+roomId>:\n");
                            String newId = InputValidator.validateReservationId(sc.nextLine());
                            model.updateId(rsv, reservationId, newId);
                            break;
                        case 2:
                         model.updateGuests(rsv);
                            break;
                        case 3:
                         model.updateRoomType(rsv);
                            break;
                        case 4:
                         model.updateBillingInfo(rsv);
                            break;
                        case 5:
                         model.updateCheckIn(rsv);
                            break;
                        case 6:
                         model.updateCheckOut(rsv);
                            break;
                        case 7:
                         model.updateStatus(rsv);
                            break;
                        default:
                            return;
                    }
                    ReservationView.printUpdateMenu();
                    option = InputValidator.validateIntRange(0, 7, "update reservation option", sc.nextInt());
                    sc.nextLine();
                }
            } catch(Exception e) {
            	ExceptionHandler.handleReservationException(e);
            }
            //  catch( Exception e) {
            //     ExceptionHandler.handleReservationException(e);
            //     updateReservationRoute();
            // }
    }
}
