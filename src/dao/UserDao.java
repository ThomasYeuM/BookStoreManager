package dao;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import util.FileConnector;
import model.User;


public class UserDao implements DAO<User>{
	String FILE_PATH = "src/db/users.txt";
	private final FileConnector<User> fileConnector = new FileConnector<User>();
	public boolean isUserExist(String username, String email) throws ClassNotFoundException, IOException {
		List<User> users = fileConnector.readFromFile(FILE_PATH);
		return users.stream().anyMatch(user -> user.getUsername().equals(username) || user.getEmail().equals(email));
	}

	@Override
	public List<User> getAll() throws ClassNotFoundException, IOException {
		return fileConnector.readFromFile(FILE_PATH);
	}

	@Override
	public boolean add(User userAdd) throws ClassNotFoundException, IOException {
		List<User> users = getAll();
		for (User u : users ) {
			if ( u.getUsername() == userAdd.getUsername() || u.getEmail() == userAdd.getEmail() ) {
				return false;
			}
		}
		fileConnector.appendObject(FILE_PATH, userAdd);
		return true;
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
		if ("admin".equalsIgnoreCase(deleteUser.getRole())) {
	        return false;
	    }
		List<User> users = getAll();
		users.removeIf(User -> User.getId() == deleteUser.getId());
		fileConnector.writeToFile(FILE_PATH, users);
		return true;
		
	}

	@Override
	public  User get(Predicate<User> predicate) throws ClassNotFoundException, IOException {
		List<User> users = fileConnector.readFromFile(FILE_PATH);
		for (User user : users) {
			if (predicate.test(user)) {
				return user;
			}
		}
		return null;
	}
	public boolean updatePassword(String email, String newPassword) throws ClassNotFoundException, IOException {
        List<User> users = fileConnector.readFromFile(FILE_PATH);

        User userToUpdate = null;
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                user.setPassword(newPassword);  // Cập nhật mật khẩu mới
                userToUpdate = user;  // Lưu đối tượng người dùng đã được cập nhật
                break;
            }
        }

        if (userToUpdate != null) {
            return fileConnector.updateObject(FILE_PATH, userToUpdate, user -> user.getEmail().equals(email));
        }

        return false;
    }
}
