package by.training.task2.reader;

import java.io.BufferedReader;
import java.io.FileReader;

/**Class TextReader helps to read file and to return string.
 * <b>path</b>
 * @author Nikita Karchahin
 * @version 1.0
 */
public class TextReader {


    /**
     * Method which reads file and returns string.
     * @param path to file
     * @return textual representation of the file
     */
    public String readFile(final String path) {
        String text = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while (br.ready()) {
                text = text + br.readLine() + '\n';
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return text;
    }


}
