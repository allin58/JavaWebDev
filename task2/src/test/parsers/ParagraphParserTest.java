package test.parsers;

import by.training.task2.parsers.CharacterParser;
import by.training.task2.parsers.ParagrphParser;
import by.training.task2.parsers.SentenceParser;
import by.training.task2.parsers.WordParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ParagraphParserTest {


    @DataProvider(name = "texts")
    public Object[][] texts() {
        return new Object[][]{
                {"4","\t\tIt has survived not only five centuries, but also the leap into electronic\n" +
                        "typesetting, remaining essentially unchanged. It was popularised in the with the\n" +
                        "release of Letraset sheets containing Lorem Ipsum passages, and more recently with\n" +
                        "desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                        "\t\tIt is a long established fact that a reader will be distracted by the readable\n" +
                        "content of a page when looking at its layout. The point of using Ipsum is that it has a\n" +
                        "more-or-less normal distribution of letters, as opposed to using 'Content here, content\n" +
                        "here', making it look like readable English.\n" +
                        "\t\tIt is a established fact that a reader will be of a page when looking at its\n" +
                        "layout.\n" +
                        "\t\tBye."},
                {"2","\t\tIt is a long established fact that a reader will be distracted by the readable\n" +
                        "content of a page when looking at its layout. The point of using Ipsum is that it has a\n" +
                        "more-or-less normal distribution of letters, as opposed to using 'Content here, content\n" +
                        "here', making it look like readable English.\n" +
                        "\t\tIt is a established fact that a reader will be of a page when looking at its\n" +
                        "layout."}

        };
    }

    @Test(description = "validation of the text parsing", dataProvider = "texts" )
    public void testParagraphParser(String a, String b) {
        CharacterParser characterParser = new CharacterParser();
        WordParser wordParser = new WordParser(characterParser);
        SentenceParser sentenceParser = new SentenceParser(wordParser);
        ParagrphParser paragrphParser = new ParagrphParser(sentenceParser);

        int expected = Integer.parseInt(a);
        int actual = paragrphParser.handleRequest(b).size();
        assertEquals(actual,expected);


    }

}
