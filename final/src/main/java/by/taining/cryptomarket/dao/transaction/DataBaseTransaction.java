package by.taining.cryptomarket.dao.transaction;

import by.taining.cryptomarket.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;


/**
 * The base class for all transactions.
 * @author Nikita Karchahin
 * @version 1.0
 */
public abstract class DataBaseTransaction {

    /**
     * The field for storage a logger.
     */
    static final Logger LOGGER = LogManager.getLogger("by.training.final.DataBaseLogger");

    /**
     * The field for storage a connection.
     */
    public Connection connection;

    /**
     * The constructor with a parameter.
     * @param connection connection
     */
    public DataBaseTransaction(final Connection connection) {
        this.connection = connection;
    }

    /**
     * The transaction abstract method.
     * @throws PersistentException PersistentException
     */
    abstract void commit() throws PersistentException;

    /**
     * The transaction rollback method.
     * @throws PersistentException PersistentException
     */
    abstract void rollback() throws PersistentException;
}
