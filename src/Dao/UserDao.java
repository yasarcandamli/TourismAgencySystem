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
        object.setUserType(resultSet.getString("user_type"));
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
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM public.users;";
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
}
