import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate startDate;
    protected LocalDate endDate;

    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(String description, boolean isDone, LocalDate startDate, LocalDate endDate) {
        super(description, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toFileString() {
        String startDateStr = this.startDate.toString();
        String endDateStr = this.endDate.toString();
        return "E | "
                + super.doneToInt()
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
        return "[E]" + super.toString() + dateStr;
    }
}
