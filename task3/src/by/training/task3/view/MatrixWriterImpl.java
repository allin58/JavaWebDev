package by.training.task3.view;

import by.training.task3.bean.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 * Class for writing matrix to file.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class MatrixWriterImpl implements MatrixWriter {


    /**
     * This logger logs the writing to file.
     */
    public static final Logger LOGGER = LogManager.getLogger("by.training.task3.view");


    /**
     *  Method which writes matrix to file.
     * @param str path to file
     * @param matrixBean inctance of Matrix
     */
    public void write(final String str, final Matrix matrixBean) {

        Path path = Paths.get(str);
        List<String> lines = new ArrayList<>();

        for (int[] ints : matrixBean.getMatrix()) {
            String s = "";
            for (int anInt : ints) {
                s += anInt + " ";
            }
            lines.add(s);
        }


        try {
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.debug("Write error");
        }

        LOGGER.debug("The matrix is written to file");



    }
}
