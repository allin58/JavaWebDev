package by.training.task2.entity;




/**Class WordComposite contains character.
 * <b>character</b>
 * @author Nikita Karchahin
 * @version 1.0
 */
public class CaracterComposite implements Component {

    /**
     * Value of character.
     */
     private String character;

    /**
     * Constructor sets textual representation of the composite.
     * @param character textual representation of the composite
      */
    public CaracterComposite(final String character) {

        this.character = character;

    }



    /**
     *   Method to get character.
     *  @return character
     */

    public String getCharacter() {
        return character;
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
    public void addComponent(final Component component) {

    }

    /**
     * Implementation abstract method getChild(int i).
     * @param i index
     * @return null because instances of this class cannot have child
     */
    @Override
    public Component getChild(final int i) {
        return null;
    }


    /**
     *This is function which return string.
     * @return textual representation of composite
     */
    @Override
    public String assemble() {


        return character;
    }

    /**
     * Implementation abstract method remove().
     * @param component to delite
     */
    public void remove(final Component component) {
           }





    /**
     * HashCode function.
     * @return hashcode of composite
     */
    public int hashCode() {
        return character.hashCode();
    }

    /**
     *
     * @param o object for comparing
     * @return true if objects are equals
     */
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        CaracterComposite object = (CaracterComposite) o;
        return (object.character.equals(character));
    }





}
