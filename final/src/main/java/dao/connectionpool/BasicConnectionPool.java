package dao.connectionpool;

import exception.PersistentException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class BasicConnectionPool  implements ConnectionPool {

    private String url;
    private String user;
    private String password;

    private static BasicConnectionPool basicConnectionPool;
    //private List<Connection> connectionPool;
   // private List<Connection> usedConnections = new ArrayList<>();

    private ArrayBlockingQueue<Connection> connectionPool;
    private ArrayBlockingQueue<Connection> usedConnections;

    private static int INITIAL_POOL_SIZE = 10;

    public static void create(String url, String user, String password) throws SQLException {

        if (basicConnectionPool == null) {
            ArrayBlockingQueue<Connection> pool = new ArrayBlockingQueue<>(INITIAL_POOL_SIZE);
            //ArrayList<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);

            for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
                pool.add(createConnection(url, user, password));
            }

            basicConnectionPool = new BasicConnectionPool(url, user, password, pool);
           basicConnectionPool.usedConnections = new ArrayBlockingQueue<>(INITIAL_POOL_SIZE);
            //basicConnectionPool.usedConnections = new ArrayList<>(INITIAL_POOL_SIZE);

        }
    }

    public static BasicConnectionPool getBasicConnectionPool() {
        return basicConnectionPool;
    }

    //private BasicConnectionPool(String url, String user, String password, ArrayList<Connection> connectionPool) {
    private BasicConnectionPool(String url, String user, String password, ArrayBlockingQueue<Connection> connectionPool) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = connectionPool;
    }


    @Override
    public Connection getConnection() throws PersistentException {

        if (connectionPool.isEmpty()) {
           throw new PersistentException();
        }

       // Connection connection = connectionPool.remove(connectionPool.size() - 1);
        Connection connection = connectionPool.poll();

        usedConnections.add(connection);

        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {

        connectionPool.add(connection);

        return usedConnections.remove(connection);
    }

    private static Connection createConnection(
            String url, String user, String password)
            throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }


    public void  shutdown() throws SQLException {

        //usedConnections.forEach(this::releaseConnection);

                while(usedConnections.size() > 0) {
                     //releaseConnection(usedConnections.get(0));
                     releaseConnection(usedConnections.poll());

                }
        for (Connection c : connectionPool) {
            c.close();
        }
        connectionPool.clear();

    }






    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}