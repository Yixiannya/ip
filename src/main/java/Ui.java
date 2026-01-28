import dukeExceptions.DukeException;

import java.io.IOException;
import java.util.ArrayList;

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

    public void readNewTask(Task task, ArrayList<Task> taskList) {
        System.out.println(SEPERATOR
                + String.format("      Sure, added:\n      %s\n", task)
                + String.format("      There's %d tasks in your list now.\n", taskList.size())
                + SEPERATOR);
    }

    public void readMarkedTask(Task task) {
        System.out.println(SEPERATOR
                + String.format("      Alright, marked this task as done:\n        %s\n", task)
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
