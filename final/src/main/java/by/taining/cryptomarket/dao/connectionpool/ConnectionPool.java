package by.taining.cryptomarket.dao.connectionpool;

import by.taining.cryptomarket.exception.PersistentException;


import java.sql.Connection;
import java.sql.SQLException;


/**
 * Interface for ConnectionPool.
 * @author Nikita Karchahin
 * @version 1.0
 */
public interface ConnectionPool {





    /**
     * The abstract method for getting connection.
     * @return connection
     * @throws PersistentException
     */
    Connection getConnection() throws PersistentException;

    /**
     * The abstract method for releasing connection.
     * @param connection
     * @return
     */
    boolean releaseConnection(Connection connection);



    /**
     * The abstract method for clearing connection pool.
     * @throws SQLException
     */
     void shutdown() throws SQLException;
}

