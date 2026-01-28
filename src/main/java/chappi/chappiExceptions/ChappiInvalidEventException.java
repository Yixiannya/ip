package chappi.chappiExceptions;

public class ChappiInvalidEventException extends ChappiException {
    public ChappiInvalidEventException(String message) {
        super("Sorry, this is an invalid Event task format.\n" + "      " + message);
    }
}
