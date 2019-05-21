package by.training.task2.entity;

import by.training.task2.parsers.ParagrphParser;
import by.training.task2.parsers.SentenceParser;
import by.training.task2.parsers.WordParser;

import java.util.List;

public class TextComponent extends Component{
    private List<Component> paragraphs;


    public TextComponent(String text) {
        super.setComponent(text);
    }

    public int getSize() {
        return paragraphs.size();
    }

    @Override
    public void addComponent(Component component) {
        paragraphs.add(component);
    }

    @Override
    public Component getChild(int i) {

        return paragraphs.get(i);
    }

    public void setParagraphs(List<Component> sentences) {
        this.paragraphs = sentences;
    }




}
