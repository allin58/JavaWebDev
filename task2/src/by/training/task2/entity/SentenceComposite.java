package by.training.task2.entity;





import java.util.List;


/**Class SentenceComposite contains list of words.
 * <b>words</b>
 * @author Nikita Karchahin
 * @version 1.0
 */
public class SentenceComposite implements Component {

    /**
     * Varible which stores list of words.
     */
    private List<Component> words;



    /**
     * Implementation abstract method getSize().
     * @return size of list
     */
    public int getSize() {
        return words.size();
    }

    /**
     * Implementation abstract method addComponent(Component component).
     * @param component is added to the collection
     */
    @Override
    public void addComponent(final Component component) {
        words.add(component);
    }

    /**
     * Implementation abstract method getChild(int i).
     * @param i index
     * @return component depends on i
     */
    @Override
    public Component getChild(final int i) {
        return words.get(i);
    }

    /**
     * Method which sets colletion of sentences.
     * @param words list of words
     */
    public void setWords(final List<Component> words) {

        this.words = words;
    }

    /**
     * Implementation abstract method remove().
     * @param component to delite
     */
    public void remove(final Component component) {
        words.remove(component);
    }


    /**
     *This is function which return string.
     * @return textual representation of composite
     */
    @Override
    public String assemble() {
        String str = "";
        String temp;
        for (Component word : words) {
            temp = word.assemble();

            if (",".equals(temp) || ".".equals(temp) || "?".equals(temp) || "!".equals(temp) || "...".equals(temp)) {
                str = str + temp;
            } else {
                str = str + " " + temp;
            }
        }

       return str;
    }






    /**
     * HashCode function.
     * @return hashcode of composite
     */
    public int hashCode() {
        return words.hashCode();
    }

    /**
     *
     * @param o object for comparing
     * @return true if objects are equals
     */
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
        SentenceComposite object = (SentenceComposite) o;
        return (object.words.equals(words));
    }





}
