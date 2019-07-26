package by.taining.cryptomarket.dao.sql;

import by.taining.cryptomarket.entity.Coin;
import by.taining.cryptomarket.exception.PersistentException;
import by.taining.cryptomarket.dao.CoinDao;
import by.taining.cryptomarket.entity.Coin;
import by.taining.cryptomarket.exception.PersistentException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * The class of CoinDao implamentation.
 * @author Nikita Karchahin
 * @version 1.0
 */
public class CoinDaoImpl extends BaseDao implements CoinDao {

    /**
     * The field to store the sql create request.
     */
    private static  String createSql = "INSERT INTO `coins` (`identity`,`coin`, `full_name`) VALUES (?,?, ?)";

    /**
     * The field to store the sql readList request.
     */
    private static  String readListSql = "SELECT `identity`,`coin`, `full_name` FROM `coins`";

    /**
     * The field to store the sql read request.
     */
    private static  String readSql = "SELECT `coin`, `full_name` FROM `coins` WHERE `identity` = ?";

    /**
     * The field to store the sql update request.
     */
    private static  String updateSql = "UPDATE `coins` SET `coin` = ?, `full_name` = ? WHERE `identity` = ?";

    /**
     * The field to store the sql delete request.
     */
    private static  String deleteSql = "DELETE FROM `coins` WHERE `identity` = ?";


    /**
     * The method that returns collection of coins.
     * @return collection of coins
     * @throws PersistentException
     */
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
            LOGGER.info("PersistentException in CoinDaoImpl, method read()");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in CoinDaoImpl, method read()");
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in CoinDaoImpl, method read()");
            }
        }
    }



    /**
     * The method that creates a new record.
     * @param coin coin
     * @return number of record
     * @throws PersistentException
     */
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
            LOGGER.info("PersistentException in CoinDaoImpl, method create()");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in CoinDaoImpl, method create()");
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in CoinDaoImpl, method create()");
            }
        }


    }



    /**
     * The method that returns coin by id.
     * @param identity
     * @return coin
     * @throws PersistentException
     */
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
            LOGGER.info("PersistentException in CoinDaoImpl, method read()");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in CoinDaoImpl, method read()");
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in CoinDaoImpl, method read()");
            }
        }
    }


    /**
     * The method that updates coin.
     * @param coin coin
     * @throws PersistentException
     */
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
            LOGGER.info("PersistentException in CoinDaoImpl, method update()");
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in CoinDaoImpl, method update()");
            }
        }

    }

    /**
     *  The method that deletes coin by id.
     * @param identity identity
     * @throws PersistentException
     */
    @Override
    public void delete(Integer identity) throws PersistentException {

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(deleteSql);
            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch(SQLException e) {
            LOGGER.info("PersistentException in CoinDaoImpl, method delete()");
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in CoinDaoImpl, method delete()");
            }
        }


    }
}
