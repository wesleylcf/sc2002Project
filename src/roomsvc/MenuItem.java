package roomsvc;
import java.io.Serializable;

/**
* MenuItem class to hold the details of each room service MenuItem, such as its name, description and price.
* @author Lee Alessandro
* @version 1.0
* @since 2022-04-17
*/
public class MenuItem implements Serializable {
	
	/**
	* Name of room service MenuItem
	*/
	private String name;

	/**
	* Description of room service MenuItem and how it is prepared
	*/
	private String description;

	/**
	* Price of room service MenuItem
	*/
	private double price;

	/**
	 * Constructor which facilitates creating of a new room service MenuItem.
	 * @param name			Name of new MenuItem entered by staff
	 * @param description	Description of new MenuItem entered by staff
	 * @param price			Price of new MenuItem entered by staff
	 */
	public MenuItem(String name, String description, double price)
	{
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	/**
	 * Set method to update room service MenuItem's name.
	 * @param name			Name of new MenuItem entered by staff
	 */
	public void setName(String name){this.name = name;}

	/**
	 * Set method to update room service MenuItem's description.
	 * @param description	Description of new MenuItem entered by staff
	 */
	public void setDescription(String description){this.description = description;}

	/**
	 * Set method to update room service MenuItem's price.
	 * @param price			Price of new MenuItem entered by staff
	 */
	public void setPrice(double price){this.price = price;}

	/**
	 * Get method to retrieve room service MenuItem's name.
	 * @param name			Name of new MenuItem entered by staff
	 */
	public String getName(){return name;}

	/**
	 * Get method to retrieve room service MenuItem's description.
	 * @param description	Description of new MenuItem entered by staff
	 */
	public String getDescription(){return description;}

	/**
	 * Get method to retrieve room service MenuItem's price.
	 * @param price			Price of new MenuItem entered by staff
	 */
	public double getPrice(){return price;}

}
