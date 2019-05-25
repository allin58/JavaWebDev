package by.training.task2.entity;




import java.util.List;

/**Class ParagraphComposite contains list of sentences.
 * <b>sentences</b>
 * @author Nikita Karchahin
 * @version 1.0
 */
public class ParagraphComposite extends Component {

    /**
     * Varible which stores list of sentences.
     */
    private List<Component> sentences;


    /**
     * Constructor sets textual representation of the composite.
     * @param paragraph textual representation of the composit
     */
    public ParagraphComposite(final String paragraph) {
        super.setComponent(paragraph);
    }



    /**
     * Implementation abstract method getSize().
     * @return size of list
     */
    public int getSize() {

        return sentences.size();
    }

    /**
     * Implementation abstract method addComponent(Component component).
     * @param component is added to the collection
     */
    @Override
    public void addComponent(final Component component) {
        sentences.add(component);
    }


    /**
     * Implementation abstract method getChild(int i).
     * @param i index
     * @return component depends on i
     */
    @Override
    public Component getChild(final int i) {
     return sentences.get(i);
    }

    /**
     * Method which sets colletion of sentences.
     * @param sentences list of sentences
     */
    public void setSentences(final List<Component> sentences) {
        this.sentences = sentences;
    }



}
