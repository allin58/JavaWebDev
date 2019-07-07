package dao.transaction;



import dao.connectionpool.BasicConnectionPool;
import dao.sql.WalletDaoImpl;
import entity.Order;
import entity.Wallet;
import exception.PersistentException;

import java.math.BigDecimal;
import java.sql.*;

public class SetOrderTransaction implements DataBaseTransaction {


    private static  String createSql = "INSERT INTO `orders` (`user_id`,`pair`,`amount`,`price`,`type`,`state`) VALUES (?, ?, ?, ?, ?, ?)";





    public SetOrderTransaction(Connection connection) {

        this.connection = connection;
    }

    private Order order;
    private Connection connection;

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

            switch (order.getType()) {
                case "Ask": wallet.reduceCurrency(order.getAmount(),stringArr[0]);
                    break;

                case "Bid": wallet.reduceCurrency(order.getAmount(),stringArr[1]);
                    break;
            }
            walletDao.update(wallet);
            connection.commit();


        } catch (Exception e) {
            rollback();
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