package by.training.task2.entity;

import by.training.task2.specification.TextComporator;

import java.util.List;

public class SentenceComponent extends Component {
    private List<Component> words;


    public SentenceComponent(String sentence) {
        super.setComponent(sentence);
    }

    public int getSize() {
        return words.size();
    }
    @Override
    public void addComponent(Component component) {
        words.add(component);
    }

    @Override
    public Component getChild(int i) {
        return words.get(i);
    }

    public void setWords(List<Component> sentences) {

        this.words = sentences;
    }

    public void query(TextComporator textComporator){

        words.sort(textComporator);

    }

}
