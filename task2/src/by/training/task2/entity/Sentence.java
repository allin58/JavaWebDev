package by.training.task2.entity;

import java.util.List;

public class Sentence implements BasicEntity {
    private String sentence;
    private List<BasicEntity> words;


    public Sentence(String sentence) {
        this.sentence = sentence;
       /* WordParser wordParser = new WordParser();
        words = wordParser.handleRquest(sentence);*/
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public List<BasicEntity> getWords() {
        return words;
    }

    public void setWords(List<BasicEntity> words) {
        this.words = words;
    }
}
