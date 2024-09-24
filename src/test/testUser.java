package test;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.UserDao;
import model.Category;
import model.User;

public class testUser {
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		UserDao userDao = new UserDao();
		
		User testUser = new User(1, "admin", "nguyentuan20042207@gmail.com", "admin", true);
		
//		userDao.add(testUser);
		
		
		List<User> userList = userDao.getAll();
		
		for (User user : userList) {
	        System.out.println(user.toString());
	    }
		
		System.out.println(userList.size());
		
		
	} 
	
	
	
	
	
}
