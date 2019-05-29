package test.parsers;

import by.training.task2.parsers.CharacterParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CharacterParserTest {




    @DataProvider(name = "words")
    public Object[][] words() {
        return new Object[][]{
                {"7","urvived"},
                {"3","Bye"},
                {"2","It"},
                {"9","centuries"},
                {"4","also"}
        };
    }

    @Test(description = "validation of the word parsing", dataProvider = "words" )
    public void testCharacterParser(String a, String b) {
        CharacterParser characterParser = new CharacterParser();

        int expected = Integer.parseInt(a);
        int actual = characterParser.handleRequest(b).size();
        assertEquals(actual,expected);


    }
}
