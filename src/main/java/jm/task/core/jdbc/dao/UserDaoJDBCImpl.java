package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;
    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void createUsersTable() {
        String createSql = "CREATE TABLE IF NOT EXISTS `db_users`.`users` (\n" +
                "  `id_user` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `user_name` VARCHAR(45) NOT NULL,\n" +
                "  `user_lastname` VARCHAR(45) NOT NULL,\n" +
                "  `user_age` INT(3) NOT NULL,\n" +
                "  PRIMARY KEY (`id_user`),\n" +
                "  UNIQUE INDEX `id_user_UNIQUE` (`id_user` ASC) VISIBLE);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(createSql)){
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public void dropUsersTable() {
        String dropSql = "DROP TABLE IF EXISTS db_users.users;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(dropSql)){
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String insertSql = "INSERT INTO `db_users`.`users` (`user_name`, `user_lastname`, `user_age`) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String removeSql = "DELETE FROM `db_users`.`users` WHERE `id_user` = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(removeSql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String querySQL = "SELECT id_user, user_name, user_lastName, user_age FROM users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id_user"));
                user.setName(resultSet.getString("user_name"));
                user.setLastName(resultSet.getString("user_lastname"));
                user.setAge(resultSet.getByte("user_age"));
                users.add(user);
                System.out.println(user.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String cleanSql = "TRUNCATE TABLE `db_users`.`users`;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(cleanSql)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
