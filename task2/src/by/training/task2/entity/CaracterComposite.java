package by.training.task2.entity;




/**Class WordComposite contains character.
 * <b>character</b>
 * @author Nikita Karchahin
 * @version 1.0
 */
public class CaracterComposite extends Component {


    /**
     * Constructor sets textual representation of the composite.
     * @param character textual representation of the composite
      */
    public CaracterComposite(final String character) {
        super.setComponent(character);
    }


    /**
     * Implementation abstract method getSize().
     * @return size of character
     */
    public int getSize() {
        return 1;
    }


    /**
     * Implementation abstract method addComponent(Component component).
     * @param component is added to the collection
     */
    @Override
    public void addComponent(final Component component) {    }

    /**
     * Implementation abstract method getChild(int i).
     * @param i index
     * @return null because instances of this class cannot have child
     */
    @Override
    public Component getChild(final int i) {
        return null;
    }




}
