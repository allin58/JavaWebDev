package by.training.task1.entity;
import by.training.task1.main.Main;

/**
 * Candy class inherited from the abstract class sweetness.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */

public class Candy extends Sweetness {


    /**
     *Constructor - creating a new object with specific values.
     *@param weight This is weight of candy
     *@param name This is name of candy
     *@param id This is id of candy
    */
    public Candy(final String name, final double weight, final int id) {
        final double PERCENT = 0.8;
        this.setWeight(weight);
        this.setName(name);
        this.setId(id);
        this.setSugar(weight * PERCENT);
        Main.logger.debug("created candy");
    }


}
