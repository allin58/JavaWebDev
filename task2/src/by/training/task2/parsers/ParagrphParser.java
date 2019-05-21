package by.training.task2.parsers;


import by.training.task2.entity.ParagraphComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagrphParser extends BasicParser{

    private final String p = "\\t\\t";

   private BasicParser nextParser;

    public ParagrphParser() {
           }



    public ParagrphParser(BasicParser nextParser) {
        this.nextParser = nextParser;
    }

    public List handleRequest(String text) {


         ArrayList<ParagraphComponent> paragraphs = new ArrayList<>();

        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(text);
        ArrayList<Integer> pos = new ArrayList<>();
        while (matcher.find()) {
            pos.add(matcher.start());
        }
        int pastPos=0;
        int currentPos=0;
        for (int i = 1; i < pos.size(); i++) {
            currentPos = pos.get(i);
            String p = text.substring(pastPos,currentPos-1);
            ParagraphComponent paragraph = new ParagraphComponent(p);
            paragraph.setSentences(nextParser.handleRequest(p));
            paragraphs.add(paragraph);
            pastPos = currentPos;
          }
        String p = text.substring(pastPos);
        ParagraphComponent paragraph = new ParagraphComponent(p);
        paragraph.setSentences(nextParser.handleRequest(p));
        paragraphs.add(paragraph);

        return paragraphs;
    }


}
