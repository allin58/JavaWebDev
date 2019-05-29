package by.training.task2.parsers;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Class BasicParser is base for creating custm parsers.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public abstract class BasicParser {
    /**
     * This logger logs the action of parsers.
     */
    public static final Logger LOGGER = LogManager.getLogger("by.training.task2.Parser");

    /**
     * Abstract method, which implamentation will parse string.
     * @param text input string
     * @return list of composites
     */
    public abstract List handleRequest(String text);



    /**
     * Constructor which logs creating of parsers.
     */
    public BasicParser() {
        ParserCounter.incremantCounter();
      }




}
