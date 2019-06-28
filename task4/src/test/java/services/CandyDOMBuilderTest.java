package services;

import by.training.task4.entity.Candy;
import by.training.task4.services.AbstractCandiesBuilder;
import by.training.task4.services.CandyBuilderFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

public class CandyDOMBuilderTest {

    @DataProvider(name = "values")
    public Object[][] values() {
        return new Object[][]{
                {"1-fructose","3"},
                {"2-sugar","0"},
                {"3-water","1"},
                {"4-name","Alpen Gold with almond"},
                {"5-typeOfChocolate","black"},
                {"6-vanillin","6"},
                {"10-water","0"}

        };
    }


    @Test(description = "validation of the DOM parsing", dataProvider = "values" )
    public void testSAXParser(String a, String b){


        CandyBuilderFactory candyBuilderFactory = new CandyBuilderFactory();
        AbstractCandiesBuilder candiesBuilder = candyBuilderFactory.createCandyBuilder("DOM");
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
