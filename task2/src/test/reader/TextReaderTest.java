package test.reader;

import by.training.task2.parsers.CharacterParser;
import by.training.task2.parsers.SentenceParser;
import by.training.task2.parsers.WordParser;
import by.training.task2.reader.TextReader;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TextReaderTest {

    @Test(description = "validation of the file reading" )
    public void readFile() {


        String expected = "\t\tIt has survived not only five centuries, but also the leap into electronic\n" +
                "typesetting, remaining essentially unchanged. It was popularised in the with the\n" +
                "release of Letraset sheets containing Lorem Ipsum passages, and more recently with\n" +
                "desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "\t\tIt is a long established fact that a reader will be distracted by the readable\n" +
                "content of a page when looking at its layout. The point of using Ipsum is that it has a\n" +
                "more-or-less normal distribution of letters, as opposed to using 'Content here, content\n" +
                "here', making it look like readable English.\n" +
                "\t\tIt is a established fact that a reader will be of a page when looking at its\n" +
                "layout.\n" +
                "\t\tBye.\n";

        String atual =  new TextReader().readFile("data/input.txt");;
        assertEquals(atual,expected);
    }
}
