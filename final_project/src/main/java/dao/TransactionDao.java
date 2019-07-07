package dao;

import entity.Order;
import entity.Transaction;
import exception.PersistentException;

import java.util.List;

public interface TransactionDao extends Dao<Transaction> {
    List<Transaction> read() throws PersistentException;
}
