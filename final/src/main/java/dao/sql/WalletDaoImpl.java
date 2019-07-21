package dao.sql;

import dao.WalletDao;
import entity.Wallet;
import exception.PersistentException;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WalletDaoImpl extends BaseDao implements WalletDao {
    private static  String createSql = "INSERT INTO `wallets` (`user_id`, `BTC`, `ETH`, `USDT`) VALUES (?, ?, ?, ?)";
    private static  String readListSql = "SELECT `user_id`, `BTC`, `ETH`, `USDT` FROM `wallets`";
    private static  String readSql = "SELECT `user_id`, `BTC`, `ETH`, `USDT` FROM `wallets` WHERE `user_id` = ?";
    private static  String updateSql = "UPDATE `wallets` SET  `BTC` = ?, `ETH` = ?, `USDT` = ?  WHERE `user_id` = ?";
    private static  String deleteSql = "DELETE FROM `wallets` WHERE `user_id` = ?";



    @Override
    public List<Wallet> read() throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Wallet> arrayList = new ArrayList<>();
        Wallet wallet = null;

        try {
            statement = connection.prepareStatement(readListSql);

            resultSet = statement.executeQuery();

            while(resultSet.next()) {

                wallet = new Wallet();
                wallet.setIdentity(Integer.valueOf(resultSet.getString("user_id")));
                wallet.setBtc(resultSet.getBigDecimal("BTC").doubleValue());
                wallet.setEth(resultSet.getBigDecimal("ETH").doubleValue());
                wallet.setUsdt(resultSet.getBigDecimal("USDT").doubleValue());


                arrayList.add(wallet);

            }

            return arrayList;
        } catch(SQLException e) {
            LOGGER.info("PersistentException in WalletDaoImpl, method read()");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in WalletDaoImpl, method read()");
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in WalletDaoImpl, method read()");
            }
        }
    }

    @Override
    public Integer create(Wallet wallet) throws PersistentException {
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
        } catch(SQLException e) {
            LOGGER.info("PersistentException in WalletDaoImpl, method create()");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in WalletDaoImpl, method create()");
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in WalletDaoImpl, method create()");
            }
        }
    }

    @Override
    public Wallet read(Integer identity) throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(readSql);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            Wallet wallet = null;
            if(resultSet.next()) {
                wallet = new Wallet();
                wallet.setIdentity(identity);
                wallet.setBtc(resultSet.getBigDecimal("BTC").doubleValue());
                wallet.setEth(resultSet.getBigDecimal("ETH").doubleValue());
                wallet.setUsdt(resultSet.getBigDecimal("USDT").doubleValue());

            }
            return wallet;
        } catch(SQLException e) {
            LOGGER.info("PersistentException in WalletDaoImpl, method read()");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close resultSet in WalletDaoImpl, method read()");
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in WalletDaoImpl, method read()");
            }
        }
    }

    @Override
    public void update(Wallet wallet) throws PersistentException {

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(updateSql);
            statement.setInt(4, wallet.getIdentity());
            statement.setBigDecimal(1, new BigDecimal(wallet.getBtc()));
            statement.setBigDecimal(2, new BigDecimal(wallet.getEth()));
            statement.setBigDecimal(3, new BigDecimal(wallet.getUsdt()));
            statement.executeUpdate();
        } catch(SQLException e) {
            LOGGER.info("PersistentException in WalletDaoImpl, method update()");
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in WalletDaoImpl, method update()");
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
            LOGGER.info("PersistentException in WalletDaoImpl, method delete()");
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                LOGGER.info("failed to close statement in WalletDaoImpl, method delete()");
            }
        }


    }
}
