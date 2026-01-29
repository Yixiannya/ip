package chappi.tasklist;

import java.util.ArrayList;

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
        arrayList.add(task);
    }

    /**
     * Removes the specified task from the array list.
     * @param task Task to be removed.
     */
    public void removeTask(Task task) {
        arrayList.remove(task);
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
