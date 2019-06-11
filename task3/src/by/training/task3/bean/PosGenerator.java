package by.training.task3.bean;


/**
 * This class provides the correct brute force matrix.
 */
public class PosGenerator {

    /**
     * Value of position.
     */
    private int currentPos = 0;


    /**
     * This method returns and increments currentPos.
     * @return currentPos
     */
    public int getNext() {
        return currentPos++;
    }


}
