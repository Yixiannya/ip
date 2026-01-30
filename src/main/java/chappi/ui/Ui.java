package chappi.ui;

import chappi.chappiExceptions.ChappiException;
import chappi.task.Task;
import chappi.tasklist.TaskList;

import java.io.IOException;
import java.util.Scanner;

/**
 * Performs all user interfaced related methods.
 * Accepts user commands and displays any messages and information.
 * Displayed messages are decorated appropriately.
 */
public class Ui {
    private static Scanner scanner;
    private static final String SEPERATOR = "     ____________________________________________________________\n";

    /**
     * Creates a new ui object.
     * Will create a scanner to read user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads user given input.
     * @return User input as a String.
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Displays greeting message.
     */
    public void showGreeting() {
        System.out.println(SEPERATOR
                + "      Hello! I'm Chappi!\n"
                + "      What can I do for you?\n"
                + SEPERATOR);
    }

    /**
     * Displays goodbye message.
     */
    public void showGoodbye() {
        System.out.println(SEPERATOR
                + "      Bye. Hope to see you again soon! ☆*:.｡. o(≧▽≦)o .｡.:*☆\n"
                + SEPERATOR);
    }

    /**
     * Displays the given task to the user.
     * @param task Task to be shown to user.
     * @param taskList List of tasks to have its size shown to user.
     */
    public void showNewTask(Task task, TaskList taskList) {
        System.out.println(SEPERATOR
                + String.format("      Sure, added:\n      %s\n", task)
                + String.format("      There's %d tasks in your list now.\n", taskList.size())
                + SEPERATOR);
    }

    /**
     * Displays the given task that is marked to the user.
     * @param task Task to be shown that has been marked.
     */
    public void showMarkedTask(Task task) {
        System.out.println(SEPERATOR
                + String.format("      Alright, marked this task as done:\n        %s\n", task)
                + SEPERATOR);
    }

    /**
     * Displays the given task that is unmarked to the user.
     * @param task Task to be shown that has been unmarked.
     */
    public void showUnmarkedTask(Task task) {
        System.out.println(SEPERATOR
                + String.format("      Alright, marked this task as not done yet:\n        %s\n", task)
                + SEPERATOR);
    }

    /**
     * Displays the given task that was deleted to the user.
     * @param task Task to be shown that had been deleted.
     */
    public void showDeletedTask(Task task) {
        System.out.println(SEPERATOR
                + String.format("      OK, I removed this task as you wanted:\n        %s\n", task)
                + SEPERATOR);
    }

    /**
     * Displays the given task list as a string to the user.
     * @param taskList List of tasks to be shown to the user.
     */
    public void showTaskList(TaskList taskList) {
        System.out.println(SEPERATOR
                + "      Here's your list:\n"
                + taskList
                + SEPERATOR);
    }

    /**
     * Tells user that the task list is empty.
     */
    public void showEmptyTaskList() {
        System.out.println(SEPERATOR
                + "      The list is empty!\n"
                + SEPERATOR);
    }
    
    /**
     * Sends a message to the user regarding the given list of found tasks.
     * If there are no found tasks, tell the user that none were found.
     * If there were, show the tasks to the user.
     * @param foundTasks Task list of all tasks that match the user's keyword. Can be empty.
     */
    public void showFoundTasks(TaskList foundTasks) {
        if (foundTasks.isEmpty()) {
            System.out.println(SEPERATOR
                    + "      Sorry, no tasks with this keyword was found!\n"
                    + SEPERATOR);
        } else {
            System.out.println(SEPERATOR
                    + "      Here are the tasks that may match!\n"
                    + foundTasks
                    + SEPERATOR);
        }
    }

    /**
     * Displays encountered exception as a message to the user.
     * Used to tell user that a given command has encountered some problems.
     * @param e Exception to be shown to user.
     */
    public void showDukeException(ChappiException e) {
        System.out.println(SEPERATOR
                        + e.getMessage()
                        + SEPERATOR);
    }

    /**
     * Displays encountered exception as a message to the user.
     * Used to tell user that a storage command has encountered some problems.
     * @param e Exception to be shown to user.
     */
    public void showIOException(IOException e) {
        System.out.println(SEPERATOR
                + "      Sorry, there's been a problem:\n"
                + e
                + SEPERATOR);
    }
}
