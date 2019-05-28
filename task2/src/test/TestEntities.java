package test;


import by.training.task2.parsers.CharacterParser;
import by.training.task2.parsers.ParagrphParser;
import by.training.task2.parsers.SentenceParser;
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



    @DataProvider(name = "paragraphs")
    public Object[][] paragraphs() {
        return new Object[][]{
                {"2","It has survived not only five centuries, but also the leap into electronic\n" +
                "typesetting, remaining essentially unchanged. It was popularised in the with the\n" +
                "release of Letraset sheets containing Lorem Ipsum passages, and more recently with."},

                {"1","It is a established fact that a reader will be of a page when looking at its\n" +
                        "layout."}
        };
    }

    @Test(description = "validation of the paragraph parsing", dataProvider = "paragraphs" )
    public void testParagraphsParser(String a, String b) {
        CharacterParser characterParser = new CharacterParser();
        WordParser wordParser = new WordParser(characterParser);
        SentenceParser sentenceParser = new SentenceParser(wordParser);
        String str = b;
        int atual = sentenceParser.handleRequest(str).size();
        int expected = Integer.parseInt(a);
        assertEquals(atual,expected);
    }



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
                        "\t\tBye."}


        };
    }

    @Test(description = "validation of the paragraph parsing", dataProvider = "texts" )
    public void testTextParser(String a, String b) {
        CharacterParser characterParser = new CharacterParser();
        WordParser wordParser = new WordParser(characterParser);
        SentenceParser sentenceParser = new SentenceParser(wordParser);
        ParagrphParser paragrphParser = new ParagrphParser(sentenceParser);
        String str = b;
        int atual = paragrphParser.handleRequest(str).size();
        int expected = Integer.parseInt(a);
        assertEquals(atual,expected);
    }







}
