package database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
* interface for how hash map data bases should be made
* @author Lee Alessandro
* @version 1.0
* @since 2022-04-17
*/
public interface ListDataBase<T> extends IDataBase {

	public abstract ArrayList populate() throws IOException;
	
	public abstract void save(List<T> al) throws IOException;
	
}
