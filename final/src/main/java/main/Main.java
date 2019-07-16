package main;

import command.CommandFactory;
import dao.connectionpool.BasicConnectionPool;
import dao.sql.CryptoPairDaoImpl;
import dao.sql.OrderDaoImpl;
import dao.sql.TransactionDaoImpl;
import dao.sql.WalletDaoImpl;
import entity.*;
import org.bouncycastle.util.encoders.Hex;
import service.CryptoPairService;
import service.UserService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;

public class Main {
    public static final String url = "jdbc:mariadb://localhost:3306/market";
    public static final String username = "market";
    public static final String password = "market";

    public static void main(String[] args) throws Exception{
        Class.forName("org.mariadb.jdbc.Driver");
        BasicConnectionPool.create(url,username,password);

       BasicConnectionPool basicConnectionPool = BasicConnectionPool.getBasicConnectionPool();
       Connection connection = basicConnectionPool.getConnection();



        TransactionDaoImpl transactionDao = new TransactionDaoImpl();
        transactionDao.setConnection(connection);

        Transaction transaction = new Transaction();

        transaction.setAmount(18.0);
        transaction.setUserId(1);
        transaction.setType("withdrow");
        transaction.setCoinId(3);
        transaction.setStatus("pending");
        transactionDao.create(transaction);



        basicConnectionPool.releaseConnection(connection);



    }
}
