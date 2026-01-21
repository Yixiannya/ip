package dukeExceptions;

public class DukeInvalidTodoException extends DukeException {
    public DukeInvalidTodoException(String message) {
        super("     Sorry, this is an invalid Todo task format.\n" + "     " + message + "\n");
    }
}
