package by.training.task2.parsers;

import by.training.task2.entity.WordComposite;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class SentenceParser is crated for parsing to words.
 *<b>nextParser</b>
 * @author Nikita Karchahin
 * @version 1.0
 */
public class WordParser extends BasicParser {


    /**
     * Template for regular expression.
     */
    private static final String TEMPLATE1 = "[!?//.]|,|\\.\\.\\.";

    /**
     * Template for regular expression.
     */
    private static final String TEMPLATE2 = " ";

    /**
     * Next parser for implantation chain of responsibility.
     */
    private BasicParser nextParser;



    /**
     * Constructor sets next parser.
     * @param nextParser varible for linking parsers
     */
    public WordParser(final BasicParser nextParser) {
        this.nextParser = nextParser;
    }

    /**
     * handleRequest method parses string.
     * @param text input string
     * @return list of composites
     */
    public List handleRequest(String text) {
        ArrayList<WordComposite> words = new ArrayList<>();

        Pattern pattern = Pattern.compile(TEMPLATE1);
        Matcher matcher = pattern.matcher(text);
        ArrayList<Integer> pos = new ArrayList<>();

        while (matcher.find()) {
            pos.add(matcher.start());
        }

        for (int i = pos.size() - 1; i >= 0; i--) {
            text = text.substring(0, pos.get(i)) + " " + text.substring(pos.get(i), text.length());

        }

        for (String s : text.split(TEMPLATE2)) {
            WordComposite word = new WordComposite();
            word.setCharacters(nextParser.handleRequest(s));
            words.add(word);

        }

        LOGGER.debug("The sentence was divided into " + words.size() + " words.");
        return words;
    }


}


