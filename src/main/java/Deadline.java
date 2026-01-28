import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate endDate;

    public Deadline(String description, LocalDate endDate) {
        super(description);
        this.endDate = endDate;
    }

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
