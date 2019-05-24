package by.training.task2.parsers;

import by.training.task2.entity.Component;
import by.training.task2.entity.SentenceComponent;
import by.training.task2.entity.WordComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser extends BasicParser {

    private final String p = "[!?//.]|,|\\.\\.\\.";

    private BasicParser nextParser;




    public WordParser(BasicParser nextParser) {
        this.nextParser = nextParser;
    }

    public List handleRequest(String text) {
        ArrayList<WordComponent> words = new ArrayList<>();

        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(text);
        ArrayList<Integer> pos = new ArrayList<>();

        while (matcher.find()) {
            pos.add(matcher.start());
        }

        for (int i = pos.size() - 1; i >= 0; i--) {
            text = text.substring(0, pos.get(i)) + " " + text.substring(pos.get(i), text.length());

        }

        for (String s : text.split(" ")) {
            WordComponent word = new WordComponent(s);
            word.setCharacters(nextParser.handleRequest(s));
           // list.add(new WordComponent(s));
            words.add(word);

        }

        logger.debug("The sentence was divided into " + words.size() + " words.");
        return words;
    }

    public String assemble(Component component) {



        SentenceComponent sentenceComponent = (SentenceComponent) component;
        String str = "";
        String temp;

         for (int i = 0; i < sentenceComponent.getSize(); i++) {
            temp = nextParser.assemble(sentenceComponent.getChild(i));
            if (",".equals(temp) || ".".equals(temp) || "?".equals(temp) || "!".equals(temp) || "...".equals(temp)) {
                str = str + temp;
            } else {
                str = str + " " + temp;
            }
        }
        logger.debug("The sentence was assembled from " + sentenceComponent.getSize() + " words.");
        return str;
    }
}


