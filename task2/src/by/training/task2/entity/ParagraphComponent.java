package by.training.task2.entity;

import java.util.List;

public class ParagraphComponent extends Component {

    private List<Component> sentences;


    public ParagraphComponent(String paragraph) {
        super.setComponent(paragraph);
    }

    public int getSize() {
        return sentences.size();
    }

    @Override
    public void addComponent(Component component) {
        sentences.add(component);
    }

    @Override
    public Component getChild(int i) {
        return sentences.get(i);
    }

    public void setSentences(List<Component> sentences) {
        this.sentences = sentences;
    }
}
