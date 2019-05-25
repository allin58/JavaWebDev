package by.training.task2.entity;




import by.training.task2.exception.TextException;

import java.util.List;

/**Class TextComposite contains list of paragraghs.
 * <b>paragraphs</b>
 * @author Nikita Karchahin
 * @version 1.0
 */
public class TextComposite extends Component {

    /**
     * Varible which stores list of paragraghs.
     */
    private List<Component> paragraphs;


    /**
     * Constructor sets textual representation of the composite.
     * @param text textual representation of the composite
     * @throws TextException appear when input string is empty
     */
    public TextComposite(final String text) throws TextException {

        if ("".equals(text))  {
            throw new TextException("text error");
        }

        super.setComponent(text);
    }


    /**
     * Implementation abstract method getSize().
     * @return size of list
     */
    @Override
    public int getSize() {
        return paragraphs.size();
    }

    /**
     * Implementation abstract method addComponent(Component component).
     * @param component is added to the collection
     */
   @Override
    public void addComponent(final Component component) {
        paragraphs.add(component);
    }

    /**
     * Implementation abstract method getChild(int i).
     * @param i index
     * @return component depends on i
     */
    @Override
    public Component getChild(final int i) {

        return paragraphs.get(i);
    }


    /**
     * Method which sets colletion of sentences.
     * @param paragraphs list of paragraphs
     */
    public void setParagraphs(final List<Component> paragraphs) {
            this.paragraphs = paragraphs;
    }





}
