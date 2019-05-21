package by.training.task2.entity;

import by.training.task2.parsers.BasicParser;

import java.util.List;

public class WordComponent extends Component{
    private List<Component> characters;


    public WordComponent(String word) {
        super.setComponent(word);
    }

    public int getSize() {
        return characters.size();
    }
    @Override
    public void addComponent(Component component) {
        characters.add(component);
    }

    @Override
    public Component getChild(int i) {
        return characters.get(i);
    }

    public void setCharacters(List<Component> sentences) {

        this.characters = sentences;
    }
}
