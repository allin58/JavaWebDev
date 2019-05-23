package by.training.task2.specification;

import by.training.task2.entity.Component;
import by.training.task2.entity.ParagraphComponent;
import by.training.task2.entity.SentenceComponent;

public class TextComporatorToParagraph implements TextComporator {
    @Override
    public int compare(Component o1, Component o2) {
        ParagraphComponent paragraphComponent1 = (ParagraphComponent)o1;
        ParagraphComponent paragraphComponent2 = (ParagraphComponent)o2;


        return paragraphComponent1.getSize() - paragraphComponent2.getSize();
    }
}
