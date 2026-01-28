package chappi.chappiExceptions;

public class ChappiUnrecognisedCommandException extends ChappiException {
    public ChappiUnrecognisedCommandException() {
        super("I did not recognise that command.");
    }
}
