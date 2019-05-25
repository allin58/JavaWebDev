package by.training.task2.parsers;



import by.training.task2.entity.Component;
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
     * This logger logs the creation of parsers.
     */
    public static final Logger LOGGER = LogManager.getLogger(BasicParser.class);

    /**
     * Abstract method, which implamentation will parse string.
     * @param text input string
     * @return list of composites
     */
    public abstract List handleRequest(String text);

    /**
     * Abstract method, which implamentation will assemble components to string.
     * @param component input component
     * @return string
     */
    public abstract String assemble(Component component);

    /**
     * Constructor which logs creating of parsers.
     */
    public BasicParser() {
        ParserCounter.incremantCounter();
        LOGGER.debug("Parser was created " + ParserCounter.checkCounter());
    }




}
