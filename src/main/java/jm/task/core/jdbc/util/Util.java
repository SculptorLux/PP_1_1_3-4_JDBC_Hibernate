package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/db_users";

    private final static String USERNAME = "root";

    private final static String PASSWORD = "Super268413975!";


    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection to database");
        } catch (SQLException e) {
            System.out.println("Error of connection" + e.getMessage());
        }
        return connection;
    }
}
