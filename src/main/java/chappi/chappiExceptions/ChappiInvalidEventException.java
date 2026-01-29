package chappi.chappiExceptions;

/**
 * Exception that decorates the given message
 * with a line that specifies the problem to
 * be with the event command.
 */
public class ChappiInvalidEventException extends ChappiException {

    /**
     * Creates an exception specific to the event task
     * that contains the message to tell the user what went wrong.
     * @param message Input message given from method that throws this exception.
     */
    public ChappiInvalidEventException(String message) {
        super("Sorry, this is an invalid Event task format.\n"
                + "      "
                + message);
    }
}
