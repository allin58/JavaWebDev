package dao.sql;

import dao.UserDao;
import entity.User;
import exception.PersistentException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
  private static  String createSql = "INSERT INTO `users` (`user_name`, `name`, `surname`, `hash_of_password`, `role`) VALUES (?, ?, ?, ?, ?)";
  private static  String readListSql = "SELECT `identity`,`user_name`, `name`, `surname`, `hash_of_password`, `role` FROM `users`";
  private static  String readSql = "SELECT `user_name`, `name`, `surname`, `hash_of_password`, `role` FROM `users` WHERE `identity` = ?";
  private static  String updateSql = "UPDATE `users` SET `user_name` = ?, `name` = ?, `surname` = ?, `hash_of_password` = ?, `role` = ? WHERE `identity` = ?";
  private static  String deleteSql = "DELETE FROM `users` WHERE `identity` = ?";


    public List<User> read() throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<User> arrayList = new ArrayList<>();
        User user = null;

        try {
            statement = connection.prepareStatement(readListSql);

            resultSet = statement.executeQuery();

            while(resultSet.next()) {

                user = new User();
                user.setIdentity(resultSet.getInt("identity"));
                user.setUserName(resultSet.getString("user_name"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setHashOfPassword(resultSet.getString("hash_of_password"));
                user.setRole(resultSet.getString("role"));
                arrayList.add(user);

            }

            return arrayList;
        } catch(SQLException e) {
            LOGGER.info("PersistentException in TransactionDaoImpl, method read()");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
    }

    public Integer create(User user) throws PersistentException {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(createSql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getUserName());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSurname());
            statement.setString(4, user.getHashOfPassword());
            statement.setString(5, user.getRole());


            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            } else {

                throw new PersistentException();
            }
        } catch(SQLException e) {
            LOGGER.info("PersistentException in TransactionDaoImpl, method create()");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in TransactionDaoImpl, method create()");
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in TransactionDaoImpl, method create()");
            }
        }
    }

    public User read(Integer identity) throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(readSql);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            User user = null;
            if(resultSet.next()) {
                user = new User();
                user.setIdentity(identity);
                user.setUserName(resultSet.getString("user_name"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setHashOfPassword(resultSet.getString("hash_of_password"));
                user.setRole(resultSet.getString("role"));


            }
            return user;
        } catch(SQLException e) {
            LOGGER.info("PersistentException in TransactionDaoImpl, method read()");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in TransactionDaoImpl, method read()");
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in TransactionDaoImpl, method read()");
            }
        }
    }

    public void update(User user) throws PersistentException {

             PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(updateSql);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSurname());
            statement.setString(4, user.getHashOfPassword());
            statement.setString(5, user.getRole());
            statement.setInt(6, user.getIdentity());
            statement.executeUpdate();
        } catch(SQLException e) {
            LOGGER.info("PersistentException in TransactionDaoImpl, method update()");
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in TransactionDaoImpl, method update()");
            }
        }


    }

    public void delete(Integer identity) throws PersistentException {



        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(deleteSql);
            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch(SQLException e) {
            LOGGER.info("PersistentException in TransactionDaoImpl, method delete()");
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in TransactionDaoImpl, method delete()");
            }
        }

    }
}
