package dao.transaction;

import exception.PersistentException;

public interface DataBaseTransaction {

    public void commit() throws PersistentException;
    public void rollback() throws PersistentException;
}
