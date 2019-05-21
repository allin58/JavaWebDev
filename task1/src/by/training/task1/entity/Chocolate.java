package by.training.task1.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Chocolate class inherited from the abstract class sweetness.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */

public class Chocolate extends Sweetness {

    public static final Logger logger = LogManager.getLogger(Chocolate.class);

    /**
     *Constructor - creating a new object with specific values.
     *@param weight This is weight of chocolate
     *@param name This is name of chocolate
     *@param id This is id of chocolate
     */
    public Chocolate(final String name, final double weight, final int id) {
        final double PERCENT = 0.5;
        this.setWeight(weight);
        this.setName(name);
        this.setId(id);
        this.setSugar(weight * PERCENT);
        logger.debug("created chocolate");
    }


}
