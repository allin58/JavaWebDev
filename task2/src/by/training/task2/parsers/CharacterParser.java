package by.training.task2.parsers;

import by.training.task2.entity.CaracterComponent;
import by.training.task2.entity.Component;
import by.training.task2.entity.WordComponent;

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


    public  String assemble(Component component) {

        WordComponent wordComponent = (WordComponent) component;
        String str = "";


        for (int i = 0; i < wordComponent.getSize(); i++) {
         str = str + wordComponent.getChild(i).getComponent();

        }

        return str;
    }

}
