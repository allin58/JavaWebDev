package by.training.task2.entity;

import java.util.List;

public class Paragraph implements BasicEntity {
    private String paragraph;
    private List<BasicEntity> sentences;


    public Paragraph(String paragraph) {
        this.paragraph = paragraph;
       /* SentenceParser sentenceParser =new SentenceParser();
        sentences = sentenceParser.handleRquest(paragraph);*/

    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public List<BasicEntity> getSentences() {
        return sentences;
    }

    public void setSentences(List<BasicEntity> sentences) {
        this.sentences = sentences;
    }
}
