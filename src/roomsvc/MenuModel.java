package roomsvc;

import java.util.InputMismatchException;

import hrps.ExceptionHandler;
import hrps.HRPS;
import hrps.InputValidator;

/**
* MenuModel class to take in staff inputs and call the respective methods in MenuData to
* support the creating, updating and removing of MenuItems.
* @author Lee Alessandro
* @version 1.0
* @since 2022-04-17
*/
public class MenuModel {
    
	/**
	* Local variable of type MenuData to assist in calling the methods to create, update and remove MenuItem.
	*/
    private MenuData menuData;
	/**
	* Defining the following variables to format Menu table.
      Variables declared as static protected are updated directly in Formatting class.
	*/
    static protected int topLeftRepeatMenu=0, topRightRepeatMenu=0, bottomRepeatMenu=0;
    static protected String formatHeaderMenu;
	/**
	* Defining the following variables to format Menu table.
      private String formatContentMenu is returned from Formatting class.
	*/
    private String formatContentMenu;
    
	/**
	* Defining the following private variables to be used in local methods.
	*/
	private int identifier, opt1=0;
	private double price; 
	private String name, description;
    
	/**
	 * Constructor.
	 * @param menu passed in as parameter to assign to local menuData of type MenuData.
	 */
    public MenuModel(MenuData menuData){ 
     	this.menuData = menuData;
    }

    /**
	 * Method to take in staff input to create new MenuItem.
	 */
    public void createMenuItem(){
		try {
	    	System.out.println("==Create==");
			System.out.println("Enter Dish Name: ");
			name = InputValidator.validateMenuName(HRPS.sc.nextLine());
			System.out.println("Enter Dish Description: ");
			description = InputValidator.validateMenuDescription(HRPS.sc.nextLine());
			System.out.println("Enter Dish Price: ");
			price = InputValidator.validateMenuPrice(HRPS.sc.nextDouble());
		   	menuData.create(name, description, price);
			this.printMenuItem();
		}
		catch(Exception e) {
			ExceptionHandler.handleRoomServiceException(e);
		}
    }
    
    /**
 	 * Method to take in staff input to update MenuItem.
 	 */
    public void updateMenuItem(){
    	try {
			System.out.println("==Update==");
			if(menuData.getMenuList().size()==0)
				System.out.println("Room Service Menu is empty. Unable to update.");
			else {
				System.out.println("Enter Dish S/N to modify: "); 
				identifier = InputValidator.validateIntRange(1, menuData.getMenuList().size(), "Enter Dish S/N", HRPS.sc.nextInt());
				identifier -= 1;
				HRPS.sc.nextLine();
				/*By default we retrieve the respective name, description & price
				  so that we can pass it into the argument should the staff not change it.
				*/
    			if(menuData.getMenuList().size()==0)
    				System.out.println("Room Service Menu is already empty. Unable to update.\n");
    			else {
					name = menuData.getMenuList().get(identifier).getName();
            		description = menuData.getMenuList().get(identifier).getDescription();
            		price = menuData.getMenuList().get(identifier).getPrice();
            		while(opt1!=4) {
            			try {
	            			RoomServiceView.printUpdateRoomServiceMenuItems();
	                		opt1 = InputValidator.validateIntRange(1, 4, "Enter Dish S/N to modify", HRPS.sc.nextInt());
	                		HRPS.sc.nextLine();
	                		switch(opt1) {
	                			case 1:
	                				System.out.println("Enter New Name: ");
	                    			name = InputValidator.validateMenuName(HRPS.sc.nextLine());
	                    			break;
	                			case 2:
	                    			System.out.println("Enter New Description: ");
	                    			description = InputValidator.validateMenuDescription(HRPS.sc.nextLine());
	                    			break;
	                			case 3:
	                    			System.out.println("Enter New Price: ");
	                    			price = InputValidator.validateMenuPrice(HRPS.sc.nextDouble());
	                    			break;
	                			case 4:
	                				System.out.println("==Exited Update==\n");
	                				break;
	                			default:
	                				break;
	                		}
	                		if(opt1==4) {
	                		  	menuData.update(identifier, name, description, price);
	                		}
                		}
            			catch(Exception e) {
            				ExceptionHandler.handleRoomServiceException(e);
            			}
            		}
            		opt1=0;
    			} 
    		} 
	   	}
		catch(Exception e) {
			ExceptionHandler.handleRoomServiceException(e);
		}
    }
    
    /**
  	 * Method to take in staff input to remove MenuItem.
  	 */
    public void removeMenuItem() {
		try{
			System.out.println("==Remove==");
    		if(menuData.getMenuList().size()==0)
    			System.out.println("Room Service Menu is already empty. Unable to remove.\n");
    		else {
        		System.out.println("Enter Dish S/N  to remove: ");
        		identifier = InputValidator.validateIntRange(1, menuData.getMenuList().size(), "Enter Dish S/N", HRPS.sc.nextInt());
        		identifier -= 1;
        		HRPS.sc.nextLine();
            	menuData.remove(identifier);
    		}
		}
		catch(Exception e) {
			ExceptionHandler.handleRoomServiceException(e);
		}
    }
    
    /**
  	 * Method to print existing MenuItem.
  	 */
    public void printMenuItem(){
    	if(menuData.getMenuList().size()==0) {
    		//Setting up fixed format to print top header, content and bottom header
	    	//Printing top header of menu: 
	    	System.out.printf("-".repeat(17)); 
	    	System.out.printf("Room Service Menu"); 
	    	System.out.printf("-".repeat(17)); 
	    	System.out.println();
	    	//Printing contents of menu:
	    	System.out.printf(" ".repeat(23)); 
    		System.out.printf("EMPTY"); 
    		System.out.printf(" ".repeat(23));
    		System.out.println();
	    	//Printing bottom header of menu:
	    	System.out.printf("-".repeat(51)); 
	    	System.out.println();
    	}
    	else {
		 	//Getting dynamic format to print top header, content and bottom header
    		formatContentMenu = Formatting.typeMenu(menuData.getMenuList());
	    	//Printing top header of menu
	    	System.out.printf("-".repeat(topLeftRepeatMenu));
	    	System.out.printf("Room Service Menu");
	    	System.out.printf("-".repeat(topRightRepeatMenu));
	    	System.out.println();
	    	//Printing header of contents
	    	System.out.printf(formatHeaderMenu, "S/N", "Dish", "Description", "Price");
	    	//Printing contents of menu
	    	int i=1;
	    	for(MenuItem menuItem : menuData.getMenuList()) {
	    		System.out.printf(formatContentMenu, i, menuItem.getName(), menuItem.getDescription(), menuItem.getPrice());
	    		i++;
	    	}
	    	//Printing bottom header of menu
	    	System.out.printf("-".repeat(bottomRepeatMenu));
	    	System.out.println();
    	}
    }
}   











    
