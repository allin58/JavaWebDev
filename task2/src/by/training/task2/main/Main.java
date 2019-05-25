package by.training.task2.main;

import by.training.task2.entity.TextComposite;
import by.training.task2.parsers.*;
import by.training.task2.reader.TextReader;
import by.training.task2.specification.SortHelper;


public class Main {



    public static void main(String[] args) {



       String text = new TextReader().readFile("data/input.txt");


        /**
         * Here is creating and linking parsers.
         */
        CharacterParser characterParser = new CharacterParser();
        WordParser wordParser = new WordParser(characterParser);
        SentenceParser sentenceParser = new SentenceParser(wordParser);
        ParagrphParser paragrphParser = new ParagrphParser(sentenceParser);


        /**
         * This is where the text is parsed and assembled back.
         */
        try {
            TextComposite textComponent = new TextComposite(text);
            textComponent.setParagraphs(paragrphParser.handleRequest(text));
            System.out.println(paragrphParser.assemble(textComponent));


            /**
             * Here are examples for sorting.
             */
            //System.out.println(SortHelper.sortParagraphs(textComponent,sentenceParser));
            //System.out.println(SortHelper.sortSentences(textComponent,wordParser,0));
            //System.out.println(SortHelper.sortWords(textComponent,characterParser,0,0));


        } catch (Exception e) {
            e.printStackTrace();
        }




    }





}
