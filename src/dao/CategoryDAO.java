package dao;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import model.Category;
import model.User;
import util.FileConnector;
import util.GetFilePath;

public class CategoryDAO implements DAO<Category> {



	private final String FILE_PATH = GetFilePath.getAbsoluteFilePath()+"/src/db/categories.txt";

	private final FileConnector<Category> fileConnector = new FileConnector<Category>();


	private final String FILE_PATH = GetFilePath.getAbsoluteFilePath() + "/src/db/categories.txt";
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
				categories.set(i, categoryUpdate);
				break;
			}
		}
		fileConnector.writeToFile(FILE_PATH, categories);
		return true;
	}

	@Override
	public boolean delete(Category categoryDelete) throws ClassNotFoundException, IOException {
		List<Category> categories = getAll();
		categories.removeIf(category -> category.getId() == categoryDelete.getId());
		fileConnector.writeToFile(FILE_PATH, categories);
		return true;
	}

	@Override
	public Category get(Predicate<Category> predicate) throws ClassNotFoundException, IOException {
		List<Category> categories = fileConnector.readFromFile(FILE_PATH);
		for (Category category : categories) {
			if (predicate.test(category)) {
				return category;
			}
		}
		return null;
	}

}
