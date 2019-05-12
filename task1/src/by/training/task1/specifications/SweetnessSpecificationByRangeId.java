package by.training.task1.specifications;

import by.training.task1.entity.Sweetness;


/**
 * Class for specification by range id.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class SweetnessSpecificationByRangeId implements SweetnessSpecificationBy {
    /**
     * This is min criteria for specification.
     */
   private int min;
    /**
     * This is max criteria for specification.
     */
    private int max;

    /**
     *Constructor for setting  criteria".
     * @param min criteria for specification
     * @param max criteria for specification
     */
    public SweetnessSpecificationByRangeId(final int min, final int max) {
        this.min = min;
        this.max = max;
    }


    /**
     *Function of specification sweetness by range id.
     *@param sweetness sorting criterion
     *@return boolean value
     */
    @Override
    public boolean specified(final Sweetness sweetness) {
        int actual = sweetness.getID();
        if (min <= actual && actual <= max)   {
            return true;
        }
        return false;
    }
}
