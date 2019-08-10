package by.taining.cryptomarket.service;

import by.taining.cryptomarket.dao.connectionpool.BasicConnectionPool;
import by.taining.cryptomarket.dao.sql.CoinDaoImpl;
import by.taining.cryptomarket.dao.sql.TransactionDaoImpl;
import by.taining.cryptomarket.dao.sql.UserDaoImpl;
import by.taining.cryptomarket.dao.transaction.ApproveRequestTransaction;
import by.taining.cryptomarket.dao.transaction.RejectRequestTransaction;
import by.taining.cryptomarket.dao.transaction.WithdrawTransaction;
import by.taining.cryptomarket.entity.Coin;
import by.taining.cryptomarket.entity.Transaction;
import by.taining.cryptomarket.entity.mapping.MappingTransaction;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


/**
 * This service ensures interact with transactions.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class TransactionService {


    /**
     * Method for getting collection of transactions.
     * @return all transactions
     * @throws Exception Exception
     */
    public List<MappingTransaction> getAllTransactions() throws Exception {
        Connection transactionConnection = null;
        Connection userConnection = null;
        Connection coinConnection = null;

        List<Transaction> transactions;
        List<MappingTransaction> mappingTransactions = new ArrayList<>();

               try {
           transactionConnection = BasicConnectionPool.getBasicConnectionPool().getConnection();
           userConnection = BasicConnectionPool.getBasicConnectionPool().getConnection();
           coinConnection = BasicConnectionPool.getBasicConnectionPool().getConnection();

            TransactionDaoImpl transactionDao = new TransactionDaoImpl();
            transactionDao.setConnection(transactionConnection);
            transactions = transactionDao.read();


            UserDaoImpl userDao = new UserDaoImpl();
            userDao.setConnection(userConnection);
            CoinDaoImpl coinDao = new CoinDaoImpl();
            coinDao.setConnection(coinConnection);

                   for (Transaction transaction : transactions) {
                       MappingTransaction mappingTransaction = new MappingTransaction();
                       mappingTransaction.setIdentity(transaction.getIdentity());
                       mappingTransaction.setAmount(transaction.getAmount());
                       mappingTransaction.setType(transaction.getType());
                       mappingTransaction.setStatus(transaction.getStatus());
                       mappingTransaction.setCoin(coinDao.read(transaction.getCoinId()).getFullName());
                       mappingTransaction.setUser(userDao.read(transaction.getUserId()).getUserName());
                       mappingTransactions.add(mappingTransaction);
                   }

        } finally {
            BasicConnectionPool.getBasicConnectionPool().releaseConnection(transactionConnection);
            BasicConnectionPool.getBasicConnectionPool().releaseConnection(userConnection);
            BasicConnectionPool.getBasicConnectionPool().releaseConnection(coinConnection);
        }


        return  mappingTransactions;
    }



    /**
     * Method for getting collection of transactions.
     * @param userName userName
     * @return transactions by userName
     * @throws Exception Exception
     */
    public List<MappingTransaction> getTransactionsByUser(final String userName) throws Exception {

        List<MappingTransaction> mappingTransactions = new ArrayList<>();
        for (MappingTransaction mappingTransaction : getAllTransactions()) {
           if (mappingTransaction.getUser().equals(userName)) {
               mappingTransactions.add(mappingTransaction);
           }
        }
        return  mappingTransactions;
    }


    /**
     *  Method for getting collection of transactions.
     * @return pending transactions
     * @throws Exception Exception
     */
    public List<MappingTransaction> getPendingTransactions() throws Exception {
        List<MappingTransaction> mappingTransactions = new ArrayList<>();
        for (MappingTransaction mappingTransaction : getAllTransactions()) {
            if (mappingTransaction.getStatus().equals("pending")) {
                mappingTransactions.add(mappingTransaction);
            }
        }
        return  mappingTransactions;
    }


    /**
     * Method which approves transaction.
     * @param identity identity
     * @throws Exception Exception
     */
    public void approveTransaction(final Integer identity) throws Exception {

        Connection transactionConnection = null;


        try {

            transactionConnection = BasicConnectionPool.getBasicConnectionPool().getConnection();
            ApproveRequestTransaction approveRequestTransaction = new ApproveRequestTransaction(transactionConnection);
            approveRequestTransaction.setIdTransaction(identity);
            approveRequestTransaction.commit();


        } finally {
            BasicConnectionPool.getBasicConnectionPool().releaseConnection(transactionConnection);

        }


    }

    /**
     * Method which rejects transaction.
     * @param identity identity
     * @throws Exception Exception
     */
    public void rejectTransaction(final Integer identity) throws Exception {

        Connection transactionConnection = null;

        try {
            transactionConnection = BasicConnectionPool.getBasicConnectionPool().getConnection();
            RejectRequestTransaction rejectRequestTransaction = new RejectRequestTransaction(transactionConnection);
            rejectRequestTransaction.setIdTransaction(identity);
            rejectRequestTransaction.commit();

        } finally {
            BasicConnectionPool.getBasicConnectionPool().releaseConnection(transactionConnection);
        }


    }


    /**
     *  The method for creating a deposit transaction.
     * @param userId userId
     * @param amount amount
     * @param coin coin
     * @throws Exception Exception
     */
    public void setDepositTransaction(final Integer userId,
                                      final Double amount,
                                      final String coin) throws  Exception {
        Connection connection = null;
        try {
            connection = BasicConnectionPool.getBasicConnectionPool().getConnection();
            CoinDaoImpl coinDao = new CoinDaoImpl();
            coinDao.setConnection(connection);
            List<Coin> coins = coinDao.read();
            Integer coinId = null;
            for (Coin coin1 : coins) {
               if (coin1.getTicker().equals(coin)) {
                   coinId = coin1.getIdentity();
               }
            }
            Transaction transaction = new Transaction();
            transaction.setCoinId(coinId);
            transaction.setStatus("pending");
            transaction.setType("deposit");
            transaction.setUserId(userId);
            transaction.setAmount(amount);
            TransactionDaoImpl transactionDao = new TransactionDaoImpl();
            transactionDao.setConnection(connection);
            transactionDao.create(transaction);

        } finally {
            BasicConnectionPool.getBasicConnectionPool().releaseConnection(connection);
        }
    }


    /**
     *  The method for creating a withdraw transaction.
     * @param userId userId
     * @param amount amount
     * @param coin coin
     * @throws Exception Exception
     */
    public void setWithdrawTransaction(final Integer userId,
                                       final Double amount,
                                       final String coin) throws Exception {
        Connection connection = null;
        try {
            connection = BasicConnectionPool.getBasicConnectionPool().getConnection();
            CoinDaoImpl coinDao = new CoinDaoImpl();
            coinDao.setConnection(connection);
            List<Coin> coins = coinDao.read();
            Integer coinId = null;
            for (Coin coin1 : coins) {
                if (coin1.getTicker().equals(coin)) {
                    coinId = coin1.getIdentity();
                }
            }
            Transaction transaction = new Transaction();
            transaction.setCoinId(coinId);
            transaction.setStatus("pending");
            transaction.setType("withdraw");
            transaction.setUserId(userId);
            transaction.setAmount(amount);
            WithdrawTransaction withdrawTransaction = new WithdrawTransaction(connection);
            withdrawTransaction.setTransaction(transaction);
            withdrawTransaction.commit();


        } finally {
            BasicConnectionPool.getBasicConnectionPool().releaseConnection(connection);
        }
    }







}
