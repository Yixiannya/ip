package chappi.exceptions;

/**
 * Represents the various exceptions that can occur
 * during the execution of the Chappi chatbot.
 * Contains a message to relay to the user.
 * Message will be decorated to politely tell the user
 * that an issue in the chatbot has occurred.
 */
public class ChappiException extends Exception {
    /**
     * Creates an exception specific to the Chappi chatbot
     * that contains the message to tell the user what went wrong.
     * @param message Input message given from method that throws this exception.
     */
    public ChappiException(String message) {
        super("There's been a problem.\n"
                + message);
    }
}
