package by.training.task3.reader;

import by.training.task3.exception.MatrixException;
import by.training.task3.validator.MatrixLineValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatrixReader {
    private int[] threadData;


    public int[] getThreadData() {
        return threadData;
    }

    public int[][] read(String str) throws MatrixException {

        Path path = Paths.get(str);
        List<String> list = null;
        try(Stream<String> lineStream = Files.lines(path)) {
            list = lineStream.filter(MatrixLineValidator::validateLine).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        MatrixLineValidator.validate(list);

        int[][] matrix = new int[list.size()-1][list.size()-1];

        String[] threadDataString = list.get(0).split(" ");

        threadData = new int[threadDataString.length];
        for (int i = 0; i < threadDataString.length; i++) {
            threadData[i] = Integer.parseInt(threadDataString[i]);
        }





        for (int i = 1; i < list.size(); i++) {
            int j =0;
            for (String s : list.get(i).split(" ")) {
                matrix[i-1][j] = Integer.parseInt(s);
                j++;
            }
        }







        return matrix;
    }

}
