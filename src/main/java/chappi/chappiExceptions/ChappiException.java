package chappi.chappiExceptions;

public class ChappiException extends Exception {
    protected String msg;

    public ChappiException(String message) {
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
