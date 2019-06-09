package by.training.task3.exception;




/**
 * MatrixException class inherited from the Exception class,
 * this class was created for matrix exeption.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class MatrixException extends Exception {

    /**
     * Empty constructor.
     */
    public MatrixException() {
        super();
    }

    /**
     * Constructor with param.
     * @param message message
     */
    public MatrixException(final String message) {
        super(message);
    }



    /**
     * Constructor with params.
     * @param message message
     * @param cause cause
     */
    public MatrixException(final String message, final Throwable cause) {
        super(message, cause);
    }


    /**
     * Constructor with param.
     * @param cause cause
     */
    public MatrixException(final Throwable cause) {
        super(cause);
    }



}
