package Business;

import Dao.UserDao;
import Entity.User;

public class UserManager {
    private final UserDao userDao;

    public UserManager() {
        this.userDao = new UserDao();
    }

    public User findByLogin(String userName, String userPassword) {
        return this.userDao.findByLogin(userName, userPassword);
    }
}
