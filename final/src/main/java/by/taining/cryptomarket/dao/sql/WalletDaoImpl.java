package by.taining.cryptomarket.dao.sql;

import by.taining.cryptomarket.entity.Wallet;
import by.taining.cryptomarket.exception.PersistentException;
import by.taining.cryptomarket.dao.WalletDao;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * The class of WalletDao implamentation.
 * @author Nikita Karchahin
 * @version 1.0
 */
public class WalletDaoImpl extends BaseDao implements WalletDao {

    /**
     * The field to store the sql create request.
     */
    private static  String createSql = "INSERT INTO `wallets` (`user_id`, `BTC`, `ETH`, `USDT`) VALUES (?, ?, ?, ?)";

    /**
     * The field to store the sql readList request.
     */
    private static  String readListSql = "SELECT `user_id`, `BTC`, `ETH`, `USDT` FROM `wallets`";

    /**
     * The field to store the sql read request.
     */
    private static  String readSql = "SELECT `user_id`, `BTC`, `ETH`, `USDT` FROM `wallets` WHERE `user_id` = ?";

    /**
     * The field to store the sql update request.
     */
    private static  String updateSql = "UPDATE `wallets` SET  `BTC` = ?, `ETH` = ?, `USDT` = ?  WHERE `user_id` = ?";

    /**
     * The field to store the sql delete request.
     */
    private static  String deleteSql = "DELETE FROM `wallets` WHERE `user_id` = ?";



    /**
     * The method that returns collection of wallets.
     * @return collection of wallets
     * @throws PersistentException
     */
    @Override
    public List<Wallet> read() throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Wallet> arrayList = new ArrayList<>();
        Wallet wallet = null;

        try {
            statement = connection.prepareStatement(readListSql);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                wallet = new Wallet();
                wallet.setIdentity(Integer.valueOf(resultSet.getString("user_id")));
                wallet.setBtc(resultSet.getBigDecimal("BTC").doubleValue());
                wallet.setEth(resultSet.getBigDecimal("ETH").doubleValue());
                wallet.setUsdt(resultSet.getBigDecimal("USDT").doubleValue());
                arrayList.add(wallet);

            }

            return arrayList;
        } catch (SQLException e) {
            LOGGER.info("PersistentException in WalletDaoImpl, method read()");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in WalletDaoImpl, method read()");
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in WalletDaoImpl, method read()");
            }
        }
    }


    /**
     * The method that creates a new record.
     * @param wallet wallet
     * @return number of record
     * @throws PersistentException PersistentException
     */
    @Override
    public Integer create(final Wallet wallet) throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(createSql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, wallet.getIdentity());
            statement.setBigDecimal(2, new BigDecimal(wallet.getBtc()));
            statement.setBigDecimal(3, new BigDecimal(wallet.getEth()));
            statement.setBigDecimal(4, new BigDecimal(wallet.getUsdt()));



            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();

            return wallet.getIdentity();
        } catch (SQLException e) {
            LOGGER.info("PersistentException in WalletDaoImpl, method create()");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in WalletDaoImpl, method create()");
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in WalletDaoImpl, method create()");
            }
        }
    }

    /**
     * The method that returns wallet by id.
     * @param identity
     * @return wallet
     * @throws PersistentException
     */
    @Override
    public Wallet read(final Integer identity) throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(readSql);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            Wallet wallet = null;
            if (resultSet.next()) {
                wallet = new Wallet();
                wallet.setIdentity(identity);
                wallet.setBtc(resultSet.getBigDecimal("BTC").doubleValue());
                wallet.setEth(resultSet.getBigDecimal("ETH").doubleValue());
                wallet.setUsdt(resultSet.getBigDecimal("USDT").doubleValue());

            }
            return wallet;
        } catch (SQLException e) {
            LOGGER.info("PersistentException in WalletDaoImpl, method read()");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in WalletDaoImpl, method read()");
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in WalletDaoImpl, method read()");
            }
        }
    }


    /**
     * The method that updates wallet.
     * @param wallet wallet
     * @throws PersistentException
     */
    @Override
    public void update(final Wallet wallet) throws PersistentException {

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(updateSql);
            statement.setInt(4, wallet.getIdentity());
            statement.setBigDecimal(1, new BigDecimal(wallet.getBtc()));
            statement.setBigDecimal(2, new BigDecimal(wallet.getEth()));
            statement.setBigDecimal(3, new BigDecimal(wallet.getUsdt()));
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info("PersistentException in WalletDaoImpl, method update()");
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in WalletDaoImpl, method update()");
            }
        }


    }

    /**
     *  The method that deletes wallet by id.
     * @param identity identity
     * @throws PersistentException PersistentException
     */
    @Override
    public void delete(final Integer identity) throws PersistentException {

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(deleteSql);
            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info("PersistentException in WalletDaoImpl, method delete()");
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in WalletDaoImpl, method delete()");
            }
        }


    }
}
