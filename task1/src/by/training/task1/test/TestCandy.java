package by.training.task1.test;

import org.testng.annotations.Test;
import by.training.task1.entity.Candy;

import static org.testng.Assert.assertEquals;

public class TestCandy {

    @Test
    public void test(){

        Candy candy = new Candy("toffifee",10,1);
        double atual = candy.getSugar();
        double expected = 8.0;
        assertEquals(atual,expected);
    }
}
