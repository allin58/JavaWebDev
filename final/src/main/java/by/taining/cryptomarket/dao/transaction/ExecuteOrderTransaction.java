package by.taining.cryptomarket.dao.transaction;

import by.taining.cryptomarket.entity.Order;
import by.taining.cryptomarket.entity.User;
import by.taining.cryptomarket.entity.Wallet;
import by.taining.cryptomarket.entity.qualifier.WalletQualifier;
import by.taining.cryptomarket.exception.PersistentException;
import by.taining.cryptomarket.dao.sql.OrderDaoImpl;
import by.taining.cryptomarket.dao.sql.WalletDaoImpl;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class is responsible for execution of order.
 * @author Nikita Karchahin
 * @version 1.0
 */
public class ExecuteOrderTransaction extends DataBaseTransaction {

    /**
     * The field for storage a amount.
     */
   private Double amount;

    /**
     * The field for storage a user.
     */
   private User user;

    /**
     * The field for storage a order.
     */
   private Order order;


    /**
     * The getter for amount.
     * @return amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * The setter for amount.
     * @param amount amount
     */
    public void setAmount(final Double amount) {
        this.amount = amount;
    }


    /**
     * The getter for user.
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * The setter for user.
     * @param user user
     */
    public void setUser(final User user) {
        this.user = user;
    }


    /**
     * The getter for order.
     * @return order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * The setter for order.
     * @param order order
     */
    public void setOrder(final Order order) {
        this.order = order;
    }

    /**
     * The constructor with a parameter.
     * @param connection connection
     */
    public ExecuteOrderTransaction(final Connection connection) {
        super(connection);
    }


    /**
     * Transaction method that executes orders.
     * @throws PersistentException
     */
    @Override
    public void commit() throws PersistentException {


            try {
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



                } else {

                    if ("Bid".equals(order.getType())) {

                        walletQualifier.reduceCurrency(amount, firstCurrency, wallet1);
                        walletQualifier.increaseCurrency(amount, firstCurrency, wallet2);
                        walletQualifier.increaseCurrency(amount * order.getPrice(), secondCurrency, wallet1);

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

                        walletQualifier.reduceCurrency(amount * order.getPrice(), secondCurrency, wallet1);
                        walletQualifier.increaseCurrency(amount * order.getPrice(), secondCurrency, wallet2);
                        walletQualifier.increaseCurrency(amount, firstCurrency, wallet1);

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


    /**
     * The transaction rollback method.
     * @throws PersistentException
     */
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
