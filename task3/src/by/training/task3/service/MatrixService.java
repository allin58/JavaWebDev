package by.training.task3.service;


/**
 * Interface for service.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public interface MatrixService {

    /**
     * Abstract method for reading matrix from file.
     * @param path of file
     */
    void readMatrix(String path);

    /**
     * Abstract method for filling matrix.
     */
    void fillMatrix();


    /**
     * Abstract method for showing matrix.
     * @return true if matrix exists
     */
    boolean showMatrix();

}
