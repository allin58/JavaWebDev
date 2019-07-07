package main;

import dao.CoinDao;
import dao.WalletDao;
import dao.connectionpool.BasicConnectionPool;
import dao.connectionpool.ConnectionPool;
import dao.sql.*;
import dao.transaction.CancelOrderTransaction;
import entity.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Main {

    public static void main(String[] args) {
String url = "jdbc:mysql://localhost:3306/market";
String username = "market";
String password = "market";
        try {
            ConnectionPool connectionPool = BasicConnectionPool.create(url,username,password);
            TransactionDaoImpl transactionDao = new TransactionDaoImpl();
            transactionDao.setConnection(connectionPool.getConnection());
           /*Transaction transaction = new Transaction();
            transaction.setCoinId(2);
            transaction.setAmount(1.0);
            transaction.setType("deposit");
            transaction.setUserId(3);
            transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
            transactionDao.create(transaction);*/

            transactionDao.delete(2);

            connectionPool.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
