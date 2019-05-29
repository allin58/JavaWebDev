package test.comporators;

import by.training.task2.comparators.Sorter;
import by.training.task2.entity.TextComposite;
import by.training.task2.exception.TextException;
import by.training.task2.parsers.CharacterParser;
import by.training.task2.parsers.ParagrphParser;
import by.training.task2.parsers.SentenceParser;
import by.training.task2.parsers.WordParser;
import by.training.task2.reader.TextReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TextCoparatorToWorldTest {


    @DataProvider(name = "words")
    public Object[][] words() {

        return new Object[][]{
                {"0", "a"},
                {"1", "a"},
                {"2", "a"},
                {"3", "."},
                {"4", "It"},
                {"5", "is"},
                {"6", "be"},
                {"7", "of"},
                {"8", "at"},
                {"9", "fact"},
                {"10", "that"},
                {"11", "will"},
                {"12", "page"},
                {"13", "when"},
                {"14", "reader"},
                {"15", "looking"},
                {"16", "its"+'\n'+"layout"},
                {"17", "established"}

        };

    }







    @Test(description = "validation of sorting to words", dataProvider = "words" )
    public void testComparatorToWord(String a, String b) {

        String text = new TextReader().readFile("data/input.txt");
        CharacterParser characterParser = new CharacterParser();
        WordParser wordParser = new WordParser(characterParser);
        SentenceParser sentenceParser = new SentenceParser(wordParser);
        ParagrphParser paragrphParser = new ParagrphParser(sentenceParser);
        TextComposite textComposite = null;
        try {
            textComposite = new TextComposite(text);
        } catch (TextException e) {
            e.printStackTrace();
        }
        textComposite.setParagraphs(paragrphParser.handleRequest(text));

        String actual = Sorter.sortWords(textComposite,2,0).get(Integer.parseInt(a)).assemble();
        String expected = b;

        assertEquals(actual,expected);


    }


}
