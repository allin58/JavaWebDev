package by.training.task2.parsers;



import by.training.task2.entity.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public abstract class BasicParser {
    public static final Logger logger = LogManager.getLogger(BasicParser.class);


    public abstract List handleRequest(String text);
    public abstract String assemble(Component component);

    public BasicParser() {
        ParserCounter.incremantCounter();
        logger.debug("Parser was created " + ParserCounter.checkCounter());
    }




}
