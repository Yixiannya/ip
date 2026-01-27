public class Deadline extends Task {
    protected String endDate;

    public Deadline(String description, String endDate) {
        super(description);
        this.endDate = endDate;
    }

    @Override
    public String toFileString() {
        return "D | "
                + super.doneToInt()
                + " | "
                + super.description
                + " | "
                + "End: "
                + endDate;
    }

    @Override
    public String toString() {
        String endDateStr = String.format(" (by: %s)", this.endDate);
        return "[D]" + super.toString() + endDateStr;
    }
}
