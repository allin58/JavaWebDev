package test.parsers;

import by.training.task2.parsers.CharacterParser;
import by.training.task2.parsers.WordParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class WordParserTest {

    @DataProvider(name = "sentences")
    public Object[][] sentences() {
        return new Object[][]{
                {"20","It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged."},
                {"32","It was popularised in the with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."}

        };
    }

    @Test(description = "validation of the sentence parsing", dataProvider = "sentences" )
    public void testWordParser(String a, String b) {
        CharacterParser characterParser = new CharacterParser();
        WordParser wordParser = new WordParser(characterParser);

        int expected = Integer.parseInt(a);
        int actual = wordParser.handleRequest(b).size();
        assertEquals(actual,expected);


    }

}
