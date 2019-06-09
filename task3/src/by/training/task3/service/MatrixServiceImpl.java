package by.training.task3.service;

import by.training.task3.bean.Matrix;
import by.training.task3.dal.MatrixDAL;
import by.training.task3.dal.MatrixReader;
import by.training.task3.exception.MatrixException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Class for matrix service.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class MatrixServiceImpl implements MatrixService {

    /**
     * Inctance of data access layer.
     */
    private MatrixDAL matrixDAL;

    /**
     * Bean.
     */
    private Matrix matrixBean;

    /**
     * This logger logs the behaviour of threads.
     */
    public static final Logger LOGGER = LogManager.getLogger("by.training.task3.service");


    /**
     * This method reads matrix from file.
     * @param path of file
     */
    @Override
    public void readMatrix(final String path) {
        matrixDAL = new MatrixReader();
        matrixBean = Matrix.getInstance();


        try {
            matrixBean.setMatrix(matrixDAL.read(path));
        } catch (MatrixException e) {
            LOGGER.debug("Input error");
        }


    }


    /**
     * This method fills diagonal of matrix.
     */
    @Override
    public void fillMatrix() {
        ReentrantLock locker = new ReentrantLock();
        ArrayList<Integer> dataForFill = new ArrayList<>();
        for (int i : matrixDAL.getThreadData()) {
            dataForFill.add(i);
        }

        if (matrixBean.getMatrix().length > matrixDAL.getThreadData().length) {
            int difference = matrixBean.getMatrix().
                    length - matrixDAL.getThreadData().length;
            for (int i = 0; i < difference; i++) {
                dataForFill.add(matrixDAL.getThreadData()[i]);            }


        }

        for (Integer integer : dataForFill) {
            new Thread(new MatrixThread(locker, matrixBean, integer)).start();
        }
        LOGGER.debug("The diagonal of the matrix is filled with data");

    }

    /**
     * Method for showing matrix.
     * @return true if matrix exists
     */
    @Override
    public boolean showMatrix() {
       return matrixBean.showMatrix();
    }
}
