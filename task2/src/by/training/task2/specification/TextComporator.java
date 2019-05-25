package by.training.task2.specification;

import by.training.task2.entity.Component;

import java.util.Comparator;

/**
 * Interface for specification.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public interface TextComporator extends Comparator<Component> {

    @Override
    int compare(Component o1, Component o2);

}
