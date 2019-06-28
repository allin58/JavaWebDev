package by.training.task4.dal;

import java.io.File;

/**
 * Interface for dal.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public interface DAL {

    /**
     * Abstract method to getting file.
     * @param name name of file
     * @return file
     */
    File getFile(String name);
}
