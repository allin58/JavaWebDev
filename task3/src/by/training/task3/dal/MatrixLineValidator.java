package by.training.task3.dal;

import by.training.task3.exception.MatrixException;

import java.util.List;


/**
 * Class for validating of matrix data.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public final class MatrixLineValidator {

    /**
     * Private constructor.
     */
    private MatrixLineValidator() {
    }

    /**
     * This method validates line.
     * @param line for validation
     * @return true if line is suitable
     */
    public static boolean validateLine(final String line) {
        if ("".equals(line)) {
            return false;
        }
        String[] arrString = line.split(" ");


        for (String s : arrString) {

            try {
                int i = Integer.parseInt(s);
                if (i < 0) {
                    throw new MatrixException();
                }
            } catch (Exception e) {
               return false;
            }
        }

       return true;
    }

    /**
     * This method validates matrix.
     * @param lines of digits
     * @throws MatrixException reading or validating exception
     */
    public static void validate(final List<String> lines) throws MatrixException {
        int numberOfLines = lines.size();

        if ((numberOfLines - 1) < 8 || (numberOfLines - 1) > 12) {
            throw new MatrixException();
        }

        for (int i = 1; i < numberOfLines; i++) {
               if (lines.get(i).split(" ").length != numberOfLines - 1) {
                throw new MatrixException();

            }
        }

        int numberOfThreads = lines.get(0).split(" ").length;
        if (numberOfThreads < 4 || numberOfThreads > 6) {
            throw new MatrixException();
        }




    }
}
