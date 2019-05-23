package by.training.task2.entity;

public abstract class Component {

    private String component;



    public abstract void addComponent(Component component);
    public abstract Component getChild(int i);
    public abstract int getSize();

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }


    public int hashCode() {

        return component.hashCode();
    }


    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Component object = (Component) o;
        return (object.getComponent().equals(component));
    }

}
