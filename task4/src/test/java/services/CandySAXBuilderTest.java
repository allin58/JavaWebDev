package services;

import by.training.task4.entity.Candy;
import by.training.task4.services.AbstractCandiesBuilder;
import by.training.task4.services.CandyBuilderFactory;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static org.testng.Assert.assertEquals;

public class CandySAXBuilderTest {


    @DataProvider(name = "values")
    public Object[][] values() {
        return new Object[][]{
                {"0-energy","520"},
                {"0-sugar","5"},
                {"0-fructose","0"},
                {"0-name","Alpen Gold"},
                {"0-typeOfChocolate","black"},
                {"0-water","7"}

        };
    }


@Test(description = "validation of the SAX parsing", dataProvider = "values" )
public void testSAXParser(String a, String b){


    CandyBuilderFactory candyBuilderFactory = new CandyBuilderFactory();
    AbstractCandiesBuilder candiesBuilder = candyBuilderFactory.createCandyBuilder("SAX");
    candiesBuilder.buildListCandies(new File("data.xml"),"candyschema.xsd");
    ArrayList<Candy> candies = candiesBuilder.getCandies();

    int number = Integer.valueOf(a.split("-")[0]);


    executeRequest(a.split("-")[1],candies.get(number));



    String expected = b;
    String actual = executeRequest(a.split("-")[1],candies.get(number));
    assertEquals(actual,expected);

}



    public String executeRequest(String req, Candy candy) {

        switch (req) {
            case "name" : return candy.getName();
            case "energy" : return candy.getEnergy().toString();
            case "production" : return candy.getProduction();
            case "id" : return candy.getId().toString();
            case "type" : return candy.getType();
            case "proteins" : return candy.getProteins().toString();
            case "fats" : return candy.getFats().toString();
            case "carbohydrates" : return candy.getCarbohydrates().toString();
            case "typeOfChocolate" : return candy.getTypeOfChocolate();
            case "water" : return candy.getWater().toString();
            case "sugar" : return candy.getSugar().toString();
            case "fructose" : return candy.getFructose().toString();
            case "vanillin" : return candy.getVanillin().toString();
                  }

        return null;

    }




}
