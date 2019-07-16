package dao.transaction;

import dao.sql.CoinDaoImpl;
import dao.sql.TransactionDaoImpl;
import dao.sql.WalletDaoImpl;
import entity.Coin;
import entity.Transaction;
import entity.Wallet;
import entity.WalletQualifier;
import exception.PersistentException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class WithdrowTransaction extends DataBaseTransaction {


   private Transaction transaction;

    public WithdrowTransaction(Connection connection) {
        super(connection);
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
   public void commit() throws PersistentException {
        try {
            WalletDaoImpl walletDao = new WalletDaoImpl();
            walletDao.setConnection(connection);
            TransactionDaoImpl transactionDao = new TransactionDaoImpl();
            transactionDao.setConnection(connection);
            CoinDaoImpl coinDao = new CoinDaoImpl();
            coinDao.setConnection(connection);
            String coin = coinDao.read(transaction.getCoinId()).getTicker();
            Wallet wallet = walletDao.read(transaction.getUserId());
            WalletQualifier walletQualifier = new WalletQualifier();
            walletQualifier.reduceCurrency(transaction.getAmount(),coin,wallet);
            connection.setAutoCommit(false);
            walletDao.update(wallet);
            transactionDao.create(transaction);
            connection.setAutoCommit(true);


            connection.commit();

        } catch (Exception e) {
            rollback();
            throw new PersistentException();
        }
    }

    @Override
    void rollback() throws PersistentException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new PersistentException();
        }
    }
}
