package by.training.task2.parsers;

import by.training.task2.entity.Word;

import java.util.ArrayList;
import java.util.List;

public class WordParser implements BasicParser {


    public List handleRequest(String text) {
        ArrayList<Word> list = new ArrayList<>();
        String[] arr = text.split(" ");
        for (String s : arr) {
            list.add(new Word(s));
           }


        return list;
    }
}
