package by.taining.cryptomarket.service;

import by.taining.cryptomarket.dao.connectionpool.BasicConnectionPool;
import by.taining.cryptomarket.dao.sql.WalletDaoImpl;
import by.taining.cryptomarket.entity.Wallet;

import java.sql.Connection;

/**
 * This service ensures interact with wallet.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class WalletService {


    /**
     * The method for getting wallet by identity.
     * @param identity
     * @return wallet
     * @throws Exception
     */
    public Wallet getWalletByUserId(Integer identity) throws Exception {


        Connection connection = null;
        Wallet wallet = null;
        try {
            connection = BasicConnectionPool.getBasicConnectionPool().getConnection();
            WalletDaoImpl walletDao = new WalletDaoImpl();
            walletDao.setConnection(connection);
            wallet = walletDao.read(identity);

        }
        finally {
            BasicConnectionPool.getBasicConnectionPool().releaseConnection(connection);
        }

        return wallet;
    }


    /**
     * The method for adding new wallet.
     * @param wallet
     * @throws Exception
     */
    public void addNewWallet(Wallet wallet) throws Exception {


        Connection connection = null;

        try {
            connection = BasicConnectionPool.getBasicConnectionPool().getConnection();
            WalletDaoImpl walletDao = new WalletDaoImpl();
            walletDao.setConnection(connection);
            walletDao.create(wallet);

        }
        finally {
            BasicConnectionPool.getBasicConnectionPool().releaseConnection(connection);
        }



    }


}
