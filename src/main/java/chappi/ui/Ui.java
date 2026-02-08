package chappi.ui;

import java.io.IOException;

import chappi.exceptions.ChappiException;
import chappi.task.Task;
import chappi.tasklist.TaskList;

/**
 * Performs all user interfaced related methods.
 * Accepts user commands and displays any messages and information.
 * Displayed messages are decorated appropriately.
 */
public class Ui {
    /**
     * Displays greeting message.
     * @return String representation of Chappi's greeting.
     */
    public String showGreeting() {
        return "Hello! I'm Chappi!\n"
                + "What can I do for you?\n"
                + "☆*:.｡. o(≧▽≦)o .｡.:*☆";
    }

    /**
     * Displays goodbye message.
     * @return String representation of Chappi's goodbye message.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon! ☆*:.｡. o(≧▽≦)o .｡.:*☆\n";
    }

    /**
     * Displays the given task to the user.
     * @param task Task to be shown to user.
     * @param taskList List of tasks to have its size shown to user.
     * @return String representation of Chappi's message and created task.
     */
    public String showNewTask(Task task, TaskList taskList) {
        assert task != null;
        assert taskList != null;
        return String.format("Sure, added:\n      %s\n", task)
                + String.format("There's %d tasks in your list now.\n", taskList.size());
    }

    /**
     * Displays the given task that is marked to the user.
     * @param task Task to be shown that has been marked.
     * @return String representation of Chappi's message and marked task.
     */
    public String showMarkedTask(Task task) {
        assert task != null;
        return String.format("Alright, marked this task as done:\n        %s\n", task);
    }

    /**
     * Displays the given task that is unmarked to the user.
     * @param task Task to be shown that has been unmarked.
     * @return String representation of Chappi's message and unmarked task.
     */
    public String showUnmarkedTask(Task task) {
        assert task != null;
        return String.format("Alright, marked this task as not done yet:\n        %s\n", task);
    }

    /**
     * Displays the given task that was deleted to the user.
     * @param task Task to be shown that had been deleted.
     * @return String representation of Chappi's message and deleted task.
     */
    public String showDeletedTask(Task task) {
        return String.format("OK, I removed this task as you wanted:\n        %s\n", task);
    }

    /**
     * Displays the given task list as a string to the user.
     * @param taskList List of tasks to be shown to the user.
     * @return String representation of Chappi's message and task list.
     */
    public String showTaskList(TaskList taskList) {
        assert taskList != null;
        return "Here's your list:\n"
                + taskList;
    }

    /**
     * Tells user that the task list is empty.
     * @return String representation of Chappi's message.
     */
    public String showEmptyTaskList() {
        return "The list is empty!\n";
    }
    /**
     * Sends a message to the user regarding the given list of found tasks.
     * If there are no found tasks, tell the user that none were found.
     * If there were, show the tasks to the user.
     * @param foundTasks Task list of all tasks that match the user's keyword. Can be empty.
     * @return String representation of Chappi's message and list of tasks if found.
     */
    public String showFoundTasks(TaskList foundTasks) {
        if (foundTasks.isEmpty()) {
            return "Sorry, no tasks with this keyword was found!\n";
        } else {
            return "Here are the tasks that may match!\n"
                    + foundTasks;
        }
    }

    /**
     * Displays encountered exception as a message to the user.
     * Used to tell user that a given command has encountered some problems.
     * @param e Exception to be shown to user.
     * @return String representation of Chappi's message and exception message.
     */
    public String showChappiException(ChappiException e) {
        return e.getMessage();
    }

    /**
     * Displays encountered exception as a message to the user.
     * Used to tell user that a storage command has encountered some problems.
     * @param e Exception to be shown to user.
     * @return String representation of Chappi's message and exception message.
     */
    public String showIoException(IOException e) {
        return "Sorry, there's been a problem:\n"
                + e;
    }
}
