package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl(Util.getConnection());

        userDao.createUsersTable();
        userDao.saveUser("Sasha1", "SashaLastName1", (byte) 23);
        userDao.saveUser("Lolia2", "KoliaLastName2", (byte) 22);
        userDao.saveUser("Nikita3", "NikitaLastName3", (byte) 31);
        userDao.saveUser("Bob4", "BobLastName4", (byte) 30);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
