package by.training.task2.main;

import by.training.task2.entity.Component;
import by.training.task2.entity.ParagraphComponent;
import by.training.task2.entity.SentenceComponent;
import by.training.task2.entity.TextComponent;
import by.training.task2.parsers.*;
import by.training.task2.reader.TextReader;
import by.training.task2.specification.*;


public class Main {





    public static void main(String[] args) {



       String text = new TextReader().readFile("data/input.txt");

        CharacterParser characterParser = new CharacterParser();
        WordParser wordParser = new WordParser(characterParser);
        SentenceParser sentenceParser = new SentenceParser(wordParser);
        ParagrphParser paragrphParser = new ParagrphParser(sentenceParser);

        TextComponent textComponent = new TextComponent(text);
        textComponent.setParagraphs(paragrphParser.handleRequest(text));




        System.out.println(SortHelper.sortWords(textComponent,wordParser,0,0));

    }





}
