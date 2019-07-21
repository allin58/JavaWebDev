package dao.transaction;

import dao.sql.OrderDaoImpl;
import dao.sql.WalletDaoImpl;
import entity.Order;
import entity.Wallet;
import entity.qualifier.WalletQualifier;
import exception.PersistentException;

import java.sql.Connection;
import java.sql.SQLException;

public class CancelOrderTransaction extends DataBaseTransaction {

    private Order order;

    public CancelOrderTransaction(Connection connection) {
        super(connection);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

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
                case "Ask":    walletQualifier.increaseCurrency(order.getAmount(),stringArr[0],wallet);
                    break;

                case "Bid":    walletQualifier.increaseCurrency(order.getAmount() * order.getPrice() ,stringArr[1],wallet);
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
