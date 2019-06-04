package by.training.task3.dal.validator;

import by.training.task3.exception.MatrixException;

import java.util.List;

public class MatrixLineValidator {



    public static boolean validateLine(String line) {
        if ("".equals(line)) {
            return false;
        }
        String[] arrString = line.split(" ");


        for (String s : arrString) {

            try {
                Integer.parseInt(s);
            } catch (NumberFormatException e) {
               return false;
            }
        }

       return true;
    }

    public static void validate(List<String> lines) throws MatrixException {
        int numberOfLines = lines.size();

        if ((numberOfLines-1) < 8 || (numberOfLines-1) > 12) {
            throw new MatrixException();
        }

        for (int i = 1; i < numberOfLines ; i++) {
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
