package by.taining.cryptomarket.service;

import by.taining.cryptomarket.exception.PersistentException;
import by.taining.cryptomarket.dao.connectionpool.BasicConnectionPool;
import by.taining.cryptomarket.dao.sql.UserDaoImpl;
import by.taining.cryptomarket.entity.User;
import org.bouncycastle.util.encoders.Hex;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.util.List;

/**
 * This service ensures interact with transactions.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class UserService {

    /**
     *  Method for adding of new user.
     * @param user user
     * @return identity of user
     * @throws Exception Exception
     */
    public Integer addUser(final User user) throws Exception {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest((user.getHashOfPassword() + "cryptomarket").getBytes(StandardCharsets.UTF_8));
        user.setHashOfPassword(new String(Hex.encode(hash)));


        Connection connection = null;
        try {
            connection = BasicConnectionPool.getBasicConnectionPool().getConnection();
            UserDaoImpl userDao = new UserDaoImpl();
            userDao.setConnection(connection);
            return userDao.create(user);
        } finally {
            BasicConnectionPool.getBasicConnectionPool().releaseConnection(connection);
        }
      }


    /**
     *
     * This method checks the presence of the user.
     * @param name name
     * @return boolean value
     * @throws Exception Exception
     */
    public boolean userIsExist(final String name) throws Exception {
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

    /**
     *  This method checks the presence of the user by userName and password.
     * @param userName userName
     * @param password password
     * @return identity of user
     * @throws Exception Exception
     */
  public Integer getIdByUserNameAndPassword(final String userName, String password)throws Exception {
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

    /**
     * This method returns user by identity.
     * @param id id
     * @return user
     * @throws Exception Exception
     */
    public User getUserById(final Integer id)throws Exception {

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


    /**
     * The method for getting all users.
     * @return collection of users
     * @throws Exception Exception
     */
    public List getAllUsers() throws Exception {
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


        return users;
    }




}
