package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Util.getConnectionDB();
        UserService userServ = new UserServiceImpl();

        userServ.createUsersTable();

        List<User> userList = new ArrayList<>();

        userList.add(new User("Elena","Ivanova",(byte)30));
        userList.add(new User("Irina","Petrova",(byte)50));
        userList.add(new User("Oleg","Ivanov",(byte)35));
        userList.add(new User("Victor","Petrov",(byte)22));

        userList.forEach(x -> {
            userServ.saveUser(x.getName(), x.getLastName(), x.getAge());
            System.out.format("User с именем - %s добавлен в базу данных%n", x.getName());
        });
        userServ.getAllUsers().forEach(user -> System.out.println(user.toString()));
        userServ.removeUserById(1);
        userServ.cleanUsersTable();
        userServ.dropUsersTable();
    }
}

