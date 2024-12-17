package util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import dao.BillDAO;
import dao.BookDao;
import dao.CategoryDAO;
import dao.UserDao;
import model.User;

public class GenNewId {
	private static final Logger LOGGER = Logger.getLogger(GenNewId.class.getName());

	private static final UserDao userDao = new UserDao();
	private static final BookDao bookDao = new BookDao();
	private static final CategoryDAO categoryDao = new CategoryDAO();
	private static final BillDAO billDao = new BillDAO();

	// Private constructor to prevent instantiation
	private GenNewId() {
		throw new IllegalStateException("Utility class");
	}

	public static void generateNewIds() throws ClassNotFoundException, IOException {
		int newUserId = userDao.getAll().size() + 1;
		int newBookId = bookDao.getAll().size() + 1;
		int newCategoryId = categoryDao.getAll().size() + 1;
		int newBillId = billDao.getAll().size() + 1;

		LOGGER.log(Level.INFO, "New user id: {0}", newUserId);
		LOGGER.log(Level.INFO, "New book id: {0}", newBookId);
		LOGGER.log(Level.INFO, "New category id: {0}", newCategoryId);
		LOGGER.log(Level.INFO, "New bill id: {0}", newBillId);
	}

	// Static methods to get individual new IDs
	public static int getNewUserId() throws ClassNotFoundException, IOException {
		int size = userDao.getAll().size();
		if(size == 0) {
			return 1;
		}
		return userDao.getAll().get(userDao.getAll().size() - 1).getId() + 1;
	}

	public static int getNewBookId() throws ClassNotFoundException, IOException {
		int size = bookDao.getAll().size();
		if(size == 0) {
			return 1;
		}
		
		return bookDao.getAll().get(bookDao.getAll().size() - 1).getId() + 1;
	}

	public static int getNewCategoryId() throws ClassNotFoundException, IOException {
		int size = categoryDao.getAll().size();
		if(size == 0) {
			return 1;
		}
		return categoryDao.getAll().get(categoryDao.getAll().size() - 1).getId() + 1;
	}

	public static int getNewBillId() throws ClassNotFoundException, IOException {
		int size = billDao.getAll().size();
		if(size == 0) {
			return 1;
		}
		return ((User) billDao.getAll().get(billDao.getAll().size() - 1)).getId() + 1;
	}
}