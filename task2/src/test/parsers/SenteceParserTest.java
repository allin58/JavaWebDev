package test.parsers;

import by.training.task2.parsers.CharacterParser;
import by.training.task2.parsers.SentenceParser;
import by.training.task2.parsers.WordParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SenteceParserTest {

    @DataProvider(name = "paragraphs")
    public Object[][] paragraphs() {
        return new Object[][]{
                {"2","It has survived not only five centuries, but also the leap into electronic\n" +
                        "typesetting, remaining essentially unchanged. It was popularised in the with the\n" +
                        "release of Letraset sheets containing Lorem Ipsum passages, and more recently with\n" +
                        "desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."},
                {"1","It is a established fact that a reader will be of a page when looking at its\n" +
                        "layout."}

        };
    }

    @Test(description = "validation of the paragraph parsing", dataProvider = "paragraphs" )
    public void testSentenceParser(String a, String b) {
        CharacterParser characterParser = new CharacterParser();
        WordParser wordParser = new WordParser(characterParser);
        SentenceParser sentenceParser = new SentenceParser(wordParser);


        int expected = Integer.parseInt(a);
        int actual = sentenceParser.handleRequest(b).size();
        assertEquals(actual,expected);


    }



}
