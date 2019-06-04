package by.training.task3.sercice;

import by.training.task3.bean.MatrixRepository;
import by.training.task3.dal.reader.MatrixReader;
import by.training.task3.exception.MatrixException;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixService {
   private MatrixReader matrixReader;
    private MatrixRepository matrixRepository;


    public void readMatrix(String path) {
         matrixReader = new MatrixReader();
         matrixRepository = MatrixRepository.getInstance();


        try {
            matrixRepository.setMatrix(matrixReader.read(path));
        } catch (MatrixException e) {
            e.printStackTrace();
        }


    }

    public void fillMatrix() {
        ReentrantLock locker = new ReentrantLock();
        ArrayList<Integer> dataForFill = new ArrayList<>();
        for (int i : matrixReader.getThreadData()) {
            dataForFill.add(i);
        }

        if (matrixRepository.getMatrix().length > matrixReader.getThreadData().length) {
            int difference = matrixRepository.getMatrix().length - matrixReader.getThreadData().length;
            for (int i = 0; i < difference; i++) {
                dataForFill.add(matrixReader.getThreadData()[i]);            }


        }

        for (Integer integer : dataForFill) {
            new Thread(new MatrixThread(locker,matrixRepository,integer)).start();
        }





    }



}
