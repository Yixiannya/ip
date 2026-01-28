package dukeExceptions;

public class DukeUnrecognisedCommandException extends DukeException {
    public DukeUnrecognisedCommandException() {
        super("I did not recognise that command.");
    }
}
