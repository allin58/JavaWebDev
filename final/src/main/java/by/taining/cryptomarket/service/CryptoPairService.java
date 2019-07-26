package by.taining.cryptomarket.service;

import by.taining.cryptomarket.dao.connectionpool.BasicConnectionPool;
import by.taining.cryptomarket.dao.sql.CoinDaoImpl;
import by.taining.cryptomarket.dao.sql.CryptoPairDaoImpl;
import by.taining.cryptomarket.entity.CryptoPair;
import by.taining.cryptomarket.entity.mapping.TraidingCouple;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


/**
 * This service ensures interact with cryptocurrency pairs.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class CryptoPairService {

    /**
     * The method for getting all pairs.
     * @return collection of all pairs
     * @throws Exception
     */
   public List<TraidingCouple> getAllPairs() throws Exception {
       Connection connection = null;
       List<CryptoPair> cryptoPairs;
       List<TraidingCouple> traidingCouples = new ArrayList<>();
       try {
           connection = BasicConnectionPool.getBasicConnectionPool().getConnection();
           CryptoPairDaoImpl cryptoPairDao = new CryptoPairDaoImpl();
           cryptoPairDao.setConnection(connection);
           cryptoPairs = cryptoPairDao.read();

           CoinDaoImpl coinDao = new CoinDaoImpl();
           coinDao.setConnection(connection);


           for (CryptoPair cryptoPair : cryptoPairs) {

               TraidingCouple traidingCouple = new TraidingCouple();

               traidingCouple.setIdentity(cryptoPair.getIdentity());
               traidingCouple.setActive(cryptoPair.getActive());
               Integer first = cryptoPair.getFirstCurrency();
               Integer second = cryptoPair.getSecondCurrency();
               String pair = coinDao.read(first).getTicker() + "-" + coinDao.read(second).getTicker();
               traidingCouple.setPair(pair);
               traidingCouples.add(traidingCouple);
           }


       } finally {
           BasicConnectionPool.getBasicConnectionPool().releaseConnection(connection);
       }


      return  traidingCouples;

   }


    /**
     * The method for getting active pairs.
     * @return collection of active pairs
     * @throws Exception
     */
    public List<TraidingCouple> getActivePairs() throws Exception {
        List<TraidingCouple> traidingCouples = new ArrayList<>();
        for (TraidingCouple traidingCouple : getAllPairs()) {
            if (traidingCouple.getActive()) {
                traidingCouples.add(traidingCouple);
            }
        }

        return  traidingCouples;

    }

    /**
     * The method for toggling status of pair.
     * @param id id of pair
     * @throws Exception
     */
    public void togglePair(String id) throws Exception {


    Integer identity=Integer.parseInt(id.trim());


    Connection connection = null;

    try {
        connection = BasicConnectionPool.getBasicConnectionPool().getConnection();
        CryptoPairDaoImpl cryptoPairDao = new CryptoPairDaoImpl();
        cryptoPairDao.setConnection(connection);
        CryptoPair cryptoPair = cryptoPairDao.read(identity);

        if (cryptoPair.getActive()){
            cryptoPair.setActive(false);
        } else {
            cryptoPair.setActive(true);
        }
        cryptoPairDao.update(cryptoPair);

    }

    finally {
        BasicConnectionPool.getBasicConnectionPool().releaseConnection(connection);
    }


}



    }
