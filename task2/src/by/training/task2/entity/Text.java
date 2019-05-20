package by.training.task2.entity;

import by.training.task2.parsers.ParagrphParser;
import by.training.task2.parsers.SentenceParser;
import by.training.task2.parsers.WordParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class Text implements BasicEntity {
   private String text = "";
   private List<BasicEntity> paragraphs;


    public Text(String file) {

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.ready()) {
                text = text + br.readLine() + '\n';
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
      /*  ParagrphParser paragrphParser = new ParagrphParser();
        paragraphs = paragrphParser.handleRquest(text);*/

        WordParser wordParser = new WordParser();
        SentenceParser sentenceParser = new SentenceParser(wordParser);
        ParagrphParser paragrphParser = new ParagrphParser(sentenceParser);
        paragrphParser.handleRequest(text);



    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<BasicEntity> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<BasicEntity> paragraphList) {
        this.paragraphs = paragraphList;
    }




}
