package by.training.task2.entity;

import java.util.List;

public class CaracterComponent extends Component {



    public CaracterComponent(String character) {
        super.setComponent(character);
    }

    public int getSize() {
        return 1;
    }

    @Override
    public void addComponent(Component component) {

    }

    @Override
    public Component getChild(int i) {
        return null;
    }




}
