package by.training.task2.exception;



/**
 * TextException class inherited from the Exception class,
 * this class was created for text exeption.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class TextException extends Exception {
    /**
     * Empty constructor.
     */
    public TextException() { }

    /**
     * Constructor with param.
     * @param message message
     */
    public TextException(final String message) {
        super(message);
    }

    /**
     * Constructor with param.
     * @param throwable throwable
     */
    public TextException(final Throwable throwable) {
        super(throwable);
    }

    /**
     * Constructor with params.
     * @param message message
     * @param throwable throwable
     */
    public TextException(final String message, final Throwable throwable) {
        super(message, throwable);
    }




}
