package dukeExceptions;

public class DukeInvalidEventException extends DukeException {
    public DukeInvalidEventException(String message) {
        super("Sorry, this is an invalid Event task format.\n" + "      " + message);
    }
}
