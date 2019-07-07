package dao.transaction;

import dao.sql.OrderDaoImpl;
import dao.sql.WalletDaoImpl;
import entity.Order;
import entity.Wallet;
import exception.PersistentException;

import java.sql.Connection;
import java.sql.SQLException;

public class CancelOrderTransaction implements DataBaseTransaction {

    private Order order;
    private Connection connection;


    public CancelOrderTransaction(Connection connection) {
        this.connection = connection;
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

            switch (order.getType()) {
                case "Ask": wallet.increaseCurrency(order.getAmount(),stringArr[0]);
                    break;

                case "Bid": wallet.increaseCurrency(order.getAmount(),stringArr[1]);
                    break;
            }
            order.setState("canceled");
            walletDao.update(wallet);
            orderDao.update(order);
            connection.commit();


        } catch (SQLException e) {
            throw new PersistentException();
        }


    }

    @Override
    public void rollback() throws PersistentException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new PersistentException();
        }
    }
}
