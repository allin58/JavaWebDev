package by.training.task2.parsers;


import by.training.task2.entity.SentenceComposite;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class SentenceParser is crated for parsing to sentences.
 *<b>nextParser</b>
 * @author Nikita Karchahin
 * @version 1.0
 */
public class SentenceParser extends BasicParser {

    /**
     * Template for regular expression.
     */
    private static final String TEMPLATE = "\\.\\.\\.|[.!?]";

    /**
     * Next parser for implantation chain of responsibility.
     */
    private BasicParser nextParser;


    /**
     * Constructor sets next parser.
     * @param nextParser varible for linking parsers
     */
    public SentenceParser(final BasicParser nextParser) {
        this.nextParser = nextParser;
    }


    /**
     * handleRequest method parses string.
     * @param text input string
     * @return list of composites
     */
    public List handleRequest(final String text) {


        ArrayList<SentenceComposite> sentences = new ArrayList<>();

        Pattern pattern = Pattern.compile(TEMPLATE);
        Matcher matcher = pattern.matcher(text);
        ArrayList<Integer> pos = new ArrayList<>();
        while (matcher.find()) {
            pos.add(matcher.end());


        }
        int pastPos = 0;
        int currentPos = 0;
        for (int i = 0; i < pos.size(); i++) {
            currentPos = pos.get(i);
            String s = text.substring(pastPos, currentPos).trim();
            SentenceComposite sentence = new SentenceComposite();
            sentence.setWords(nextParser.handleRequest(s));
            sentences.add(sentence);
            pastPos = currentPos;
            }
        LOGGER.debug("The paragraph was divided into " + sentences.size() + " sentences.");

        return sentences;
    }




}
