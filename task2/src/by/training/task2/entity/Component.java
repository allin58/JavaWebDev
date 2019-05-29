package by.training.task2.entity;

/**
 * Interfase Component with properties.
 * <b>component</b>
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public interface Component {



    /**
     * Abstract function for adding a new composite.
     * @param com composire for adding
     */
     void addComponent(Component com);

    /**
     *This is abstract function which returns child composite.
     * @param i index
     * @return component depends on i
     */
    Component getChild(int i);

    /**
     *This is abstract function which remove child composite.
     * @param component object to remove
     */
     void remove(Component component);

    /**
     *This is abstract function which return string.
     * @return textual representation of composite
     */
    String assemble();


    /**
     * Abstract method getSize().
     * @return size of character
     */
    int getSize();



}
