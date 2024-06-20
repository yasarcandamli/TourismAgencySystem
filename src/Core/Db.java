package Core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    //Singleton Design Pattern
    private static Db instance = null;
    private Connection connection = null;
    private final String DB_URL = "jdbc:postgresql://localhost:5432/tourismagencysystem";
    private final String DB_USER = "postgres";
    private final String DB_PASSWORD = "yasarcan";

    private Db() { //private -> because don't want to be accessed from another classes
        try {
            this.connection = DriverManager.getConnection(
                    this.DB_URL,
                    this.DB_USER,
                    this.DB_PASSWORD
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() { //just getter method, don't want to be changed
        return connection;
    }

    //If the instance is null or closed, create a new instance and return an object from the Db class and call the getConnection method on this object
    public static Connection getInstance() {
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new Db();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return instance.getConnection();
    }
}
