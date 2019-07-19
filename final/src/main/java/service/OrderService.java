package service;

import dao.connectionpool.BasicConnectionPool;
import dao.sql.OrderDaoImpl;
import dao.transaction.CancelOrderTransaction;
import dao.transaction.DataBaseTransaction;
import dao.transaction.ExecuteOrderTransaction;
import dao.transaction.SetOrderTransaction;
import entity.Order;
import entity.User;
import entity.comparator.OrderComparatorImpl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderService {

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
