import dukeExceptions.DukeException;
import dukeExceptions.DukeUnrecognisedCommandException;

public class Parser {
    public Parser() {}

    public static int parse(String input) throws DukeException {
        if (input.equals("list")) {
            return 0;
        } else if (input.equals("bye")) {
            return 1;
        } else if (input.startsWith("mark")) {
            return 2;
        } else if (input.startsWith("unmark")) {
            return 3;
        } else if (input.startsWith("delete")) {
            return 4;
        } else if (input.startsWith("todo")) {
            return 5;
        } else if (input.startsWith("deadline")) {
            return 6;
        } else if (input.startsWith("event")) {
            return 7;
        } else {
            return -1;
        }
    }
}
