package by.taining.cryptomarket.dao.transaction;

import by.taining.cryptomarket.entity.Order;
import by.taining.cryptomarket.entity.User;
import by.taining.cryptomarket.entity.Wallet;
import by.taining.cryptomarket.entity.qualifier.WalletQualifier;
import by.taining.cryptomarket.exception.PersistentException;
import by.taining.cryptomarket.dao.sql.OrderDaoImpl;
import by.taining.cryptomarket.dao.sql.WalletDaoImpl;
import by.taining.cryptomarket.entity.Order;
import by.taining.cryptomarket.entity.User;
import by.taining.cryptomarket.entity.Wallet;
import by.taining.cryptomarket.entity.qualifier.WalletQualifier;
import by.taining.cryptomarket.exception.PersistentException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteOrderTransaction extends DataBaseTransaction {
private Double amount;

   private User user;
   private Order order;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ExecuteOrderTransaction(Connection connection) {
        super(connection);
    }

    @Override
    public void commit() throws PersistentException {


            try{
                WalletDaoImpl walletDao = new WalletDaoImpl();
                walletDao.setConnection(connection);
                OrderDaoImpl orderDao = new OrderDaoImpl();
                orderDao.setConnection(connection);
                Wallet wallet1 = walletDao.read(user.getIdentity());
                Wallet wallet2 = walletDao.read(order.getUserId());
                connection.setAutoCommit(false);
                String pair = order.getPair();
                String firstCurrency = pair.split("-")[0];
                String secondCurrency = pair.split("-")[1];

                WalletQualifier walletQualifier = new WalletQualifier();

             
                if (amount == null) {


                    if ("Bid".equals(order.getType())) {

                        walletQualifier.reduceCurrency(order.getAmount(), firstCurrency, wallet1);
                        walletQualifier.increaseCurrency(order.getAmount(), firstCurrency, wallet2);
                        walletQualifier.increaseCurrency(order.getAmount() * order.getPrice(), secondCurrency, wallet1);
                    }

                    if ("Ask".equals(order.getType())) {

                        walletQualifier.reduceCurrency(order.getAmount() * order.getPrice(), secondCurrency, wallet1);
                        walletQualifier.increaseCurrency(order.getAmount() * order.getPrice(), secondCurrency, wallet2);
                        walletQualifier.increaseCurrency(order.getAmount(), firstCurrency, wallet1);
                    }



                }else {

                    if ("Bid".equals(order.getType())) {

                        walletQualifier.reduceCurrency(amount,firstCurrency,wallet1);
                        walletQualifier.increaseCurrency(amount,firstCurrency,wallet2);
                        walletQualifier.increaseCurrency(amount * order.getPrice(), secondCurrency,wallet1);

                        Order newOrder = new Order();
                        newOrder.setState(order.getState());
                        newOrder.setAmount(order.getAmount() - amount);
                        newOrder.setPrice(order.getPrice());
                        newOrder.setPair(order.getPair());
                        newOrder.setUserId(order.getUserId());
                        newOrder.setType(order.getType());
                        orderDao.create(newOrder);
                    }
                    if ("Ask".equals(order.getType())) {

                        walletQualifier.reduceCurrency(amount * order.getPrice(),secondCurrency,wallet1);
                        walletQualifier.increaseCurrency(amount * order.getPrice(),secondCurrency,wallet2);
                        walletQualifier.increaseCurrency(amount , firstCurrency,wallet1);

                        Order newOrder = new Order();
                        newOrder.setState(order.getState());
                        newOrder.setAmount(order.getAmount() - amount);
                        newOrder.setPrice(order.getPrice());
                        newOrder.setPair(order.getPair());
                        newOrder.setUserId(order.getUserId());
                        newOrder.setType(order.getType());
                        orderDao.create(newOrder);
                    }



                }

                order.setState("executed");
                orderDao.update(order);
                walletDao.update(wallet1);
                walletDao.update(wallet2);
                connection.commit();
                connection.setAutoCommit(true);
                LOGGER.info("Order " + order.getIdentity() + " is executed");
            } catch (Exception e) {
                LOGGER.info("PersistentException in ExecuteOrderTransaction, method commit()");
                rollback();
                throw new PersistentException();
            }








    }

    @Override
    void rollback() throws PersistentException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.info("PersistentException in ExecuteOrderTransaction, method rollback()");
            throw new PersistentException();
        }
    }
}
