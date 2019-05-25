package by.training.task2.specification;

import by.training.task2.entity.Component;
import by.training.task2.entity.ParagraphComposite;
import by.training.task2.entity.SentenceComposite;
import by.training.task2.entity.TextComposite;
import by.training.task2.parsers.CharacterParser;
import by.training.task2.parsers.SentenceParser;
import by.training.task2.parsers.WordParser;

import java.util.ArrayList;


/**Class SortHelper helps to sort.
 * @author Nikita Karchahin
 * @version 1.0
 */
 public class SortHelper {
     /**
     * Method sortParagraphs sorts the paragraphs by the number of sentences.
     * @param textComposite contains required paragraphs
     * @param sentenceParser for assembling to string
     * @return string representation of paragraphs
     */
    public static String sortParagraphs(final TextComposite textComposite, final SentenceParser sentenceParser) {
        ArrayList<Component> arrayList = new ArrayList<>();
        String str = "  ";
        for (int i = 0; i < textComposite.getSize(); i++) {
            arrayList.add(textComposite.getChild(i));
        }
        arrayList.sort(new TextComporatorToParagraph());
        for (Component component : arrayList) {
            str = str + sentenceParser.assemble(component) + '\n' + "  ";
        }

        return str;
    }


    /**
     * Method sortSentensies sorts the sentences by the number of words.
     * @param textComposite contains required sentences
     * @param wordParser for assembling to string
     * @param paragraphNumber number of paragraph
     * @return string representation of sentences
     */
    public static String sortSentences(final TextComposite textComposite, final WordParser wordParser, final int paragraphNumber) {
        ArrayList<Component> arrayList = new ArrayList<>();
        String str = "";
        ParagraphComposite paragraphComposite = (ParagraphComposite) (textComposite.getChild(paragraphNumber));

        for (int i = 0; i < paragraphComposite.getSize(); i++) {
            arrayList.add(paragraphComposite.getChild(i));
        }
        arrayList.sort(new TextComporatorToSentence());

        for (Component component : arrayList) {
            str = str + wordParser.assemble(component) + '\n';
        }

        return str;
    }


    /**
     * Method sortWords sorts the words by the length.
     * @param textComposite contains required words
     * @param characterParser for assembling to string
     * @param paragraphNumber number of paragraph
     * @param sentenseNumber number of sentence
     * @return string representation of words
     */
    public static String sortWords(final TextComposite textComposite, final CharacterParser characterParser, final int paragraphNumber, final int sentenseNumber) {

        ArrayList<Component> arrayList = new ArrayList<>();
        String str = "";

        ParagraphComposite paragraphComposite = (ParagraphComposite) (textComposite.getChild(paragraphNumber));
        SentenceComposite sentenceComposite = (SentenceComposite) paragraphComposite.getChild(sentenseNumber);

        for (int i = 0; i < sentenceComposite.getSize(); i++) {
            arrayList.add(sentenceComposite.getChild(i));
        }
        arrayList.sort(new TextComporatorToWorld());

        for (Component component : arrayList) {
            str = str + characterParser.assemble(component) + " ";
        }


        return str;

    }


}
