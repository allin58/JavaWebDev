package by.training.task2.main;

import by.training.task2.entity.TextComposite;
import by.training.task2.parsers.*;
import by.training.task2.reader.TextReader;




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
         * Here is the text is parsed and assembled back.
         */
        try {


            TextComposite textComposite = new TextComposite(text);
            textComposite.setParagraphs(paragrphParser.handleRequest(text));
            System.out.println(textComposite.assemble());


        } catch (Exception e) {
            e.printStackTrace();
        }



    }









}
