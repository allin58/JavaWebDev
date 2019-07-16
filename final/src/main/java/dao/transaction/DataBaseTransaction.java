package dao.transaction;

import exception.PersistentException;

import java.sql.Connection;

abstract public class DataBaseTransaction {


    public Connection connection;

    public DataBaseTransaction(Connection connection) {
        this.connection = connection;
    }

    abstract void commit() throws PersistentException;
    abstract void rollback() throws PersistentException;
}
