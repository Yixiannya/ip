package chappi.chappiExceptions;

/**
 * Exception that decorates the given message
 * with a line that specifies the problem to
 * be with the deadline command.
 */
public class ChappiInvalidDeadlineException extends ChappiException {

    /**
     * Creates an exception specific to the deadline task
     * that contains the message to tell the user what went wrong.
     * @param message Input message given from method that throws this exception.
     */
    public ChappiInvalidDeadlineException(String message) {
        super("Sorry, this is an invalid Deadline task format.\n" + "      " + message);
    }
}
