package by.training.task1.idgenerator;

/**
 * This class is needed for generation value of id.
 * <b>weight</b> and <b>name</b> and <b>sugar</b>
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public final class GeneratorID {

    /**
     * This is empty constructior.
     */
    private GeneratorID() {
    }

    /**
     * Field for storing ID.
     */
    private static int id = 1;

    /**
     * Function to get new id.
     * @return id value of id
     */
    public static int getId() {
        return id++;
    }
}
