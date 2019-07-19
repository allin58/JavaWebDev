package service;

import dao.CoinDao;
import dao.connectionpool.BasicConnectionPool;
import dao.sql.CoinDaoImpl;
import dao.sql.TransactionDaoImpl;
import dao.sql.UserDaoImpl;
import dao.sql.WalletDaoImpl;
import dao.transaction.ApproveRequestTransaction;
import dao.transaction.RejectRequestTransaction;
import dao.transaction.WithdrowTransaction;
import entity.Coin;
import entity.Transaction;
import entity.mapping.MappingTransaction;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class TransactionService {




    public List<MappingTransaction> getAllTransactions() throws Exception{
        Connection transactionConnection = null;
        Connection userConnection = null;;
        Connection coinConnection = null;;

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


    public List<MappingTransaction> getTransactionsByUser(String userName) throws Exception{

        List<MappingTransaction> mappingTransactions = new ArrayList<>();
        for (MappingTransaction mappingTransaction : getAllTransactions()) {
           if (mappingTransaction.getUser().equals(userName)) {
               mappingTransactions.add(mappingTransaction);
           }
        }
        return  mappingTransactions;
    }

    public List<MappingTransaction> getPendingTransactions() throws Exception {
        List<MappingTransaction> mappingTransactions = new ArrayList<>();
        for (MappingTransaction mappingTransaction : getAllTransactions()) {
            if (mappingTransaction.getStatus().equals("pending")) {
                mappingTransactions.add(mappingTransaction);
            }
        }
        return  mappingTransactions;
    }



    public void approveTransaction(Integer identity) throws Exception {

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


    public void rejectTransaction(Integer identity) throws Exception {

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



    public void setDepositTransaction(Integer userId,Double amount, String coin) throws  Exception{
        Connection connection = null;
        try {
            connection = BasicConnectionPool.getBasicConnectionPool().getConnection();
            CoinDaoImpl coinDao = new CoinDaoImpl();
            coinDao.setConnection(connection);
            List<Coin> coins = coinDao.read();
            Integer coinId = null;
            for (Coin coin1 : coins) {
               if(coin1.getTicker().equals(coin)) {
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


    public void setWithdrowTransaction(Integer userId,Double amount, String coin) throws  Exception{
        Connection connection = null;
        try {
            connection = BasicConnectionPool.getBasicConnectionPool().getConnection();
            CoinDaoImpl coinDao = new CoinDaoImpl();
            coinDao.setConnection(connection);
            List<Coin> coins = coinDao.read();
            Integer coinId = null;
            for (Coin coin1 : coins) {
                if(coin1.getTicker().equals(coin)) {
                    coinId = coin1.getIdentity();
                }
            }
            Transaction transaction = new Transaction();
            transaction.setCoinId(coinId);
            transaction.setStatus("pending");
            transaction.setType("withdrow");
            transaction.setUserId(userId);
            transaction.setAmount(amount);
            WithdrowTransaction withdrowTransaction = new WithdrowTransaction(connection);
            withdrowTransaction.setTransaction(transaction);
            withdrowTransaction.commit();



           /* TransactionDaoImpl transactionDao = new TransactionDaoImpl();
            transactionDao.setConnection(connection);
            transactionDao.create(transaction);*/

        } finally {
            BasicConnectionPool.getBasicConnectionPool().releaseConnection(connection);
        }
    }







}
