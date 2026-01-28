import dukeExceptions.DukeException;

import java.io.IOException;

public class Ui {
    private static final String SEPERATOR = "     ____________________________________________________________\n";

    public Ui() {

    }

    public void greetUser() {
        System.out.println(SEPERATOR
                + "      Hello! I'm Chappi!\n"
                + "      What can I do for you?\n"
                + SEPERATOR);
    }

    public void sayBye() {
        System.out.println(SEPERATOR
                + "      Bye. Hope to see you again soon! ☆*:.｡. o(≧▽≦)o .｡.:*☆\n"
                + SEPERATOR);
    }

    public void showDukeException(DukeException e) {
        System.out.println(SEPERATOR
                        + e
                        + SEPERATOR);
    }

    public void showIOException(IOException e) {
        System.out.println(SEPERATOR
                + "Sorry, there's been a problem:\n"
                + e
                + SEPERATOR);
    }
}
