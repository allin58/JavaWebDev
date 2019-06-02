package by.training.task3.main;
import by.training.task3.exception.MatrixException;
import by.training.task3.reader.*;
import by.training.task3.repository.MatrixRepository;
import by.training.task3.threads.MatrixThread;
import by.training.task3.validator.MatrixLineValidator;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Main {


    public static void main(String[] args) {
        MatrixReader matrixReader = new MatrixReader();
        ReentrantLock locker = new ReentrantLock();

        try {
          String path = "data/input.txt";

            MatrixRepository matrixRepository = MatrixRepository.getInstance();
            matrixRepository.setMatrix(matrixReader.read(path));
            for (int i : matrixReader.getThreadData()) {
               new Thread(new MatrixThread(locker,matrixRepository,i)).start();
            }



            matrixRepository.showMatrix();




        } catch (MatrixException e) {
            System.out.println("ошибка");
        }



    }


}
