package chappi.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with an end date.
 * Otherwise, has the same properties
 * as that of the parent Task class.
 * Displays end date in MMM DD YYYY format.
 */
public class Deadline extends Task {
    protected LocalDate endDate;

    /**
     * Creates a task with the given description.
     * Also contains end date which is represented
     * as a LocalDate object.
     * Only accepts dates in the YYYY-MM-DD format.
     * Automatically marked as not done.
     * @param description String describing deadline task.
     * @param endDate LocalDate representation of the task's deadline.
     */
    public Deadline(String description, LocalDate endDate) {
        super(description);
        assert endDate != null;
        this.endDate = endDate;
    }

    /**
     * Creates a task with the given description.
     * Also contains end date which is represented
     * as a LocalDate object.
     * Only accepts dates in the YYYY-MM-DD format.
     * Marked as done or not done depending on input.
     * @param description String describing deadline task.
     * @param isDone Boolean indicating if task is marked as done.
     * @param endDate LocalDate representation of the task's deadline.
     */
    public Deadline(String description, boolean isDone, LocalDate endDate) {
        super(description, isDone);
        assert endDate != null;
        this.endDate = endDate;
    }

    @Override
    public String toFileString() {
        String endDateStr = this.endDate.toString();
        return "D | "
                + super.getIsDoneAsInt()
                + " | "
                + super.description
                + " | "
                + "End: "
                + endDateStr;
    }

    @Override
    public String toString() {
        String endDateStr = this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String dateStr = String.format(" (by: %s)", endDateStr);
        return "[D]"
                + super.toString()
                + dateStr;
    }
}
