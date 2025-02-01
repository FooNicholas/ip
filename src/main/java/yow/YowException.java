package yow;
/**
 * Custom exception class for handling errors specific to the Yow application.
 * This exception is thrown when invalid commands or incorrect input formats are encountered.
 */
public class YowException extends Exception {

    /**
     * Constructs a new YowException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public YowException(String message) {
        super(message);
    }
}