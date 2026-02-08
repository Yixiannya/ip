package chappi.task;

/**
 * Represents basic task that
 * has no start date or end date.
 * Contains a boolean
 * that is true if task is marked as done,
 * false if the task is not marked as done.
 */
public class ToDo extends Task {

    /**
     * Creates a task with the given description
     * with no start or end date.
     * Automatically set as not done.
     * @param description String describing todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a task with the given description
     * with no start or end date.
     * Contained boolean is set according to given input.
     * @param description String describing todo task.
     * @param isDone Boolean indicating if task is marked as done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toFileString() {
        return "T | "
                + super.getIsDoneAsInt()
                + " | "
                + super.description;
    }

    @Override
    public String toString() {
        return "[T]"
                + super.toString();
    }
}
