package by.taining.cryptomarket.dao.connectionpool;

import by.taining.cryptomarket.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;


/**
 * The implementation of connection pool.
 * @author Nikita Karchahin
 * @version 1.0
 */
public final class BasicConnectionPool  implements ConnectionPool {

    /**
     * DataBaseLogger.
     */
    static final Logger LOGGER = LogManager.getLogger("by.training.final.DataBaseLogger");

    /**
     * The field for storage a url.
     */
    private String url;

    /**
     * The field for storage a user.
     */
    private String user;

    /**
     * The field for storage a password.
     */
    private String password;

    /**
     * The field for storage instance of BasicConnectionPool.
     */
    private static BasicConnectionPool basicConnectionPool;

    /**
     * The field for storage a collection of unused connections.
     */
    private ArrayBlockingQueue<Connection> connectionPool;

    /**
     * The field for storage a collection of used connections.
     */
    private ArrayBlockingQueue<Connection> usedConnections;

    /**
     * The field for storage a size of connection pool.
     */
    private static int INITIAL_POOL_SIZE = 10;


    /**
     * The method for creating of connection pool.
     * @param url url
     * @param user user
     * @param password password
     * @throws SQLException SQLException
     */
    public static void create(final String url,
                              final String user,
                              final String password) throws SQLException {
        if (basicConnectionPool == null) {
            ArrayBlockingQueue<Connection> pool = new ArrayBlockingQueue<>(INITIAL_POOL_SIZE);
            for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
                pool.add(createConnection(url, user, password));
            }
            basicConnectionPool = new BasicConnectionPool(url, user, password, pool);
            basicConnectionPool.usedConnections = new ArrayBlockingQueue<>(INITIAL_POOL_SIZE);
        }
        LOGGER.info("connection pool is created");

    }


    /**
     * The method returns basicConnectionPool instance.
     * @return basicConnectionPool instance
     */
    public static BasicConnectionPool getBasicConnectionPool() {
        return basicConnectionPool;
    }

    /**
     * The private constructor which implements singleton template.
     * @param url url
     * @param user user
     * @param password password
     * @param connectionPool connectionPool
     */
    private BasicConnectionPool(final String url,
                                final String user,
                                final  String password,
                                final ArrayBlockingQueue<Connection> connectionPool) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = connectionPool;
    }


    /**
     * The method for getting connection.
     * @return connection
     * @throws PersistentException
     */
    @Override
    public Connection getConnection() throws PersistentException {

        if (connectionPool.isEmpty()) {
           throw new PersistentException();
        }

        Connection connection = connectionPool.poll();
        usedConnections.add(connection);
        LOGGER.info("connection given");
        return connection;
    }


    /**
     * The method for releasing connection.
     * @param connection
     * @return
     */
    @Override
    public boolean releaseConnection(final Connection connection) {

        connectionPool.add(connection);
        LOGGER.info("connection released");
        return usedConnections.remove(connection);
    }


    /**
     * The method for creating of connection.
     * @param url url
     * @param user user
     * @param password password
     * @return new connection
     * @throws SQLException SQLException
     */
    private static Connection createConnection(final String url,
                                               final  String user,
                                               final  String password)
            throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }


    /**
     * The method for clearing connection pool.
     * @throws SQLException
     */
    @Override
    public void  shutdown() throws SQLException {

                while(usedConnections.size() > 0) {
                     releaseConnection(usedConnections.poll());
                }

        for (Connection c : connectionPool) {
            c.close();
        }
        connectionPool.clear();
        LOGGER.info("connection pool is shutdown");
    }




    /**
     * The getter for url.
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * The setter for url.
     * @param url url
     */
    public void setUrl(final String url) {
        this.url = url;
    }


    /**
     * The getter for user.
     * @return user
     */
    public String getUser() {
        return user;
    }

    /**
     * The setter for user.
     * @param user user
     */
    public void setUser(final String user) {
        this.user = user;
    }

    /**
     * The getter for password.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * The setter for password.
     * @param password password
     */
    public void setPassword(final String password) {
        this.password = password;
    }
}
