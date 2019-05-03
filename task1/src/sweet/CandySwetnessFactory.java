package sweet;

/**
 * Class CandySwetnessFactory is needed to create Candy
 *
 * @author Nikita Karchahin
 * @version 1.0
 */

public class CandySwetnessFactory implements SweetnessFactory {
    @Override
    public Sweetness createSweetness(String name, double weight) {
        return new Candy(name, weight);
    }
}
