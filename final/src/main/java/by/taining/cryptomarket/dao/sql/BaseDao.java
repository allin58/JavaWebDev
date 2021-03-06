package by.taining.cryptomarket.dao.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;


/**
 * The base class for daoimpl.
 * @author Nikita Karchahin
 * @version 1.0
 */
public class BaseDao {

    /**
     * The field for storage a logger.
     */
    static final Logger LOGGER = LogManager.getLogger("by.training.final.DataBaseLogger");

    /**
     * The field for storage a connection.
     */
    protected Connection connection;

    /**
     * The setter for connection.
     * @param connection connection
     */
    public void setConnection(final Connection connection) {
        this.connection = connection;
    }
}
