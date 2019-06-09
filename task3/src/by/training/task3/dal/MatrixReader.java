package by.training.task3.dal;

import by.training.task3.exception.MatrixException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Class for reading matrix from file.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class MatrixReader implements MatrixDAL {


    /**
     * This logger logs the reading and validating of file.
     */
    public static final Logger LOGGER = LogManager.getLogger("by.training.task3.dal");

    /**
     * Array of data for threads.
     */
    private int[] threadData;


    /**
     * Getter for threadData.
     * @return threadData
     */
    public int[] getThreadData() {
        return threadData;
    }


    /**
     * This method to reading file.
     * @param str path to file
     * @return array
     * @throws MatrixException reading or validating exception
     */
    public int[][] read(final String str) throws MatrixException {

        Path path = Paths.get(str);
        List<String> list = null;
        try (Stream<String> lineStream = Files.lines(path)) {
            list = lineStream.filter(MatrixLineValidator::validateLine).
                    collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.debug("Reading error");
        }

        LOGGER.debug("The matrix is read");

        MatrixLineValidator.validate(list);

        LOGGER.debug("The matrix is validated");

        int[][] matrix = new int[list.size() - 1][list.size() - 1];

        String[] threadDataString = list.get(0).split(" ");

        threadData = new int[threadDataString.length];
        for (int i = 0; i < threadDataString.length; i++) {
            threadData[i] = Integer.parseInt(threadDataString[i]);
        }


        for (int i = 1; i < list.size(); i++) {
            int j = 0;
            for (String s : list.get(i).split(" ")) {
                matrix[i - 1][j] = Integer.parseInt(s);
                j++;
            }
        }
        return matrix;
    }

}
