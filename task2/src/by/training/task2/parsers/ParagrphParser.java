package by.training.task2.parsers;



import by.training.task2.entity.ParagraphComposite;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class ParagrphParser is crated for parsing to paragraphs.
 *<b>nextParser</b>
 * @author Nikita Karchahin
 * @version 1.0
 */
public class ParagrphParser extends BasicParser {

    /**
     * Template for regular expression.
     */
    private static final String  TEMPLATE = "\\t\\t";

    /**
     * Next parser for implantation chain of responsibility.
     */
    private BasicParser nextParser;


    /**
     * Constructor sets next parser.
     * @param nextParser varible for linking parsers
     */
    public ParagrphParser(final BasicParser nextParser) {
        this.nextParser = nextParser;
    }


    /**
     * handleRequest method parses string.
     * @param text input string
     * @return list of composites
     */
    public List handleRequest(final String text) {

         ArrayList<ParagraphComposite> paragraphs = new ArrayList<>();

        Pattern pattern = Pattern.compile(TEMPLATE);
        Matcher matcher = pattern.matcher(text);
        ArrayList<Integer> pos = new ArrayList<>();
        while (matcher.find()) {
            pos.add(matcher.start());
        }
        int pastPos = 0;
        int currentPos = 0;
        for (int i = 1; i < pos.size(); i++) {
            currentPos = pos.get(i);
            String p = text.substring(pastPos, currentPos - 1);
            ParagraphComposite paragraph = new ParagraphComposite();
            paragraph.setSentences(nextParser.handleRequest(p));
            paragraphs.add(paragraph);
            pastPos = currentPos;
          }
        String p = text.substring(pastPos);
        ParagraphComposite paragraph = new ParagraphComposite();
        paragraph.setSentences(nextParser.handleRequest(p));
        paragraphs.add(paragraph);
        LOGGER.debug("The text was divided into " + paragraphs.size() + " paragraphs.");

        return paragraphs;
    }





}
