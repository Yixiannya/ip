package chappi.chappiExceptions;

/**
 * Exception that decorates the given message
 * with a line that specifies the problem to
 * be with the todo command.
 */
public class ChappiInvalidTodoException extends ChappiException {

    /**
     * Creates an exception specific to the todo task
     * that contains the message to tell the user what went wrong.
     * @param message
     */
    public ChappiInvalidTodoException(String message) {
        super("Sorry, this is an invalid Todo task format.\n" + "      " + message);
    }
}
