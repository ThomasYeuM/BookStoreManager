package dao;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

public class BookDao implements DAO<T> {
	
	
	@Override
	public List<T> getAll() throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(T t) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(T t) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(T t) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T get(Predicate<T> predicate) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
