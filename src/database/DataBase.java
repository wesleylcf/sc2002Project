package database;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
* 
* @author Lee Alessandro
* @version 1.0
* @since 2022-04-17
*/
public class DataBase {

	/**
	 * write the contents of the string into file of a given name
	 * @param fileName name of file to store in
	 * @param data store given string data in file
	 * @throws IOException
	 */
	  public void write(String fileName, List<String> data) throws IOException  {
	    PrintWriter out = new PrintWriter(new FileWriter(fileName));

	    try {
			for (int i =0; i < data.size() ; i++) {
	      		out.println((String)data.get(i));
			}
	    }
	    finally {
	      out.close();
	    }
	  }

	  /**
	   * Read the contents of the given file.
	   * @param fileName name of file to read from
	   * @return values of text file as a string
	   */
	  public List<String> read(String fileName) throws IOException {
		List<String> data = new ArrayList<String>() ;
	    Scanner scanner = new Scanner(new FileInputStream(fileName));
	    try {
	      while (scanner.hasNextLine()){
	        data.add(scanner.nextLine());
	      }
	    }
	    finally{
	      scanner.close();
	    }
	    return data;
	  }
}
