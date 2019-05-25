package by.training.task2.parsers;

/** Class ParserCounter is created for counting parsers.
 * <b>counter</b>
 */
public class ParserCounter {

    /**
     * Varible to store the number of parsers.
     */
    private static int counter = 0;

    /**
     * Method which increments counter.
     */
   public static void incremantCounter() {
        counter++;
    }

    /**
     * Function to check the quantity.
     * @return number of parsers
     */
    public static int checkCounter() {
        return counter;
    }


}
