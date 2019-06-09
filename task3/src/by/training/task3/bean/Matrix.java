package by.training.task3.bean;

/**
 * Class Matrix is singlton for storing data of matrix.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public final  class Matrix {


    /**
     * Array of integer digits.
     */
    private int[][] matrixInt;

    /**
     * Instance of Matrix.
     */
    private static Matrix matrixBean = null;

    /**
     * The position for thread.
     */
    private int pos = 0;


    /**
     * This is empty constructor.
     */
    private Matrix() {
    }

    /**
     * This method implenents singlton template.
     * @return instance of Matrix
     */
   public static Matrix getInstance() {
        if (matrixBean == null) {
            matrixBean = new Matrix();
        }

        return matrixBean;
    }


    /**
     * This method dirives the matrix to screeen.
     * @return true if matrix exist
     */
    public boolean showMatrix() {
        if (matrixInt == null) {
            return false;
        }

        for (int[] ints : matrixInt) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
       return true;
    }

    /**
     * Method returns the matrix.
     * @return matrix
     */
    public int[][] getMatrix() {
        return matrixInt;
    }

    /**
     * Methods sets matrix.
     * @param matrix array of integer digits
     */
    public void setMatrix(final int[][] matrix) {
        this.matrixInt = matrix;
    }

    /**
     * Threads use this method for setting of diagonal data.
     * @param value diagonal data
     */
    public void setNext(final int value) {

        if (pos < matrixInt.length) {
            matrixInt[pos][pos] = value;
            pos++;
        }

    }




}
