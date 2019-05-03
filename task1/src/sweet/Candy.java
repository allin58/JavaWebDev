package sweet;
import main.Main;

/**
 * Candy class inherited from the abstract class sweetness
 *
 * @author Nikita Karchahin
 * @version 1.0
 */

public class Candy extends Sweetness {

    /**
     * The constructor calculates the sugar content
     */

    public Candy(String name, double weight) {
        this.weight = weight;
        this.name = name;
        sugar = weight * 0.8;
        Main.logger.debug("created candy");
    }


}
