package main;

import dao.CoinDao;
import dao.WalletDao;
import dao.connectionpool.BasicConnectionPool;
import dao.connectionpool.ConnectionPool;
import dao.sql.CoinDaoImpl;
import dao.sql.UserDaoImpl;
import dao.sql.WalletDaoImpl;
import entity.Coin;
import entity.User;
import entity.Wallet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
String url = "jdbc:mysql://localhost:3306/market";
String username = "market";
String password = "market";


        try {
            UserDaoImpl userDao = new UserDaoImpl();
            CoinDaoImpl coinDao = new CoinDaoImpl();
            WalletDaoImpl walletDao =new WalletDaoImpl();
            ConnectionPool connectionPool = BasicConnectionPool.create(url,username,password);
            walletDao.setConnection(connectionPool.getConnection());
            userDao.setConnection(connectionPool.getConnection());

           /* User user = new User();
                       user.setName("void3");
            user.setUserName("void2");
            user.setSurname("void2");
            user.setRole("void2");
            user.setHashOfPassword("void2");
            userDao.update(user);*/

       /*   Wallet wallett = new Wallet();
            wallett.setIdentity(5);
            wallett.setBtc(Float.valueOf(521));
            wallett.setEth(Float.valueOf(521));
            wallett.setUsdt(Float.valueOf(522));
            walletDao.create(wallett);*/


       Wallet wallett = walletDao.read(7);
            wallett.setBtc(Float.valueOf(0));
            walletDao.update(wallett);

            for (Wallet wallet : walletDao.read()) {
                System.out.println(wallet.getIdentity()+" "+wallet.getUsdt()+" "+ wallet.getBtc() +" "+wallet.getEth());
            }





            connectionPool.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }








    }


}
