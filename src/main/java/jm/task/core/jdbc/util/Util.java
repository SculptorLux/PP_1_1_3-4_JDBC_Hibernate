package jm.task.core.jdbc.util;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

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

    public static class HbFactoryConfiguration {
        private static HbFactoryConfiguration hbFactoryConfiguration;
        private SessionFactory sessionFactory;
        //create session factory

        private HbFactoryConfiguration() {
            //add configuration
            Configuration configuration = new Configuration();

            //add property
            Properties properties = new Properties();

            //add already created hibernate file to properties in current thread
            try {
                properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("src.main.resources.hibernate.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //add properties to configure
            configuration.setProperties(properties);

            //add annotated class configure
            configuration.addAnnotatedClass(User.class);

            //build session factory
            sessionFactory = configuration.buildSessionFactory();
        }
        public static HbFactoryConfiguration getInstance() {
            return (hbFactoryConfiguration == null) ? new HbFactoryConfiguration() : hbFactoryConfiguration;
        }
        public Session getSession() {
            return sessionFactory.openSession();
            //open session and return it
        }
        //this class implemented singleton pattern

    }

}
