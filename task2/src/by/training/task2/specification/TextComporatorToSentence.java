package by.training.task2.specification;

import by.training.task2.entity.Component;
import by.training.task2.entity.SentenceComposite;

/** Class TextComporatorToSentence is intend for sorting by sntences.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class TextComporatorToSentence implements TextComporator {


    /**
     * This is method of comparing.
     * @param o1 first object
     * @param o2 second object
     * @return difference in size
     */
    @Override
    public int compare(final Component o1, final Component o2) {
        SentenceComposite sentenceComposite1 = (SentenceComposite) o1;
        SentenceComposite sentenceComposite2 = (SentenceComposite) o2;

        return sentenceComposite1.getSize() - sentenceComposite2.getSize();
       }
}
