package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoJDBCImpl();
    private UserDao userDao = new UserDaoHibernateImpl();
    public void createUsersTable() {
        dao.createUsersTable();
        userDao.createUsersTable();

    }

    public void dropUsersTable() {

        dao.dropUsersTable();
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
      //  dao.saveUser(name,lastName,age);
        userDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {

      //  dao.removeUserById(id);
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {

      //  return dao.getAllUsers();
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {

      //  dao.cleanUsersTable();
        userDao.cleanUsersTable();
    }
}


