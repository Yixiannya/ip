import dukeExceptions.DukeException;
import dukeExceptions.DukeInvalidDeadlineException;
import dukeExceptions.DukeInvalidTodoException;
import dukeExceptions.DukeUnrecognisedCommandException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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

    public static String parseTodo(String input) throws DukeException{
        if (input.equals("todo")) {
            throw new DukeInvalidTodoException("Please enter a task description.");
        } else if (input.startsWith("todo ")) {
            String description = Util.trimPrefix(input, "todo ").strip();
            if (description.isBlank()) {
                throw new DukeInvalidTodoException("Please enter a task description.");
            }
            return description;
        } else {
            throw new DukeUnrecognisedCommandException();
        }
    }

    public static Deadline parseDeadline(String input) throws DukeException {
        if (input.equals("deadline")) {
            throw new DukeInvalidDeadlineException("Please enter the event's description and start date.");
        } else if (input.startsWith("deadline ")) {
            String description = Util.trimPrefix(input, "deadline ").strip();
            if (!description.contains("/by ")) {
                throw new DukeInvalidDeadlineException("Please enter a due date with the '/by' keyword.");
            }
            String[] strings = description.split("/by ");
            String desc = strings[0].strip();
            String endDateStr = strings[1].strip();

            if (desc.isBlank()) {
                throw new DukeInvalidDeadlineException("Please enter a valid description.");
            }
            if (endDateStr.isBlank()) {
                throw new DukeInvalidDeadlineException("Please enter a valid end date.");
            }
            if (!endDateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
                throw new DukeInvalidDeadlineException("Please enter a valid end date in the YYYY-MM-DD format.");
            }
            try {
                LocalDate endDate = LocalDate.parse(endDateStr);
                return new Deadline(desc, endDate);
            } catch (DateTimeParseException e) {
                throw new DukeInvalidDeadlineException(e.toString());
            }
        } else {
            throw new DukeUnrecognisedCommandException();
        }
    }

    public static Task parseSavedTask(String line) {
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
