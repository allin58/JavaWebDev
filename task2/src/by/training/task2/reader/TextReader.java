package by.training.task2.reader;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**Class TextReader helps to read file and to return string.
 * <b>path</b>
 * @author Nikita Karchahin
 * @version 1.0
 */
public class TextReader {


    /**
     * Method which reads file and returns string.
     * @param p path to file
     * @return textual representation of the file
     */
    public String readFile(final String p) {
        String text = "";

        List<String> lines = new ArrayList<>();
        Path path = Paths.get(p);
                try (Stream<String> lineStream = Files.lines(path)) {
            lines =  lineStream.map(str -> str + '\n').collect(Collectors.toList());
        } catch (IOException ignored) {
        }
        for (String line : lines) {
            text += line;
        }



        return text;
    }


}
