package by.training.task2.entity;





import java.util.List;


/**Class SentenceComposite contains list of words.
 * <b>words</b>
 * @author Nikita Karchahin
 * @version 1.0
 */
public class SentenceComposite extends Component {

    /**
     * Varible which stores list of words.
     */
    private List<Component> words;


    /**
     * Constructor sets textual representation of the composite.
     * @param sentence textual representation of the composite
     */
    public SentenceComposite(final String sentence) {
        super.setComponent(sentence);
    }


    /**
     * Implementation abstract method getSize().
     * @return size of list
     */
    public int getSize() {
        return words.size();
    }

    /**
     * Implementation abstract method addComponent(Component component).
     * @param component is added to the collection
     */
    @Override
    public void addComponent(final Component component) {
        words.add(component);
    }

    /**
     * Implementation abstract method getChild(int i).
     * @param i index
     * @return component depends on i
     */
    @Override
    public Component getChild(final int i) {
        return words.get(i);
    }

    /**
     * Method which sets colletion of sentences.
     * @param words list of words
     */
    public void setWords(final List<Component> words) {

        this.words = words;
    }




}
