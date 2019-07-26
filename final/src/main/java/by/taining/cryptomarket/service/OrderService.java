package by.taining.cryptomarket.service;

import by.taining.cryptomarket.dao.connectionpool.BasicConnectionPool;
import by.taining.cryptomarket.dao.sql.OrderDaoImpl;
import by.taining.cryptomarket.dao.transaction.CancelOrderTransaction;
import by.taining.cryptomarket.dao.transaction.ExecuteOrderTransaction;
import by.taining.cryptomarket.dao.transaction.SetOrderTransaction;
import by.taining.cryptomarket.entity.Order;
import by.taining.cryptomarket.entity.User;
import by.taining.cryptomarket.entity.comparator.OrderComparatorImpl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



/**
 * This service ensures interact with orderds.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class OrderService {

    /**
     * This method returns a collection of ask orders by text view of pair.
     * @param pair
     * @return ask orders by pair
     * @throws Exception
     */
    public List getAskOrdersByPair(String pair) throws Exception{
        List<Order> resultOrders = new ArrayList<>();
        Connection connection = null;
        try {
            connection = BasicConnectionPool.getBasicConnectionPool().getConnection();
            OrderDaoImpl orderDao = new OrderDaoImpl();
            orderDao.setConnection(connection);
            List<Order> orders = orderDao.read();
            for (Order order : orders) {
              if (pair.equals(order.getPair()) && "Ask".equals(order.getType()) && "active".equals(order.getState())) {
                  resultOrders.add(order);
              }
            }

            Collections.sort(resultOrders, new OrderComparatorImpl());



        } finally {
            BasicConnectionPool.getBasicConnectionPool().releaseConnection(connection);
        }
        return resultOrders;
    }


    /**
     * This method returns a collection of bid orders by text view of pair.
     * @param pair
     * @return bid orders by pair
     * @throws Exception
     */
    public List getBidOrdersByPair(String pair) throws Exception{
        List<Order> resultOrders = new ArrayList<>();
        Connection connection = null;
        try {
            connection = BasicConnectionPool.getBasicConnectionPool().getConnection();
            OrderDaoImpl orderDao = new OrderDaoImpl();
            orderDao.setConnection(connection);
            List<Order> orders = orderDao.read();
            for (Order order : orders) {
                if (pair.equals(order.getPair()) && "Bid".equals(order.getType()) && "active".equals(order.getState())) {
                    resultOrders.add(order);
                }
            }

            Collections.sort(resultOrders, new OrderComparatorImpl());





        } finally {
            BasicConnectionPool.getBasicConnectionPool().releaseConnection(connection);
        }
        return resultOrders;
    }


    /**
     *  This method creates a new order.
     * @param order order
     * @throws Exception
     */
   public void createNewOrder(Order order) throws Exception {
       Connection connection = null;


       try {
           connection = BasicConnectionPool.getBasicConnectionPool().getConnection();
           SetOrderTransaction setOrderTransaction = new SetOrderTransaction(connection);
           setOrderTransaction.setOrder(order);
           setOrderTransaction.commit();

       } finally {
           BasicConnectionPool.getBasicConnectionPool().releaseConnection(connection);
       }


   }


    /**
     * This method executes orders.
     * @param order order to be processed
     * @param user user who requested order processing
     * @throws Exception
     */
    public void executeOrder(Order order, User user) throws Exception {
        Connection connection = null;


        try {
            connection = BasicConnectionPool.getBasicConnectionPool().getConnection();
            ExecuteOrderTransaction executeOrderTransaction = new ExecuteOrderTransaction(connection);
            executeOrderTransaction.setOrder(order);
            executeOrderTransaction.setUser(user);
            executeOrderTransaction.commit();

        } finally {
            BasicConnectionPool.getBasicConnectionPool().releaseConnection(connection);
        }


    }


    /**
     * This method partly executed orders.
     * @param order order to be processed
     * @param user user who requested order processing
     * @param amount amount
     * @throws Exception
     */
    public void executePartlyOrder(Order order, User user, Double amount) throws Exception {
        Connection connection = null;


        try {
            connection = BasicConnectionPool.getBasicConnectionPool().getConnection();
            ExecuteOrderTransaction executeOrderTransaction = new ExecuteOrderTransaction(connection);
            executeOrderTransaction.setOrder(order);
            executeOrderTransaction.setUser(user);
            executeOrderTransaction.setAmount(amount);
            executeOrderTransaction.commit();

        } finally {
            BasicConnectionPool.getBasicConnectionPool().releaseConnection(connection);
        }


    }


    /**
     * This method returns a collection of orders userId.
     * @param userId identity of user
     * @return specific user orders
     * @throws Exception
     */
    public List getOrdersByUserId(Integer userId) throws Exception{
        List<Order> resultOrders = new ArrayList<>();
        Connection connection = null;
        try {
            connection = BasicConnectionPool.getBasicConnectionPool().getConnection();
            OrderDaoImpl orderDao = new OrderDaoImpl();
            orderDao.setConnection(connection);
            List<Order> orders = orderDao.read();
            for (Order order : orders) {
               if (order.getUserId().equals(userId)) {
                   resultOrders.add(order);
               }
            }

        } finally {
            BasicConnectionPool.getBasicConnectionPool().releaseConnection(connection);
        }
        return resultOrders;
    }


    /**
     * This method rejects order by id.
     * @param identity identity
     * @throws Exception
     */
    public void rejectOrderById(Integer identity) throws Exception {



        Connection connection = null;
        try {
            connection = BasicConnectionPool.getBasicConnectionPool().getConnection();
            OrderDaoImpl orderDao = new OrderDaoImpl();
            orderDao.setConnection(connection);
            Order order = orderDao.read(identity);
            CancelOrderTransaction cancelOrderTransaction = new CancelOrderTransaction(connection);
            cancelOrderTransaction.setOrder(order);
            cancelOrderTransaction.commit();

        } finally {
            BasicConnectionPool.getBasicConnectionPool().releaseConnection(connection);
        }


    }


}
