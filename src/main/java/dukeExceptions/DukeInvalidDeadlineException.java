package dukeExceptions;

public class DukeInvalidDeadlineException extends DukeException {
    public DukeInvalidDeadlineException(String message) {
        super("Sorry, this is an invalid Deadline task format.\n" + "      " + message);
    }
}
