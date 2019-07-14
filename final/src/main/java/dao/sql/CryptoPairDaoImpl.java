package dao.sql;

import dao.CryptoPairDao;
import entity.Coin;
import entity.CryptoPair;
import exception.PersistentException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CryptoPairDaoImpl extends BaseDao implements CryptoPairDao {

    private static  String createSql = "INSERT INTO `cryptocurrency_pairs` (`identity`,`first_currency`, `second_currency`, `active`) VALUES (?,?,?,?)";
    private static  String readListSql = "SELECT `identity`,`first_currency`, `second_currency`, `active` FROM `cryptocurrency_pairs`";
    private static  String readSql = "SELECT `identity`,`first_currency`, `second_currency`, `active` FROM `cryptocurrency_pairs` WHERE `identity` = ?";
    private static  String updateSql = "UPDATE `cryptocurrency_pairs` SET `first_currency` = ?, `second_currency` = ? , `active` = ? WHERE `identity` = ?";
    private static  String deleteSql = "DELETE FROM `cryptocurrency_pairs` WHERE `identity` = ?";




    @Override
    public List<CryptoPair> read() throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<CryptoPair> arrayList = new ArrayList<>();
        CryptoPair cryptoPair = null;

        try {
            statement = connection.prepareStatement(readListSql);

            resultSet = statement.executeQuery();

            while(resultSet.next()) {

                cryptoPair = new CryptoPair();
                cryptoPair.setIdentity((resultSet.getInt("identity")));
                cryptoPair.setFirstCurrency(resultSet.getInt("first_currency"));
                cryptoPair.setSecondCurrency(resultSet.getInt("second_currency"));
                cryptoPair.setActive(resultSet.getBoolean("active"));
                arrayList.add(cryptoPair);
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
    public Integer create(CryptoPair cryptoPair) throws PersistentException {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(createSql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, cryptoPair.getIdentity());
            statement.setInt(2, cryptoPair.getFirstCurrency());
            statement.setInt(3, cryptoPair.getSecondCurrency());
            statement.setBoolean(4, cryptoPair.getActive());


            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            return cryptoPair.getIdentity();

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
    public CryptoPair read(Integer identity) throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(readSql);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            CryptoPair cryptoPair = null;
            if(resultSet.next()) {

                cryptoPair = new CryptoPair();


                cryptoPair.setIdentity(identity);
                cryptoPair.setFirstCurrency(resultSet.getInt("identity"));
                cryptoPair.setFirstCurrency(resultSet.getInt("first_currency"));
                cryptoPair.setSecondCurrency(resultSet.getInt("second_currency"));
                cryptoPair.setActive(resultSet.getBoolean("active"));

            }
            return cryptoPair;
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
    public void update(CryptoPair cryptoPair) throws PersistentException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(updateSql);


            statement.setInt(1, cryptoPair.getFirstCurrency());
            statement.setInt(2, cryptoPair.getSecondCurrency());
            statement.setBoolean(3, cryptoPair.getActive());
            statement.setInt(4, cryptoPair.getIdentity());

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
