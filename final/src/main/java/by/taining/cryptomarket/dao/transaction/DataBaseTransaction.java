package by.taining.cryptomarket.dao.transaction;

import by.taining.cryptomarket.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

abstract public class DataBaseTransaction {

    final static Logger LOGGER = LogManager.getLogger("by.training.final.DataBaseLogger");
    public Connection connection;

    public DataBaseTransaction(Connection connection) {
        this.connection = connection;
    }

    abstract void commit() throws PersistentException;
    abstract void rollback() throws PersistentException;
}
