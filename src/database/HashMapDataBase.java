package database;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import exception.InvalidStatusString;

/**
* interface for how hash map data bases should be made
* @author Lee Alessandro
* @version 1.0
* @since 2022-04-17
*/
public interface HashMapDataBase<T, U> extends IDataBase {
	
	public abstract HashMap populate() throws IOException, ParseException, InvalidStatusString;
	
	public abstract void save(HashMap<T, U> al) throws IOException;
	
}
