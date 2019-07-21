package dao.sql;

import dao.OrderDao;
import entity.Order;
import exception.PersistentException;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {




    private static  String createSql = "INSERT INTO `orders` (`user_id`, `pair`, `amount`, `price`, `type`, `state`) VALUES (?, ?, ?, ?, ?, ?)";
    private static  String readListSql = "SELECT `identity`,`user_id`, `pair`, `amount`, `price`, `type`, `state` FROM `orders`";
    private static  String readSql = "SELECT `user_id`, `pair`, `amount`, `price`, `type`, `state` FROM `orders` WHERE `identity` = ?";
    private static  String updateSql = "UPDATE `orders` SET `user_id` = ?, `pair` = ?, `amount` = ?, `price` = ?, `type` = ?, `state` = ? WHERE `identity` = ?";
    private static  String deleteSql = "DELETE FROM `orders` WHERE `identity` = ?";



    @Override
    public List<Order> read() throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Order> arrayList = new ArrayList<>();
        Order order = null;

        try {
            statement = connection.prepareStatement(readListSql);

            resultSet = statement.executeQuery();

            while(resultSet.next()) {

                order = new Order();

             order.setIdentity(resultSet.getInt("identity"));
             order.setUserId(resultSet.getInt("user_id"));
             order.setPair((resultSet.getString("pair")));
             order.setAmount((resultSet.getBigDecimal("amount")).doubleValue());
             order.setPrice((resultSet.getBigDecimal("price")).doubleValue());
             order.setType((resultSet.getString("type")));
             order.setState((resultSet.getString("state")));

             arrayList.add(order);

            }

            return arrayList;
        } catch(SQLException e) {
            LOGGER.info("PersistentException in OrderDaoImpl, method read()");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in OrderDaoImpl, method read()");
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in OrderDaoImpl, method read()");
            }
        }
    }

    @Override
    public Integer create(Order order) throws PersistentException {


        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(createSql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, order.getUserId());
            statement.setString(2, order.getPair());
            statement.setBigDecimal(3, new BigDecimal(order.getAmount()));
            statement.setBigDecimal(4, new BigDecimal(order.getPrice()));
            statement.setString(5, order.getType());
            statement.setString(6, order.getState());


            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                LOGGER.info("PersistentException in OrderDaoImpl, method create()");
                throw new PersistentException();
            }
        } catch(SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in OrderDaoImpl, method create()");
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in OrderDaoImpl, method create()");
            }
        }


     }

    @Override
    public Order read(Integer identity) throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(readSql);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            Order order = null;
            if(resultSet.next()) {

                order = new Order();
                order.setIdentity(identity);
                order.setUserId(resultSet.getInt("user_id"));
                order.setPair(resultSet.getString("pair"));
                order.setAmount(resultSet.getBigDecimal("amount").doubleValue());
                order.setPrice(resultSet.getBigDecimal("price").doubleValue());
                order.setType(resultSet.getString("type"));
                order.setState(resultSet.getString("state"));



            }
            return order;
        } catch(SQLException e) {
            LOGGER.info("PersistentException in OrderDaoImpl, method read()");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in OrderDaoImpl, method read()");
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in OrderDaoImpl, method read()");
            }
        }
    }

    @Override
    public void update(Order order) throws PersistentException {



        PreparedStatement statement = null;
        try {

            statement = connection.prepareStatement(updateSql);

            statement.setInt(1, order.getUserId());
            statement.setString(2, order.getPair());
            statement.setBigDecimal(3, new BigDecimal(order.getAmount()));
            statement.setBigDecimal(4, new BigDecimal(order.getPrice()));
            statement.setString(5, order.getType());
            statement.setString(6, order.getState());
            statement.setInt(7, order.getIdentity());


            statement.executeUpdate();
        } catch(SQLException e) {
            LOGGER.info("PersistentException in OrderDaoImpl, method update()");
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in OrderDaoImpl, method update()");
            }
        }

    }

    @Override
    public void delete(Integer identity) throws PersistentException {

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(deleteSql);
            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch(SQLException e) {
            LOGGER.info("PersistentException in OrderDaoImpl, method delete()");
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in OrderDaoImpl, method delete()");
            }
        }
    }
}
