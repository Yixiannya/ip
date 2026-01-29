package chappi.chappiExceptions;

/**
 * Exception that decorates the given message
 * with a line that specifies the problem to
 * be an unrecognised command.
 */
public class ChappiUnrecognisedCommandException extends ChappiException {

    /**
     * Creates an exception that contains the message
     * to tell the user that the command was not recognised.
     */
    public ChappiUnrecognisedCommandException() {
        super("I did not recognise that command.");
    }
}
