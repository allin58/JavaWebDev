package dao.sql;

import dao.CoinDao;
import entity.Coin;
import entity.User;
import exception.PersistentException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CoinDaoImpl extends BaseDao implements CoinDao {


    private static  String createSql = "INSERT INTO `coins` (`identity`,`coin`, `full_name`) VALUES (?,?, ?)";
    private static  String readListSql = "SELECT `identity`,`coin`, `full_name` FROM `coins`";
    private static  String readSql = "SELECT `coin`, `full_name` FROM `coins` WHERE `identity` = ?";
    private static  String updateSql = "UPDATE `coins` SET `coin` = ?, `full_name` = ? WHERE `identity` = ?";
    private static  String deleteSql = "DELETE FROM `coins` WHERE `identity` = ?";


    @Override
    public List<Coin> read() throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Coin> arrayList = new ArrayList<>();
        Coin coin = null;

        try {
            statement = connection.prepareStatement(readListSql);

            resultSet = statement.executeQuery();

            while(resultSet.next()) {

                coin = new Coin();
                coin.setIdentity(resultSet.getInt("identity"));
                coin.setTicker(resultSet.getString("coin"));
                coin.setFullName(resultSet.getString("full_name"));
                arrayList.add(coin);

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
    public Integer create(Coin coin) throws PersistentException {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(createSql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, coin.getIdentity());
            statement.setString(2, coin.getTicker());
            statement.setString(3, coin.getFullName());


            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            return coin.getIdentity();

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
    public Coin read(Integer identity) throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(readSql);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            Coin coin = null;
            if(resultSet.next()) {
                coin = new Coin();
                coin.setIdentity(identity);
                coin.setTicker(resultSet.getString("coin"));
                coin.setFullName(resultSet.getString("full_name"));


            }
            return coin;
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
    public void update(Coin coin) throws PersistentException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(updateSql);
            statement.setString(1, coin.getTicker());
            statement.setString(2, coin.getFullName());
            statement.setInt(3, coin.getIdentity());
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
