package by.training.task2.entity;


import java.util.List;

/**Class WordComposite contains list of characters.
 * <b>characters</b>
 * @author Nikita Karchahin
 * @version 1.0
 */
public class WordComposite implements Component {

    /**
     * Varible which stores list of characters.
     */
    private List<Component> characters;


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

    /**
     * Implementation abstract method remove().
     * @param component to delite
     */
    public void remove(final Component component) {
        characters.remove(component);
    }


    /**
     *This is function which return string.
     * @return textual representation of composite
     */
    @Override
    public String assemble() {
        String str = "";

        for (Component character : characters) {
            str += character.assemble();
        }

        return str;
    }





    /**
     * HashCode function.
     * @return hashcode of composite
     */
    public int hashCode() {
        return characters.hashCode();
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
        WordComposite object = (WordComposite) o;
        return (object.characters.equals(characters));
    }



}
