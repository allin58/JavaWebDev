package by.training.task3.service;

import by.training.task3.bean.Matrix;

import java.util.concurrent.locks.ReentrantLock;


/**
 * Class MatrixThread implements Runnable.
 * Instances of this class change the diagonal of matrix
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class MatrixThread implements Runnable {

    /**
     * Locker is common to all threads.
     */
   private ReentrantLock locker;

    /**
     * Bean.
     */
   private Matrix matrixBean;

    /**
     * Digit is for each threads.
     */
   private int value;


    /**
     * Constructor with parametrs.
     * @param locker common locker
     * @param matrixBean bean
     * @param value digit is for each threads
     */
    public MatrixThread(final ReentrantLock locker, final Matrix matrixBean, final int value) {
        this.locker = locker;
        this.matrixBean = matrixBean;
        this.value = value;

    }


    /**
     * This method sets new value in diagonal.
     */
    @Override
    public void run() {
        locker.lock();

        matrixBean.setNext(value);

        locker.unlock();
    }
}
