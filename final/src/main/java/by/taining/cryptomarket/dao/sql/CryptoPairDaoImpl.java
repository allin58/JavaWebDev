package by.taining.cryptomarket.dao.sql;

import by.taining.cryptomarket.entity.CryptoPair;
import by.taining.cryptomarket.exception.PersistentException;
import by.taining.cryptomarket.dao.CryptoPairDao;
import by.taining.cryptomarket.entity.Coin;
import by.taining.cryptomarket.entity.CryptoPair;
import by.taining.cryptomarket.exception.PersistentException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * The class of CryptoPairDao implamentation.
 * @author Nikita Karchahin
 * @version 1.0
 */
public class CryptoPairDaoImpl extends BaseDao implements CryptoPairDao {

    /**
     * The field to store the sql create request.
     */
    private static  String createSql = "INSERT INTO `cryptocurrency_pairs` (`identity`,`first_currency`, `second_currency`, `active`) VALUES (?,?,?,?)";

    /**
     * The field to store the sql readList request.
     */
    private static  String readListSql = "SELECT `identity`,`first_currency`, `second_currency`, `active` FROM `cryptocurrency_pairs`";

    /**
     * The field to store the sql read request.
     */
    private static  String readSql = "SELECT `identity`,`first_currency`, `second_currency`, `active` FROM `cryptocurrency_pairs` WHERE `identity` = ?";

    /**
     * The field to store the sql update request.
     */
    private static  String updateSql = "UPDATE `cryptocurrency_pairs` SET `first_currency` = ?, `second_currency` = ? , `active` = ? WHERE `identity` = ?";

    /**
     * The field to store the sql delete request.
     */
    private static  String deleteSql = "DELETE FROM `cryptocurrency_pairs` WHERE `identity` = ?";




    /**
     * The method that returns collection of CryptoPair.
     * @return collection of CryptoPair
     * @throws PersistentException
     */
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
            LOGGER.info("PersistentException in CryptoPairDaoImpl, method read()");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in CryptoPairDaoImpl, method read()");
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in CryptoPairDaoImpl, method read()");
            }
        }
    }


    /**
     * The method that creates a new record.
     * @param cryptoPair cryptoPair
     * @return number of record
     * @throws PersistentException
     */
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
            LOGGER.info("PersistentException in CryptoPairDaoImpl, method create()");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in CryptoPairDaoImpl, method create()");
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in CryptoPairDaoImpl, method create()");
            }
        }


    }

    /**
     * The method that returns cryptoPair by id.
     * @param identity
     * @return cryptoPair
     * @throws PersistentException
     */
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
            LOGGER.info("PersistentException in CryptoPairDaoImpl, method read()");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in CryptoPairDaoImpl, method read()");
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in CryptoPairDaoImpl, method read()");
            }
        }
    }

    /**
     * The method that updates cryptoPair.
     * @param cryptoPair cryptoPair
     * @throws PersistentException
     */
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
            LOGGER.info("PersistentException in CryptoPairDaoImpl, method update()");
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in CryptoPairDaoImpl, method update()");
            }
        }

    }

    /**
     *  The method that deletes cryptoPair by id.
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
            LOGGER.info("PersistentException in CryptoPairDaoImpl, method delete()");
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in CryptoPairDaoImpl, method delete()");
            }
        }

    }
}
