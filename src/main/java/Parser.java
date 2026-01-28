import dukeExceptions.DukeException;
import dukeExceptions.DukeUnrecognisedCommandException;

import java.time.LocalDate;

public class Parser {
    public Parser() {}

    public static int parse(String input) throws DukeException {
        if (input.equals("list")) {
            return 0;
        } else if (input.equals("bye")) {
            return 1;
        } else if (input.startsWith("mark")) {
            return 2;
        } else if (input.startsWith("unmark")) {
            return 3;
        } else if (input.startsWith("delete")) {
            return 4;
        } else if (input.startsWith("todo")) {
            return 5;
        } else if (input.startsWith("deadline")) {
            return 6;
        } else if (input.startsWith("event")) {
            return 7;
        } else {
            return -1;
        }
    }

    public static Task parseTask(String line) {
        String[] splitLine = line.split(" \\| ");
        String taskType = splitLine[0];
        boolean isDone = splitLine[1].equals("1");
        String description = splitLine[2];

        switch (taskType) {
        case "T":
            return new ToDo(description, isDone);
        case "D":
            LocalDate deadlineEndDate = LocalDate.parse(Util.trimPrefix(splitLine[3], "End: "));
            return new Deadline(description, isDone, deadlineEndDate);
        case "E":
            LocalDate eventStartDate = LocalDate.parse(Util.trimPrefix(splitLine[3], "Start: "));
            LocalDate eventEndDate = LocalDate.parse(Util.trimPrefix(splitLine[4], "End: "));
            return new Event(description, isDone, eventStartDate, eventEndDate);
        default:
            throw new IllegalArgumentException("Unknown task type");
        }
    }
}
