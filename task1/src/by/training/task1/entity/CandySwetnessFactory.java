package by.training.task1.entity;

/**
 * Class CandySwetnessFactory is needed to create Candy.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */

public class CandySwetnessFactory implements SweetnessFactory {

    /**
     *Function to get new candy with specific values.
     *@param weight
     *@param name
     *@return returns new candy
     */
    @Override
    public Sweetness createSweetness(final String name, final double weight, final int id) {
        return new Candy(name, weight, id);
    }
}
