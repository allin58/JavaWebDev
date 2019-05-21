package test;

import by.training.task1.entity.Chocolate;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.training.task1.entity.Candy;

import static org.testng.Assert.assertEquals;

public class TestEntities {



    @DataProvider(name = "input_for_candy")
    public Object[][] checkCounting() {
        return new Object[][]{
                {10.0,8.0},
                {20.0,16.0},
                {30.0,24.0},
                {40.0,32.0}};
    }




    @Test(description = "check correcе counting for consist of sugar", dataProvider = "input_for_candy" )
    public void testCandy(double a,double b) {

        Candy candy = new Candy("toffifee",a,1);
        double atual = candy.getSugar();
        double expected = b;
        assertEquals(atual,expected);
    }


    @Test(description = "check correcе counting for consist of sugar")
    public void testChocolate(){

        Chocolate chocolate = new Chocolate("Nestle",10,2);
        double atual = chocolate.getSugar();
        double expected = 5.0;
        assertEquals(atual,expected);

    }

}
