package by.training.task1.specifications;

import by.training.task1.entity.Sweetness;

/**
 * Class for specification by id.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class SweetnessSpecificationById implements SweetnessSpecificationBy {

    /**
     * This is criteria for specification.
     */
   private int id;


    /**
     *Constructor for setting  criteria".
     * @param id criteria for specification
     */
    public SweetnessSpecificationById(final int id) {
        this.id = id;
    }

    /**
     *Function of specification sweetness by id.
     *@param sweetness sorting criterion
     *@return boolean value
     */
    @Override
    public boolean specified(final Sweetness sweetness) {
        int actual = sweetness.getID();
        return (actual == id);

    }
}
