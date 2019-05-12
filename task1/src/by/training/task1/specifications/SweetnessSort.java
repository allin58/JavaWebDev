package by.training.task1.specifications;

import by.training.task1.entity.Sweetness;
import java.util.Comparator;

/**
 * Interface for sorting collection.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public interface SweetnessSort extends Comparator<Sweetness> {
    @Override
    int compare(Sweetness o1, Sweetness o2);
}
