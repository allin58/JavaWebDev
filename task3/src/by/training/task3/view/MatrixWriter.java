package by.training.task3.view;

import by.training.task3.bean.Matrix;

/**
 * Interface for view.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public interface MatrixWriter {

    /**
     * Abstract method for writing matrix to file.
     * @param str path to file
     * @param matrixBean inctance of Matrix
     */
    void write(String str, Matrix matrixBean);
}
