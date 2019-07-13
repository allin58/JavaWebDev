package dao.connectionpool;

import exception.PersistentException;

import java.sql.Connection;
import java.sql.SQLException;


public interface ConnectionPool {
    Connection getConnection() throws PersistentException;
    boolean releaseConnection(Connection connection);
    String getUrl();
    String getUser();
    String getPassword();
     void shutdown() throws SQLException;
}

