package by.training.task3.controller;
import by.training.task3.bean.Matrix;
import by.training.task3.service.MatrixService;
import by.training.task3.service.MatrixServiceImpl;
import by.training.task3.view.MatrixWriterImpl;

import java.util.Scanner;

/**
 * Controller class.
 */
public final class Main {

    /**
     * Private constructor.
     */
    private Main() {
    }

    /**
     * Method main.
     * @param args args
     */
    public static void main(final String[] args) {

        Scanner scanner = new Scanner(System.in);
        MatrixService matrixService = null;
        boolean flag = true;

        System.out.println("1 - read matrix from the file" + '\n'
                + "2 - fill the matrix" + '\n'
                + "3 - dirive the matrix to the screen" + '\n'
                + "4 - write the matrix to the file" + '\n'
                + "5 - exit");


while (flag) {

    String str = scanner.next();

    switch (str) {
       case "1" : matrixService = new MatrixServiceImpl();
                  matrixService.readMatrix("data/input.txt");
                  break;

       case "2" : if (matrixService != null) {
                     matrixService.fillMatrix();
                  } else {
                     System.out.println("The matrix is not initialized");
                     }
                   break;

       case "3" : if (!matrixService.showMatrix()) {
           System.out.println("The matrix not exist yet");
       }
       break;

        case "4" :
            new MatrixWriterImpl().write("data/output.txt", Matrix.getInstance());
            break;

        case "5" :
            flag = false;
            break;

        default:
            System.out.println("Error of input");
            break;


    }

}
        scanner.close();



    }





}
