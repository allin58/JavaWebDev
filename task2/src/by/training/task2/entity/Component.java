package by.training.task2.entity;

public abstract class Component {

    private String component;



    public abstract void addComponent(Component component);
    public abstract Component getChild(int i);

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }
}
