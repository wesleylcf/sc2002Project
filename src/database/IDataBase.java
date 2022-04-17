package database;

import java.io.IOException;
import java.util.List;

/**
* interface on how database should be made
* @author Lee Alessandro
* @version 1.0
* @since 2022-04-17
*/
public interface IDataBase {
	
	public abstract void write(String fileName, List<String> data) throws IOException;
	
	public abstract List<String> read(String fileName) throws IOException;
	
}
