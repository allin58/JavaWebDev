package sweet;

/**
 * Abstract class Sweetness
 *
 * @author Nikita Karchahin
 * @version 1.0
 */

public abstract class Sweetness {

    /**
     * Fields for storing name, weight, sugar content
     */

    double weight;
    String name;
    double sugar;


    public double getWeight() {
        return weight;
    }

    public String getName() {
                return name;
    }

    public double getSugar() {

        return sugar;
    }

}
