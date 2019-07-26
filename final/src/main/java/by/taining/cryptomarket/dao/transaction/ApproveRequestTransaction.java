package by.taining.cryptomarket.dao.transaction;

import by.taining.cryptomarket.entity.Coin;
import by.taining.cryptomarket.entity.Transaction;
import by.taining.cryptomarket.entity.Wallet;
import by.taining.cryptomarket.entity.qualifier.WalletQualifier;
import by.taining.cryptomarket.exception.PersistentException;
import by.taining.cryptomarket.dao.sql.CoinDaoImpl;
import by.taining.cryptomarket.dao.sql.TransactionDaoImpl;
import by.taining.cryptomarket.dao.sql.WalletDaoImpl;
import by.taining.cryptomarket.entity.Coin;
import by.taining.cryptomarket.entity.Transaction;
import by.taining.cryptomarket.entity.Wallet;
import by.taining.cryptomarket.entity.qualifier.WalletQualifier;
import by.taining.cryptomarket.exception.PersistentException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class ApproveRequestTransaction extends DataBaseTransaction {
    private Integer idTransaction;


    public ApproveRequestTransaction(Connection connection) {
        super(connection);
    }


    public Integer getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Integer idTransaction) {
        this.idTransaction = idTransaction;
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


            Transaction transaction = transactionDao.read(idTransaction);


            if ("pending".equals(transaction.getStatus())) {
                Coin coin = coinDao.read(transaction.getCoinId());
                Wallet wallet = walletDao.read(transaction.getUserId());
                connection.setAutoCommit(false);

                if ("deposit".equals(transaction.getType())) {
                    WalletQualifier walletQualifier = new WalletQualifier();
                    walletQualifier.increaseCurrency(transaction.getAmount(),coin.getTicker(),wallet);
                    walletDao.update(wallet);
                }


                transaction.setStatus("approved");
                Date date = new Date();
                transaction.setTimestamp(new Timestamp(date.getTime()));
                transactionDao.update(transaction);
                connection.commit();
                connection.setAutoCommit(true);
                LOGGER.info("Transaction " + idTransaction + " is approve");
            }

        } catch (Exception e) {
            rollback();
            LOGGER.info("PersistentException in ApproveRequestTransaction, method commit()");
            throw new PersistentException();
        }
    }

    @Override
    void rollback() throws PersistentException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.info("PersistentException in ApproveRequestTransaction, method rollback()");
            throw new PersistentException();
        }
    }
}
