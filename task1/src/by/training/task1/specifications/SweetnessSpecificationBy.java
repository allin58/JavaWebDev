package by.training.task1.specifications;

import by.training.task1.entity.Sweetness;

/**
 * Interface for specification by specific attribute.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public interface SweetnessSpecificationBy extends SweetnessSpecification {

    /**
     *Function of specification sweetness.
     *@param sweetness specification criterion
     *@return boolean value
     */
    boolean specified(Sweetness sweetness);
}
