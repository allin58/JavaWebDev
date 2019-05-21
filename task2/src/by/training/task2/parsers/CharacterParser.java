package by.training.task2.parsers;

import by.training.task2.entity.CaracterComponent;

import java.util.ArrayList;
import java.util.List;

public class CharacterParser extends BasicParser {

    @Override
    public List handleRequest(String text) {
        ArrayList<CaracterComponent> list = new ArrayList<>();
        for (char c : text.toCharArray()) {
            list.add(new CaracterComponent(c+""));
        }

        return list;
    }
}
