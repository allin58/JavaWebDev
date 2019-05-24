package by.training.task2.specification;

import by.training.task2.entity.Component;
import by.training.task2.entity.ParagraphComponent;
import by.training.task2.entity.SentenceComponent;
import by.training.task2.entity.TextComponent;
import by.training.task2.parsers.CharacterParser;
import by.training.task2.parsers.ParagrphParser;
import by.training.task2.parsers.SentenceParser;
import by.training.task2.parsers.WordParser;

import java.util.ArrayList;

public class SortHelper {

    public static String sortParagraphs(TextComponent textComponent, SentenceParser sentenceParser) {
        ArrayList<Component> arrayList = new ArrayList<>();
        String str = "  ";
        for (int i = 0; i < textComponent.getSize(); i++) {
            arrayList.add(textComponent.getChild(i));
        }
        arrayList.sort(new TextComporatorToParagraph());
        for (Component component : arrayList) {
            str = str + sentenceParser.assemble(component) + '\n' + "  ";
        }

        return str;
    }

    public static String sortSentensies(TextComponent textComponent, WordParser wordParser, int paragraphNumber) {
        ArrayList<Component> arrayList = new ArrayList<>();
        String str = "";
        ParagraphComponent paragraphComponent = (ParagraphComponent)(textComponent.getChild(paragraphNumber));

        for (int i = 0; i < paragraphComponent.getSize(); i++) {
            arrayList.add(paragraphComponent.getChild(i));
        }
        arrayList.sort(new TextComporatorToSentence());

        for (Component component : arrayList) {
            str = str + wordParser.assemble(component) + '\n';
        }

        return str;
    }





    public static String sortWords(TextComponent textComponent, CharacterParser characterParser, int paragraphNumber, int sentenseNumber) {

        ArrayList<Component> arrayList = new ArrayList<>();
        String str = "";

        ParagraphComponent paragraphComponent = (ParagraphComponent)(textComponent.getChild(paragraphNumber));
        SentenceComponent sentenceComponent = (SentenceComponent)paragraphComponent.getChild(sentenseNumber);

        for (int i = 0; i < sentenceComponent.getSize(); i++) {
            arrayList.add(sentenceComponent.getChild(i));
        }
        arrayList.sort(new TextComporatorToWorld());

        for (Component component : arrayList) {
            str = str + characterParser.assemble(component) + " ";
        }


        return str;

    }


}
