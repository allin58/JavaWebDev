package by.taining.cryptomarket.dao.sql;

import by.taining.cryptomarket.entity.Order;
import by.taining.cryptomarket.exception.PersistentException;
import by.taining.cryptomarket.dao.OrderDao;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * The class of OrderDao implamentation.
 * @author Nikita Karchahin
 * @version 1.0
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {



    /**
     * The field to store the sql create request.
     */
    private static  String createSql = "INSERT INTO `orders` (`user_id`, `pair`, `amount`, `price`, `type`, `state`) VALUES (?, ?, ?, ?, ?, ?)";

    /**
     * The field to store the sql readList request.
     */
    private static  String readListSql = "SELECT `identity`,`user_id`, `pair`, `amount`, `price`, `type`, `state` FROM `orders`";

    /**
     * The field to store the sql read request.
     */
    private static  String readSql = "SELECT `user_id`, `pair`, `amount`, `price`, `type`, `state` FROM `orders` WHERE `identity` = ?";

    /**
     * The field to store the sql update request.
     */
    private static  String updateSql = "UPDATE `orders` SET `user_id` = ?, `pair` = ?, `amount` = ?, `price` = ?, `type` = ?, `state` = ? WHERE `identity` = ?";

    /**
     * The field to store the sql delete request.
     */
    private static  String deleteSql = "DELETE FROM `orders` WHERE `identity` = ?";



    /**
     * The method that returns collection of orders.
     * @return collection of orders
     * @throws PersistentException
     */
    @Override
    public List<Order> read() throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Order> arrayList = new ArrayList<>();
        Order order = null;

        try {
            statement = connection.prepareStatement(readListSql);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {

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
        } catch (SQLException e) {
            LOGGER.info("PersistentException in OrderDaoImpl, method read()");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in OrderDaoImpl, method read()");
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in OrderDaoImpl, method read()");
            }
        }
    }


    /**
     * The method that creates a new record.
     * @param order order
     * @return number of record
     * @throws PersistentException
     */
    @Override
    public Integer create(final Order order) throws PersistentException {


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
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                LOGGER.info("PersistentException in OrderDaoImpl, method create()");
                throw new PersistentException();
            }
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in OrderDaoImpl, method create()");
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in OrderDaoImpl, method create()");
            }
        }


     }

    /**
     * The method that returns order by id.
     * @param identity
     * @return order
     * @throws PersistentException
     */
    @Override
    public Order read(final Integer identity) throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(readSql);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            Order order = null;
            if (resultSet.next()) {

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
        } catch (SQLException e) {
            LOGGER.info("PersistentException in OrderDaoImpl, method read()");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in OrderDaoImpl, method read()");
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in OrderDaoImpl, method read()");
            }
        }
    }


    /**
     * The method that updates order.
     * @param order order
     * @throws PersistentException PersistentException
     */
    @Override
    public void update(final Order order) throws PersistentException {



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
        } catch (SQLException e) {
            LOGGER.info("PersistentException in OrderDaoImpl, method update()");
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in OrderDaoImpl, method update()");
            }
        }

    }

    /**
     *  The method that deletes order by id.
     * @param identity identity
     * @throws PersistentException
     */
    @Override
    public void delete(final Integer identity) throws PersistentException {

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(deleteSql);
            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info("PersistentException in OrderDaoImpl, method delete()");
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in OrderDaoImpl, method delete()");
            }
        }
    }
}
