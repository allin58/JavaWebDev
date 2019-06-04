package by.training.task3.bean;

public class MatrixRepository {
    private int[][] matrix;
    static private MatrixRepository matrixRepository = null;
    private int pos = 0;

    private MatrixRepository() {
    }

   public static MatrixRepository getInstance() {
        if (matrixRepository == null ) {
            matrixRepository = new MatrixRepository();
        }

        return matrixRepository;
    }

    public void showMatrix() {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public void setNext(int value) {

        if (pos < matrix.length) {
            matrix[pos][pos] = value;
            pos++;
        }

    }




}
