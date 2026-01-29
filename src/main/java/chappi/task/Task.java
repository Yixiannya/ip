package chappi.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description; // mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public int doneToInt() {
        return isDone ? 1 : 0;
    }

    public String toFileString() {
        return description;
    }

    /**
     * Compares keyword with task description and returns
     * a boolean.
     * True if the keyword contains it.
     * False otherwise.
     * @param keyword String representation of a keyword to compare.
     * @return Boolean indicating if the task has that keyword.
     */
    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        String str = "[%s] %s";
        return String.format(str, statusIcon, this.description);
    }
}
