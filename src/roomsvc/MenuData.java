package roomsvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
* MenuData class which holds an ArrayList Data Structure to support creating, updating and removing MenuItem.
* @author Lee Alessandro
* @version 1.0
* @since 2022-04-17
*/
public class MenuData{
	
	/**
	 * ArrayList of MenuItem with private visibility to allow access from MenuModel class.
	 */
	private ArrayList<MenuItem> menuList = new ArrayList<>(); 
	
	/**
	 * Constructor which reads from menuList.txt database to initialise base menu items.
	 */
	public MenuData(){
	    //Creating a standard set of menu
		try{
			menuList = MenuDB.getInstance().populate();
		}catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}
	
	/**
	 * Method to create a new MenuItem.
	 * @param name			Name of new MenuItem entered by staff
	 * @param description	Description of new MenuItem entered by staff
	 * @param price			Price of new MenuItem entered by staff
	 */
	public void create(String name, String description, double price) {
		menuList.add(new MenuItem(name, description, price));
	}

	/**
	 * Method to update existing MenuItem.
	 * @param identifier	S/N of MenuItem entered by staff
	 * @param name			Name of new MenuItem entered by staff
	 * @param description	Description of new MenuItem entered by staff
	 * @param price			Price of new MenuItem entered by staff
	 */
    public void update(int identifier, String name, String description, double price){
        menuList.get(identifier).setName(name);
        menuList.get(identifier).setDescription(description);
        menuList.get(identifier).setPrice(price);
		System.out.println("Successfully updated");
    }
    
	/**
	 * Method to remove existing MenuItem.
	 * @param identifier	S/N of MenuItem entered by staff
	 */
    public void remove(int identifier) {
    	menuList.remove(identifier);
   		System.out.println("Successfully removed");
    }
    
    /**
     * gets array of menu list
     * @return array of menu list
     */
	public ArrayList<MenuItem> getMenuList(){
		return menuList;
	}
   
}
