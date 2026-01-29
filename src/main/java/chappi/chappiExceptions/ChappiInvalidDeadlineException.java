package chappi.chappiExceptions;

public class ChappiInvalidDeadlineException extends ChappiException {
    public ChappiInvalidDeadlineException(String message) {
        super("Sorry, this is an invalid Deadline task format.\n"
                + "      "
                + message);
    }
}
