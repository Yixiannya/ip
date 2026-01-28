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
