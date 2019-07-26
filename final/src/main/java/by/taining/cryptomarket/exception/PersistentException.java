package by.taining.cryptomarket.exception;

/**
 * PersistentException class inherited from the Exception class,
 * this class was created for persistent exeption.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class PersistentException extends Exception {

    /**
     * Empty constructor.
     */
    public PersistentException() {}

    /**
     * Constructor with params.
     * @param message message
     * @param cause cause
     */
    public PersistentException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with param.
     * @param message message
     */
    public PersistentException(String message) {
        super(message);
    }

    /**
     * Constructor with param.
     * @param cause cause
     */
    public PersistentException(Throwable cause) {
        super(cause);
    }
}
