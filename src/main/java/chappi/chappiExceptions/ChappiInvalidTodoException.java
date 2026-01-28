package chappi.chappiExceptions;

public class ChappiInvalidTodoException extends ChappiException {
    public ChappiInvalidTodoException(String message) {
        super("Sorry, this is an invalid Todo task format.\n" + "      " + message);
    }
}
