package by.training.task2.entity;




import java.util.List;

/**Class ParagraphComposite contains list of sentences.
 * <b>sentences</b>
 * @author Nikita Karchahin
 * @version 1.0
 */
public class ParagraphComposite implements Component {

    /**
     * Varible which stores list of sentences.
     */
    private List<Component> sentences;



    /**
     *  Method to get size.
     * @return size of list
     */
    public int getSize() {

        return sentences.size();
    }


    /**
     * Implementation abstract method remove().
     * @param component to delite
     */
    public void remove(final Component component) {
        sentences.remove(component);
    }


    /**
     * Implementation abstract method addComponent(Component component).
     * @param component is added to the collection
     */
    @Override
    public void addComponent(final Component component) {
        sentences.add(component);
    }


    /**
     * Implementation abstract method getChild(int i).
     * @param i index
     * @return component depends on i
     */
    @Override
    public Component getChild(final int i) {
     return sentences.get(i);
    }

    /**
     * Method which sets colletion of sentences.
     * @param sentences list of sentences
     */
    public void setSentences(final List<Component> sentences) {
        this.sentences = sentences;
    }


    /**
     *This is function which return string.
     * @return textual representation of composite
     */
    @Override
    public String assemble() {
        String str = "";

        for (Component sentence : sentences) {
            str += sentence.assemble();
        }
        str = str.trim();
        return str;
    }




    /**
     * HashCode function.
     * @return hashcode of composite
     */
    public int hashCode() {
        return sentences.hashCode();
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
        ParagraphComposite object = (ParagraphComposite) o;
        return (object.sentences.equals(sentences));
    }






}
