package service;

import dao.connectionpool.BasicConnectionPool;
import dao.sql.UserDaoImpl;
import entity.User;
import exception.PersistentException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UserService {


    public boolean userIsExist(String name, String password) throws Exception {

       Connection connection = BasicConnectionPool.getBasicConnectionPool().getConnection();
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.setConnection(connection);
        List<User> users = userDao.read();
        BasicConnectionPool.getBasicConnectionPool().releaseConnection(connection);
        for (User user : users) {

            if (name.equals(user.getUserName()) && password.equals(user.getHashOfPassword())) {
                return true;
            }
        }
        return false;
    }


  public Integer getIdByUserName(String userName,String password)throws Exception {
      Connection connection = BasicConnectionPool.getBasicConnectionPool().getConnection();
      UserDaoImpl userDao = new UserDaoImpl();
      userDao.setConnection(connection);
      List<User> users = userDao.read();
      BasicConnectionPool.getBasicConnectionPool().releaseConnection(connection);
      for (User user : users) {

          if (userName.equals(user.getUserName()) && password.equals(user.getHashOfPassword())) {
              return user.getIdentity();
          }
      }
      return 0;
  }

    public User getUserById(Integer id)throws Exception {

        User user = null;
        Connection connection = null;
        try {

            connection = BasicConnectionPool.getBasicConnectionPool().getConnection();

            UserDaoImpl userDao = new UserDaoImpl();
            userDao.setConnection(connection);
            user = userDao.read(id);
           } catch (PersistentException e) {

        } finally {
            BasicConnectionPool.getBasicConnectionPool().releaseConnection(connection);
        }

        return user;
    }



}
