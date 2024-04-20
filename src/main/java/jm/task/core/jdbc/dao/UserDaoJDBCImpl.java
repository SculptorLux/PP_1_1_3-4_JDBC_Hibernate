package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

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
        try (Statement statement = Util.createStatement()){
            statement.executeUpdate(createSql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public void dropUsersTable() {
        String dropSql = "DROP TABLE IF EXISTS db_users.users;";
        try (Statement statement = Util.createStatement()){
            statement.executeUpdate(dropSql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String insertSql = "INSERT INTO `db_users`.`users` (`user_name`, `user_lastname`, `user_age`) VALUES ('"
                + name + "', '"
                + lastName + "', "
                + age + ");";
        try (Statement statement = Util.createStatement()){
            statement.executeUpdate(insertSql);
            System.out.println("User с именем " + name + " Добавлен в базу данных");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String removeSql = "DELETE FROM `db_users`.`users` WHERE `id_user` = " + id;
        try (Statement statement = Util.createStatement()) {
            statement.executeUpdate(removeSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String querySQL = "SELECT * FROM users";
        try (Statement statement = Util.createStatement();
             ResultSet resultSet = statement.executeQuery(querySQL)) {
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
        try (Statement statement = Util.createStatement()){
            statement.executeUpdate(cleanSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
