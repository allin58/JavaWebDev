package dao.sql;

import dao.TransactionDao;
import entity.Transaction;
import exception.PersistentException;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl extends BaseDao implements TransactionDao {

    private static  String createSql = "INSERT INTO `transactions` (`user_id`, `coin_id`, `amount`, `type`, `date`, `status`) VALUES (?, ?, ?, ?, ?, ?)";
    private static  String readListSql = "SELECT `identity`,`user_id`, `coin_id`, `amount`, `type`, `date`, `status` FROM `transactions`";
    private static  String readSql = "SELECT `identity`,`user_id`, `coin_id`, `amount`, `type`, `date`, `status` FROM `transactions` WHERE `identity` = ?";
    private static  String updateSql = "UPDATE `transactions` SET `user_id` = ?, `coin_id` = ?, `amount` = ?, `type` = ?, `date` = ?, `status`= ? WHERE `identity` = ?";
    private static  String deleteSql = "DELETE FROM `transactions` WHERE `identity` = ?";






    @Override
    public List<Transaction> read() throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Transaction> arrayList = new ArrayList<>();
        Transaction transaction = null;

        try {
            statement = connection.prepareStatement(readListSql);

            resultSet = statement.executeQuery();

            while(resultSet.next()) {

                transaction = new Transaction();

                transaction.setIdentity(resultSet.getInt("identity"));
                transaction.setUserId(resultSet.getInt("user_id"));
                transaction.setCoinId((resultSet.getInt("coin_id")));
                transaction.setAmount((resultSet.getBigDecimal("amount")).doubleValue());
                transaction.setType((resultSet.getString("type")));
                transaction.setStatus((resultSet.getString("status")));
                transaction.setTimestamp((resultSet.getTimestamp("date")));

                arrayList.add(transaction);

            }

            return arrayList;
        } catch(SQLException e) {
            LOGGER.info("PersistentException in TransactionDaoImpl, method read()");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in TransactionDaoImpl, method read()");
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in TransactionDaoImpl, method read()");
            }
        }
    }

    @Override
    public Integer create(Transaction transaction) throws PersistentException {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(createSql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, transaction.getUserId());
            statement.setInt(2, transaction.getCoinId());
            statement.setBigDecimal(3, new BigDecimal(transaction.getAmount()));
            statement.setString(4, transaction.getType());
            statement.setTimestamp(5, transaction.getTimestamp());
            statement.setString(6, transaction.getStatus());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                LOGGER.info("PersistentException in TransactionDaoImpl, method create()");
                throw new PersistentException();
            }
        } catch(SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in TransactionDaoImpl, method create()");
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in TransactionDaoImpl, method create()");
            }
        }

    }

    @Override
    public Transaction read(Integer identity) throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(readSql);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            Transaction transaction = null;
            if(resultSet.next()) {
                transaction = new Transaction();
                transaction.setIdentity(resultSet.getInt("identity"));
                transaction.setUserId(resultSet.getInt("user_id"));
                transaction.setCoinId((resultSet.getInt("coin_id")));
                transaction.setAmount((resultSet.getBigDecimal("amount")).doubleValue());
                transaction.setType((resultSet.getString("type")));
                transaction.setTimestamp((resultSet.getTimestamp("date")));
                transaction.setStatus((resultSet.getString("status")));

            }
            return transaction;
        } catch(SQLException e) {
            LOGGER.info("PersistentException in TransactionDaoImpl, method read()");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in TransactionDaoImpl, method read()");
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in TransactionDaoImpl, method read()");
            }
        }
    }

    @Override
    public void update(Transaction transaction) throws PersistentException {
        PreparedStatement statement = null;
        try {

            statement = connection.prepareStatement(updateSql);

            statement.setInt(1, transaction.getUserId());
            statement.setInt(2, transaction.getCoinId());
            statement.setBigDecimal(3, new BigDecimal(transaction.getAmount()));
            statement.setString(4, transaction.getType());
            statement.setTimestamp(5, transaction.getTimestamp());
            statement.setString(6, transaction.getStatus());
            statement.setInt(7, transaction.getIdentity());


            statement.executeUpdate();
        } catch(SQLException e) {
            LOGGER.info("PersistentException in TransactionDaoImpl, method update()");
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in TransactionDaoImpl, method update()");
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
            LOGGER.info("PersistentException in TransactionDaoImpl, method delete()");
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in TransactionDaoImpl, method delete()");
            }
        }

    }
}