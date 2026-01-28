import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> arrayList;

    public TaskList() {
        this.arrayList = new ArrayList<>();
    }

    public void addTask(Task task) {
        arrayList.add(task);
    }
}
