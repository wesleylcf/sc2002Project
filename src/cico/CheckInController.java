package cico;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import exception.InvalidInputException;
import hrps.ExceptionHandler;
import hrps.HRPS;
import hrps.InputValidator;

/**
Check in Controller class in charge of routing to various menus or functions in CheckinModel
@author Wesley Lim
@version 1.0
@since 2022-04-17
*/
public class CheckInController {
    private Scanner sc = HRPS.sc;
    CheckInModel checkInModel;
    
    /*
     * Assigns a checkInModel to checkInController
     * @param checkInModel checkInModel to aggregate with checkInController
     */
    public CheckInController(CheckInModel checkInModel) {
        this.checkInModel = checkInModel;
    }
    
    /**
     * User interface to interact with checked-in data or to check in
     * @throws InvalidInputException 
     */
    public void init() throws InvalidInputException {
        int option = 1;
        do {
        	try {
        		CheckInView.printMainMenu();
        		option = InputValidator.validateIntRange(0, 2, "Checkin main menu", sc.nextInt());
                sc.nextLine();
                switch(option) {
                case 1:
                	checkInModel.checkIn(); 
                    break; 
                case 2:
                	checkInModel.printCheckIns();
                	break;
                default:
                    break;
                } 	
        	}catch(Exception e) {
        		ExceptionHandler.handleReservationException(e);
        	}
            
        }while(option!=0);
       
    }
}
