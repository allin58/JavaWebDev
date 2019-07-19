package service;

import dao.connectionpool.BasicConnectionPool;
import dao.sql.WalletDaoImpl;
import entity.Wallet;

import java.sql.Connection;

public class WalletService {

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
