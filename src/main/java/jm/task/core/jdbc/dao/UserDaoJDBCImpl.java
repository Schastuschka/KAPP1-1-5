package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    private Connection connection = Util.getConnectionDB();

    public void createUsersTable() {
        try(Statement statement = connection.createStatement()){
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(" +
                        "ID BIGINT NOT NULL AUTO_INCREMENT," +
                        "NAME VARCHAR(100)," +
                        "LAST_NAME VARCHAR(100)," +
                        "AGE INT," +
                        "PRIMARY KEY (ID) )");
            }catch(SQLException err){
                err.printStackTrace();
            }
        }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preStat = connection.prepareStatement("INSERT INTO users (NAME, LAST_NAME, AGE) VALUES(?, ?, ?)")) {
            preStat.setString(1, name);
            preStat.setString(2, lastName);
            preStat.setByte(3, age);
            preStat.executeUpdate();
        } catch (SQLException err) {
            System.out.print("saveUser");
            err.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        try(PreparedStatement preStat = connection.prepareStatement("DELETE FROM users WHERE id = ?")){
            preStat.setLong(1,id);
            preStat.executeUpdate();
        }catch (SQLException err){
            err.printStackTrace();
        }


    }

    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT ID, NAME, LAST_NAME, AGE FROM users;");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LAST_NAME"));
                user.setAge(resultSet.getByte("AGE"));

                userList.add(user);
            }
        } catch (SQLException err) {
            System.out.print("getUsers");
            err.printStackTrace();
        }
        return userList;

    }

    public void cleanUsersTable() {
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate("DELETE FROM users");
        }catch (SQLException err){
            err.printStackTrace();
        }
    }
}
