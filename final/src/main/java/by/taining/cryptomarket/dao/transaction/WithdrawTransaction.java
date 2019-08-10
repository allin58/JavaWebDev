package by.taining.cryptomarket.dao.transaction;

import by.taining.cryptomarket.entity.Transaction;
import by.taining.cryptomarket.entity.Wallet;
import by.taining.cryptomarket.entity.qualifier.WalletQualifier;
import by.taining.cryptomarket.exception.PersistentException;
import by.taining.cryptomarket.dao.sql.CoinDaoImpl;
import by.taining.cryptomarket.dao.sql.TransactionDaoImpl;
import by.taining.cryptomarket.dao.sql.WalletDaoImpl;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class is responsible for creating  of withdraw request.
 * @author Nikita Karchahin
 * @version 1.0
 */
public class WithdrawTransaction extends DataBaseTransaction {

    /**
     * The field for storage a transaction.
     */
   private Transaction transaction;

    /**
     * The constructor with a parameter.
     * @param connection connection
     */
    public WithdrawTransaction(final Connection connection) {
        super(connection);
    }

    /**
     * The getter for transaction.
     * @return transaction
     */
    public Transaction getTransaction() {
        return transaction;
    }

    /**
     * The setter for transaction.
     * @param transaction transaction
     */
    public void setTransaction(final Transaction transaction) {
        this.transaction = transaction;
    }


    /**
     * Transaction method that creates of withdraw request.
     * @throws PersistentException
     */
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
            walletQualifier.reduceCurrency(transaction.getAmount(), coin, wallet);
            connection.setAutoCommit(false);
            walletDao.update(wallet);
            transactionDao.create(transaction);
            connection.setAutoCommit(true);


            connection.commit();

            LOGGER.info("Transaction " + transaction.getIdentity() + " is completed");

        } catch (Exception e) {
            LOGGER.info("PersistentException in WithdrawTransaction, method commit()");
            rollback();
            throw new PersistentException();
        }
    }

    /**
     * The transaction rollback method.
     * @throws PersistentException
     */
    @Override
    void rollback() throws PersistentException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.info("PersistentException in WithdrawTransaction, method rollback()");
            throw new PersistentException();
        }
    }
}
