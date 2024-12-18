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

    // Phương thức kiểm tra trùng username hoặc email
    public boolean isUsernameOrEmailTaken(String username, String email) throws ClassNotFoundException, IOException {
        List<User> users = userDao.getAll(); // Lấy danh sách user
        return users.stream()
                    .anyMatch(user -> user.getUsername().equals(username) || user.getEmail().equals(email));
    }

    // Thêm user mới với kiểm tra username và email trước khi thêm
    public boolean addUser(User user) throws ClassNotFoundException, IOException {
        // Kiểm tra trùng username hoặc email
        if (isUsernameOrEmailTaken(user.getUsername(), user.getEmail())) {
            return false; // Nếu trùng trả về false
        }
        return userDao.add(user); // Gọi phương thức add của UserDao
    }

    // Lấy danh sách tất cả users
    public List<User> getAllUsers() throws ClassNotFoundException, IOException {
        return userDao.getAll();
    }

    // Kiểm tra email có tồn tại không
    public static boolean checkEmailExists(String email, UserDao userDao) throws ClassNotFoundException, IOException {
        // Lấy danh sách người dùng từ UserDao
        List<User> users = userDao.getAll();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
