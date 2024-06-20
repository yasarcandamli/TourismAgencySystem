package Dao;

import Core.Db;
import Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    private final Connection connection;

    public UserDao() {
        this.connection = Db.getInstance();
    }

    //DTO: Data Transfer Object
    public User match(ResultSet resultSet) throws SQLException {
        User object = new User();
        object.setUserId(resultSet.getInt("user_id"));
        object.setUserName(resultSet.getString("user_name"));
        object.setUserPassword(resultSet.getString("user_password"));
        object.setUserType(User.UserType.valueOf(resultSet.getString("user_type"))); //Get data belonging to the enum structure
        return object;
    }

    //Querying whether the user is present
    public User findByLogin(String userName, String userPassword) {
        User object = null;
        String query = "SELECT * FROM public.users WHERE user_name = ? AND user_password = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPassword);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                object = this.match(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    //Query that returns all users
    public ArrayList<User> findAll() {
        return this.selectByQuery("SELECT * FROM public.users ORDER BY user_id ASC;");
    }

    public ArrayList<User> selectByQuery(String query) {
        ArrayList<User> userList = new ArrayList<>();
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()) {
                userList.add(this.match(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public boolean add(User user) {
        String query = "INSERT INTO public.users " +
                "(" +
                "user_name, " +
                "user_password, " +
                "user_type" +
                ")" +
                " VALUES (?, ?, ?);";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getUserPassword());
            preparedStatement.setString(3, user.getUserType().toString());

            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(User user) {
        String query = "UPDATE public.users SET " +
                "user_name = ?, " +
                "user_password = ?, " +
                "user_type = ? " +
                "WHERE user_id = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getUserPassword());
            preparedStatement.setString(3, user.getUserType().toString());
            preparedStatement.setInt(4, user.getUserId());

            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int userId) {
        String query = "DELETE FROM public.users WHERE user_id = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public User getById(int selectUserId) {
        User object = null;
        String query = "SELECT * FROM public.users WHERE user_id = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, selectUserId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) object = this.match(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }
}
