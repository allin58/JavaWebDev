package by.training.task2.specification;

import by.training.task2.entity.Component;
import by.training.task2.entity.WordComposite;

/** Class TextComporatorToWorld is intend for sorting by words.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class TextComporatorToWorld implements TextComporator {

    /**
     * This is method of comparing.
     * @param o1 first object
     * @param o2 second object
     * @return difference in size
     */
    @Override
    public int compare(final Component o1, final Component o2) {
        WordComposite wordComposite1 = (WordComposite) o1;
        WordComposite wordComposite2 = (WordComposite) o2;

        return wordComposite1.getSize() - wordComposite2.getSize();
    }

}
