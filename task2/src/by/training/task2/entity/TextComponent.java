package by.training.task2.entity;




import by.training.task2.exception.TextException;

import java.util.List;

public class TextComponent extends Component{
    private List<Component> paragraphs;


    public TextComponent(String text) throws TextException{

        if("".equals(text))  {
            throw new TextException("text error");}

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
