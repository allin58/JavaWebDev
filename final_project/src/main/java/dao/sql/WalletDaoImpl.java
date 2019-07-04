package dao.sql;

import dao.WalletDao;
import entity.User;
import entity.Wallet;
import exception.PersistentException;

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
                wallet.setBtc(Float.valueOf(resultSet.getString("BTC")));
                wallet.setEth(Float.valueOf(resultSet.getString("ETH")));
                wallet.setUsdt(Float.valueOf(resultSet.getString("USDT")));


                arrayList.add(wallet);

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
    public Integer create(Wallet wallet) throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(createSql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, wallet.getIdentity().toString());
            statement.setString(2, wallet.getBtc().toString());
            statement.setString(3, wallet.getEth().toString());
            statement.setString(4, wallet.getUsdt().toString());



            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();

            return wallet.getIdentity();
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
                wallet.setBtc(Float.valueOf(resultSet.getString("BTC")));
                wallet.setEth(Float.valueOf(resultSet.getString("ETH")));
                wallet.setUsdt(Float.valueOf(resultSet.getString("USDT")));

            }
            return wallet;
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
    public void update(Wallet wallet) throws PersistentException {

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(updateSql);
            statement.setFloat(4, wallet.getIdentity());
            statement.setFloat(1, wallet.getBtc());
            statement.setFloat(2, wallet.getEth());
            statement.setFloat(3, wallet.getUsdt());
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
