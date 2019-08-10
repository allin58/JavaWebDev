package by.taining.cryptomarket.dao.transaction;

import by.taining.cryptomarket.entity.Order;
import by.taining.cryptomarket.entity.Wallet;
import by.taining.cryptomarket.entity.qualifier.WalletQualifier;
import by.taining.cryptomarket.exception.PersistentException;
import by.taining.cryptomarket.dao.sql.OrderDaoImpl;
import by.taining.cryptomarket.dao.sql.WalletDaoImpl;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class is responsible for canceling of order.
 * @author Nikita Karchahin
 * @version 1.0
 */
public class CancelOrderTransaction extends DataBaseTransaction {

    /**
     * The field for storage a order.
     */
    private Order order;

    /**
     * The constructor with a parameter.
     * @param connection connection
     */
    public CancelOrderTransaction(final Connection connection) {
        super(connection);
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
     * Transaction method that cancels orders.
     * @throws PersistentException
     */
    @Override
    public void commit() throws PersistentException {


        try {
            WalletDaoImpl walletDao = new WalletDaoImpl();
            OrderDaoImpl orderDao = new OrderDaoImpl();
            walletDao.setConnection(connection);
            orderDao.setConnection(connection);
            Wallet wallet = walletDao.read(order.getUserId());
            connection.setAutoCommit(false);
            String[] stringArr = order.getPair().split("-");
            WalletQualifier walletQualifier = new WalletQualifier();


            switch (order.getType()) {
                case "Ask":    walletQualifier.increaseCurrency(order.getAmount(), stringArr[0], wallet);
                    break;

                case "Bid":    walletQualifier.increaseCurrency(order.getAmount() * order.getPrice(), stringArr[1], wallet);
                    break;
            }
            order.setState("canceled");
            walletDao.update(wallet);
            orderDao.update(order);
            connection.commit();
            connection.setAutoCommit(true);

            LOGGER.info("Order " + order.getIdentity() + " is canceled");
        } catch (SQLException e) {
            LOGGER.info("PersistentException in CancelOrderTransaction, method commit()");
            throw new PersistentException();
        }


    }

    /**
     * The transaction rollback method.
     * @throws PersistentException
     */
    @Override
    public void rollback() throws PersistentException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.info("PersistentException in CancelOrderTransaction, method rollback()");
            throw new PersistentException();
        }
    }
}
