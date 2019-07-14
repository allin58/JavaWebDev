package main;

import dao.connectionpool.BasicConnectionPool;
import dao.sql.CryptoPairDaoImpl;
import dao.sql.OrderDaoImpl;
import dao.sql.WalletDaoImpl;
import entity.CryptoPair;
import entity.Order;
import entity.Wallet;
import org.bouncycastle.util.encoders.Hex;
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
        CryptoPairDaoImpl cryptoPairDao = new CryptoPairDaoImpl();
        OrderDaoImpl orderDao = new OrderDaoImpl();
        orderDao.setConnection(connection);
        for (Order order : orderDao.read()) {
            System.out.println(order.getState());
        }



        basicConnectionPool.releaseConnection(connection);




    }
}
