package by.training.task2.specification;

import by.training.task2.entity.Component;
import by.training.task2.entity.WordComponent;

public class TextComporatorToWorld implements TextComporator {

    @Override
    public int compare(Component o1, Component o2) {
        WordComponent wordComponent1 = (WordComponent)o1;
        WordComponent wordComponent2 = (WordComponent)o2;

        return wordComponent1.getSize()-wordComponent2.getSize();
    }

}
