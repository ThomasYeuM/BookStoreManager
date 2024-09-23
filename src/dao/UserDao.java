package dao;

import java.io.IOException;
import java.util.List;

import util.FileConnector;
import model.User;


public class UserDao implements DAO<User>{
	String FILE_PATH = "db/users.txt";
	private final FileConnector<User> fileConnector = new FileConnector<User>();

	@Override
	public List<User> getAll() throws ClassNotFoundException, IOException {
		return fileConnector.readFromFile(FILE_PATH);
	}

	@Override
	public boolean add(User userAdd) throws ClassNotFoundException, IOException {
		List<User> users = getAll();
		for (User u : users ) {
			if ( u.getId() == userAdd.getId() ) {
				return false;
			}
		}
		fileConnector.appendObject(FILE_PATH, userAdd);
		return false;
	}

	@Override
	public boolean update(User userUpdate) throws ClassNotFoundException, IOException {
		List<User> users = getAll();
		boolean isUpdate = false;
		for (int i = 0 ; i < users.size(); i++) {
			if ( users.get(i).getId() == userUpdate.getId()) {
				users.set(i, userUpdate);
				isUpdate = true;
			}
		}
		if ( isUpdate ) {
			fileConnector.writeToFile(FILE_PATH, users);			
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(User deleteUser) throws ClassNotFoundException, IOException {
		List<User> users = getAll();
		users.removeIf(User -> User.getId() == deleteUser.getId());
		fileConnector.writeToFile(FILE_PATH, users);
		return true;
		
	}

}
