package by.training.task2.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TextReader {

    public String readFile(String path) {
        String text= "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while (br.ready()) {
                text = text + br.readLine() + '\n';
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return text;
    }


}
