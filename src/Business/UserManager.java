package Business;

import Core.Helper;
import Dao.UserDao;
import Entity.User;

import java.util.ArrayList;

public class UserManager {
    private final UserDao userDao;

    public UserManager() {
        this.userDao = new UserDao();
    }

    public User findByLogin(String userName, String userPassword) {
        return this.userDao.findByLogin(userName, userPassword);
    }

    public ArrayList<User> findAll() {
        return userDao.findAll();
    }

    public boolean save(User userSample) {
        if (userSample.getUserId() != 0) {
            Helper.showMessage("error");
        }
        return this.userDao.save(userSample);
    }

    public boolean update(User userSample) {
        if (this.getById(userSample.getUserId()) == null) {
            Helper.showMessage("notFound");
            return false;
        }
        return this.userDao.update(userSample);
    }

    public boolean delete(int userId) {
        if (this.getById(userId) == null) {
            Helper.showMessage("notFound");
            return false;
        }
        return this.userDao.delete(userId);
    }

    public User getById(int selectUserId) {
        return this.userDao.getById(selectUserId);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<User> userList) { //Create objects as many as the number of columns
        ArrayList<Object[]> userObjectList = new ArrayList<>();

        for (User object : userList) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = object.getUserId();
            rowObject[i++] = object.getUserName();
            rowObject[i++] = object.getUserPassword();
            rowObject[i++] = object.getUserType();
            userObjectList.add(rowObject);
        }
        return userObjectList;
    }

    public ArrayList<User> filterForTable(User.UserType userType) {
        String select = "SELECT * FROM public.users";
        ArrayList<String> whereList = new ArrayList<>();

        if (userType != null) {
            whereList.add("user_type = '" + userType.toString() + "'");
        }
        String whereString = String.join(" AND ", whereList);
        String query = select;
        if (!whereString.isEmpty()) {
            query += " WHERE " + whereString;
        }
        return this.userDao.selectByQuery(query);
    }
}
