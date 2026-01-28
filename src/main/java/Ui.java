import dukeExceptions.DukeException;

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

    public void showException(DukeException e) {
        System.out.println(SEPERATOR
                        + e
                        + SEPERATOR);
    }
}
