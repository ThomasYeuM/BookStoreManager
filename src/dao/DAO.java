package dao;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

public interface DAO<T> {
	
	
	List<T> getAll() throws ClassNotFoundException, IOException;
	
	boolean add(T t) throws ClassNotFoundException, IOException;
	
	boolean update(T t) throws ClassNotFoundException, IOException;
	
	boolean delete(T t) throws ClassNotFoundException, IOException;
}
