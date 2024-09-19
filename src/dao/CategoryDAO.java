package dao;

import java.io.IOException;
import java.util.List;
import java.util.Locale.Category;
import java.util.function.Predicate;

import util.FileConnector;

public  class CategoryDAO implements DAO<Category>{
	private final String FILE_PATH = "/src/db/categories.bin";
	private final FileConnector<Category> fileConnector = new FileConnector<Category>();
	@Override
	public Category get(Predicate<Category> predicate) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> getAll() throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Category t) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Category t) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Category category) throws ClassNotFoundException, IOException {
		
		return false;
	}

	

}
