package by.training.task1.entity;

/**
 * Interface for Factory.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */

public interface SweetnessFactory {

    /**
     * Function to create a new sweetnes.
     * @param name This is name of sweetnes
     * @param weight This is weight of sweetnes
     * @return returns new sweetness
     */
    Sweetness createSweetness(String name, double weight, int id);
}
