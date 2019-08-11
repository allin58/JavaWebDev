package service;

import by.taining.cryptomarket.dao.connectionpool.BasicConnectionPool;
import by.taining.cryptomarket.entity.User;
import by.taining.cryptomarket.entity.mapping.TraidingCouple;
import by.taining.cryptomarket.service.UserService;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UserServiceTest {

    @BeforeTest
    public void init() throws Exception{
        String url = "jdbc:mariadb://localhost:3306/testmarket";
        String username = "testmarket";
        String password = "testmarket";



        Class.forName("org.mariadb.jdbc.Driver");
        BasicConnectionPool.create(url,username,password);

    }

    @DataProvider(name = "values")
    public Object[] values() throws Exception {
      //  Object[][] objects = new Object[][]{{true,true}, {true,true}, {true,true}};

        UserService userService = new UserService();
        List<User> users = userService.getAllUsers();
        Object[] objects = new Object[users.size()];
        for (int i = 0; i < users.size(); i++) {
            objects[i] = users.get(i).getUserName();
        }
        return objects;

    }




    @Test(description = "UserService service check", dataProvider = "values" )
    public void userIsExistTest(String name) throws Exception{
        UserService userService = new UserService();
        Boolean actual = userService.userIsExist(name);
        assertTrue(actual);
    }






    @AfterTest
    public void destroy() throws Exception{
        BasicConnectionPool.getBasicConnectionPool().shutdown();
    }


}
