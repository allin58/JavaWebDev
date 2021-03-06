package by.training.task1.specifications;

import by.training.task1.entity.Sweetness;

/**
 * Class for sorting collection by weight.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class SweetnessSortByWeight implements SweetnessSort, SweetnessSpecification {

    /**
     *Sorting function.
     *@return returns int value
     */
    @Override
    public int compare(final Sweetness o1, final Sweetness o2) {
        return Double.compare(o1.getWeight(), o2.getWeight());
    }
}
