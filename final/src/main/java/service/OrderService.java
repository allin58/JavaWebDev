package service;

import dao.connectionpool.BasicConnectionPool;
import dao.sql.OrderDaoImpl;
import dao.transaction.CancelOrderTransaction;
import entity.Order;
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
