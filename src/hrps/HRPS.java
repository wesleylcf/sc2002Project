package hrps;


import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

//import ExceptionHandler;
//import HRPSView;
import cico.*;
import guest.*;
import payment.PaymentController;
import reservation.*;
import room.*;
import roomsvc.*;
import systemTime.*;

/**
* Entry for the program. First populates local app state(Data classes) using DB classes. Then routes to the respective
* Controller classes which use the appropriate Model classes to implement app logic.
* Inherits directly from Room
* @author Wesley Lim
* @version 1.0
* @since 2022-04-17
*/
public class HRPS {
    static public Scanner sc = new Scanner(System.in);
    static public SystemTime time;
    static public MenuData menuData;
    static public OrderData orderData;
    static public RoomData roomData; 
    static public GuestData guestData;
    static public WaitList waitList;
    static public ReservationData reservationData;
    static public CheckInData checkInData;;
     
    

    /**
     * Entry point to HRPS
     * @param arg
     */
    static public void main(String[] arg) {
    try{
    	System.out.println("Initializing App state...");
    	menuData = new MenuData();
    	orderData = new OrderData();
    	roomData = new RoomData();
    	guestData = new GuestData();
    	waitList = new WaitList();
    	reservationData = new ReservationData();
    	checkInData = new CheckInData(reservationData);
    	
        
    } catch(Exception e){   
        ExceptionHandler.handleFileIOException(e);
    }
    System.out.println("App state initialized successfully...\n");
    GuestModel guestModel = new GuestModel(guestData);
    RoomModel roomModel = new RoomModel(roomData);
    ReservationModel reservationModel = new ReservationModel(roomModel, guestModel);
    ReservationController reservationController = new ReservationController(reservationModel);
    OrderModel orderModel = new OrderModel(orderData);
    CheckInModel checkInModel = new CheckInModel(guestModel, roomModel, orderModel);
    time = new SystemTime(checkInModel);
    
    CheckInController CICOController = new CheckInController(checkInModel);
    SystemTimeController systemTimeController = new SystemTimeController();
    MenuModel menuModel = new MenuModel(menuData);
    
    RoomServiceController roomServiceController = new RoomServiceController(menuData, menuModel, orderData, orderModel, roomModel );
    GuestController guestController = new GuestController(guestModel);
    RoomController roomController = new RoomController(roomModel);
    
    

    
    int option  = 1;
    
    do {
    	try {
    		HRPSView.printMainMenu();
    		option = InputValidator.validateIntRange(0, 6, "HRPS Menu option", sc.nextInt());
        	sc.nextLine();
        	switch(option) {
    	        case 1:
    	            reservationController.init();
    	            break;
    	        case 2:
    	            CICOController.init();
    	            break;
    	        case 3:
    	            systemTimeController.init();
    	            break;
    	        case 4:
    	            roomServiceController.init();
    	        case 5:
    	        	guestController.init();
    	        	break;
    	        case 6:
    	        	roomController.init();
    	        default:
    	            break;
        	}
    	} catch(Exception e) {
    		ExceptionHandler.handleMenuException(e);
    	}
    	
    } while(option != 0);
    try {
    	ReservationDB.getInstance().save(reservationData.readAll());
    	RoomDB.getInstance().save(roomData.getRooms());
    	MenuDB.getInstance().save(menuData.getMenuList());
    	OrderDB.getInstance().save(orderData.getOrdersHashMap());
    	GuestDB.getInstance().save(guestData.getGuestHashMap());
    } catch(IOException e) {
    	ExceptionHandler.handleFileIOException(e);
    }
    
    System.out.println();
    System.out.println("App state written to DB successfully...");

    sc.close();

    }
}
