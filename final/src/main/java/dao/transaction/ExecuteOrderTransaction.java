package dao.transaction;

import dao.sql.OrderDaoImpl;
import dao.sql.WalletDaoImpl;
import entity.Order;
import entity.User;
import entity.Wallet;
import entity.qualifier.WalletQualifier;
import exception.PersistentException;

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

        if ("Bid".equals(order.getType())) {
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
                    walletQualifier.reduceCurrency(order.getAmount(),firstCurrency,wallet1);
                    walletQualifier.increaseCurrency(order.getAmount(),firstCurrency,wallet2);
                    walletQualifier.increaseCurrency(order.getAmount() * order.getPrice(),secondCurrency,wallet1);
                }else {
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

                order.setState("executed");
                orderDao.update(order);
                walletDao.update(wallet1);
                walletDao.update(wallet2);
                connection.commit();
                connection.setAutoCommit(true);

            } catch (Exception e) {
                rollback();
                throw new PersistentException();
            }
        }
    }

    @Override
    void rollback() throws PersistentException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new PersistentException();
        }
    }
}
