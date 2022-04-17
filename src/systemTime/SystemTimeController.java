package systemTime;
import java.util.Scanner;

import hrps.*;
import room.*;


import java.util.ArrayList;

import reservation.*;
import room.*;
import exception.*;


/**
* Controller class for system time which routes to various options such as check time and update time
* @author Wesley Lim
* @version 1.0
* @since 2022-04-17
*/
public class SystemTimeController {
    private Scanner sc = HRPS.sc;
    
    /**
     * Initialize menu for system time
     * @throws InvalidInputException
     */
    public void init() throws InvalidInputException {
        SystemTimeView.printMainMenu();
        int option = InputValidator.validateIntRange(0, 2, "system time menu", sc.nextInt());
        sc.nextLine();
        while(option != 0) {
            switch(option) {
                case 1:
                    System.out.printf("%s\n",SystemTime.parseDate(HRPS.time.getDate()));
                    break;
                case 2:
                    System.out.printf("Enter a new date to update <dd-mm-yyyy>:\n");
                    String newDate = sc.nextLine();
                    HRPS.time.setDate(newDate);
                    break;
                    
                default:
                    break;
            }
            SystemTimeView.printMainMenu();
            option = InputValidator.validateIntRange(0, 2, "system time menu", sc.nextInt());
            sc.nextLine();
        }
    }
}


		
