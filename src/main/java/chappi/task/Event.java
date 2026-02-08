package chappi.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a start and end date.
 * Otherwise, has the same properties
 * as that of the parent Task class.
 * Displays both dates in MMM DD YYYY format.
 */
public class Event extends Task {
    protected LocalDate startDate;
    protected LocalDate endDate;

    /**
     * Creates a task with the given description.
     * Also contains start and end date
     * which is represented as a LocalDate object.
     * Only accepts dates in the YYYY-MM-DD format.
     * Automatically marked as not done.
     * @param description String describing event task.
     * @param startDate LocalDate representation of the task's starting date.
     * @param endDate LocalDate representation of the task's ending date.
     */
    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        assert startDate != null;
        assert endDate != null;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Creates a task with the given description.
     * Also contains start and end date
     * which is represented as a LocalDate object.
     * Only accepts dates in the YYYY-MM-DD format.
     * Marked as done or not done depending on input.
     * @param description String describing event task.
     * @param isDone Boolean indicating if task is marked as done.
     * @param startDate LocalDate representation of the task's starting date.
     * @param endDate LocalDate representation of the task's ending date.
     */
    public Event(String description, boolean isDone, LocalDate startDate, LocalDate endDate) {
        super(description, isDone);
        assert startDate != null;
        assert endDate != null;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toFileString() {
        String startDateStr = this.startDate.toString();
        String endDateStr = this.endDate.toString();
        return "E | "
                + super.getIsDoneAsInt()
                + " | "
                + super.description
                + " | "
                + "Start: "
                + startDateStr
                + " | "
                + "End: "
                + endDateStr;
    }

    @Override
    public String toString() {
        String startDateStr = this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String endDateStr = this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String dateStr = String.format(" (from: %s to: %s)", startDateStr, endDateStr);
        return "[E]"
                + super.toString()
                + dateStr;
    }
}
