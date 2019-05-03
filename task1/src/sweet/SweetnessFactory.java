package sweet;

/**
 * Interface for Factory
 *
 * @author Nikita Karchahin
 * @version 1.0
 */

public interface SweetnessFactory {
    Sweetness createSweetness(String name, double weight);
}
