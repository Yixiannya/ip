package chappi.task;

/**
 * Represents the tasks that Chappi chatbot creates.
 * Contains a description and
 * has a boolean representing if the task has been marked as done.
 * True if done. False if not done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the given description.
     * Automatically marks the task as not done.
     * @param description String describing what the task is about.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a task with the given description.
     * Marks the task according to the specified boolean.
     * Used for loading tasks from a save file.
     * @param description String describing what the task is about.
     * @param isDone Boolean representing whether the task has been marked as done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a single character String representing
     * if the task has been marked as done.
     * X if the task is done. White space otherwise.
     * @return String showing if task is done.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description the task contains.
     * @return Description of task as a String.
     */
    public String getDescription() {
        return this.description; // mark done task with X
    }

    /**
     * Sets isDone boolean in task to true.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Sets isDone boolean in task to false.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the isDone boolean contained in the task
     * as an integer.
     * Used for writing the task to a text file save.
     * 1 represents marked done. 0 represents not done.
     * @return Single digit integer.
     */
    public int doneToInt() {
        return isDone ? 1 : 0;
    }

    /**
     * Returns the task in the format for saving to a text file.
     * @return String description of task.
     */
    public String toFileString() {
        return description;
    }

    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        String str = "[%s] %s";
        return String.format(str, statusIcon, this.description);
    }
}
