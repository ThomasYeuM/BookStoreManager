package dao;

import java.io.IOException;
import java.util.List;
import model.Category;

import util.FileConnector;

public class CategoryDAO implements DAO<Category> {
	private final String FILE_PATH = "src/db/categories.bin";
	private final FileConnector<Category> fileConnector = new FileConnector<Category>();


	@Override
	public List<Category> getAll() throws ClassNotFoundException, IOException {
		return fileConnector.readFromFile(FILE_PATH);
	}

	@Override
	public boolean add(Category categoryAdd) throws ClassNotFoundException, IOException {
		List<Category> categories = fileConnector.readFromFile(FILE_PATH);
		categories.add(categoryAdd);
		fileConnector.writeToFile(FILE_PATH, categories);
		return true;
	}

	@Override
	public boolean update(Category categoryUpdate) throws ClassNotFoundException, IOException {
		List<Category> categories = fileConnector.readFromFile(FILE_PATH);
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getId() == categoryUpdate.getId()) {
				categories.set(i,categoryUpdate);
				break;
			}
		}
		fileConnector.writeToFile(FILE_PATH, categories);
		return true;
	}

	@Override
	public boolean delete(Category categoryDelete) throws ClassNotFoundException, IOException {
		List<Category> categories = fileConnector.readFromFile(FILE_PATH);
		boolean isDeleted = false;
		for (Category cate : categories) {
			if (cate.getId() == categoryDelete.getId()) {
				categories.remove(cate);
				isDeleted = true;
				break;
			}
		}
		if (isDeleted) {
			fileConnector.writeToFile(FILE_PATH, categories);
			return true;
		} else {
			return false;
		}
	}

}
