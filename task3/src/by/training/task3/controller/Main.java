package by.training.task3.controller;
import by.training.task3.dal.reader.MatrixReader;
import by.training.task3.exception.MatrixException;
import by.training.task3.bean.MatrixRepository;
import by.training.task3.sercice.MatrixService;
import by.training.task3.sercice.MatrixThread;
import by.training.task3.view.MatrixWriter;

import java.util.concurrent.locks.ReentrantLock;

public class Main {


    public static void main(String[] args) {


        MatrixService matrixService = new MatrixService();
        matrixService.readMatrix("data/input.txt");
        matrixService.fillMatrix();
        new MatrixWriter().write("data/output.txt",MatrixRepository.getInstance());

        MatrixRepository.getInstance().showMatrix();









    }


}
