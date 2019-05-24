package test;


import by.training.task2.parsers.CharacterParser;
import by.training.task2.parsers.WordParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestEntities {


    @DataProvider(name = "sentencies")
    public Object[][] sentencies() {
        return new Object[][]{
                {"20","It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged."},
                {"2","Bye."},
                {"19","It is a established fact that a reader will be of a page when looking at its layout."}
                };
    }

    @Test(description = "validation of the sentence parsing", dataProvider = "sentencies" )
    public void testSentenceParser(String a, String b) {
        CharacterParser characterParser = new CharacterParser();
        WordParser wordParser = new WordParser(characterParser);
        String str = b;
        int atual = wordParser.handleRequest(str).size();
        int expected = Integer.parseInt(a);
        assertEquals(atual,expected);
    }



    @DataProvider(name = "words")
    public Object[][] words() {
        return new Object[][]{
                {"2","It"},
                {"3","Bye"},
                {"1","a"},
                {"10","stablished"},
                {"6","layout"},
                {"7","looking"}
        };
    }


    @Test(description = "validation of the word parsing", dataProvider = "words" )
    public void testWordParser(String a, String b) {
        CharacterParser characterParser = new CharacterParser();

        String str = b;
        int atual = characterParser.handleRequest(str).size();
        int expected = Integer.parseInt(a);
        assertEquals(atual,expected);
    }



}
