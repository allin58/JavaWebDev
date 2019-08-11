package service;

import by.taining.cryptomarket.dao.connectionpool.BasicConnectionPool;
import by.taining.cryptomarket.dao.sql.OrderDaoImpl;
import by.taining.cryptomarket.dao.sql.WalletDaoImpl;
import by.taining.cryptomarket.entity.Order;
import by.taining.cryptomarket.entity.User;
import by.taining.cryptomarket.entity.Wallet;
import by.taining.cryptomarket.service.OrderService;
import by.taining.cryptomarket.service.UserService;
import by.taining.cryptomarket.service.WalletService;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class ExchangeServiceTest {


    @BeforeTest
    public void init() throws Exception{
        String url = "jdbc:mariadb://localhost:3306/testmarket";
        String username = "testmarket";
        String password = "testmarket";
        Class.forName("org.mariadb.jdbc.Driver");
        BasicConnectionPool.create(url,username,password);

        WalletService walletService = new WalletService();
        Wallet wallet1 = walletService.getWalletByUserId(3);
        Wallet wallet2 = walletService.getWalletByUserId(4);
        wallet1.setBtc(1.0);
        wallet1.setEth(1.0);
        wallet1.setUsdt(10000.0);
        wallet2.setBtc(1.0);
        wallet2.setEth(1.0);
        wallet2.setUsdt(20000.0);
        WalletDaoImpl walletDao = new WalletDaoImpl();
        walletDao.setConnection(BasicConnectionPool.getBasicConnectionPool().getConnection());
        walletDao.update(wallet1);
        walletDao.update(wallet2);

        OrderDaoImpl orderDao = new OrderDaoImpl();
        orderDao.setConnection(BasicConnectionPool.getBasicConnectionPool().getConnection());
        List<Order> orders = orderDao.read();
        for (Order order : orders) {
            order.setState("executed");
            orderDao.update(order);
        }





    }



    @Test(description = "The check of execution orders")
    public void ordersExecutionTest()throws Exception{
        OrderService orderService = new OrderService();
        UserService userService = new UserService();

        Order order1 = new Order();
        order1.setUserId(4);
        order1.setAmount(0.5);
        order1.setPair("BTC-USDT");
        order1.setPrice(10000.0);
        order1.setType("Bid");
        order1.setState("active");
        orderService.createNewOrder(order1);
        User user1 = userService.getUserById(3);
        orderService.executeOrder((Order)orderService.getBidOrdersByPair("BTC-USDT").get(0),user1);
        WalletService walletService = new WalletService();
        Wallet wallet1 = walletService.getWalletByUserId(3);
        Wallet wallet2 = walletService.getWalletByUserId(4);

        assertEquals(wallet1.getUsdt(),wallet2.getUsdt());

    }



    @AfterTest
    public void destroy() throws Exception{
        BasicConnectionPool.getBasicConnectionPool().shutdown();
    }




}
