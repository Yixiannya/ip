package chappi.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with an end date.
 * Otherwise, has the same properties
 * as that of the parent Task class.
 */
public class Deadline extends Task {
    protected LocalDate endDate;

    /**
     * Creates a task with the given description.
     * Also contains end date which is represented
     * as a LocalDate object.
     * Automatically marked as not done.
     * @param description String describing deadline task.
     * @param endDate LocalDate representation of the task's deadline.
     */
    public Deadline(String description, LocalDate endDate) {
        super(description);
        this.endDate = endDate;
    }

    /**
     * Creates a task with the given description.
     * Also contains end date which is represented
     * as a LocalDate object.
     * Marked as done or not done depending on input.
     * @param description String describing deadline task.
     * @param isDone Boolean indicating if task is marked as done.
     * @param endDate LocalDate representation of the task's deadline.
     */
    public Deadline(String description, boolean isDone, LocalDate endDate) {
        super(description, isDone);
        this.endDate = endDate;
    }

    @Override
    public String toFileString() {
        String endDateStr = this.endDate.toString();
        return "D | "
                + super.doneToInt()
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
        return "[D]" + super.toString() + dateStr;
    }
}
