package chappi.tasklist;

import java.time.LocalDate;
import java.util.ArrayList;

import chappi.task.Deadline;
import chappi.task.Event;
import chappi.task.Task;

/**
 * Class that abstracts the array list interactions involving classes.
 * Also provides formatting for how the list should appear as a String.
 */
public class TaskList {
    private ArrayList<Task> arrayList;

    /**
     * Creates a TaskList that has an empty array list.
     * Array list only accepts task objects.
     */
    public TaskList() {
        this.arrayList = new ArrayList<>();
    }

    /**
     * Inserts the specified task to the end of the array list.
     * @param task Task to be inserted.
     */
    public void addTask(Task task) {
        assert task != null;
        arrayList.add(task);
    }

    /**
     * Removes the specified task from the array list.
     * @param task Task to be removed.
     */
    public void removeTask(Task task) {
        assert task != null;
        arrayList.remove(task);
    }

    /**
     * Updates tasks to match the new information provided.
     * @param task The task to be updated.
     * @param info Object array in a specific pattern containing the new information.
     */
    public void updateTask(Task task, Object[] info) {
        String description = (String) info[1];
        LocalDate endDate = (LocalDate) info[2];
        LocalDate startDate = (LocalDate) info[3];
        if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            deadlineTask.updateEndDate(endDate);
        }
        if (task instanceof Event) {
            Event eventTask = (Event) task;
            eventTask.updateEndDate(endDate);
            eventTask.updateStartDate(startDate);
        }
        task.updateDescription(description);
    }

    /**
     * Returns the task at the specified index of the array list.
     * @param i Index of the task in the array list.
     * @return Task that was specified by i.
     */
    public Task getTask(int i) {
        return arrayList.get(i);
    }

    /**
     * Returns the size of the array list in task list.
     * @return Size of array list in integer.
     */
    public int size() {
        return arrayList.size();
    }

    /**
     * Returns true if the array list contains no tasks,
     * false otherwise.
     * @return Boolean value indicating if array has no tasks.
     */
    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

    /**
     * Returns a task list of all tasks that match keyword.
     * Returns an empty task list if no tasks match.
     * @param keyword String representation of a keyword to match with the tasks.
     * @return Task list containing all the tasks that the user wants.
     */
    public TaskList findMatchingTasks(String keyword) {
        TaskList foundTasks = new TaskList();
        for (int i = 0; i < arrayList.size(); i++) {
            Task t = this.getTask(i);
            if (t.containsKeyword(keyword)) {
                foundTasks.addTask(t);
            }
        }
        return foundTasks;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < arrayList.size(); i++) {
            int index = i + 1;
            String msg = "      %d.%s\n";
            result = result.concat(String.format(msg, index, arrayList.get(i)));
        }
        return result;
    }
}
