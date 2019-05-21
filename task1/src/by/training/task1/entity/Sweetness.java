package by.training.task1.entity;

/**
 * Abstract class Sweetness with properties.
 * <b>weight</b> and <b>name</b> and <b>sugar</b>  <b>id</b>
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public abstract class Sweetness {

    /**
     * Field for storing ID.
     */
    private int id;

    /**
     * Field for storing weight.
     */
    private double weight;

    /**
     * Field for storing name.
     */
    private String name;

    /**
     * Field for storing sugar content.
     */
    private double sugar;

    /**
     * Function to get value weight.
     * @return returns weight value
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Function to get name.
     * @return returns name value
     */
    public String getName() {
        return name;
    }

    /**
     * Function to get value weight.
     * @return returns sugar content value
     */
    public double getSugar() {

        return sugar;
    }


    /**
     * Function to get field value id.
     * @return returns id
     */
    public int getID() {

        return id;
    }

    /**
     * Function to set field value id.
     * @param id This is id of sweetnes
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Function to weight field value id.
     * @param weight This is weight of sweetnes
     */
    public void setWeight(final double weight) {

        this.weight = weight;
    }

    /**
     * Function to name field value id.
     * @param name This is name of sweetnes
     */
    public void setName(final String name) {

        this.name = name;
    }

    /**
     * Function to sugar field value id.
     * @param sugar This is sugar content sweets
     */
    public void setSugar(final double sugar) {

       this.sugar = sugar;
    }

    /**
     * Function to get hashcode.
     * @return returns the object hash
     */
    public int hashCode() {

        return (name + weight + sugar).hashCode();
    }

    /**
     * Function to define the equality of objects.
     * @param o This is object for comparing
     * @return returns the boolean value
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
        Sweetness sweetness = (Sweetness) o;
        return (sweetness.getName().equals(name) && sweetness.getWeight() == weight && sweetness.getSugar() == sugar);
    }
}
