package by.training.task2.main;

import by.training.task2.entity.TextComponent;
import by.training.task2.parsers.*;
import by.training.task2.reader.TextReader;

public class Main {

    public static void main(String[] args) {

       TextReader textReader = new TextReader();
       String text = textReader.readFile("data/input.txt");

        CharacterParser characterParser = new CharacterParser();
        WordParser wordParser = new WordParser(characterParser);
        SentenceParser sentenceParser = new SentenceParser(wordParser);
        ParagrphParser paragrphParser = new ParagrphParser(sentenceParser);

        TextComponent textComponent = new TextComponent(text);
        textComponent.setParagraphs(paragrphParser.handleRequest(text));








    }
}
