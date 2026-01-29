package chappi.tasklist;

import chappi.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> arrayList;

    public TaskList() {
        this.arrayList = new ArrayList<>();
    }

    public void addTask(Task task) {
        arrayList.add(task);
    }

    public void removeTask(Task task) {
        arrayList.remove(task);
    }

    public Task getTask(int i) {
        return arrayList.get(i);
    }

    public int size() {
        return arrayList.size();
    }

    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

    /**
     * Finds all tasks that have a description that
     * is matching the given String keyword and
     * places them in a new task list to be returned.
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
