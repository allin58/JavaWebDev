package by.training.task2.parsers;

import by.training.task2.entity.SentenceComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends BasicParser{

    private final String p = "[.!?]";

    private BasicParser nextParser;

    public SentenceParser() {
    }

    public SentenceParser(BasicParser nextParser) {
        this.nextParser = nextParser;
    }

    public List handleRequest(String text) {


        ArrayList<SentenceComponent> sentences = new ArrayList<>();

        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(text);
        ArrayList<Integer> pos = new ArrayList<>();
        while (matcher.find()) {
            pos.add(matcher.end());
        }
        int pastPos=0;

        for (int i = 0; i <pos.size() ; i++) {
            int currentPos = pos.get(i);
            String s = text.substring(pastPos,currentPos).trim();
            SentenceComponent sentence = new SentenceComponent(s);
            sentence.setWords(nextParser.handleRequest(s));
            sentences.add(sentence);
            pastPos = currentPos;
           }


        return sentences;
    }


}
