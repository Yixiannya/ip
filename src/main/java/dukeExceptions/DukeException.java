package dukeExceptions;

public class DukeException extends Exception {
    protected String msg;

    public DukeException(String message) {
        msg = "      There's been a problem.\n"
                + "      "
                + message
                + "\n";
    }

    @Override
    public String toString() {
        return msg;
    }
}
