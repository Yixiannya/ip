package dukeExceptions;

public class DukeException extends Exception {
    public DukeException(String message) {
        super("     There's been a problem.\n" + "     " + message + "\n");
    }
}
