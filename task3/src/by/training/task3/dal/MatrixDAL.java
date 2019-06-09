package by.training.task3.dal;

import by.training.task3.exception.MatrixException;

/**
 * Interface for dal.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public interface MatrixDAL {

    /**
     * Abstract method to getting data for threads.
     * @return array of integer dirits for threads
     */
     int[] getThreadData();

    /**
     * Abstract method to reading file.
     * @param str path to file
     * @return matrix
     * @throws MatrixException reading or validating exception
     */
     int[][] read(String str) throws MatrixException;
}
