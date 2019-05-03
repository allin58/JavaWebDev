package sweet;
import main.Main;

/**
 * Chocolate class inherited from the abstract class sweetness
 *
 * @author Nikita Karchahin
 * @version 1.0
 */

public class Chocolate extends Sweetness {

    /**
     * The constructor calculates the sugar content
     */

    public Chocolate(String name,double weight) {
        this.weight = weight;
        this.name = name;
        sugar = weight * 0.5;
        Main.logger.debug("created chocolate");
    }


}
