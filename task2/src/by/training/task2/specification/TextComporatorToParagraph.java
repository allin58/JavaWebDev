package by.training.task2.specification;

import by.training.task2.entity.Component;
import by.training.task2.entity.ParagraphComposite;



/** Class TextComporatorToParagraph is intend for sorting by paragraphs.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class TextComporatorToParagraph implements TextComporator {

    /**
     * This is method of comparing.
     * @param o1 first object
     * @param o2 second object
     * @return difference in size
     */
    @Override
    public int compare(final Component o1, final Component o2) {
        ParagraphComposite paragraphComposite1 = (ParagraphComposite) o1;
        ParagraphComposite paragraphComposite2 = (ParagraphComposite) o2;


        return paragraphComposite1.getSize() - paragraphComposite2.getSize();
    }
}
