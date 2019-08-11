package service;

import by.taining.cryptomarket.dao.connectionpool.BasicConnectionPool;
import by.taining.cryptomarket.entity.mapping.TraidingCouple;
import by.taining.cryptomarket.service.CryptoPairService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class CryptoPairServiceTest {

    @BeforeTest
    public void init() throws Exception{
         String url = "jdbc:mariadb://localhost:3306/testmarket";
         String username = "testmarket";
         String password = "testmarket";
         Class.forName("org.mariadb.jdbc.Driver");
         BasicConnectionPool.create(url,username,password);
    }



    @DataProvider(name = "values")
    public Object[][] values() throws Exception {

           CryptoPairService cryptoPairService = new CryptoPairService();
           List<TraidingCouple> allPairsBefore = cryptoPairService.getAllPairs();
            for (TraidingCouple traidingCouple : allPairsBefore) {
                cryptoPairService.togglePair(traidingCouple.getIdentity().toString());
                cryptoPairService.togglePair(traidingCouple.getIdentity().toString());

            }

            List<TraidingCouple> allPairsAfter = cryptoPairService.getAllPairs();
            Object[][] objects = new Object[allPairsBefore.size()][2];
            for (int i = 0; i < allPairsBefore.size(); i++) {
                objects[i][0] = allPairsAfter.get(i).getActive();
                objects[i][1] = allPairsBefore.get(i).getActive();
            }



        return objects;

    }




    @Test(description = "CryptoPairService service check", dataProvider = "values" )
    public void togglePairTest(Boolean a, Boolean b){


        assertEquals(a,b);

    }

    @AfterTest
    public void destroy() throws Exception{
            BasicConnectionPool.getBasicConnectionPool().shutdown();
    }



}
