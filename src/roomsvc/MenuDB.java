package roomsvc;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

import database.DataBase;
import database.ListDataBase;

/**
* Menu DB control the reading and writing of the menuList.txt
* Can only be instanciated once
* @author Lee Alessandro
* @version 1.0
* @since 2022-04-17
*/
public class MenuDB extends DataBase implements ListDataBase<MenuItem> {
	public static final String SEPARATOR = "|";
	private final String fileName = "menuList.txt";
	private static MenuDB singleton = null;
	
	/**
	 * Constructor method of MenuDB
	 * Singleton design pattern
	 * This class can only be instantiated once
	 * @return 
	 */
	public static MenuDB getInstance() {
		if(singleton == null) {
			singleton = new MenuDB();
		}
		return singleton;
	}
	
    /**
     * reads through the text file and converts it in to a an array
     * @return an array of menu items
     */
	public ArrayList<MenuItem> populate() throws IOException {
		// read String from text file
		ArrayList<String> stringArray = (ArrayList<String>)read(fileName);
		ArrayList<MenuItem> menuList = new ArrayList<>(); //to store MenuItem (BaseMenu) data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"

				String  name = star.nextToken().trim();	// first token
				String  description = star.nextToken().trim();	// second token
				double price = Double.parseDouble(star.nextToken().trim()); // third token
		
				// create MenuItem object from file data
				MenuItem menuItem = new MenuItem(name, description, price);
				// add to menuList
				menuList.add(menuItem) ;
			}
			return menuList ;
	}

	
	/**
	 * takes in an array of menu items to write to memory
	 * @param al array list of menu items to write into a txt file for saving
	 */
	public void save(List<MenuItem> al) throws IOException {
		List<String> alw = new ArrayList<String>() ;// to store MenuItem data

        for (int i = 0 ; i < al.size() ; i++) {
				MenuItem menuItem = (MenuItem)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(menuItem.getName().trim());
				st.append(SEPARATOR);
				st.append(menuItem.getDescription().trim());
				st.append(SEPARATOR);
				st.append(Double.toString(menuItem.getPrice()));
				alw.add(st.toString()) ;
		}
		write(fileName,alw);
	}

}