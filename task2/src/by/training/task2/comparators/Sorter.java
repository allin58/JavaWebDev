package by.training.task2.comparators;

import by.training.task2.entity.Component;
import by.training.task2.entity.ParagraphComposite;
import by.training.task2.entity.SentenceComposite;
import by.training.task2.entity.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * Class sorter helps to perform sorting.
 */
public final class Sorter {

    /**
     * Private constructor.
     */
    private Sorter() {
    }

    /**
     * This logger logs the sorting.
     */
    public static final Logger LOGGER = LogManager.getLogger("by.training.task2.Sorter");

    /**
     * This method sorts and dirives string.
     * @param textComposite text to sorting
     * @return  arrayList sorted list
     */
    public static List<Component> sortParagraphs(final TextComposite textComposite) {
        ArrayList<Component> arrayList = new ArrayList<>();

        for (int i = 0; i < textComposite.getSize(); i++) {
            arrayList.add(textComposite.getChild(i));
        }
        arrayList.sort(new TextComparatorToParagraph());
        LOGGER.debug("Sort by paragraphs");
       return arrayList;
    }

    /**
     * This method sorts and dirives string.
     * @param textComposite text to sorting
     * @param numberOfParagraph number of paragraph
     * @return  arrayList sorted list
     */
    public static List<Component> sortSentences(final TextComposite textComposite, final int numberOfParagraph) {
        ParagraphComposite paragraphComposite = (ParagraphComposite) textComposite.getChild(numberOfParagraph);

        ArrayList<Component> arrayList = new ArrayList<>();
        for (int i = 0; i < paragraphComposite.getSize(); i++) {
            arrayList.add(paragraphComposite.getChild(i));
        }
        arrayList.sort(new TextComparatorToSentence());
        LOGGER.debug("Sort by sentences");
        return arrayList;
    }

    /**
     * This method sorts and dirives string.
     * @param textComposite text to sorting
     * @param numberOfParagraph number of paragraph
     * @param numberOfSentence number of sentence
     * @return  arrayList sorted list
     */
    public static List<Component> sortWords(final TextComposite textComposite, final int numberOfParagraph, final int numberOfSentence) {
        ParagraphComposite paragraphComposite = (ParagraphComposite) textComposite.getChild(numberOfParagraph);
        SentenceComposite sentenceComposite = (SentenceComposite) paragraphComposite.getChild(numberOfSentence);

        ArrayList<Component> arrayList = new ArrayList<>();
        for (int i = 0; i < sentenceComposite.getSize(); i++) {
            arrayList.add(sentenceComposite.getChild(i));
        }
        arrayList.sort(new TextComporatorToWorld());
        LOGGER.debug("Sort by words");
        return arrayList;
    }






}
