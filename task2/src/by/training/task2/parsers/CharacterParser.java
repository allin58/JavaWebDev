package by.training.task2.parsers;

import by.training.task2.entity.CaracterComposite;
import by.training.task2.entity.Component;
import by.training.task2.entity.WordComposite;

import java.util.ArrayList;
import java.util.List;

/**
 * Class SentenceParser is crated for parsing to characters.
 *<b>nextParser</b>
 * @author Nikita Karchahin
 * @version 1.0
 */
public class CharacterParser extends BasicParser {



    /**
     * handleRequest method parses string.
     * @param text input string
     * @return list of composites
     */
    @Override
    public List handleRequest(final String text) {
        ArrayList<CaracterComposite> list = new ArrayList<>();
        for (char c : text.toCharArray()) {
            list.add(new CaracterComposite(c + ""));

        }


        return list;
    }


    /**
     * assemble method assembles to string.
     * @param component list of composites
     * @return output string
     */
    public  String assemble(final Component component) {

        WordComposite wordComposite = (WordComposite) component;
        String str = "";


        for (int i = 0; i < wordComposite.getSize(); i++) {
         str = str + wordComposite.getChild(i).getComponent();

        }

        return str;
    }

}
