package main;

import dao.CoinDao;
import dao.WalletDao;
import dao.connectionpool.BasicConnectionPool;
import dao.connectionpool.ConnectionPool;
import dao.sql.CoinDaoImpl;
import dao.sql.OrderDaoImpl;
import dao.sql.UserDaoImpl;
import dao.sql.WalletDaoImpl;
import dao.transaction.CancelOrderTransaction;
import entity.Coin;
import entity.Order;
import entity.User;
import entity.Wallet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
String url = "jdbc:mysql://localhost:3306/market";
String username = "market";
String password = "market";
        try {
            ConnectionPool connectionPool = BasicConnectionPool.create(url,username,password);
            OrderDaoImpl orderDao = new OrderDaoImpl();
            orderDao.setConnection(connectionPool.getConnection());


            Order order = orderDao.read(8);

            CancelOrderTransaction cancelOrderTransaction = new CancelOrderTransaction(connectionPool.getConnection());
            cancelOrderTransaction.setOrder(order);
            cancelOrderTransaction.commit();

            connectionPool.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
