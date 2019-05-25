package by.training.task2.entity;


import java.util.List;

/**Class WordComposite contains list of characters.
 * <b>characters</b>
 * @author Nikita Karchahin
 * @version 1.0
 */
public class WordComposite extends Component {

    /**
     * Varible which stores list of characters.
     */
    private List<Component> characters;


    /**
     * Constructor sets textual representation of the composite.
     * @param word textual representation of the composite
     */
    public WordComposite(final String word) {
        super.setComponent(word);
    }


    /**
     * Implementation abstract method getSize().
     * @return size of list
     */
    public int getSize() {
        return characters.size();
    }

    /**
     * Implementation abstract method addComponent(Component component).
     * @param component is added to the collection
     */
    @Override
    public void addComponent(final Component component) {
        characters.add(component);
    }

    /**
     * Implementation abstract method getChild(int i).
     * @param i index
     * @return component depends on i
     */
    @Override
    public Component getChild(final int i) {
        return characters.get(i);
    }

    /**
     * Method which sets colletion of sentences.
     * @param characters list of characters
     */
    public void setCharacters(final List<Component> characters) {

        this.characters = characters;
    }
}
