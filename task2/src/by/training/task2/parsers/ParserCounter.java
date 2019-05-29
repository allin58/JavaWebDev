package by.training.task2.parsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** Class ParserCounter is created for counting parsers.
 * <b>counter</b>
 */
public final  class ParserCounter {

    /**
     * Private constructor.
     */
    private ParserCounter() {
    }

    /**
     * This logger logs the creation of parsers.
     */
    public static final Logger LOGGER = LogManager.getLogger("by.training.task2.Counter");


    /**
     * Varible to store the number of parsers.
     */
    private static int counter = 0;

    /**
     * Method which increments counter.
     */
   public static void incremantCounter() {
       counter++;
       LOGGER.debug("Parser was created " + ParserCounter.counter);

    }

    /**
     * Function to check the quantity.
     * @return number of parsers
     */
    public static int checkCounter() {
        return counter;
    }


}
