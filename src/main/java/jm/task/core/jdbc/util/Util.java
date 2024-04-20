package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/db_users";

    private final static String USERNAME = "root";

    private final static String PASSWORD = "Super268413975!";


    public static void getConnection() throws SQLException {

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            if (!connection.isClosed()) {

                System.out.println("We are connected!");

            }

        } catch (SQLException e) {

            System.out.println("there is no connection... Exception!");

        }
    }

    public static Statement createStatement() throws SQLException {

        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        return connection.createStatement();

    }
}
