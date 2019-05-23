package by.training.task2.specification;

import by.training.task2.entity.ParagraphComponent;
import by.training.task2.entity.SentenceComponent;
import by.training.task2.entity.TextComponent;
import by.training.task2.parsers.ParagrphParser;
import by.training.task2.parsers.SentenceParser;
import by.training.task2.parsers.WordParser;

public class SortHelper {

    public static String sortParagraphs(TextComponent textComponent, ParagrphParser paragrphParser) {
        textComponent.query(new TextComporatorToParagraph());
        return paragrphParser.assemble(textComponent);
    }

    public static String sortSentensies(TextComponent textComponent, SentenceParser sentenceParser, int paragraphNumber) {

        ParagraphComponent paragraphComponent = (ParagraphComponent)(textComponent.getChild(paragraphNumber));
        paragraphComponent.query(new TextComporatorToSentence());
        return sentenceParser.assemble(paragraphComponent);
    }

    public static String sortWords(TextComponent textComponent, WordParser wordParser, int paragraphNumber, int sentenseNumber) {

        ParagraphComponent paragraphComponent = (ParagraphComponent)(textComponent.getChild(paragraphNumber));
        SentenceComponent sentenceComponent = (SentenceComponent)paragraphComponent.getChild(sentenseNumber);
        sentenceComponent.query(new TextComporatorToWorld());
        return wordParser.assemble(sentenceComponent);

    }



}
