package by.training.task3.sercice;

import by.training.task3.bean.MatrixRepository;

import java.util.concurrent.locks.ReentrantLock;

public class MatrixThread implements Runnable {

   private ReentrantLock locker;
   private MatrixRepository matrixRepository;
   private int value;

    public MatrixThread(ReentrantLock locker, MatrixRepository matrixRepository, int value) {
        this.locker = locker;
        this.matrixRepository = matrixRepository;
        this.value = value;
      //  System.out.println(value);
    }

    @Override
    public void run() {
        locker.lock();

        matrixRepository.setNext(value);

        locker.unlock();
    }
}
