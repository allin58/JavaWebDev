package by.training.task2.specification;

import by.training.task2.entity.Component;
import by.training.task2.entity.SentenceComponent;
import by.training.task2.entity.WordComponent;

public class TextComporatorToSentence implements TextComporator {
    @Override
    public int compare(Component o1, Component o2) {
        SentenceComponent sntenceComponent1 = (SentenceComponent)o1;
        SentenceComponent sntenceComponent2 = (SentenceComponent)o2;

        return sntenceComponent1.getSize() - sntenceComponent2.getSize();
       }
}
