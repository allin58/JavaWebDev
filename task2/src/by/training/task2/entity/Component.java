package by.training.task2.entity;

/**
 * Abstract class Component with properties.
 * <b>component</b>
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public abstract class Component {

    /**
     * Field for storing textual representation of the composire.
     */
    private String component;


    /**
     * Abstract function for adding a new composire.
     * @param com composire for adding
     */
    public abstract void addComponent(Component com);

    /**
     *This is abstract function which returns child composire.
     * @param i index
     * @return component depennds on i
     */
    public abstract Component getChild(int i);


    /**
     * Abstract function returns list size.
     * @return size of list
     */
    public abstract int getSize();

    /**
     * Function which returns textual representation of the composire.
     * @return list of components
     */
    public String getComponent() {
        return component;
    }

    /**
     * Function which sets textual representation of the composire.
     * @param component textual representation of the composire
     */
    public void setComponent(final String component) {
        this.component = component;
    }


    /**
     * HashCode function.
     * @return hashcode of composite
     */
    public int hashCode() {

        return component.hashCode();
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
        Component object = (Component) o;
        return (object.getComponent().equals(component));
    }

}
