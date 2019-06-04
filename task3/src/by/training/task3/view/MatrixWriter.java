package by.training.task3.view;

import by.training.task3.bean.MatrixRepository;
import by.training.task3.dal.validator.MatrixLineValidator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatrixWriter {

    public void write(String str, MatrixRepository matrixRepository) {

        Path path = Paths.get(str);
        List<String> lines = new ArrayList<>();

        for (int[] ints : matrixRepository.getMatrix()) {
            String s = "";
            for (int anInt : ints) {
                s += anInt + " ";
            }
            lines.add(s);
        }


        try {
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
