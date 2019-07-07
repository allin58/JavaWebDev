package dao.sql;

import dao.TransactionDao;
import entity.Order;
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

    private static  String createSql = "INSERT INTO `transactions` (`user_id`, `coin_id`, `amount`, `type`, `date`) VALUES (?, ?, ?, ?, ?)";
    private static  String readListSql = "SELECT `identity`,`user_id`, `coin_id`, `amount`, `type`, `date` FROM `transactions`";
    private static  String readSql = "SELECT `identity`,`user_id`, `coin_id`, `amount`, `type`, `date` FROM `transactions` WHERE `identity` = ?";
    private static  String updateSql = "UPDATE `transactions` SET `user_id` = ?, `coin_id` = ?, `amount` = ?, `type` = ?, `date` = ? WHERE `identity` = ?";
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
                transaction.setTimestamp((resultSet.getTimestamp("date")));

                arrayList.add(transaction);

            }

            return arrayList;
        } catch(SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
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

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                //  logger.error("There is no autoincremented index after trying to add record into table `authors`");
                throw new PersistentException();
            }
        } catch(SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
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

            }
            return transaction;
        } catch(SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
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
            statement.setInt(6, transaction.getIdentity());

            statement.executeUpdate();
        } catch(SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
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
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }

    }
}
