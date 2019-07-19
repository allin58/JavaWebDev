package dao.transaction;

import dao.sql.CoinDaoImpl;
import dao.sql.TransactionDaoImpl;
import dao.sql.WalletDaoImpl;
import entity.Coin;
import entity.Transaction;
import entity.Wallet;
import entity.qualifier.WalletQualifier;
import exception.PersistentException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class RejectRequestTransaction extends DataBaseTransaction {

    private Integer idTransaction;


    public RejectRequestTransaction(Connection connection) {
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
            Coin coin = coinDao.read(transaction.getCoinId());
            Wallet wallet = walletDao.read(transaction.getUserId());
            connection.setAutoCommit(false);

            if ("withdrow".equals(transaction.getType())) {
                WalletQualifier walletQualifier = new WalletQualifier();
                walletQualifier.increaseCurrency(transaction.getAmount(),coin.getTicker(),wallet);
                walletDao.update(wallet);
            }

            transaction.setStatus("rejected");
            Date date = new Date();
            transaction.setTimestamp(new Timestamp(date.getTime()));
            transactionDao.update(transaction);
            connection.commit();
            connection.setAutoCommit(true);

        } catch (Exception e) {
            rollback();
            throw new PersistentException();
        }


    }

    @Override
    public void rollback() throws PersistentException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new PersistentException();
        }
    }
}
