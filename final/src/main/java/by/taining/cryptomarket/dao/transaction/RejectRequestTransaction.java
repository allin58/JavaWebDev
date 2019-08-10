package by.taining.cryptomarket.dao.transaction;

import by.taining.cryptomarket.entity.Coin;
import by.taining.cryptomarket.entity.Transaction;
import by.taining.cryptomarket.entity.Wallet;
import by.taining.cryptomarket.entity.qualifier.WalletQualifier;
import by.taining.cryptomarket.exception.PersistentException;
import by.taining.cryptomarket.dao.sql.CoinDaoImpl;
import by.taining.cryptomarket.dao.sql.TransactionDaoImpl;
import by.taining.cryptomarket.dao.sql.WalletDaoImpl;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;


/**
 * This class is responsible for the rejection of withdrawals and deposits.
 * @author Nikita Karchahin
 * @version 1.0
 */
public class RejectRequestTransaction extends DataBaseTransaction {

    /**
     * The field for storage a idTransaction.
     */
    private Integer idTransaction;


    /**
     * The constructor with a parameter.
     * @param connection connection
     */
    public RejectRequestTransaction(final Connection connection) {
        super(connection);
    }

    /**
     * The getter for idTransaction.
     * @return idTransaction
     */
    public Integer getIdTransaction() {
        return idTransaction;
    }

    /**
     * The setter for idTransaction.
     * @param idTransaction idTransaction
     */
    public void setIdTransaction(final Integer idTransaction) {
        this.idTransaction = idTransaction;
    }



    /**
     * Transaction method that rejects withdrawal or deposit.
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


            Transaction transaction = transactionDao.read(idTransaction);


            if ("pending".equals(transaction.getStatus())) {
                Coin coin = coinDao.read(transaction.getCoinId());
                Wallet wallet = walletDao.read(transaction.getUserId());
                connection.setAutoCommit(false);

                if ("withdraw".equals(transaction.getType())) {
                    WalletQualifier walletQualifier = new WalletQualifier();
                    walletQualifier.increaseCurrency(transaction.getAmount(), coin.getTicker(), wallet);
                    walletDao.update(wallet);
                }

                transaction.setStatus("rejected");
                Date date = new Date();
                transaction.setTimestamp(new Timestamp(date.getTime()));
                transactionDao.update(transaction);
                connection.commit();
                connection.setAutoCommit(true);
                LOGGER.info("Transaction " + idTransaction + " is rejected");
            }


        } catch (Exception e) {
            LOGGER.info("PersistentException in RejectRequestTransaction, method commit()");
            rollback();
            throw new PersistentException();
        }


    }

    /**
     * The transaction rollback method.
     * @throws PersistentException
     */
    @Override
    public void rollback() throws PersistentException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.info("PersistentException in RejectRequestTransaction, method rollback()");
            throw new PersistentException();
        }
    }
}
