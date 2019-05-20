package by.training.task2.parsers;


import by.training.task2.entity.Paragraph;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagrphParser implements BasicParser{

   private BasicParser nextParser;

    public ParagrphParser(BasicParser nextParser) {
        this.nextParser = nextParser;
    }

    public List handleRequest(String text) {


         ArrayList<Paragraph> paragraphs = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\t\\t");
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
            Paragraph paragraph = new Paragraph(p);
            paragraph.setSentences(nextParser.handleRequest(p));
            paragraphs.add(paragraph);
            pastPos = currentPos;
          }
        String p = text.substring(pastPos);
        Paragraph paragraph = new Paragraph(p);
        paragraph.setSentences(nextParser.handleRequest(p));
        paragraphs.add(paragraph);

        return paragraphs;
    }


}
