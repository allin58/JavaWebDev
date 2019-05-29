package by.training.task2.entity;




import by.training.task2.exception.TextException;

import java.util.List;

/**Class TextComposite contains list of paragraghs.
 * <b>paragraphs</b>
 * @author Nikita Karchahin
 * @version 1.0
 */
public class TextComposite implements Component {

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

    }


    /**
     * Implementation abstract method getSize().
     * @return size of list
     */
     public int getSize() {
        return paragraphs.size();
    }


    /**
     * Implementation abstract method remove().
     * @param component to delite
     */
    public void remove(final Component component) {
        paragraphs.remove(component);
    }





    /**
     *This is function which return string.
     * @return textual representation of composite
     */
    @Override
    public String assemble() {
        String str = "";

        for (Component paragraph : paragraphs) {
            str = str + "\n\t\t" + paragraph.assemble();
        }


        return str;
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


    /**
     * HashCode function.
     * @return hashcode of composite
     */
    public int hashCode() {
        return paragraphs.hashCode();
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
        TextComposite object = (TextComposite) o;
        return (object.paragraphs.equals(paragraphs));
    }




}
