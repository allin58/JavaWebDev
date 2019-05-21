package by.training.task1.entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Candy class inherited from the abstract class sweetness.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */

public class Candy extends Sweetness {

    public static final Logger logger = LogManager.getLogger(Candy.class);


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
        logger.debug("created candy");
    }


}
