package service;

import dao.connectionpool.BasicConnectionPool;
import dao.sql.UserDaoImpl;
import entity.User;
import exception.PersistentException;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UserService {


    public void addUser(User user) throws Exception {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest((user.getHashOfPassword() + "cryptomarket").getBytes(StandardCharsets.UTF_8));
        user.setHashOfPassword(new String(Hex.encode(hash)));


        Connection connection = null;
        try {
            connection = BasicConnectionPool.getBasicConnectionPool().getConnection();
            UserDaoImpl userDao = new UserDaoImpl();
            userDao.setConnection(connection);
            userDao.create(user);
        }
        finally {
            BasicConnectionPool.getBasicConnectionPool().releaseConnection(connection);
        }

          }


    public boolean userIsExist(String name) throws Exception {
        Connection connection = null;

        List<User> users;
        try {
            connection = BasicConnectionPool.getBasicConnectionPool().getConnection();
            UserDaoImpl userDao = new UserDaoImpl();
            userDao.setConnection(connection);
            users = userDao.read();
        } finally {
            BasicConnectionPool.getBasicConnectionPool().releaseConnection(connection);
        }

        for (User user : users) {

            if (name.equals(user.getUserName())) {
                return true;
            }
        }
        return false;
    }


  public Integer getIdByUserNameAndPassword(String userName,String password)throws Exception {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hash = digest.digest((password + "cryptomarket").getBytes(StandardCharsets.UTF_8));
      password = new String(Hex.encode(hash));


      Connection connection = null;
      List<User> users;
      try {
          connection = BasicConnectionPool.getBasicConnectionPool().getConnection();
          UserDaoImpl userDao = new UserDaoImpl();
          userDao.setConnection(connection);
          users = userDao.read();
      } finally {
          BasicConnectionPool.getBasicConnectionPool().releaseConnection(connection);
      }


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
