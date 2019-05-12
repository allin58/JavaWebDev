package by.training.task1.entity;

/**
 * Class CandySwetnessFactory is needed to create Chocolate.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */

public class ChocolateSweetnessFactory implements SweetnessFactory {

    /**
     *Function to get new chocolate with specific values.
     *@param weight
     *@param name
     *@return returns new chocolate
     */
    @Override
    public Sweetness createSweetness(final String name, final double weight, final int id) {

        return new Chocolate(name, weight, id);
    }
}
