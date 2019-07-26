package by.taining.cryptomarket.dao.transaction;


import by.taining.cryptomarket.entity.Order;
import by.taining.cryptomarket.entity.Wallet;
import by.taining.cryptomarket.entity.qualifier.WalletQualifier;
import by.taining.cryptomarket.exception.PersistentException;
import by.taining.cryptomarket.dao.sql.WalletDaoImpl;
import by.taining.cryptomarket.entity.Order;
import by.taining.cryptomarket.entity.Wallet;
import by.taining.cryptomarket.entity.qualifier.WalletQualifier;
import by.taining.cryptomarket.exception.PersistentException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SetOrderTransaction extends DataBaseTransaction {


    private static  String createSql = "INSERT INTO `orders` (`user_id`,`pair`,`amount`,`price`,`type`,`state`) VALUES (?, ?, ?, ?, ?, ?)";


    public SetOrderTransaction(Connection connection) {
        super(connection);

    }

    private Order order;


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public void commit() throws PersistentException {



        try{
            WalletDaoImpl walletDao = new WalletDaoImpl();
            walletDao.setConnection(connection);
            Wallet wallet = walletDao.read(order.getUserId());
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(createSql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, order.getUserId());
            statement.setString(2, order.getPair());
            statement.setBigDecimal(3, new BigDecimal(order.getAmount()));
            statement.setBigDecimal(4, new BigDecimal(order.getPrice()));
            statement.setString(5, order.getType());
            statement.setString(6, order.getState());
            statement.executeUpdate();
            String[] stringArr = order.getPair().split("-");
            WalletQualifier walletQualifier = new WalletQualifier();

            switch (order.getType()) {
                case "Ask": walletQualifier.reduceCurrency(order.getAmount(),stringArr[0],wallet);
                    break;

                case "Bid": walletQualifier.reduceCurrency(order.getAmount() * order.getPrice() ,stringArr[1],wallet);
                    break;
            }
            walletDao.update(wallet);
            connection.commit();
            connection.setAutoCommit(true);

            LOGGER.info("Order " + order.getIdentity() + " is set");

        } catch (Exception e) {
            LOGGER.info("PersistentException in SetOrderTransaction, method commit()");
            rollback();
            throw new PersistentException();
        }

    }

    @Override
    public void rollback() throws PersistentException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.info("PersistentException in SetOrderTransaction, method rollback()");
            throw new PersistentException();
        }
    }




}