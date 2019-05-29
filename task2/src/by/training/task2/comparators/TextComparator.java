package by.training.task2.comparators;

import by.training.task2.entity.Component;

import java.util.Comparator;

/**
 * Interface for comparators.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public interface TextComparator extends Comparator<Component> {

    @Override
    int compare(Component o1, Component o2);

}
