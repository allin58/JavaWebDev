package by.training.task1.test;

import org.testng.annotations.Test;
import by.training.task1.entity.Chocolate;

import static org.testng.Assert.assertEquals;

public class TestChocolate {

    @Test
    public void test(){

        Chocolate chocolate = new Chocolate("Nestle",10,2);
        double atual = chocolate.getSugar();
        double expected = 5.0;
        assertEquals(atual,expected);

    }

}
