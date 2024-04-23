package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Sasha1", "SashaLastName1", (byte) 23);
        userService.saveUser("Lolia2", "KoliaLastName2", (byte) 22);
        userService.saveUser("Nikita3", "NikitaLastName3", (byte) 31);
        userService.saveUser("Bob4", "BobLastName4", (byte) 30);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
