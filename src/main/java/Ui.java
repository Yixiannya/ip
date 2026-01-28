import dukeExceptions.DukeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static Scanner scanner;
    private static final String SEPERATOR = "     ____________________________________________________________\n";

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readInput() {
        return scanner.nextLine();
    }

    public void showGreeting() {
        System.out.println(SEPERATOR
                + "      Hello! I'm Chappi!\n"
                + "      What can I do for you?\n"
                + SEPERATOR);
    }

    public void showGoodbye() {
        System.out.println(SEPERATOR
                + "      Bye. Hope to see you again soon! ☆*:.｡. o(≧▽≦)o .｡.:*☆\n"
                + SEPERATOR);
    }

    public void showNewTask(Task task, ArrayList<Task> taskList) {
        System.out.println(SEPERATOR
                + String.format("      Sure, added:\n      %s\n", task)
                + String.format("      There's %d tasks in your list now.\n", taskList.size())
                + SEPERATOR);
    }

    public void showMarkedTask(Task task) {
        System.out.println(SEPERATOR
                + String.format("      Alright, marked this task as done:\n        %s\n", task)
                + SEPERATOR);
    }

    public void showUnmarkedTask(Task task) {
        System.out.println(SEPERATOR
                + String.format("      Alright, marked this task as not done yet:\n        %s\n", task)
                + SEPERATOR);
    }

    public void showDeletedTask(Task task) {
        System.out.println(SEPERATOR
                + String.format("      OK, I removed this task as you wanted:\n        %s\n", task)
                + SEPERATOR);
    }

    public void showTaskList(ArrayList<Task> taskList) {
        String result = "      Here's your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            String msg = "      %d.%s\n";
            result = result.concat(String.format(msg, index, taskList.get(i)));
        }
        System.out.println(SEPERATOR
                + result
                + SEPERATOR);
    }

    public void showEmptyTaskList() {
        System.out.println(SEPERATOR
                + "      The list is empty!\n"
                + SEPERATOR);
    }

    public void showInputTooSmall() {
        System.out.println(SEPERATOR
                + "      Please input a number greater than 0."
                + SEPERATOR);
    }

    public void showInputTooBig(int limit) {
        System.out.println(SEPERATOR
                + "      The given number is larger than the size of the list.\n"
                + String.format("      Please give a number smaller than %d.\n", limit)
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
