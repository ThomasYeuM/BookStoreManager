package controller;

import java.io.IOException;
import java.util.List;

import dao.UserDao;
import model.User;

public class UserController {
	private UserDao userDao;
	
	public UserController(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public List<User> getAllUsers() throws ClassNotFoundException, IOException {
		return userDao.getAll();
	}
	
	public int getTotalUser() throws ClassNotFoundException, IOException {
		return userDao.getAll().size();
	}
	
	public boolean addUser(User newUser) throws ClassNotFoundException, IOException {
		if(userDao.add(newUser)) {
			return true;
		};
		return false;
	}
}
