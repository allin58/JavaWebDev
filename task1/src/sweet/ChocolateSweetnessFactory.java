package sweet;

/**
 * Class CandySwetnessFactory is needed to create Chocolate
 *
 * @author Nikita Karchahin
 * @version 1.0
 */

public class ChocolateSweetnessFactory implements SweetnessFactory {
    @Override
    public Sweetness createSweetness(String name, double weight) {

        return new Chocolate(name,weight);
    }
}
